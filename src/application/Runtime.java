package application;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.*;
import java.lang.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Runtime} (or <i>Runtime system</i>) initializes the environment
 * before an application runs. <i>Applications</i> here are classes that
 * implement the {@link Runnable} interface.
 * <p>
 * Steps {@link Runtime} performs before running an application are:
 * <ol type="1">
 *  <li>Load application configuration file: {@code application.properties}
 *      from a {@code CLASSPATH} folder in the filesystem or from a
 *      {@code .jar} file of a packaged application.</li>
 *  <li>Load logger configuration file: {@code log4j2.properties} from a
 *      {@code CLASSPATH} folder in the filesystem or from a packaged
 *      {@code .jar} file and initialize the logging system.</li>
 *  <li>Scan for classes that implement the {@link Runnable} interface
 *      defining the {@code run(Properties properties, String[] args)}
 *      method. Classes are prioritized by:
 *      <ol type="a">
 *        <li>the appearance in {@code application.properties} property:
 *          {@code runtime.run.priority} list and (secondary)</li>
 *        <li>by the value of the {@link Run} annotation of a
 *          {@link Runnable} class for priority values {@code < 1000}.
 *          Priority values {@code >= 1000} overule the properties order.
 *        </li>
 *      </ol>
 *      The {@link Runnable} class with the highest priority is
 *      instantiated and the
 *      {@code void run(Properties properties, String[] args);}
 *      method is called with application {@code properties} and
 *      {@code args[]} passed as arguments.
 *  </li>
 * </ol>
 * The {@link Runtime} class itself is instantiated as a single object
 * (<i>"singleton"</i>) following the
 * <a href="https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples#3-lazy-initialization">
 * (lazy) singleton pattern</a>.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */

public class Runtime {

    /**
     * Interface definition a class must implement in order to be
     * found during class scan as candidate for a <i>runnable</i>
     * instance. The {@code Runtime} system selects a class by
     * priority, creates an instance and invokes the
     * {@code run(Properties properties, String[] args)} method.
     * 
     * Only one {@link Runnable} class (with highest {@link Run})
     * priority is instantiated and run.
     */
    public static interface Runnable {
        /**
         * Method invoked by the {@link Runtime} system on a created
         * instance.
         * @param properties properties extracted from the
         *          {@code application.properties} file
         * @param args arguments passed from command line
         */
        void run(Properties properties, String[] args);
    }

    /**
     * Definition of the {@code @Run(priority=value)} annotation
     * to specify a run priority for classes that implement the
     * {@link Runnable} interface.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public static @interface Run {
        /**
         * Return the priority value.
         * @return priority value of the annotation
         */
        public int priority() default 0;
    }


    /*
     * Private reference to the {@code Runtime} singleton object.
     */
    private static Runtime singleton = null;

    /*
     * Lifecycle states of the {@code Runtime} singleton object.
     */
    private enum StartUpStatus {notStarted, starting, started, shuttingDown, shutDown};
    private StartUpStatus status = StartUpStatus.notStarted;

    /*
     * Logger instance used by {@code Runtime} registered under: "Runtime" (no package
     * path), see configuration in: {@code log4j2.properties}.
     */
    private final Logger log = LoggerFactory.getLogger(Runtime.class.getSimpleName());

    /*
     * Properties obtained from the {@code application.properties} file.
     */
    private final Properties properties = new Properties();

    /*
     * Map of names of {@link Runnable} classes found during class scan and
     * their priorities derived from (1.) the position in
     * {@code application.properties}, {@code runtime.run.priority}
     * or (2.) derived from the {@link Run} annotation of the class.
     */
    private final Map<String, Integer> runPriorities = new HashMap<>();


    /**
     * Private constructor according to the (lazy) singleton pattern.
     */
    private Runtime() { }

    /**
     * Public access getter for the {@link Runtime} singleton instance.
     * The instance is created according to the (lazy) singleton pattern.
     * @return Runtime singleton object
     */
    public static Runtime getInstance() {
        if(singleton==null) {
            singleton = new Runtime();
        }
        return singleton;
    }

    /**
     * Public method as main entry point for the Java VM.
     * @param args arguments passed from the command line
     */
    public static void main(String[] args) {
        getInstance().start(args);
    }

    /**
     * Public method to initialize and start the {@link Runtime} instance.
     * The method is re-entrant, which is it can be called multiple times,
     * but performs actions only once for first call.
     * @param args arguments passed from the command line
     * @return chainable self-reference
     */
    public Runtime start(String[] args) {
        if(status != StartUpStatus.notStarted) {
            return this;
        }
        status = StartUpStatus.starting;
        log.info(String.format("------------ starting: %s", this.getClass().getName()));
        // final String[] argsNN = args2 != null? args2 : new String[] { };
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(System.getProperty("path.separator"));
        boolean resourcesFromJar = classpathEntries.length==1;

        String appPropertiesFile = "application.properties";
        // var paths = List.of(fileName, "resources/" + fileName, "config/" + fileName);
        List<String> paths = List.of("", "resources/", "config/").stream()
            .map(p -> p + appPropertiesFile).toList();
        // load properties file from filesystem (priority) or from class loader
        loadProperties(properties, paths, resourcesFromJar? classpathEntries[0] : "");

        String loggerPropertiesFile = "log4j2.properties";
        String from = "from";
        if(resourcesFromJar) {
            boolean loggerInit = false;
            for(String fn : List.of("resources/").stream().map(p -> p + loggerPropertiesFile).toList()) {
                LoggerContext ctx = Configurator.initialize(null, /* "classpath:" + */ fn);
                loggerInit = ctx.getConfiguration().getAppender("console.appender") != null;
                if(loggerInit) {
                    from = "from jar";
                    break;
                }
            }
        }
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        if(ctx.getConfiguration().getAppender("console.appender") != null) {
            log.info(String.format("loaded logger configuration %s: \"%s%s\"", from, "resources/", loggerPropertiesFile));
        }

        // loading 'application.properties' and 'log4j2.properties' complete
        status = StartUpStatus.started;
        log.info(String.format("%s.%s", this.getClass().getSimpleName(), status));
        //
        List<String> resources = findResources(classpathEntries);
        List<Class<Runnable>> runnableClasses = runnableClassesFromResources(resources);
        runnableClasses.sort((c1, c2) -> - Integer.compare(runPriority(c1), runPriority(c2)));
        //
        var runnable = createRunnableInstance(runnableClasses, args);
        if(runnable.isPresent()) {
            log.info(String.format("runnable instance created: \"%s\"", runnable.get().getClass().getName()));
            log.info(String.format("runnable instance started: \"%s%s\"",
                runnable.get().getClass().getName(), ".run(properties, args[])"));
            //
            runnable.get().run(properties, args);
        } else {
            log.warn(String.format("no runnable instance created"));
            log.warn("no runnable instance: must have at least one class on CLASSPATH");
            log.warn("that implements the Runtime.Runnable interface");
        };
        status = StartUpStatus.shuttingDown;
        log.info(String.format("%s.%s", this.getClass().getSimpleName(), status));
        resources.clear();
        runnableClasses.clear();
        runPriorities.clear();
        status = StartUpStatus.shutDown;
        log.info(String.format("%s.%s ------------", this.getClass().getSimpleName(), status));
        return this;
    }

    /**
     * Load {@code application.properties} file from a {@code CLASSPATH}
     * folder in the filesystem or from a {@code .jar} file.
     * @param properties collect properties
     * @param paths {@code CLASSPATH} folders
     * @param jar name of a {@code .jar} file
     * @return number of properties found
     */
    private int loadProperties(Properties properties, List<String> paths, String jar) {
        int before = properties.size();
        // attempt to read from filesystem paths
        for(String fn : paths) {
            try (FileInputStream fis = new FileInputStream(new File(fn))) {
                properties.load(fis);
                int loaded = properties.size() - before;
                log.info(String.format("loaded %d properties from: \"%s\"", loaded, fn));
                return loaded;
            } catch (IOException e) { }
        }
        // if no properties file was found in the filesystem, attempt to read from class loader (CLASSPATH)
        for(String fn : paths) {
            try (InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fn)) {
                if(is==null)
                    throw new IOException("no InputStream from SystemClassLoader");
                //
                properties.load(is);
                int loaded = properties.size() - before;
                jar = jar.length() > 0? String.format("%s: ", jar) : "";
                log.info(String.format("loaded %d properties from class loader resource \"%s%s\"", loaded, jar, fn));
                return loaded;
            } catch(IOException e) { }
        }
        log.warn("no \"application.properties\" file, no properties loaded");
        return -1;
    }

    /**
     * <i>Resources</i> are names of loadable elements found on the
     * {@code CLASSPATH} or in a {@code .jar} file. Method returns
     * names of found <i>resources</i>, e.g. {@code .class} files
     * from a {@code bin} directory from {@code CLASSPATH}).
     * @param classpathEntries entries from {@code CLASSPATH} split
     *          by the {@code "path.separator"}
     * @return list of <i>resource</i> names found at
     *          {@code classpathEntries}
     */
    private List<String> findResources(String[] classpathEntries) {
        List<String> resources = new ArrayList<>();
        if(classpathEntries.length==1) {   // retrieve resources from jar
            String jar = classpathEntries[0];
            try (JarFile jarFile = new JarFile(new File(jar))) {
                Collections.list(jarFile.entries()).stream()
                    .map(e -> e.getName())
                    .filter(r -> r.endsWith(".class") || r.endsWith(".properties"))
                    .map(r -> r.endsWith(".class")? r.replace("\\", "/").replace("/", ".") : r)
                    .forEach(r -> resources.add(r));
            //
            } catch(IOException ex) {
                log.error("jar", ex);
            }
            log.info(String.format("found %d resources in: \"%s\"", resources.size(), jar));
        // 
        } else {
            for(String loc : classpathEntries) {
                if( ! loc.endsWith(".jar")) {
                    findResourcesFromFilesystem(resources, loc, loc);
                }
            }
            log.info(String.format("found %d resources in filesystem", classpathEntries.length));
        }
        return resources;
    }

    /**
     * Scan list of <i>resource</i> names, load and select classes that
     * implement the {@link Runnable} interface.
     * @param resources list of <i>resource</i> names from {@code CLASSPATH}
     *      or name of a {@code jar} file containing <i>resources</i>
     * @return list of classes that implement the {@link Runnable} interface
     *      that could be loaded from <i>resource</i> names
     */
    private List<Class<Runnable>> runnableClassesFromResources(List<String> resources) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        @SuppressWarnings("unchecked")
        List<Class<Runnable>> runnableClasses = resources.stream()
            // only '.class' excluding 'module-info.class' and 'package_info.class'
            .filter(r -> r.endsWith(".class") && ! r.contains("-info") && ! r.contains("_info"))
            // exclude test classes ending with 'Test' or 'Tests'
            .filter(r -> ! r.matches(".*Test[s]?(|\\$.*).class$"))
            // remove trailing '.class'
            .map(r -> r.substring(0, r.length() - ".class".length()))
            // load class from resource name
            .map(res -> {
                Class<?> cls = null;
                try {
                    cls = classLoader.loadClass(res);
                } catch (ClassNotFoundException e) {
                    log.warn(String.format("%s: attempting to load \"%s\"", e.getClass().getSimpleName(), res));
                }
                return Optional.ofNullable(cls);
            })
            .filter(opt -> opt.isPresent())
            .map(opt -> opt.get())
            .filter(cls -> Runnable.class.isAssignableFrom(cls))
            .map(cls -> {
                for(Run anno : cls.getAnnotationsByType(Run.class)) {
                    // put first annotated priority into map
                    runPriorities.put(cls.getName(), anno.priority());
                    break;
                }
                return (Class<Runnable>)cls;
            })
            .collect(Collectors.toList());
        //
        long count = runnableClasses.size();
        if(count > 0L) {
            log.info(String.format("%d runnable classes found", runnableClasses.size()));
            // override annotated priority with position in 'runtime.run.priority' list
            // int highestAnnotatedPriority = runPriorities.values().stream().max(Comparator.naturalOrder()).orElse(0);
            Optional.ofNullable((String)properties.get("runtime.run.priority"))
                .ifPresent(priorityList -> {
                    var spl = priorityList.split("[,;:]");
                    // int priority = highestAnnotatedPriority + spl.length;
                    int priority = 1000-1;
                    // go through 'runtime.run.priority' and assign higher priority
                    for(int i=0; i < spl.length; i++) {
                        // trim and de-quote class name
                        String clsName = spl[i].trim().replaceAll("[\\\"']", "");
                        if(clsName.length() > 0) {
                            int curPrio = Optional.ofNullable(runPriorities.get(clsName)).orElse(0);
                            if((priority-1) > curPrio) {
                                // add or override class
                                runPriorities.put(clsName, priority--);
                            }
                        }
                    };
                });
        } else {
            log.warn("No runnable classes found");
        }
        return runnableClasses;
    }

    /**
     * Return value {@code p} of {@code @Run(priority=p)} annotation
     * of return {@code 0} if annotation was not present.
     * @param clazz class for which run priority value is requested
     * @return value {@code p} of {@code @Run(priority=p)} annotation
     *      or {@code 0} if no annotation was present
     */
    private int runPriority(Class<?> clazz) {
        return Optional.ofNullable(runPriorities.get(clazz.getName())).orElse(0);
    }

    /**
     * Attempt to create an instance of a class that implements the {@link Runnable}
     * interface. Attempts are made in order of appearance in the {@code runnableClasses}
     * list. The first successfull instantiation is returned.
     * @param runnableClasses list of classes that implement the {@link Runnable} classes
     *      ordered by priority
     * @param args command line arguments passed to the
     *      {@code run(Properties properties, String[] args)} method
     *      that is called on the created instance
     * @return first successfull instantiation of {@code runnableClasses}
     *      or empty result, if no class was instantiated
     */
    private Optional<Runnable> createRunnableInstance(List<Class<Runnable>> runnableClasses, String[] args) {
        return runnableClasses.stream()
            .map(cls -> create(cls, () -> {
                    // absorb exceptions in create(): NoSuchMethodException, SecurityException,
                    // InstantiationException, IllegalAccessException, InvocationTargetException
                    var ctor = cls.getConstructor(Integer.class, String[].class);
                    return (Runnable)ctor.newInstance(new Object[] {args});
                })
                .or(() -> create(cls, () -> {
                    var ctor = cls.getConstructor(Properties.class);
                    return (Runnable)ctor.newInstance(properties);
                }))
                .or(() -> create(cls, () -> {
                    var ctor = cls.getConstructor(Properties.class, String[].class);
                    return (Runnable)ctor.newInstance(properties, args);
                }))
                .or(() -> create(cls, () -> {
                    var ctor = cls.getConstructor(String[].class, Properties.class);
                    return (Runnable)ctor.newInstance(args, properties);
                }))
                .or(() -> create(cls, () -> {
                    var ctor = cls.getConstructor();    // attempt default constructor
                    ctor.setAccessible(true);
                    return (Runnable)ctor.newInstance();
                }))                                     // create default instance
                .orElse(null)
            // 
            ).filter(i -> i != null).findFirst();
    }

    /**
     * Recursively traverse filesystem and collect names of {@code .class}
     * files as <i>"resources"</i>.
     * @param collect collect results of <i>resource</i> names
     * @param prefix part removed from {@code path} for valid Java package names
     * @param path starting path of traversal
     */
    private void findResourcesFromFilesystem(List<String> collect, String prefix, String path) {
        try (DirectoryStream<Path> dis = Files.newDirectoryStream(Paths.get(path))) {
            // replace '\' or '/' with '.' as used for package paths
            String pregex = prefix.replace("/", ".").replace("\\", ".") + ".";
            for(Path p : dis) {
                if (Files.isDirectory(p)) {
                    findResourcesFromFilesystem(collect, prefix, p.toString());
                } else {
                    String r = p.toString().replace("\\", "/");
                    if(r.endsWith(".class")) {
                        // replace '/' with '.' and remove prefix-path from resource
                        r = r.replace("/", ".").replaceFirst(Pattern.quote(pregex), "");
                    }
                    collect.add(r);
                }
            }
        } catch(IOException ex) { }
    }

    /**
     * Internal functional {@link Supplier<T>} interface used by the {create()} -
     * method that allows exceptions.
     * @param <T> generic type of result obtained from supplier
     */
    @FunctionalInterface
    private interface SupplierWithExceptions<T> {
        /**
         * Retrieve result from supplier.
         * @return result from supplier
         * @throws Exception thrown by {@code T get()}
         */
        T get() throws Exception;
    }

    /**
     * Wrapper to catch exceptions when supplier is invoked.
     * 
     * @param <T> generic type of result obtained from supplier
     * @param supplier supplier that creates instance and may throw exception
     * @return created instance or empty Optional
     */
    private <T> Optional<T> create(Class<?> cls, SupplierWithExceptions<T> supplier) {
        try {
            return Optional.of((T)supplier.get());
        } catch (Exception e) {
            // System.out.println(String.format("%s: attempting to instantiate \"%s\"",
            //             e.getClass().getSimpleName(), cls.getName()));
        }
        return Optional.empty();
    }

    // // read files from within a .jar file from which classes were loaded
    // var is = ClassLoader.getSystemClassLoader().getResourceAsStream("resources/log4j2.properties");
    // StringBuilder sb = new StringBuilder();
    // try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
    //     try {
    //         String line = br.readLine();
    //         while (line != null) {
    //             sb.append(line);
    //             sb.append(System.lineSeparator());
    //             line = br.readLine();
    //         }
    //     } finally {
    //         br.close();
    //     }
    // } catch (IOException e) { e.printStackTrace(); }
    // System.out.println(sb);
}
package application;

import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;


/**
 * Class with {@code main()} method as entry point for the Java VM.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
@Accessors(priority=0)
public class Application implements Runner {

    /**
     * Method {@code main()} is the entry point for the <i>Java Virtual Machine (VM)</i>.
     * @param args arguments passed from the command line.
     */
    public static void main(String[] args) {
        // 
        // Start {@code runtime} to load {@code application.properties},
        // scan for classes that implement the {@link Runner} interface,
        // create singleton instances of selected classes and invoke their
        // {@code run(RuntimeSE runtime, String[] args)} method.
        RuntimeSE.getInstance().startup(args);
    }

    /**
     * Method invoked by the runtime. Application code starts here.
     * @param runtime reference to the runtime singleton instance ({@link RuntimeSE}).
     * @param args arguments passed from the command line.
     */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        // fetch 'application.name' property from file 'application.properties'
        String applicationName = runtime.properties().getProperty("application.name",
            "unknown (no 'application.name' property)");

        String applicationVersion = runtime.properties().getProperty("application.version",
            "unknown version (no 'application.version' property)");
        // 
        String greeting = String.format("Hello, %s (version %s)", applicationName, applicationVersion);
        System.out.println(greeting);

        // print arguments passed from the command line
        for(String arg : args) {
            String output = String.format(" - arg: %s", arg);
            System.out.println(output);
        }
        // 
        // print arguments passed from the command line as Java Stream
        // java.util.Arrays.stream(args)
        //     .map(arg -> String.format(" - arg: %s", arg))
        //     .forEach(System.out::println);
    }
}

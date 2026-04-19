package application;

/**
 * Application class with the {@code main()} - method as entry point for the
 * <i>Java Virtual Machine (VM)</i>.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Application {

    /**
     * Entry point for the <i>Java Virtual Machine (VM)</i>.
     * @param args arguments passed from the command line.
     */
    public static void main(String[] args) {
        var module = Application.class.getModule().getName();
        var greeting = String.format(module==null? "%s, se1-play" : "%s, %s (modular)", "Hello", module);
        System.out.println(greeting);
        // 
        for(String arg : args) {
            String output = String.format(" - arg: %s", arg);
            System.out.println(output);
        }
        // // 
        // java.util.Arrays.stream(args)
        //     .map(arg -> String.format(" - arg: %s", arg))
        //     .forEach(System.out::println);
    }
}

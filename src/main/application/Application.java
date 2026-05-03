package application;

/**
 * Class with {@code main()} method as entry point for the Java VM.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Application {

    /**
     * Method {@code main()} is the entry point for the <i>Java Virtual Machine (VM)</i>.
     * @param args arguments passed from the command line.
     */
    public static void main(String[] args) {
        var module = Application.class.getModule().getName();
        var greeting = String.format(module==null? "%s, se1-play" : "%s, %s (modular)", "Hello", module);
        System.out.println(greeting);
        // 
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

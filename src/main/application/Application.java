package application;

import numbers.Numbers;


/**
 * Application class with a {@code main()} - function that parses command line
 * arguments.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Application {

    /**
     * {@code main()} - function as entry point for the Java VM.
     * @param args arguments passed from the command line
     */
    public static void main(String[] args) {
        var module = Application.class.getModule().getName();
        var greeting = String.format(module==null? "%s, se1-play" : "%s, %s (modular)", "Hello", module);
        System.out.println(greeting);

        // Arrays.stream(args)
        //     .map(arg -> String.format(" - arg: %s", arg))
        //     .forEach(System.out::println);

        Numbers numbers = Numbers.getInstance();
        Runner runner = Numbers.createRunner(numbers);
        runner.run(args);
    }
}

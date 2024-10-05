/**
 * Module {@code se1.play} demonstrates Java project setup, sourcing the project
 * and project build in the <i>Software Engineering-I</i> course.
 * 
 * The module's main package {@link application} has a simple {@link Runtime}
 * that scans for classes that implement the {@link application.Runtime.Runnable}
 * interface, selects the class with highest {@link application.Runtime.Run}
 * priority, creates an instance of the class and invokes the
 * {@code run(properties, args)} - method to start the application.
 *  
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
module se1.play {
    opens application;
    exports application;                // export application package to enable Javadoc

    requires org.junit.jupiter.api;             // JUnit-5 module required for testing
    requires transitive org.slf4j;              // slf4j/log4j2 logging modules
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;
}
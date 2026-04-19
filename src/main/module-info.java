/**
 * <i>Module</i> {@link se1_play} is used during the <i>Software Engineering 1</i>
 * course.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
module se1_play {

    /*
     * Make package {@code application} accessible to other modules at compile
     * and runtime (use <i>open</i> for compile-time access only).
     */
    exports application;

    /* Open package to JUnit test runner and Javadoc compiler. */
    opens application;

    /*
     * External module required by this module (JUnit module for JUnit testing).
     */
    // requires org.junit.jupiter.api;
}

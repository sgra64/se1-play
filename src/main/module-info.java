/**
 * <i>Module</i> {@link se1_play} is used during the <i>Software Engineering 1</i>
 * course.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
module se1_play {

    /*
     * Make package {@link application} accessible to other modules at compile
     * and runtime (use <i>open</i> for compile-time access only).
     */
    exports application;

    /* Open package to JUnit test runner and the javadoc compiler. */
    opens application;
    opens components;
    opens components.impl;
    opens datamodel;

    /*
     * External modules required by this module.
     */
    requires org.junit.jupiter.api;
    requires transitive runtimeSE;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires org.jspecify;
    requires lombok;
    requires org.apache.logging.log4j;
}

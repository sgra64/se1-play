# Project: *se1-play*

Goal of this challenge is to demonstate a professionally engineered Java project.

Such a project is comprised of several parts:

- `src` - folder with Java source code (`.java` files),

- `tests` - folder with unit test code,

- `resources` - folder with none-Java sources and properties files,

- `bin` (binaries) - folder with compiled (`.class`) and packaged (`.jar`) files,

- `libs` (libraries) - folder with `.jar` libraries for imported packages,

- `.vscode` - folder with configuration and settings for the
    [*VSCode*](https://code.visualstudio.com/)
    [*IDE*](https://en.wikipedia.org/wiki/Integrated_development_environment),

- `.env` - folder with the "*sourcing script*" `env.sh` to set the environment

- "*project files*" created by the sourcing script to configure the project for the IDE,

    - e.g. `.project` and `.classpath`,

- `.git` - local repository of the [*git*](https://en.wikipedia.org/wiki/Git)
    source code management or
    [*version control*](https://en.wikipedia.org/wiki/Version_control)
    system with

    - `.gitignore` and `.gitmodules` files.


&nbsp;

---
Project "*se1-play*" is created in several steps:

1. [Project Structure ("*Scaffold*")](#1-project-structure-scaffold)

1. [Project Location and Initialization](#2-project-location-and-initialization)

1. [Create *.gitignore* - File](#3-create-gitignore---file)

1. [Importing *git*-Submodules](#4-importing-git-submodules)

1. [Exploring: *libs*](#5-exploring-libs)

1. [Add Java Code](#6-add-java-code)

1. [*"Sourcing"* the Project](#7-sourcing-the-project)

1. [Project Build and Run](#8-project-build-and-run)

1. [Add *JUnit* Tests](#9-add-junit-tests)

1. [Launching *VSCode*](#10-launching-vscode)

1. [*Javadoc*](#11-javadoc)

1. [Packaging the Project](#12-packaging-the-project)




&nbsp;

## 1. Project Structure ("*Scaffold*")


The overall project structure (*"scaffold"*) is:

```sh
<workspaces>        # workspace folder within which project directories exist
  |
  +-<se1-play>          # project folder of the "se1-play" project
     +--.project            # VSCode/eclipse file with project information (*)
     +--.classpath          # VSCode/eclipse file with CLASSPATH information (*)
     |
     +-<.env>               # folder with the "sourcing script" (env.sh)
     |
     +-<.git>               # folder with the local git repository
     +--.gitignore          # file to tell git which files to ignore
     +--.gitmodules         # file with information about git modules (*)
     |
     +-<.vscode>            # folder with VSCode project files
     |
     +-<src>                # folder with Java source code
     |
     +-<tests>              # folder with unit test code
     |
     +-<resources>          # folder with none-Java sources and properties files
     |
     +-<bin>                # folder with compiled code (.class files) (*)
     |
     +-<docs>               # folder with compiled javadoc (html content) (*)

# names in '<>' are folders, otherwise files
# content labeled with (*) is generated content
```

<!-- 
The project is created in steps indicated by tags:

<!-- relative paths work for tags and branches -->
<!-- - Step 1 (tag: [*t0*](https://github.com/sgra64/se1-play/tree/t0)) - -- >
- Initial (tag: [*root*](../../tree/root)) -
    initial (empty) root commit.

- Step 1 (tag: [*t1*](../../tree/t1)) -
    with [*.gitignore*](.gitignore).

- Step 2 (tag: [*t2*](../../tree/t2)) -
    with [*.gitmodules*](.gitmodules) after adding git submodules:

    - [*.env*](https://github.com/sgra64/se1-play/tree/env),
      [*.vscode*](https://github.com/sgra64/se1-play/tree/vscode-settings),
      [*libs*](https://github.com/sgra64/se1-play/tree/libs)

- Step 3 (tag: [*t3*](../../tree/t3)) -
    commit with the `src` folder added.

- Step 4 (tag: [*t4*](../../tree/t4)) -
    commit with the `tests` folder added.
-->


&nbsp;

## 2. Project Location and Initialization

In order to create the project, find a place or "*workspace*" where to create it.

An example is to create a "*workspace*" folder in the *HOME* directory:

```sh
cd                          # change directory to the HOME directory
mkdir workspaces            # create the 'workspaces' folder
cd workspaces               # change directory to the 'workspaces' folder
mkdir se1-play              # create folder for the 'se1-play' project
ls -la                      # show content of the 'workspaces' folder
```
```
total 16
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:29 .
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 17:42 ..
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:31 se1-play     <-- project directory
```
```sh
cd se1-play                 # cd into project directory
ls -la                      # show content of the 'se1-play' project directory
```
```
total 4                                             <-- project directory is empty
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:33 .
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:29 ..
```
```sh
pwd                         # show path to the 'se1-play' project directory
```
```
/c/Sven1/svgr2/tmp/svgr/workspaces/se1-play         <-- will show path on your laptop
```

The first step inside the *se1-play* project directory is to create a local
*git* repository with an empty root commit:

```sh
git init                    # initialize local git repository

ls -la
```
```
total 8
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:31 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:19 .git      <-- local git repository created
```

```sh
git commit --allow-empty -m "root commit (empty)"       # create empty root commit
git tag root                                            # tag commit as 'root'

git log --oneline                                       # show empty root commit
```
```
52f8547 (HEAD -> main, tag: root) root commit (empty)   <-- empty root commit
```

The project has now a local *git* repository with an empty root commit.

This is the basis for importing other project parts as *git Submodules*.



&nbsp;

## 3. Create *.gitignore* - File

Creating a *.gitignore* file should always be done. Install [*.gitignore*](.gitignore)
into the project directory and commit:

```sh
# download '.gitignore' file from remote repository
curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore

ls -la                      # show new '.gitignore' file
```
```
total 25
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:31 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:19 .git
-rw-r--r-- 1 svgr2 Kein 1214 Apr  6 19:31 .gitignore    <-- new '.gitignore' file
```

Commit the new *.gitignore* file and tag with *"t1"*:

```sh
git add .gitignore                  # stage '.gitignore' file
git commit -m "add .gitignore"      # commit '.gitignore' file

git tag t1                          # tag commit with 't1'

git log --oneline                   # show commit log/history
```
```
7eacbb9 (HEAD -> main, tag: t1) add .gitignore          <-- new commit tagged with 't1'
52f8547 (tag: root) root commit (empty)                 <-- initial root commit
```



&nbsp;

## 4. Importing *git*-Submodules

[*Git Submodules*](https://git-scm.com/docs/git-submodule)
([*article*](https://www.freecodecamp.org/news/how-to-use-git-submodules/))
is a mechanism to import other *git* repositories as *submodules* into a repository.

We import three parts in the *se1-play* project:

- `.env` - module with the "*sourcing script*" `env.sh` to set the environment.

- `.vscode` - module with configuration and settings for the *VSCode* IDE.

- `libs` - module with `.jar` libraries for imported packages.

The three submodules can be imported from the remote repository:
[*https://github.com/sgra64/se1-play.git*](https://github.com/sgra64/se1-play.git).


<!-- 
The overall pattern for importing *git submodules* is:
```
git submodule add -b <remote-branch> -- <remote-URL> <local-dir-name>
``` -->

```sh
# import '.env' submodule from branch 'env' of the remote repository
git submodule add -b env -- https://github.com/sgra64/se1-play.git .env

# import '.vscode' submodule from branch 'vscode-settings' of the remote repository
git submodule add -b vscode-settings -- https://github.com/sgra64/se1-play.git .vscode

# import '.libs' submodule from branch 'libs' of the remote repository
git submodule add -b libs -- https://github.com/sgra64/se1-play.git libs
```
```
Cloning into 'C:/Sven1/svgr2/tmp/svgr/workspaces/se1-play/.env'...
remote: Enumerating objects: 47, done.
remote: Counting objects: 100% (47/47), done.
remote: Compressing objects: 100% (41/41), done.
remote: Total 47 (delta 8), reused 43 (delta 4), pack-reused 0 (from 0)
Receiving objects: 100% (47/47), 8.71 MiB | 11.65 MiB/s, done.
Resolving deltas: 100% (8/8), done.
...
Cloning into 'C:/Sven1/svgr2/tmp/svgr/workspaces/se1-play/.vscode'...
...
Cloning into 'C:/Sven1/svgr2/tmp/svgr/workspaces/se1-play/libs'...
```

A new folder `.env` has been imported as git submodule and a new file `.gitmodules`
has been created:

```sh
ls -la                      # show new content of the 'se1-play' project directory
```
```
total 21
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:17 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:06 .env          <-- new submodule '.env'
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:17 .git
-rw-r--r-- 1 svgr2 Kein 1214 Apr  6 19:31 .gitignore
-rw-r--r-- 1 svgr2 Kein  295 Apr  6 19:17 .gitmodules   <-- new '.gitmodules' file
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:11 .vscode       <-- new submodule '.vscode'
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:17 libs          <-- new submodule '.libs'
```

Show the *.gitmodules* file:

```sh
cat .gitmodules                     # show the '.gitmodules' file
```
```
[submodule ".env"]
        path = .env
        url = https://github.com/sgra64/se1-play.git
        branch = env
[submodule ".vscode"]
        path = .vscode
        url = https://github.com/sgra64/se1-play.git
        branch = vscode-settings
[submodule "libs"]
        path = libs
        url = https://github.com/sgra64/se1-play.git
        branch = libs
```

Commit the new *.gitmodules* file and tag with *"t2"*:

```sh
git add .gitmodules                 # stage '.gitmodules' file
git commit -m "add .gitmodules"     # commit '.gitmodules' file

git tag t2                          # tag commit with 't2'

git log --oneline                   # show commit log/history
```
```
5834efb (HEAD -> main, tag: t2) add .gitmodules         <-- new commit tagged with 't2'
42ec65a (tag: t1) add .gitignore
52f8547 (tag: root) root commit (empty)
```



&nbsp;

## 5. Exploring: *libs*

<!-- 
```sh
# Fetch branch 'libs' from the repository (use the http-link, if the
# git@-link does not work)
# 
git clone -b libs --single-branch git@github.com:sgra64/se1-play.git libs
```
```
Cloning into 'libs'...
remote: Enumerating objects: 38, done.
remote: Counting objects: 100% (38/38), done.
remote: Compressing objects: 100% (32/32), done.
remote: Total 38 (delta 5), reused 38 (delta 5), pack-reused 0 (from 0)
Receiving objects: 100% (38/38), 6.91 MiB | 7.28 MiB/s, done.
Resolving deltas: 100% (5/5), done.
``` -->

Show the content of folder `libs`:

```sh
find libs                           # list content of 'libs'
```
```
libs
libs/jackson
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/jacoco
libs/jacoco/jacocoagent.jar
libs/jacoco/jacococli.jar
libs/jars-content.txt
libs/junit
libs/junit/apiguardian-api-1.1.2.jar
libs/junit/junit-jupiter-api-5.9.3.jar
libs/junit/junit-platform-commons-1.9.3.jar
libs/junit/opentest4j-1.2.0.jar
libs/junit-platform-console-standalone-1.9.2.jar
libs/logging
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
libs/lombok
libs/lombok/lombok-1.18.36.jar
libs/README.md
```

Java code is distributed as `.jar` files from large global repositories such as the
[*Maven Central Repository*](https://mvnrepository.com/).


[Learn](https://github.com/sgra64/se1-play/tree/libs) about the libraries used
in this project:

- `jackson` - processing JSON data in Java

- `jacoco`- code coverage library for Java

- `junit` - libraries for JUnit tests

- `logging` - *log4j2* logging library for Java

- `junit-platform-console-standalone-1.9.2.jar` - JUnit test runner

In order for libraries to be usable, they must be included in `CLASSPATH`.



&nbsp;

## 6. Add Java Code

Java source code is added under the `src` folder. This project is created as a
[*modular Java Project*](https://jenkov.com/tutorials/java/modules.html).
 The module concept has been introduced in Java in version 9 in order to provide
 a mechanism to build larger Java projects that not only consist of `packages`
 within a project, but can be assembled from multiple Java projects.

 A modular Java project has following files under `src`:

```sh
+-<se1-play>            # project folder of the "se1-play" project
   |
   +-<src>                  # folder with Java source code
      |
      +--module-info.java       # module description
      |
      +-<application>           # folder with 'application' package
         |
         +--Application.java        # class with main() method
         +--package-info.java       # package description (new in Java 9)
 ```

Create the `src` folder in the project directory:

```sh
mkdir -p src/application            # create 'src' with sub-folder 'application'

ls -la
```
```
total 25
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:37 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:06 .env
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:59 .git
-rw-r--r-- 1 svgr2 Kein 1331 Apr  6 19:55 .gitignore
-rw-r--r-- 1 svgr2 Kein  295 Apr  6 19:17 .gitmodules
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:11 .vscode
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:17 libs
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:37 src           <-- new 'src' folder created
```

Insert source files into the structure. Make sure to create source files in the
correct sub-folders.

`module-info.java`:

```java
/**
 * Modules have been introduced in Java 9 (in 2017) to compose software from
 * modularized projects. Prior, only packages within a project could be used.
 *
 * {@code module-info.java} indicates a <i>mudularized</i> Java project. It
 * includes the module the description with the module name: {@link se1.play},
 * external modules required by this module and project packages exported to
 * other modules.
 *
 * Locations of <i>required</i> modules must be provided via {@code MODULEPATH}.
 *
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
module se1.play {

    /*
     * Make package {@code application} accessible to other modules at compile
     * and runtime (use <i>open</i> for compile-time access only).
     */
    exports application;

    /*
     * External module required by this module (JUnit-5 module for JUnit testing).
     */
    requires org.junit.jupiter.api;
}
```

`Application.java` -- class with `main()` method:

```java
package application;

import java.util.Arrays;

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

        Arrays.stream(args)
            .map(arg -> String.format(" - arg: %s", arg))
            .forEach(System.out::println);
    }
}
```

`package-info.java` -- adjust the `Author` information with your name:

```java
/**
 * The {@link application} package includes classes with a {@code main()} -
 * function executable by the Java VM.
 *
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
package application;


/**
 * Class {@code package_info} of the {@link application} package provides global
 * variables used in <i>Javadoc</i>.
 *
 * File {@code package-info.java} has been introduced with <i>Modules</i>
 * in Java 9 (2017) to provide package-level documentation.
 */
class package_info {

    /**
     * Author attribute to appear in javadoc.
     */
    static final String Author = "sgraupner";           // <-- adjust with your name

    /**
     * Version attribute to appear in javadoc.
     */
    static final String Version = "1.0.0-SNAPSHOT";
}
```

Make sure source files have been created in the correct folders:

```sh
find src                        # show files under 'src'
```

Output must exactly match:

```
src
src/application
src/application/Application.java
src/application/package-info.java
src/module-info.java
```



&nbsp;

## 7. *"Sourcing"* the Project

*"Sourcing"* is jargon referring to executing a *shell* script with the
[*source*](https://superuser.com/questions/46139/what-does-source-do)
command in the context of the *shell* process (not as a sub-process).

*"Sourcing a Project"* means to execute a script that typically sets up the environment
of the project. Scripts are ususally called `.env` or `env.sh` and are quite common in
software development.

In this project, the source script
[env.sh](https://github.com/sgra64/se1-play/blob/env/env.sh)
resides in the `.env` submodule and will:

- set environment variables, e.g.
    `PROJECT_PATH`, `CLASSPATH`, `JUNIT_CLASSPATH`, `MODULEPATH`, `JDK_JAVAC_OPTIONS`,
    `JDK_JAVADOC_OPTIONS`, `JUNIT_OPTIONS`, `JACOCO_AGENT` ... ,

- create project configuration files for the *VSCode* IDE, e.g.
    e.g. `.classpath`, `.project`, `.vscode/.classpath`, `.vscode/.modulepath`,
    `.vscode/.sources` and

- create functions used for the *build* process, e.g.
    `show`, `mk`, `clean`, `wipe` ... .

This environment will be set for the *executing shell* and only last for the existence
of the *shell* process (terminal).

When the *shell* process ends, e.g. when the terminal is closed, *environment variables*
and *functions* will be lost (project configuration files will remain).

The *project must be sourced* with every new *shell* processes and terminal.

*"Source"* the project in the project folder with:

```sh
source .env/env.sh          # source the project (execute script 'env.sh' in '.env')
```

Output shows what was created for the environment:

```
 - created environment variables:
    - PROJECT_PATH: "/c/Sven1/svgr2/tmp/svgr/workspaces/se1-play"
    - CLASSPATH
    - JUNIT_CLASSPATH
    - MODULEPATH
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JUNIT_OPTIONS
    - JACOCO_AGENT
 - created folders or files:
    - .classpath
    - .vscode/.classpath
    - .vscode/.modulepath
    - .vscode/.sources
    - .project
```

A new function: `show` shows the commands that are now available
for *building* the project.

```sh
show                    # show commands for the build process
```
```
clean:
  rm -rf bin logs docs coverage

compile:
  javac $(find src -name '*.java') -d bin/classes;

compile-tests:
  javac -cp "$JUNIT_CLASSPATH" $(find tests -name '*.java') -d bin/test-classes;

run:
  java -p "$MODULEPATH" -m se1.play/application.Application

run-tests:
  java -cp "$JUNIT_CLASSPATH" \
    org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
    --scan-class-path

build:
  mk clean compile compile-tests run-tests package javadoc

package:
  tar cv libs/{jackson,logging}*/* | tar -C bin -xvf - ;
  jar -c -v -f bin/application-1.0.0-SNAPSHOT.jar \
        -m resources/META-INF/MANIFEST.MF \
        -C bin/classes . ;
  jar uvf bin/application-1.0.0-SNAPSHOT.jar -C bin resources

coverage:
  java $(eval echo $JACOCO_AGENT) $(eval echo $JUNIT_CLASSPATH) \
    org.junit.platform.console.ConsoleLauncher $(eval echo $JUNIT_OPTIONS) \
    --scan-class-path;
  echo coverage events recorded in: coverage/jacoco.exec

javadoc:
  javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS) \
    $(builtin cd src; find . -type d | sed -e 's!/!.!g' -e 's/^[.]*//')
```

The new environment variables that have been created in the executing *shell*
process can be listed:

```sh
# show value of the 'CLASSPATH' environment variable
echo $CLASSPATH

echo $CLASSPATH | tr '[:;]' '\n'
```

Output is a `:` (`;` on Windows) - separated list with paths pointing to folders with
`.class` files or `.jar` files:

```
bin/classes;bin/resources;libs/jackson/jackson-annotations-2.13.0.jar;libs/jackson/jackson-core-2.13.0.jar;libs/jackson/jackson-databind-2.13.0.jar;libs/junit/junit-jupiter-api-5.9.3.jar;libs/logging/log4j-api-2.23.1.jar;libs/logging/log4j-core-2.23.1.jar;libs/logging/log4j-slf4j2-impl-2.23.1.jar;libs/logging/slf4j-api-2.0.16.jar;libs/lombok/lombok-1.18.36.jar

bin/classes
bin/resources
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/junit/junit-jupiter-api-5.9.3.jar
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
libs/lombok/lombok-1.18.36.jar
```

The following script shows values of all environment variables that have been set
during sourcing:

```sh
# show values of all environment variables that have been set during sourcing
for e in PROJECT_PATH CLASSPATH JUNIT_CLASSPATH MODULEPATH JDK_JAVAC_OPTIONS \
    JDK_JAVADOC_OPTIONS JUNIT_OPTIONS JACOCO_AGENT; do
    # 
    echo "$e:" $(eval echo \$$e)    # output name: value of variable $e
    echo
done
```

New project configuration files also appear in the project directory:

```sh
ls -la . .vscode
```
```
total 30
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:43 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
-rw-r--r-- 1 svgr2 Kein 2306 Apr  6 20:43 .classpath        <-- created
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:06 .env
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:59 .git
-rw-r--r-- 1 svgr2 Kein 1331 Apr  6 19:55 .gitignore
-rw-r--r-- 1 svgr2 Kein  295 Apr  6 19:17 .gitmodules
-rw-r--r-- 1 svgr2 Kein  420 Apr  6 20:43 .project          <-- created
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:43 .vscode
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:17 libs
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:38 src

.vscode/:
total 20
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:43 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 20:43 ..
-rw-r--r-- 1 svgr2 Kein  363 Apr  6 20:43 .classpath        <-- created
-rw-r--r-- 1 svgr2 Kein   32 Apr  6 19:11 .git
-rw-r--r-- 1 svgr2 Kein   73 Apr  6 20:43 .modulepath       <-- created
-rw-r--r-- 1 svgr2 Kein  120 Apr  9 02:31 .sources          <-- created
-rw-r--r-- 1 svgr2 Kein 1299 Apr  6 19:11 launch.json
-rw-r--r-- 1 svgr2 Kein  572 Apr  6 19:11 launch-terminal.sh
-rw-r--r-- 1 svgr2 Kein 2305 Apr  6 19:11 settings.json
```

Function `wipe` unsets created environment variables, which allows rebuilding
them with project changes:

```sh
wipe                    # unset all created environment variables

wipe --all              # in addition, also remove created project configuration files
```

Environment variables are no longer set:

```sh
# show value of the 'CLASSPATH' environment variable
echo $CLASSPATH
```
```
                        <-- empty output
```

One can create a
[*symbolic link*]()
pointing to `.env/env.sh` to shorten the source command:

```sh
ln -s .env/env.sh env.sh        # create symbolic link to source script in project directory

ls -la
```
```
total 30
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 21:19 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
lrwxrwxrwx 1 svgr2 Kein   11 Apr  6 21:19 env.sh -> .env/env.sh     <-- new symbolic link
...
```

Re-source the project:

```sh
source env.sh           # re-source the project using the new symbolic link
```



&nbsp;

## 8. Project Build and Run

*"Project Build"* refers to the process of:

- compiling source code from `src` into `bin` (new folder containing compiled
    classes),

- performing unit tests

- packaging compiled files into a `.jar` file that can be released and distributed.

We use *shell* functions that have been set up during *sourcing* for the *build process*.

```sh
mk compile              # compile '.java' files from 'src' to '.class' files in 'bin'
```

Output shows the command calling the Java compiler `javac` with `.java` files discovered
under the `src` folder and compiled output directed `-d` to the `bin/classes` folder:

```
javac $(find src -name '*.java') -d bin/classes;
```

Compare the `src` folder to the (new) `bin` folder containing the compiled output:

```sh
find src bin            # output 'src' and 'bin' folders
```
```
src
src/application
src/application/Application.java                <-- .java file in package 'application'
src/application/package-info.java
src/module-info.java
bin
bin/classes
bin/classes/application
bin/classes/application/Application.class       <-- compiled .class file under 'application'
bin/classes/application/package_info.class
bin/classes/module-info.class
```

The program has been compiled and can be run:

```sh
mk run 1 23 456         # run the program with arguments: "1", "23" and "456"
```

Output shows the command invoking the Java VM `java` with arguments and after `---` the
output of the program:

```
java -p "$MODULEPATH" -m se1.play/application.Application 1 23 456
---
Hello, se1.play (modular)
 - arg: 1
 - arg: 23
 - arg: 456
```

The command can also be invoked directly:

```sh
java -p "$MODULEPATH" -m se1.play/application.Application 1 23 456
```

When everything works, source files can be committed and tagged with *"t3"*:

```sh
git add src                     # stage 'src' file
git commit -m "add src"         # commit 'src' file

git tag t3                          # tag commit with 't2'

git log --oneline                   # show commit log/history
```
```
ce009f7 (HEAD -> main, tag: t3) add src                 <-- new commit tagged with 't3'
5834efb (tag: t2) add .gitmodules
42ec65a (tag: t1) add .gitignore
52f8547 (tag: root) root commit (empty)
```



&nbsp;

## 9. Add *JUnit* Tests

[*JUnit Tests*](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
test individual code units ("*units-under-test*") in isolation to other units.

We introduce a simple test class in the `tests` folder that merely runs passing tests.
It is used to make sure unit tests run properly.

Create a tests folder in the project directory:

```sh
mkdir -p tests/application          # create 'tests' folder with 'application' package inside
```

Insert test class `Application_0_always_pass_Tests.java` under `tests/application`:

```java
package application;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class to test the JUnit test setup. @Test methods of this class
 * always pass. If JUnit is setup properly, tests should run in the IDE
 * and in the terminal.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Application_0_always_pass_Tests {

    /**
     * Method is executed once before any @Test method.
     * @throws Exception if any exception occurs
     */
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        System.out.println("\nsetUpBeforeClass() runs once before any @Test method");
    }

    /**
     * Method is executed before each @Test method
     * @throws Exception if any exception occurs
     */
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        System.out.println("setUpBeforeEach() runs before each @Test method");
    }

    /**
     * Method is executed after each @Test method
     * @throws Exception if any exception occurs
     */
    @AfterEach
    public void tearDownAfterEach() throws Exception {
        System.out.println("tearDownAfterEach() runs after each @Test method");
    }

    /**
     * First @Test method (always passes).
     */
    @Test
    @Order(001)
    void test_001_always_pass() {
        int expected = 10;
        int actual = 10;
        assertEquals(expected, actual);
    }

    /**
     * Second @Test method (always passes).
     */
    @Test
    @Order(002)
    void test_002_always_pass() {
        int expected = 10;
        int actual = 10;
        assertEquals(expected, actual);
    }

    /**
     * Method is executed once after all @Test methods have finished.
     * @throws Exception if any exception occurs
     */
    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        System.out.println("tearDownAfterClass() runs after all @Test methods have finished");
    }
}
```

Compile tests:

```sh
mk compile-tests        # compile '.java' files from 'tests' to '.class' files in 'bin/test-classes'
```

Output shows the command calling the Java compiler `javac` with `.java` files discovered
under the `tests` folder and compiled output directed `-d` to the `bin/test-classes` folder:

```
javac -cp "$JUNIT_CLASSPATH" $(find tests -name '*.java') -d bin/test-classes;
```

Compare the `src` folder to the (new) `bin` folder containing the compiled output:

```sh
find src bin            # output 'src' and 'bin' folders
```
```
src
src/application
src/application/Application.java
src/application/package-info.java
src/module-info.java
bin
bin/classes
bin/classes/application
bin/classes/application/Application.class
bin/classes/application/package_info.class
bin/classes/module-info.class
bin/test-classes
bin/test-classes/application                    <-- new compiled test .class files
bin/test-classes/application/Application_0_always_pass_Tests.class
```

Tests can be run:

```sh
mk run-tests            # run tests using the test runner: 'libs/junit-platform-console-standalone-1.9.2.jar'
```

Output shows the command invoking the Java VM `java` with arguments:

```
java -cp "$JUNIT_CLASSPATH" \
  org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  --scan-class-path
```

Output of the test runner showing two passing tests from test class `Application_0_always_pass_Tests`:

```
setUpBeforeClass() runs once before any @Test method
setUpBeforeEach() runs before each @Test method
tearDownAfterEach() runs after each @Test method
setUpBeforeEach() runs before each @Test method
tearDownAfterEach() runs after each @Test method
tearDownAfterClass() runs after all @Test methods have finished

Thanks for using JUnit! Support its development at https://junit.org/sponsoring
```
<!-- 
```
╷
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 133 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         2 tests found           ]
[         0 tests skipped         ]
[         2 tests started         ]
[         0 tests aborted         ]
[         2 tests successful      ]
[         0 tests failed          ]
``` -->

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/junit-run-2.png" width="360"/>


When tests are passing, tests can be committed and tagged with *"t4"*:

```sh
git add tests                       # stage test files
git commit -m "add junit tests"     # commit test files

git tag t4                              # tag commit with 't2'

git log --oneline                       # show commit log/history
```
```
db11e83 (HEAD -> main, tag: t4) add junit tests         <-- new commit tagged with 't4'
ce009f7 (tag: t3) add src
5834efb (tag: t2) add .gitmodules
42ec65a (tag: t1) add .gitignore
52f8547 (tag: root) root commit (empty)
```



&nbsp;

## 10. Launching *VSCode*

Re-source the project before launching *VSCode:*

```sh
wipe --all              # unset all created environment variables and remove created project files

source .env/env.sh      # re-source the project
```

*VSCode* discovers the project it is launched in.

```sh
code .                  # launch VSCode in the project directory
```

*VSCode* starts with the project. If *VSCode* is launched for the first time, it may take some
time for *VSCode* to discover the project:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-1.png" width="600"/>

Run the program in *VSCode* - the greeting "*Hello, se1.play*" will show:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-2.png" width="600"/>

Run *JUnit* tests in *VSCode* - all tests should pass:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-3.png" width="600"/>


There is always the assumption that a project builds and runs in both environments, the terminal
and the IDE.

Re-build the project and re-run in the terminal:

```sh
mk clean compile compile-tests          # clean project build

mk run A BC DEF                         # run the program

mk run-tests                            # run tests
```



&nbsp;

## 11. *Javadoc*

*Javadoc* is *Java's* documentation method and toolset relying on
[*Java doc strings*](https://de.wikipedia.org/wiki/Javadoc)
included in Java comments.

The `javadoc` compiler parses `.java` files and "*compiles*" HTML documentation.

```sh
mk javadoc              # generate javadoc in the 'docs' folder (new)
```

Output shows the command calling the `javadoc` compiler with output directed `-d` to the `docs` folder:

```
javadoc -d docs $(eval echo $JDK_JAVADOC_OPTIONS) \
  $(builtin cd src; find . -type d | sed -e 's!/!.!g' -e 's/^[.]*//')
```

Output of the `javadoc` compiler:

```
Loading source files for package application...
Constructing Javadoc information...
Creating destination directory: "docs\"
Building index for all the packages and classes...
Standard Doclet version 21+35-LTS-2513
Building tree for all the packages and classes...
Generating docs\se1.play\application\Application.html...
Generating docs\se1.play\application\package-summary.html...
Generating docs\se1.play\application\package-tree.html...
Generating docs\se1.play\module-summary.html...
Generating docs\overview-tree.html...
Building index for all classes...
Generating docs\allclasses-index.html...
Generating docs\allpackages-index.html...
Generating docs\index-all.html...
Generating docs\search.html...
Generating docs\index.html...
Generating docs\help-doc.html...
```

Output is included in the `docs` folder and can be visualized with a web-browser from
the `index.html` file.

```sh
ls -la docs                 # show generated content of the 'docs' folder
```
```
total 146
drwxr-xr-x 1 svgr2 Kein     0 Apr  6 22:15 .
drwxr-xr-x 1 svgr2 Kein     0 Apr  6 22:15 ..
-rw-r--r-- 1 svgr2 Kein  1038 Apr  6 22:15 index.html       <-- file to open in web-browser
...
```

Open file `docs/index.html` in a web-browser, e.g. with:

```sh
chrome docs/index.html      # open web-browser with Javadoc
```

You may need to open the file in another way if the `chrome` command does not work.

The web-browser shows the Java documentation:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-1.png" width="600"/>

Navigate through: `application` -> `Application`:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-2.png" width="600"/>


Since *Javadoc* is generated (compiled) content, documentation is not checked into the
*git* repository.



&nbsp;

## 12. Packaging the Project

"*Packaging*" means packing compiled code into a `.jar` (Java archive) for release and
distribution.

Package the project:

```sh
mk clean compile package            # re-build and package
```

Packaging invokes the `jar` command:

```
rm -rf bin logs docs coverage

javac $(find src -name '*.java') -d bin/classes;

tar cv libs/{jackson,logging}*/* | tar -C bin -xvf - ;
jar -c -v -f bin/application-1.0.0-SNAPSHOT.jar \
      -C bin/classes . ;
```

Output of the `jar` command when packaging `libs` and compiled files:

```
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
added manifest
added module-info: module-info.class
adding: application/(in = 0) (out= 0)(stored 0%)
adding: application/Application.class(in = 1913) (out= 940)(deflated 50%)
adding: application/package_info.class(in = 333) (out= 260)(deflated 21%)
```

Show the result in the `bin` folder:

```sh
ls -la bin
```

Output shows the packaged `.jar` file `application-1.0.0-SNAPSHOT.jar`:

```
total 8
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 23:44 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 23:44 ..
-rw-r--r-- 1 svgr2 Kein 2230 Apr  6 23:44 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 23:44 classes
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 23:44 libs
```

Run `application-1.0.0-SNAPSHOT.jar`:

```sh
java -cp bin/application-1.0.0-SNAPSHOT.jar application.Application A BC DEF
```

<!-- 
Package the `.jar` file as stand-alone `.jar` file:
-->

The `.jar` can now be released and distributed.



<!-- 
&nbsp;

## Complete Project Content

The complete content of the project is shown below. All generated content
can be removed at any time and re-created by the *Build Process*.

The *Build Process* must always work in a software project.

Only *"source"* content belongs in the code repository, not generated
artefacts. Only *"working"* content must be checked-in, never broken files.

```sh
<se1-play>              # project folder
 |
 +--.env.sh             # file to set/source the project entvironment
 +-<.git>               # local git repository
 |  +--config               # git project settings
 +--.gitignore          # files for git to ignore
 +-- README.md          # this markup file
 |
 +--.classpath          # VSCode, eclipse IDE configuration files,
 +--.project            # generated during "sourcing" the project
 |
 +-<.vscode>                    # VSCode project files
 |  +--settings.json                # project-specific VSCode configuration
 |  +--launch.json                  # launch and debug configurations
 |  +--launch_terminal.sh           # script to launch terminals in VSCode
 |  +--.classpath                   # (generated) file used by VSCoderunner
 |
 +-<src>                        # Java source code
 |  +-<application>                 # package 'application'
 |  |  +--Application.java              # main application file
 |  |  +--Runtime.java                  # basic runtime
 |  |  +--package-info.java             # package documentation
 |  +--module-info.java             # module description (exports, requires)
 |
 +-<resources>                  # none-Java sources, configuration files
 |  +--application.properties       # application configuration file
 |  +--log4j2.properties            # logging configuration file
 |  +-<META-INF>                    # required to package executabe .jar
 |     +--<MANIFEST.MF>             # define main class and CLASSPATH for .jar
 |
 +-<tests>                      # source code for tests
 |  +-<application>                 # package 'application'
 |     +--Application_0_always_pass_Tests.java      # test class
 |
 +-<libs> -> ./branches/libs    # link to 'libs'-branch in folder 'branches'
 |
 +-<branches>                   # home of separately checkout project branches
 |  |
 |  +-<libs>                    # 'libs'-branch containing project libraries
 |     +-<jackson>                  # JSON library for Java
 |     |  +--jackson-annotations-2.13.0.jar
 |     |  +--jackson-core-2.13.0.jar
 |     |  +--jackson-databind-2.13.0.jar
 |     |
 |     +-<jacoco>                   # code coverage library
 |     |  +--jacocoagent.jar
 |     |  +--jacococli.jar
 |     |
 |     +-<logging>                  # logging library
 |     |  +--log4j-api-2.23.1.jar
 |     |  +--log4j-core-2.23.1.jar
 |     |  +--log4j-slf4j2-impl-2.23.1.jar
 |     |  +--slf4j-api-2.0.16.jar
 |     |
 |     +-<junit>                    # library for JUnit5 tests
 |     |  +--apiguardian-api-1.1.2.jar
 |     |  +--junit-jupiter-api-5.9.3.jar
 |     |  +--junit-platform-commons-1.9.3.jar
 |     |  +--opentest4j-1.2.0.jar
 |     |
 |     +--junit-platform-console-standalone-1.9.2.jar   # JUnit5 test runner
 |
 +-<bin>                        # compiled code (.class files, generated)
 |  +--application-1.0.0-SNAPSHOT.jar   # packaged application
 |  |
 |  +-<classes>                 # compiled files from 'src'
 |  |  +-<application>                  # package 'application' from 'src'
 |  |  |  +--Application.class
 |  |  |  +--Runtime.class
 |  |  |  +--Runtime$Run.class
 |  |  |  +--Runtime$Runnable.class
 |  |  |  +--Runtime$StartUpStatus.class
 |  |  |  +--Runtime$SupplierWithExceptions.class
 |  |  |  +--package-info.class
 |  |  |  +--package_info.class
 |  |  +--module-info.class
 |  |
 |  +-<resources>               # files copied from 'resources' folder that
 |  |  +--application.properties    # are accessed at runtime and therefore
 |  |  +--log4j2.properties         # need to be on the CLASSPATH (bin)
 |  |
 |  +-<test-classes>            # compiled tests from 'tests'
 |     +-<application>              # package 'application' in 'tests'
 |     +--Application_0_always_pass_Tests.class     # compiled test class
 |
 +-<docs>                       # javadoc documentation (generated)
 |  +--index.html                   # javadoc landing page
 |  +-- ...                         # more .html and .css files
 |
 +-<coverage>                   # code coverage files (generated)
 |  +--jacoco.exec                  # coverage recording
 |  +--index.html                   # coverage report
 |
 +-<logs>                       # log files (generated during program execution)
    +--runtime.log                  # log file for Runtime.java logger
    +--application.log              # log file for Application.java logger
``` -->

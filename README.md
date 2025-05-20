# Project: *se1-play*

Goal of this assignment is to demonstate a professionally engineered Java project
that is comprised of several parts:

- `src` - folder with Java source code (`.java` files),

- `tests` - folder with test code (*JUnit* tests),

- `resources` - folder with none-Java source and properties files (not yet used),

- `bin` (binaries) - folder with compiled (`.class`) and packaged (`.jar`) files,

- `libs` (libraries) - folder with `.jar` libraries to use imported packages,

- `.vscode` - folder with configuration settings for the
    [*VSCode*](https://code.visualstudio.com/)
    [*IDE*](https://en.wikipedia.org/wiki/Integrated_development_environment),

- `.env` - folder with the "*sourcing script*" `env.sh` to set up the environment
    with environment variables, e.g. *CLASSPATH* and project files to configure
    the IDE for the project, e.g. files:

    - `.project` and `.classpath`,

- `.git` - folder of the local *git* repository.
    [*Git*](https://en.wikipedia.org/wiki/Git) is a popular source code management
    or [*Version Control System (VCS)*](https://en.wikipedia.org/wiki/Version_control).
    Files in the project directory used by *git* are:

    - `.gitignore` and `.gitmodules` files.


&nbsp;

---
Project "*se1-play*" is created in several steps:

1. [Project Structure ("*Scaffold*")](#1-project-structure-scaffold)

1. [Project Location and Initialization](#2-project-location-and-initialization)

1. [Create *.gitignore* - File](#3-create-gitignore---file)

1. [Importing *git*-Submodules](#4-importing-git-submodules)

1. [Exploring: *libs*](#5-exploring-libs)

1. [Adding Java Code](#6-adding-java-code)

1. [*"Sourcing"* the Project](#7-sourcing-the-project)

1. [Project *Build* and *Run*](#8-project-build-and-run)

1. [*JUnit* Tests](#9-junit-tests)

1. [Launching *VSCode*](#10-launching-vscode)

1. [*Javadoc*](#11-javadoc)

1. [Packaging the Project](#12-packaging-the-project)



&nbsp;

## 1. Project Structure ("*Scaffold*")


The project resides in a folder named `se1-play` within a folder called `workspaces`.
Folder `workspaces` may contain more projects.

Several files and sub-folders exist in the project folder `se1-play` as outlines in
the *"scaffold" :*

```sh
<workspaces>        # workspace folder within which project directories exist
  |
  +-<se1-play>          # project folder of the "se1-play" project
     +--.project            # VSCode/eclipse file with project information (*)
     +--.classpath          # VSCode/eclipse file with CLASSPATH information (*)
     |
     +-<.env>               # folder with the sourcing script (env.sh) (SUB)
     |  +--env.sh           # sourcing script to set up the project environment
     |
     +-<.git>               # folder with the local git repository (*)
     +--.gitignore          # file to tell git which files or folders to ignore
     +--.gitmodules         # file with information about git modules (*)
     |
     +-<.vscode>            # folder with VSCode project files (SUB)
     |
     +-<src>                # folder with Java source code (*.java)
     |
     +-<tests>              # folder with unit tests
     |
     +-<libs>               # folder imported libraries (SUB)
     |
     +-<bin>                # folder with compiled code (.class files) (*)
     |
     +-<docs>               # folder with compiled javadoc (html content) (*)

# names in '<>' are folders, without files
# content labeled with (*) is generated content
# folders labeled with (SUB) are imported git modules
```



&nbsp;

## 2. Project Location and Initialization

Find a place on your laptop for the `workspaces` folder and create the project
directory `se1-play` within.

For example, create the `workspaces` folder in your *HOME* directory:

```sh
cd                          # change to the HOME directory (cd: change directory)
mkdir workspaces            # create the 'workspaces' folder (mkdir: make directory)
cd workspaces               # change directory into the 'workspaces' folder

mkdir se1-play              # create the project directory 'se1-play'
ls -la                      # show the content of the 'workspaces' folder
```
```
total 16
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:29 .
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 17:42 ..
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:31 se1-play     <-- new project directory
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
pwd                         # show the path to the 'se1-play' project directory
```
```
/c/Sven1/svgr2/tmp/svgr/workspaces/se1-play         <-- shows path on your laptop
```

The first step within the *se1-play* project directory is to create a local
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

git log --oneline                                       # show new commit
```
```
52f8547 (HEAD -> main, tag: root) root commit (empty)   <-- empty root commit
```

The project has now a local *git* repository with an empty root commit.



&nbsp;

## 3. Create *.gitignore* - File

Creating a *.gitignore* file should always be done when a new project is set up.
Mind the `.` (dot) in front of the name ("*dot file*"). The file must have exactly
the name, no traling extensions such as `.txt`, which some editors might create.

Install file [*.gitignore*](.gitignore) into the project directory and commit the
new file:

```sh
# download '.gitignore' file from remote repository
curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore

ls -la                      # show the new '.gitignore' file
```
```
total 25
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:31 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:19 .git
-rw-r--r-- 1 svgr2 Kein 1214 Apr  6 19:31 .gitignore    <-- new '.gitignore' file
```

Commit the new *.gitignore* file:

```sh
git add .gitignore                  # stage the '.gitignore' file
git commit -m "add .gitignore"      # commit the '.gitignore' file

git log --oneline                   # show commit log/history
```
```
7eacbb9 (HEAD -> main) add .gitignore                   <-- new commit
52f8547 (tag: root) root commit (empty)                 <-- initial root commit
```



&nbsp;

## 4. Importing *git*-Submodules

[*Git Submodules*](https://git-scm.com/docs/git-submodule)
(read the: [*article*](https://www.freecodecamp.org/news/how-to-use-git-submodules/))
is a mechanism to import branches from external ("*remote*") *git* repositories into a
project as *sub-modules*.

We import three *sub-modules* into the *se1-play* project:

- `.env` - module with the "*sourcing script*" `env.sh` to set up the environment.

- `.vscode` - module with configuration settings for the *VSCode* IDE.

- `libs` - module with `.jar` libraries for imported packages.

The *sub-modules* are imported from the remote repository:
[*https://github.com/sgra64/se1-play.git*](https://github.com/sgra64/se1-play.git).


<!-- 
The overall pattern for importing *git submodules* is:
```
git submodule add -b <remote-branch> -- <remote-URL> <local-dir-name>
``` -->

```sh
# import submodule '.env' from branch 'env' of the remote repository
git submodule add -b env -f -- https://github.com/sgra64/se1-play.git .env

# import submodule '.vscode' from branch 'vscode-settings' of the remote repository
git submodule add -b vscode-settings -f -- https://github.com/sgra64/se1-play.git .vscode

# import submodule '.libs' from branch 'libs' of the remote repository
git submodule add -b libs -f -- https://github.com/sgra64/se1-play.git libs
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

New folders `.env`, `.vscode` and `libs` and a new file `.gitmodules` have been created
in the project directory:

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

Show the `.gitmodules` file:

```sh
cat .gitmodules             # show content of the '.gitmodules' file
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

Commit the new *.gitmodules* file:

```sh
git add .gitmodules                 # stage '.gitmodules' file
git commit -m "add .gitmodules"     # commit '.gitmodules' file

git log --oneline                   # show the commit log (history of all commits)
```
```
5834efb (HEAD -> main) add .gitmodules              <-- new commit
42ec65a add .gitignore
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
``` -->

Show the content of the new folder `libs` obtained in the previous step
as *git-submodule:*

```sh
find libs                           # list content of 'libs' folder
```
```
libs
libs/jackson                                        <-- 'jackson' is a JSON package for Java
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/jacoco                                         <-- 'jacoco' is used for code coverage analysis
libs/jacoco/jacocoagent.jar
libs/jacoco/jacococli.jar
libs/jars-content.txt
libs/junit                                          <-- 'junit' is used for JUnit tests
libs/junit/apiguardian-api-1.1.2.jar
libs/junit/junit-jupiter-api-5.9.3.jar
libs/junit/junit-platform-commons-1.9.3.jar
libs/junit/opentest4j-1.2.0.jar
libs/junit-platform-console-standalone-1.9.2.jar    <-- JUnit test runner that executes JUnit tests
libs/logging                                        <-- logging libraries
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
libs/lombok                                         <-- 'lombok' is a code generation library
libs/lombok/lombok-1.18.36.jar
libs/README.md
```

Java code is distributed as `.jar` files from large global repositories such as the
[*Maven Central Repository*](https://mvnrepository.com/).
In order to use libraries, they must be included in the `CLASSPATH`, which is done
in sourcing.

[*Learn*](https://github.com/sgra64/se1-play/tree/libs) about the libraries used
in this project.



&nbsp;

## 6. Adding Java Code

Java source code is added under the `src` folder. This project is created as a
[*Modular Java Project*](https://jenkov.com/tutorials/java/modules.html).
The module concept has been introduced in Java 9 in order to provide
a mechanism to build larger Java projects that not only consist of `packages`
within a project, but can be assembled from multiple *modular* Java projects.

A *modular* Java project has a typical structure under `src` with two specific files:

- `module-info.java` with the *module specification* with imported ("*required*") and
    "*exported*" packages and with module documentation.

- `package-info.java` as a place to provide *package* documentation, which does not
    exist in none-modular Java projects.

The structure ("*scaffold*") of the `src` folder of a *modular* Java project is:

```sh
+-<se1-play>            # project folder of the "se1-play" project
   |
   +-<src>                  # folder with Java source code
      |
      +--module-info.java       # module specification
      |
      +-<application>           # folder with 'application' package
         |
         +--Application.java        # class with main() method
         +--package-info.java       # package documentation (new in Java 9)
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

Insert following source files into the structure. Make sure to create source files
in the correct sub-folders.

If you are unsure to create files in proper locations, you can "*touch*" (create as
empty files) them before adding content:

```sh
touch src/module-info.java                  # create empty file 'module-info.java' in 'src'

touch src/application/Application.java      # create 'Application.java' in 'src/application'

touch src/application/package-info.java     # create 'package-info.java' in 'src/application'

find src                                    # show new files (still empty)
```
```
src
src/application
src/application/Application.java            <-- new file 'Application.java' in 'src/application'
src/application/package-info.java           <-- new file 'package-info.java' in 'src/application'
src/module-info.java                        <-- new file 'module-info.java' in 'src'
```

Files are still empty. Use an editor to open files and copy content, e.g. with *vim:*

```sh
vim src/module-info.java                    # open file for editing in the 'vim' editor
```

You can use any editor, but beware of launching *VSCode* is the project before it is being sourced.
Since the project environment has not yet been set up, *VSCode* will likely not understand the
project structure and create the cache with wrong content, which will cause malfunction later and
the need to manually fix the cache.

Learn the most basic
[*vim commands*](https://www.freecodecamp.org/news/vim-beginners-guide/)
to add content to the files:

1. Open the file, e.g. `vim src/module-info.java`.

1. Hit key `i` to switch to *insert* mode (*INSERT* will appear in the lower left corner).

1. Copy text and paste into the editor. Inserted text will appear in the editor.

1. Hit the `ESC` key to end *insert* mode.
    Then, hit key `:` to switch to *command-line mode* (cursor jumps to the lower left
    corner) and enter commands: `w` (write) and `q` (quit) or short: `:wq`, which will
    write the content to the file and quit *vim*.

Check that content has arrived in the file:

```sh
cat src/module-info.java                    # output content of the file
```

Output will show the content of the file.


&nbsp;

Create file: `src/module-info.java` with content:
<!-- @@ src/module-info.java @BEGIN -->
```java
/**
 * Modules have been introduced in Java 9 (in 2017) to compose software from
 * modularized projects. Prior, only packages within a project could be used.
 *
 * {@code module-info.java} indicates a <i>modularized</i> Java project. It
 * includes the module name: {@link se1.play}, external modules required by
 * this module and project packages opened and exported to other modules.
 * Opening a package makes it accessible to tools such as JUnit test runners.
 * Exporting a package makes it accessible to other modules.
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

    /* Open package to JUnit test runner. */
    opens application;

    /*
     * External module required by this module (JUnit-5 module for JUnit testing).
     */
    requires org.junit.jupiter.api;
}
```
<!-- @@ src/module-info.java @END -->


&nbsp;

Create file: `src/application/Application.java` -- class with `main()` method with content:

<!-- @@ src/application/Application.java @BEGIN -->
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
<!-- @@ src/application/Application.java @END -->


&nbsp;

Create file: `src/application/package-info.java` -- adjust the `Author` information with your name:

<!-- @@ src/application/package-info.java @BEGIN -->
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
<!-- @@ src/application/package-info.java @END -->


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

Check files have content:

```sh
ls -laR src                     # show files under 'src'
```
```
src:
total 12
drwxr-xr-x 1 svgr2 Kein    0 Apr 30 01:37 .
drwxr-xr-x 1 svgr2 Kein    0 Apr 30 01:37 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr 30 01:37 application
-rw-r--r-- 1 svgr2 Kein 1012 Apr 30 00:27 module-info.java      <-- 1012 bytes
                        ^^^^
src/application:
total 12
drwxr-xr-x 1 svgr2 Kein   0 Apr 30 01:37 .
drwxr-xr-x 1 svgr2 Kein   0 Apr 30 01:37 ..
-rw-r--r-- 1 svgr2 Kein 875 Apr 30 01:37 Application.java       <-- 875 bytes
-rw-r--r-- 1 svgr2 Kein 866 Apr 30 00:27 package-info.java      <-- 866 bytes
                        ^^^
```



&nbsp;

## 7. *"Sourcing"* the Project

*"Sourcing"* is jargon referring to executing a *shell* script with the
[*source*](https://superuser.com/questions/46139/what-does-source-do)
command in the context of the same *shell* process (not as sub-process
when the script is invoked without the *source* command).

*"Sourcing a Project"* means to execute a script that typically sets up the environment
of the project. Scripts are ususally called `.env` or `env.sh` and are quite common in
software development. In this project, the source script
[*env.sh*](https://github.com/sgra64/se1-play/blob/env/env.sh) resides in the
[*.env*](https://github.com/sgra64/se1-play/tree/env)
submodule.

The script will:

- set environment variables for the *shell* process:

  - `PROJECT_PATH`, `CLASSPATH`, `JUNIT_CLASSPATH`, `MODULEPATH`, `JDK_JAVAC_OPTIONS`,
    `JDK_JAVADOC_OPTIONS`, `JUNIT_OPTIONS`, `JACOCO_AGENT`,

- create project configuration files for the *VSCode* IDE:

  - `.classpath`, `.project`, `.vscode/.classpath`, `.vscode/.modulepath`,
    `.vscode/.sources` and

- create functions used for the *build* process:

  - `show [cmd1, cmd2...]` -- show build commands,
    - example: `show` all commands or `show compile compile-tests package`
  
  - `mk [cmd1, cmd2...] [args]` -- execute build commands,
      - example: `mk clean compile compile-tests package`
  
  - `wipe [--all|-a|-la]` -- unset project environment variables and functions.

    - `--all | -a` -- including project files

    - `-la` -- project files and 'libs' link or folder


*Environment variables* and *functions* will be set for the *executing shell* and only
last for the existence of the *shell* process (terminal).

When the *shell* process ends, e.g. when the terminal is closed, *environment variables*
and *functions* will be lost (project configuration files will remain) and need to be
*re-sourced* with new terminals and *shell* processes.

It is important to note that IDE like *VSCode* respond to environment variables, e.g.
`CLASSPATH`. Therefore, it is advised to source the project before launching *VSCode*
from the same shell process that has been sourced. This will pass environment variables
to *VSCode*. This is the reason why we have not yet launched *VSCode* in the project.

*Source* the project in the project folder executing the `env.sh` script in `.env`:

```sh
source .env/env.sh          # source the project (execute script 'env.sh' in '.env')
```

Output shows the created environment:

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
 - created functions:
    - wipe [--all|-a|-la]
    - mk [cmd1, cmd2...] [args]
    - show [cmd1, cmd2...]
```

The new function: `show` prints new commands that are now available
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
        -C bin/classes . ;

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

echo $CLASSPATH | tr '[:;]' '\n'        # pretty-print ClASSPATH
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

The following script shows the values of all environment variables that have been set
by sourcing:

```sh
# show values of all environment variables that have been set during sourcing
for e in PROJECT_PATH CLASSPATH JUNIT_CLASSPATH MODULEPATH JDK_JAVAC_OPTIONS \
    JDK_JAVADOC_OPTIONS JUNIT_OPTIONS JACOCO_AGENT
do
    echo "$e:" \"$(eval echo \$$e)\"    # output name: value of variable $e
    echo
done
```

New project configuration files appear in the project directory:

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

To shorten access to the sourcing script, a
[*symbolic link*](https://www.freecodecamp.org/news/linux-ln-how-to-create-a-symbolic-link-in-linux-example-bash-command/)
can be created pointing to `.env/env.sh`:

```sh
ln -s .env/env.sh env.sh        # create symbolic link to the source script

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

## 8. Project *Build* and *Run*

*"Project Build"* is the process of:

- compiling source code from `src` to `bin` (a new folder containing compiled classes),

- performing unit tests, and

- packaging compiled files to a `.jar` file that can be released and distributed.


We use *shell* functions that have been set up during *sourcing* for the *build process*.

```sh
mk compile              # compile '.java' files from 'src' to '.class' files in 'bin'
```

Output shows the command to call the Java compiler `javac` with `.java` files discovered
in the `src` folder and compiled output directed `-d` to the `bin/classes` folder assuming
the presence of the *CLASSPATH* environment variable:

```
javac $(find src -name '*.java') -d bin/classes;
```

Compare the `src` folder to the new `bin` folder containing the compiled output:

```sh
find src bin            # output 'src' and 'bin' folders
```
```
src
src/application
src/application/Application.java                <-- .java file in 'src' in package 'application'
src/application/package-info.java
src/module-info.java
bin
bin/classes
bin/classes/application
bin/classes/application/Application.class       <-- compiled .class file under 'bin/classes/application'
bin/classes/application/package_info.class
bin/classes/module-info.class
```

After the program has been compiled, it can be run:

```sh
mk run 1 23 456         # run the program with arguments: "1", "23" and "456"
```

Output shows the command invoking the Java VM `java` with arguments and after seperator `---`
the output of the program:

```
java -p "$MODULEPATH" -m se1.play/application.Application 1 23 456
---
Hello, se1.play (modular)
 - arg: 1
 - arg: 23
 - arg: 456
```

The command can also be invoked directly as a *shell* command:

```sh
java -p "$MODULEPATH" -m se1.play/application.Application 1 23 456
```

When everything works, source files can be committed:

```sh
git add src                     # stage 'src' file
git commit -m "add src"         # commit 'src' file

git log --oneline               # show commit log/history
```
```
ce009f7 (HEAD -> main) add src                      <-- new commit
5834efb add .gitmodules
42ec65a add .gitignore
52f8547 (tag: root) root commit (empty)
```



&nbsp;

## 9. *JUnit* Tests

[*JUnit Tests*](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
test individual code units ("*units-under-test*") in isolation to other units.

We introduce a simple test class under a new `tests` folder that merely runs passing tests
to demonstrate unit tests run properly in the terminal and in the IDE.

Create the new tests folder in the project directory:

```sh
mkdir -p tests/application          # create 'tests' folder with 'application' package inside
```

Insert the test class `Application_0_always_pass_Tests.java` under `tests/application`:
<!-- @@ tests.application.Application_0_always_pass_Tests.java @BEGIN -->
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
<!-- @@ tests.application.Application_0_always_pass_Tests.java @END -->

Wipe and re-source the project to include the new `tests` folder in the
project environment:

```sh
wipe --all              # remove project environment variable and files

source .env/env.sh      # re-source the project to include the new 'tests' folder
```

Compile tests:

```sh
mk compile-tests        # compile '.java' files from 'tests' to 'bin/test-classes'
```

Output shows the command calling the Java compiler `javac` with `.java` files discovered
under the `tests` folder and compiled output directed `-d` to the `bin/test-classes` folder:

```
javac -cp "$JUNIT_CLASSPATH" $(find tests -name '*.java') -d bin/test-classes;
```

Compare the `src` folder to the new `bin` folder containing the compiled output:

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

Tests should now run:

```sh
mk run-tests            # run tests using the JUnit test runner:
                        # - libs/junit-platform-console-standalone-1.9.2.jar
```

Output shows the command invoking the Java VM `java` with arguments:

```
java -cp "$JUNIT_CLASSPATH" \
  org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  --scan-class-path
```

Output of the test runner shows two passing tests from the test class
`Application_0_always_pass_Tests`:

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


When tests are passing, tests can be committed:

```sh
git add tests                       # stage test files
git commit -m "add junit tests"     # commit test files

git log --oneline                           # show commit log/history
```
```
db11e83 (HEAD -> main) add junit tests      <-- new commit
ce009f7 add src
5834efb add .gitmodules
42ec65a add .gitignore
52f8547 (tag: root) root commit (empty)
```



&nbsp;

## 10. Launching *VSCode*

Wipe and re-source the project before launching *VSCode:*

```sh
wipe --all              # remove project environment variable and files

source .env/env.sh      # re-source the project
```

Launch *VSCode* from the project directory from the "*sourced*" shell process to make
environment variables accessible in *VSCode*, which will discover the new project and
open the editor.

```sh
code .                  # launch VSCode in the project directory
```

If you see a message: *"code - command not found"*, *VSCode* is not on your
[*PATH*](https://www.baeldung.com/linux/path-variable) environment variable.

Locate where the *VSCode* executable is installed on your system and add the
path to *PATH*. Mind the `\` (*backslash*) to "*escape*" path separation due
to spaces:

- the default *VSCode* installation path on *Windows* is:

    `C:/Users/<User>/AppData/Local/Programs/Microsoft\ VS\ Code/bin`

- on *Mac*, *VSCode* is often installed at:

    `/Applications/Visual\ Studio\ Code.app/Contents/Resources/app/bin`

Follow instructions adding the installation path to the `PATH` variable:

- [*configure PATH*](https://code.visualstudio.com/docs/setup/mac#_manually-configure-the-path)
    for *bash* (*cygwin*) or *zsh* (*Mac*).

It is important to **start *VSCode* from a sourced shell process** in the project folder
to pass the project location and environment variables to *VSCode*.
Starting *VSCode* from an icon misses this information causing *VSCode* to potentially
misunderstand the project causing errors later on.

When *VSCode* starts for the first time, it may take some time to discover the project:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-1.png" width="800"/>


Run the Java program in *VSCode* - the greeting "*Hello, se1.play*" will show:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-2.png" width="800"/>


Run also *JUnit* tests in *VSCode* - all tests should pass showing green checks:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-3.png" width="800"/>


There is always the assumption that a project should build and run in both, the terminal
and the IDE.

Re-build the project and re-run also in the terminal:

```sh
mk clean compile compile-tests          # clean project build

mk run A BC DEF                         # run the program

mk run-tests                            # run tests
```



&nbsp;

## 11. *Javadoc*

*Javadoc* is *Java's* documentation method and toolset based on
[*Java doc strings*](https://de.wikipedia.org/wiki/Javadoc)
that are included in Java comments.

The `javadoc` compiler parses `.java` files and "*compiles*" HTML documentation
from *Java doc strings*.

```sh
mk javadoc                  # generate javadoc in the 'docs' folder (new)
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

Output is created in the `docs` folder and can be visualized with a web-browser from
the `index.html` file.

```sh
ls -la docs                 # show generated content of the 'docs' folder
```
```
total 146
drwxr-xr-x 1 svgr2 Kein     0 Apr  6 22:15 .
drwxr-xr-x 1 svgr2 Kein     0 Apr  6 22:15 ..
-rw-r--r-- 1 svgr2 Kein  1038 Apr  6 22:15 index.html       <-- file to open in web-browser
...more files
```

Open file `docs/index.html` in a web-browser, e.g. with:

```sh
chrome docs/index.html      # open web-browser with Javadoc
```

You may need to open the file in another way if the `chrome` command does not work.

The web-browser shows the Java documentation:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-1.png" width="800"/>

Navigate through: `application` -> `Application`:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-2.png" width="800"/>


Since *Javadoc* is generated (compiled) content, it is not committed into the
*git* repository. Source files have already been committed. 



&nbsp;

## 12. Packaging the Project

"*Packaging*" means packing compiled code (`.class` files) as a `.jar` (Java archive)
file for release and distribution.

Re-build and package the project:

```sh
mk clean compile package            # re-build and package
```

Output shows the commands:

```
rm -rf bin logs docs coverage                       <-- clean command
javac $(find src -name '*.java') -d bin/classes;    <-- compile command
```

Packaging invokes the `jar` command to package libraries from `libs` and
compiled Java code from `bin/classes` to a resulting file
`application-1.0.0-SNAPSHOT.jar` placed in the `bin` folder:

```
tar cv libs/{jackson,logging}*/* | tar -C bin -xvf - ;
jar -c -v -f bin/application-1.0.0-SNAPSHOT.jar \
      -C bin/classes . ;
```

Output shows the packaged files:

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

Show the resulting `.jar` file in the `bin` folder:

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

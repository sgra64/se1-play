<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- A1 (SE-2)
-->
# Project: *se1-play*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
Goal of this assignment is to demonstate a professionally engineered Java project.

The project is comprised of several parts:

- `src/main` - folder with Java source code (`.java` files),

- `src/tests` - folder with unit test code (*JUnit* tests),

- `src/resources` - folder with none-Java source and properties files,

- `target` - folder with compiled (`.class`) and packaged (`.jar`) files,

- `libs (*)` (libraries) - folder with `.jar` libraries required by the project
    (imported packages),

- `.vscode (*)` - folder with settings for the
    [*VSCode*](https://code.visualstudio.com/)
    [*IDE*](https://en.wikipedia.org/wiki/Integrated_development_environment),

- `.env (*)` - folder with the "*sourcing script*" `env.sh` to set up the environment
    for the project with environment variables, e.g. *CLASSPATH* and project files
    to properly configure the IDE, e.g. with files:

    - `.project` and `.classpath`,

- `.git` - folder of the local *git* repository.
    [*Git*](https://en.wikipedia.org/wiki/Git) is a popular
    [*Version Control System (VCS)*](https://en.wikipedia.org/wiki/Version_control).

- `.gitmodules` file that describes
    [*Git Submodules*](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
    used in the project. Packages marked with `(*)` are git submodules.

- `.gitignore` file with name patterns for *git* to ignore.

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- @@ build @BEGIN -->
<!-- 
git commit --allow-empty -m "root commit (empty)"
git tag root
cp ../.gitignore .
git add -f .gitignore
git commit -m "add .gitignore"

Start here after cloning 'se1-play' (manual steps):

git submodule add -f https://github.com/sgra64/gitmodule-env.sh.git .env
git commit -m "add git submodule: '.env', add .gitmodules"

git submodule add -f https://github.com/sgra64/gitmodule-vscode-java.git .vscode
git commit -m "add git submodule: '.vscode', update .gitmodules"

git submodule add -f https://github.com/sgra64/gitmodule-libs libs
git commit -m "add git submodule: 'libs', update .gitmodules"
# 
(cd libs; source install.sh && install -f)

# in case of ERROR with 'install', replace [*gitmodule-libs*] with
# [*gitmodule-libs-jars*], which includes .jar files
# 
git submodule deinit -f libs    # de-install the 'libs' submodule
rm -rf .git/modules/libs        # remove submodule from .git/modules
git rm -f libs                  # remove entry in .gitmodules
git reset HEAD~1                # remove prior gitmodule commit
# 
# install the alternative git-module 'gitmodule-libs-jars' under 'libs'
git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars libs
git commit -m "add git submodule: 'libs-jars', update .gitmodules"
# 

tar xvf ../se1-play-src.tar src
git apply module-info.patch && rm module-info.patch

git add src/main        && git commit -m "add src"
git add src/tests       && git commit -m "add unit tests"
git add src/resources   && git commit -m "add META-INF/MANIFEST.MF"
-->
<!-- @@ build @END -->
<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

---

Project "*se1-play*" is created in several steps:

1. [Project Structure ("*Scaffold*")](#1-project-structure-scaffold)

1. [Project Location and Initialization](#2-project-location-and-initialization)

1. [Create a *.gitignore* - File](#3-create-a-gitignore---file)

1. [Import *git*-Submodules](#4-import-git-submodules)

1. [Install: *libs*](#5-install-libs)

1. [*"Sourcing"* the Project](#6-sourcing-the-project)

1. [Add Java Code](#7-add-java-code)

1. [Project *Build* and *Run*](#8-project-build-and-run)

1. [*JUnit* Tests](#9-junit-tests)

1. [*Javadoc*](#10-javadoc)

1. [Package as *.jar*](#11-package-as-jar)

1. [Package as Stand-alone *.jar*](#12-package-as-stand-alone-jar)

1. [Clean Project Build](#13-clean-project-build)

1. [Push to Remote Repository](#14-push-to-remote-repository)

1. [Remarks to *VSCode*](#15-remarks-to-vscode)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Project Structure ("*Scaffold*")

The project resides in a project directory (or project folder) named `se1-play`
within a directory called `workspaces`. The directory `workspaces` may contain
further projects.

The `se1-play` project is comprised of files and sub-directories. The directory
structure is called the project *"scaffold" :*

```sh
<workspaces>    # workspace folder within which project directories exist
 |
 +-<se1-play>       # project folder of the 'se1-play' project
   +--.project          # VSCode/eclipse file with project information (*)
   +--.classpath        # VSCode/eclipse file with CLASSPATH information (*)
   |
   +-<.env>             # folder with the sourcing script (env.sh) (SUB)
   |  +--env.sh         # sourcing script to set up the project environment
   |
   +-<.git>             # folder with the local git repository (*)
   +--.gitignore        # file to tell git which files or folders to ignore
   +--.gitmodules       # file with information about git modules (*)
   |
   +-<.vscode>          # folder with VSCode project files (SUB)
   |   +--settings.json         # project settings
   |   +--launch.json           # launch targets
   |   +--launch-terminal.json  # terminal launch settings
   |
   +-<libs>             # folder libraries required by the project
   |  +--install.sh     # install-script for libraries
   |  |
   |  |  # JUnit test-runner and .jars for unit tests
   |  +--junit-platform-console-standalone-1.9.2.jar
   |  +-<junit>
   |  |  +--apiguardian-api-1.1.2.jar
   |  |  +--junit-jupiter-api-5.12.2.jar
   |  |  +--junit-platform-commons-1.9.2.jar
   |  |  +--opentest4j-1.3.0.jar
   |  |
   |  +-<jackson>       # package to process JSON data in Java
   |  +-<jacoco>        # code-coverage package for Java
   |  +-<junit>         # JUnit package for Java
   |  +-<logging>       # logging package for Java
   |  +-<lombok>        # code-injection package for Java
   |
   +-<src>          # <src> contains the project source code
   |  |
   |  +-<main>          # Java source code (*.java files)
   |  |
   |  +-<tests>         # Unit tests
   |  |
   |  +-<resources>     # none-Java sources, properties files
   |
   +-<target>       # <target> contains artifacts generated during the project build process
   |  |
   |  +-<classes>       # compiled source code (*.class files compiled from src/main)
   |  |
   |  +-<test-classes>  # compiled test code (*.class files compiled from src/tests)
   |  |
   |  +-<resources>     # properties files copied from src/resources
   |  |
   |  +-<docs>          # folder with compiled javadoc (html)
   |  |
   |  +-<coverage>      # folder with code coverage results
   |  |  +--jacoco.exec
   |  +-<coverage-report>   # folder with the code coverage report (html)
   |
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Project Location and Initialization

Find a place on your laptop for the `workspaces` directory and create the project
directory `se1-play` within.

For example, create the `workspaces` directory in your *HOME* directory:

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
total 4                                         <-- project directory is empty
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:33 .
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:29 ..
```

```sh
pwd                         # show the path to the 'se1-play' project directory
```
```
/c/Sven1/svgr2/workspaces/se1-play              <-- shows path on your laptop
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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Create a *.gitignore* - File

Creating a *.gitignore* file should always be the first step when a new project
is set up. Mind the `.` (dot) in front of the name (*.gitignore* is a "*dot file*").
Avoid traling extensions such as `.txt`, which some editors might create.

Install [*.gitignore*](.gitignore) in the project directory and commit the new
file:

```sh
# download '.gitignore'
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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. Import *git*-Submodules

[*Git Submodules*](https://git-scm.com/docs/git-submodule)
(read the: [*article*](https://www.freecodecamp.org/news/how-to-use-git-submodules/))
is a mechanism to import content into a project that is maintained outside the
project.

We import three *sub-modules* into the *se1-play* project:

- `.env` - module with the "*sourcing script*" `env.sh` to set up the environment.

- `.vscode` - module with configuration settings for the *VSCode* IDE.

- `libs` - module to install `.jar` libraries for imported packages.

*Git-modules* are imported from remote repositories. Add *git-modules* and
commit:

<!-- 
The overall pattern for importing *git submodules* is:
```
git submodule add -b <remote-branch> -- <remote-URL> <local-dir-name>
``` -->
<!-- 
```sh -- prior method where modules were branches in the same remote repo
# import submodule '.env' from branch 'env' of the remote repository
git submodule add -b env -f -- https://github.com/sgra64/se1-play.git .env

# import submodule '.vscode' from branch 'vscode-settings' of the remote repository
git submodule add -b vscode-settings -f -- https://github.com/sgra64/se1-play.git .vscode

# import submodule '.libs' from branch 'libs' of the remote repository
git submodule add -b libs -f -- https://github.com/sgra64/se1-play.git libs
``` -->

```sh
# import submodule '.env' from branch 'env' of the remote repository
git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
git commit -m "add git submodule: '.env', add .gitmodules"

# import submodule '.vscode' from branch 'vscode-settings' of the remote repository
git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode
git commit -m "add git submodule: '.vscode', update .gitmodules"

# import submodule '.libs' from branch 'libs' of the remote repository
git submodule add -f -- https://github.com/sgra64/gitmodule-libs libs
git commit -m "add git submodule: 'libs', update .gitmodules"
```
```
Cloning into 'C:/Sven1/svgr2/workspaces/1-SE/se1-play/.env'...
remote: Enumerating objects: 11, done.
remote: Counting objects: 100% (11/11), done.
remote: Compressing objects: 100% (9/9), done.
remote: Total 11 (delta 1), reused 8 (delta 1), pack-reused 0 (from 0)
Receiving objects: 100% (11/11), 21.93 KiB | 3.66 MiB/s, done.
Resolving deltas: 100% (1/1), done.
--
[main c825831] add git submodule '.env', add .gitmodules
 2 files changed, 4 insertions(+)
 create mode 160000 .env
 create mode 100644 .gitmodules
...
Cloning into 'C:/Sven1/svgr2/workspaces/1-SE/se1-play/.vscode'...
...
Cloning into 'C:/Sven1/svgr2/workspaces/1-SE/se1-play/libs'...
```

New folders `.env`, `.vscode` and `libs` and a new file `.gitmodules` have been
created in the project directory:

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
        url = https://github.com/sgra64/gitmodule-env.sh.git
[submodule ".vscode"]
        path = .vscode
        url = https://github.com/sgra64/gitmodule-vscode-java.git
[submodule "libs"]
        path = libs
        url = https://github.com/sgra64/gitmodule-libs
```

<!-- -- do not commit gitmodules
```sh
git add .gitmodules                 # stage the new '.gitmodules' file
git commit -m "add .gitmodules"     # commit '.gitmodules' file

git log --oneline                   # show the commit log (history of all commits)
```
```
5834efb (HEAD -> main) add .gitmodules              <-- new commit
42ec65a add .gitignore
52f8547 (tag: root) root commit (empty)
``` -->
<!-- 
Reset staged content:

```sh
git rm --cached .env .vscode libs

git status                  # show project status
```
```
On branch main
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   .gitmodules
``` -->

The commit-log shows three commits for the three submodules:

```sh
git log --oneline           # show commit log
```
```
56c00e1 (HEAD -> main) add git submodule: 'libs', update .gitmodules
20528c5 add git submodule: '.vscode', update .gitmodules
c825831 add git submodule: '.env', add .gitmodules
77b0b98 add .gitignore
081f946 (tag: root) root commit (empty)
```

Test that *git-modules* have properly been registered:

```sh
git submodule               # list registered sub-modules
```
```
 dfb545f200b3f7b24be7d031a09641d0794b4e1a .env (heads/main)
 396f9234b15db8ba3bd0d397f349b96a849d9d6a .vscode (heads/main)
 9a655561859c8063fc81eecbd6357789f895d63d libs (heads/main)
```

Check for updates that may have been published by the external *sub-modules*
providers. Git will enter each *sub-module* and invoke *git pull* asking for
updates for each *sub-module*:

```sh
git submodule foreach git pull
```
```
Entering '.env'
Already up to date.
Entering '.vscode'
Already up to date.
Entering 'libs'
Already up to date.
```

One can also check for modifications in *sub-modules*:

```sh
git submodule foreach git status
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. Install: *libs*

<!-- 
```sh
# Fetch branch 'libs' from the repository (use the http-link, if the
# git@-link does not work)
# 
git clone -b libs --single-branch git@github.com:sgra64/se1-play.git libs
``` -->
*Sub-module* `libs` does not contain actual *.jar* packages, but script
*install.sh* to install them.

Show the content of the `libs` folder:

```sh
find libs                           # list content of 'libs' folder
```
```
libs
libs/.bom               <-- 'bills-of-material' file with content to install
libs/.git
libs/.gitignore
libs/install.sh         <-- script to source the 'install' command
libs/README.md
```

Make sure you have *curl* or *wget* on your laptop for installation:

```sh
curl --version || wget --version
```

Follow instructions in
[*gitmodule-libs*](https://github.com/sgra64/gitmodule-libs)
to install libraries over the network.

`>>>>>>`

If you encounter *ERRORS* running the *install* command, replace the
*git submodule*
[*gitmodule-libs*](https://github.com/sgra64/gitmodule-libs)
with *git submodule*:
[*gitmodule-libs-jars*](https://github.com/sgra64/gitmodule-libs-jars),
which contains the *.jar* files directly and does not require *install*:

<!-- 
https://stackoverflow.com/questions/1260748/how-do-i-remove-a-submodule
 -->
```sh
# IN CASE OF ERROR running the install command -> deinstall the 'libs' module
cd <project-dir>                    # cd to the project directory (if not there)

git submodule deinit -f libs        # de-install the 'libs' submodule

rm -rf .git/modules/libs            # remove submodule from .git/modules

git rm -f libs                      # remove entry in .gitmodules

# remove prior gitmodule commit: 'add git submodule: 'libs', update .gitmodules'
git reset HEAD~1

# install the alternative git-module 'gitmodule-libs-jars' under 'libs'
git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars libs
git commit -m "add git submodule: 'libs-jars', update .gitmodules"
```

`<<<<<<`

Verify libraries have properly been installed:

```sh
find libs                           # list content of 'libs' folder
```
```
libs
libs/.bom               <-- 'bills-of-material' file with content to install
libs/.git
libs/.gitignore
libs/install.sh         <-- script to source the 'install' command

libs/jackson                                        <-- 'jackson' JSON package for Java
libs/jackson/jackson-annotations-2.13.0.jar
libs/jackson/jackson-core-2.13.0.jar
libs/jackson/jackson-databind-2.13.0.jar
libs/jacoco                                         <-- 'jacoco' for code coverage analysis
libs/jacoco/jacocoagent.jar
libs/jacoco/jacococli.jar
libs/jars-content.txt
libs/junit                                          <-- 'junit' for JUnit tests
libs/junit/apiguardian-api-1.1.2.jar
libs/junit/junit-jupiter-api-5.9.3.jar
libs/junit/junit-platform-commons-1.9.3.jar
libs/junit/opentest4j-1.2.0.jar
libs/junit-platform-console-standalone-1.9.2.jar    <-- JUnit test runner that runs JUnit tests

libs/logging                                        <-- logging package
libs/logging/log4j-api-2.23.1.jar
libs/logging/log4j-core-2.23.1.jar
libs/logging/log4j-slf4j2-impl-2.23.1.jar
libs/logging/slf4j-api-2.0.16.jar
libs/lombok                                         <-- 'lombok' code injection package
libs/lombok/lombok-1.18.36.jar
libs/README.md
```

All world-wide, publicly available Java packages are distributed as `.jar` files
from a large central *"artifact repository"*, called the:
[*Maven Repository*](https://mvnrepository.com/).


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 6. *"Sourcing"* the Project

*"Sourcing"* is jargon referring to executing a *shell* script with the
[*source*](https://superuser.com/questions/46139/what-does-source-do)
command in the context of the same *shell* process (not as sub-process
when the script is invoked without the *source* command).

*"Sourcing a Project"* means to execute a script that sets up the environment
of the project. Scripts are ususally called `.env` or `env.sh`. In this project,
the source script
[*env.sh*](https://github.com/sgra64/se1-play/blob/env/env.sh) resides in
[*.env*](https://github.com/sgra64/se1-play/tree/env)
which was imported as a *git* submodule.

The script will:

- set environment variables for the *shell* process:

  - `CLASSPATH`, `JDK_JAVAC_OPTIONS`, `JDK_JAVADOC_OPTIONS`, `JAR_PACKAGE_LIBS`

- create project configuration files for the *VSCode* IDE:

  - `.classpath`, `.project`, `.coderunner_launch` and

- create functions used for the *build* process:

  - `show cmd [args] cmd [args] ...` -- show build commands,
    - example: `show` all commands or `show compile compile-tests package`
  
  - `mk [--show] cmd [args] cmd [args] ...` -- execute build commands,
    - example: `mk clean compile compile-tests package`
  
  - `wipe [--all|-a]` -- unset project environment variables and functions.

    - `--all | -a` -- including project files

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
----------------------------
 - created environment variables:
    - CLASSPATH
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JAR_PACKAGE_LIBS
 - created files:
    - .classpath
    - .project
    - .coderunner_launch
 - created functions:
    - show cmd [args] cmd [args] ...
    - mk [--show] cmd [args] cmd [args] ...
    - wipe [--all|-a]
----------------------------
```

The new function: `show` prints new commands that are now available
for *building* the project.

```sh
show                    # show commands for the build process
```
```
build:
  mk clean compile compile-tests run-tests package

compile:
  javac $(find  -name '*.java') -d target/classes

compile-tests:
  echo no tests present

run:
  echo no main class to execute

run-tests:
  echo no tests present

package:
  jar -c -v -f "target/application-1.0.0-SNAPSHOT.jar" \
    -C target/classes . $(packaged_content) &&
    [ -f ${P[target-jar]} ] &&
      echo -e "-->\\ncreated: ${P[target-jar]}" ||
      echo -e "-->\\nno compiled classes or manifest, no .jar created"

run-jar:
  java -jar "target/application-1.0.0-SNAPSHOT.jar"

javadoc:
  echo no source files present

clean:
  : nothing to clean
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
target/classes;libs/jackson/jackson-annotations-2.19.0.jar;libs/jackson/jackson-core-2.19.0.jar;libs/jackson/jackson-databind-2.19.0.jar;libs/junit/junit-jupiter-api-5.12.2.jar;libs/logging/log4j-api-2.24.3.jar;libs/logging/log4j-core-2.24.3.jar;libs/logging/log4j-slf4j2-impl-2.24.3.jar;libs/logging/slf4j-api-2.0.17.jar;libs/lombok/lombok-1.18.38.jar

target/classes
libs/jackson/jackson-annotations-2.19.0.jar
libs/jackson/jackson-core-2.19.0.jar
libs/jackson/jackson-databind-2.19.0.jar
libs/junit/junit-jupiter-api-5.12.2.jar
libs/logging/log4j-api-2.24.3.jar
libs/logging/log4j-core-2.24.3.jar
libs/logging/log4j-slf4j2-impl-2.24.3.jar
libs/logging/slf4j-api-2.0.17.jar
libs/lombok/lombok-1.18.38.jar
```
<!-- 
The following script shows the values of all environment variables that have been set
by sourcing:

```sh
# show values of all environment variables that have been set during sourcing
for e in CLASSPATH JDK_JAVAC_OPTIONS JDK_JAVADOC_OPTIONS JAR_PACKAGE_LIBS \
    JACOCO_AGENT_OPTIONS
do
    echo "$e:" \"$(eval echo \$$e)\"    # output name: value of variable $e
    echo
done
``` -->

New project configuration files appear in the project directory:

```sh
ls -la . .vscode
```
```
total 71
drwxr-xr-x 1     0 Oct 14 22:33 ./
drwxr-xr-x 1     0 Oct 14 20:07 ../
-rw-r--r-- 1  1371 Oct 14 22:33 .classpath              <-- created
-rw-r--r-- 1   364 Oct 14 22:33 .coderunner_launch      <-- created
drwxr-xr-x 1     0 Oct 14 22:22 .env/
drwxr-xr-x 1     0 Oct 14 22:22 .git/
-rw-r--r-- 1   263 Oct 14 22:22 .gitmodules
-rw-r--r-- 1   380 Oct 14 22:33 .project                <-- created
drwxr-xr-x 1     0 Oct 14 22:22 .vscode/
drwxr-xr-x 1     0 Oct 14 22:22 libs/
```

Function `wipe` unsets created environment variables, which allows rebuilding
them with project changes:

```sh
wipe                    # unset all created environment variables
```
```
wiping:
 - unset CLASSPATH, JDK_JAVAC_OPTIONS, JDK_JAVADOC_OPTIONS, JAR_PACKAGE_LIBS
```
```sh
wipe --all              # in addition, also remove created project configuration files
```
```
wiping:
 - rm -rf .classpath .project .coderunner_launch
 - unset -f command show mk wipe prepare_manifest packaged_content
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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. Add Java Code

Java source code is maintained under the `src/main` folder. This project is
created as a
[*Modular Java Project*](https://jenkov.com/tutorials/java/modules.html).
The module concept has been introduced in *Java 9* to provide a mechanism to
create larger Java projects that not only consist of `packages`.
*Modules* allow to construct larger software from modules that are maintained
as separate projects.
Project *"se1-play"* hence is one module that could become part of a larger
software development.

A *modular* Java project has new files:

- `module-info.java` with the *module specification* with imported ("*required*") and
    "*exported*" packages and with module documentation.

- `package-info.java` as a place to provide *package* documentation, which does not
    exist in none-modular Java projects.

The structure ("*scaffold*") of the `src` folder of a *modular* Java project is:

```sh
+-<se1-play>    # project folder of the 'se1-play' project
   |
   +-<src>          # <src> contains the project source code
   |  |
   |  +-<main>          # Java source code (*.java files)
   |    |
   |    +--module-info.java         # module specification
   |    |
   |    +-<application>             # folder with the 'application' package
   |       |
   |       +--Application.java          # class with main() method
   |       +--package-info.java         # package documentation (new in Java 9)
   |
 ```

Create the `src` folder in the project directory:

```sh
mkdir -p src/main/application           # create 'src' folder with package 'application'

ls -la
```
```
total 71
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:42 .
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 20:07 ..
-rw-r--r-- 1 svgr2 Kein  1371 Oct 14 22:41 .classpath
-rw-r--r-- 1 svgr2 Kein   364 Oct 14 22:41 .coderunner_launch
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:22 .env
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:22 .git
-rw-r--r-- 1 svgr2 Kein   263 Oct 14 22:22 .gitmodules
-rw-r--r-- 1 svgr2 Kein   380 Oct 14 22:41 .project
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:22 .vscode
lrwxrwxrwx 1 svgr2 Kein    11 Oct 14 22:41 env.sh -> .env/env.sh
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:22 libs
drwxr-xr-x 1 svgr2 Kein     0 Oct 14 22:42 src           <-- new 'src' folder created
```

Insert following source files into the structure. Make sure to create source files
in the correct sub-folders.

If you are unsure to create files in proper locations, you can "*touch*" (create as
empty files) them before adding content:

```sh
touch src/main/module-info.java                 # create empty file 'module-info.java' in 'src/main'

touch src/main/application/Application.java     # create 'Application.java' in 'src/main/application'

touch src/main/application/package-info.java    # create 'package-info.java' in 'src/main/application'

find src                                    # show new files (still empty)
```
```
src
src/main/application
src/main/application/Application.java           <-- new file 'Application.java' in 'src/application'
src/main/application/package-info.java          <-- new file 'package-info.java' in 'src/application'
src/main/module-info.java                       <-- new file 'module-info.java' in 'src'
```

<!-- 
```sh
vim src/main/module-info.java                   # open file for editing in the 'vim' editor
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
Output will show the content of the file. -->

Next, Java source code should be created in files. You should use an IDE for that.

IDE (particularly *VSCode*) are sensitive where they are started.
Lanuch the IDE from the project directory, for *VSCode :*

```sh
# make sure you are in the project directory, command 'ls -la'
# should list the '.git' folder
ls -la

# launch VSCode only if you saw the '.git' folder with 'ls -la'
# if you don't see '.git', change to the project directory
# ONLY then, start VSCode in the current (.) directory
# MIND the dot (.):
# 
code .              # start VSCode in the (current: .) project directory
```

*VSCode* should start and open the project.

Other IDE are less sensitive to where they are started.


&nbsp;

Fill content into file `src/main/module-info.java` with your IDE:

<!-- @@ src/main/module-info.java @BEGIN -->
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
    //requires org.junit.jupiter.api;
}
```
<!-- @@ src/main/module-info.java @END -->


&nbsp;

Fill in content for file: `src/application/package-info.java` -- adjust the `Author`
information with your name:

<!-- @@ src/main/application/package-info.java @BEGIN -->
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
<!-- @@ src/main/application/package-info.java @END -->


&nbsp;

And fill in content for file: `src/application/Application.java`:

<!-- @@ src/main/application/Application.java @BEGIN -->
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
<!-- @@ src/main/application/Application.java @END -->


The project must be re-sourced to reflect the new content in the project
environment:

```sh
wipe --all                  # wipe project environment

source env.sh
```

With the presence of file *module-info.java*, the project has been recognized
as a modular project, shown by a new environment variable: *MODULEPATH*:

```
----------------------------
 - created environment variables:
    - CLASSPATH
    - MODULEPATH                <-- new environment variable
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JAR_PACKAGE_LIBS
 - created files:
    - .classpath
    - .project
    - .coderunner_launch
 - created functions:
    - show cmd [args] cmd [args] ...
    - mk [--show] cmd [args] cmd [args] ...
    - wipe [--all|-a]
----------------------------
```

```sh
echo $CLASSPATH             # shows as before

echo $MODULEPATH            # new environment variable
```
```
target/classes;libs/jackson;libs/junit;libs/logging;libs/lombok
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

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
```
compile:
  javac $(find src/main -name '*.java') -d target/classes
```

Output shows the command to call the Java compiler `javac`. Java source files are
discovered with the *find* command from path `src/main`. Compiled output is directed
with `-d` to `target/classes`:

Compare the `src` folder to the new `target` folder containing the compiled output:

```sh
find src target         # output 'src' and 'target' folders
```
```
src
src/main
src/main/application
src/main/application/Application.java           <-- .java file in 'src' in package 'application'
src/main/application/package-info.java
src/main/module-info.java
target
target/classes
target/classes/application
target/classes/application/Application.class    <-- .class file in 'target' in package 'application'
target/classes/application/package-info.class
target/classes/application/package_info.class
target/classes/module-info.class
```

Try various methods to run the program:

```sh
java --class-path=target/classes application.Application 1 23 456

java application.Application 1 23 456

java -p "$MODULEPATH" -m se1.play/application.Application 1 23 456
```

The program outputs the parameters passed as command line arguments
(see source code in *Application.java*):

```
Hello, se1.play (modular)
 - arg: 1
 - arg: 23
 - arg: 456
```

The program can also run with the `mk` command:

```sh
mk run 1 23 456
```
```
run: [1 23 456]
  java -p $MODULEPATH -m "se1.play/application.Application" 1 23 456
---
Hello, se1.play (modular)
 - arg: 1
 - arg: 23
 - arg: 456
```

When this works, commit source files:

```sh
git add src/main                # stage 'src' file
git commit -m "add src/main"    # commit 'src' file

git log --oneline               # show commit log
```
```
c71ab76 (HEAD -> main) add src                      <-- new commit
f3c1706 add git submodule: 'libs', update .gitmodules
f1cdf73 add git submodule: '.vscode', update .gitmodules
e340ec6 add git submodule: '.env', add .gitmodules
2f68f2b (tag: root) root commit (empty)
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 9. *JUnit* Tests

[*JUnit Tests*](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
test individual code units ("*units-under-test*") in isolation to other units.

A simple test class under a `src/tests` is used to demonstrate unit tests.
Junit tests should run properly in the terminal and in the IDE.

Create the new tests folder and JUnit test class in the project directory:

```sh
mkdir -p src/tests/application          # create 'tests' folder for package 'application'

touch src/tests/application/Application_0_always_pass_Tests.java

find src                                # show the new JUnit test class under path 'src/tests'
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java
src/tests
src/tests/application                   <-- JUnit test class under path 'src/tests'
src/tests/application/Application_0_always_pass_Tests.java
```


&nbsp;

Fill in content for file: `Application_0_always_pass_Tests.java`:

<!-- @@ src/tests/application/Application_0_always_pass_Tests.java @BEGIN -->
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
<!-- @@ src/tests/application/Application_0_always_pass_Tests.java @END -->

Remove the comment in file *module-info.java* to require *org.junit.jupiter.api :*

```java
module se1.play {

    /*
     * External module required by this module (JUnit-5 module for JUnit testing).
     */
    requires org.junit.jupiter.api;     // <-- remove comment
}
```

Wipe and re-source the project:

```sh
wipe --all              # remove project environment variable and files

source env.sh           # re-source the project to include the new 'tests' folder
```

With the presence of unit tests, new environment variables are added to the
project environment:

```
----------------------------
 - created environment variables:
    - CLASSPATH
    - MODULEPATH
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JAR_PACKAGE_LIBS
    - JUNIT_CLASSPATH       <-- new environment variable
    - JUNIT_OPTIONS         <-- new environment variable
    - JACOCO_AGENT_OPTIONS  <-- new environment variable
 - created files:
    - .classpath
    - .project
    - .coderunner_launch
 - created functions:
    - show cmd [args] cmd [args] ...
    - mk [--show] cmd [args] cmd [args] ...
    - wipe [--all|-a]
----------------------------
```

Compile tests:

```sh
mk compile-tests        # compile '.java' files from 'tests' to 'bin/test-classes'
```

Output shows the command calling the Java compiler `javac` with `.java` files discovered
under the `tests` folder and compiled output directed `-d` to the `target/test-classes` folder:

```
compile-tests:
  javac -cp $JUNIT_CLASSPATH $(find src/tests -name '*.java') -d target/test-classes
```

Compare the `src` folder to the new `target` folder containing the compiled output:

```sh
find src target         # output 'target' and 'bin' folders
```
```
src/
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java
src/tests
src/tests/application                   <-- new test class (.java file)
src/tests/application/Application_0_always_pass_Tests.java
target/
target/classes
target/classes/application
target/classes/application/Application.class
target/classes/application/package-info.class
target/classes/application/package_info.class
target/classes/module-info.class
target/test-classes
target/test-classes/application         <-- new compiled test .class files
target/test-classes/application/Application_0_always_pass_Tests.class
```

Tests should now run:

```sh
mk run-tests            # run tests using the JUnit test runner:
                        # - libs/junit-platform-console-standalone-1.9.2.jar
```

Output shows the command invoking the Java VM `java` with arguments:

```
run-tests:
  java -cp $JUNIT_CLASSPATH \
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
git add src/tests                   # stage test files
git commit -m "add unit tests"      # commit test files

git log --oneline                           # show commit log/history
```
```
3f49067 (HEAD -> main) add unit tests
c71ab76 add src
f3c1706 add git submodule: 'libs', update .gitmodules
f1cdf73 add git submodule: '.vscode', update .gitmodules
e340ec6 add git submodule: '.env', add .gitmodules
2f68f2b (tag: root) root commit (empty)
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 10. *Javadoc*

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
javadoc:
  javadoc $(eval echo $JDK_JAVADOC_OPTIONS) \
    $(builtin cd src/main &&
       find -type d | sed -e 's!^[\./]*!!' -e 's!/!.!g') &&
    echo -e "-->\\ncreated javadoc in: ${P[docs]}"
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

Output is compiled to the `target/docs` folder and can be visualized with
a web-browser from the `index.html` file.

```sh
ls -la target/docs              # show generated content of the 'docs' folder
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
chrome target/docs/index.html   # open web-browser with Javadoc
```

You may need to open the file in another way if the `chrome` command does not work.

The web-browser shows the Java documentation:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-1.png" width="800"/>

Navigate through: `application` -> `Application`:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-2.png" width="800"/>

Make sure, your name appears as *Author* - adjust in: `src/application/package-info.java`
and recompuile *Javadoc*.

Since *Javadoc* is generated (compiled) content, it is not committed into the
*git* repository. Source files have already been committed. 


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 11. Package as *.jar*

"*Packaging*" means packing compiled code (`.class` files) as a `.jar` (Java archive)
file for release and distribution.

Packaging required a so-called MANIFEST.MF file, which will be created in under
path `src/resources/META-INF`:

```sh
mkdir -p src/resources/META-INF
touch src/resources/META-INF/MANIFEST.MF
```

Fill content into file `MANIFEST.MF`:

<!-- @@ src/resources/META-INF/MANIFEST.MF @BEGIN -->
```
Manifest-Version: 1.0
Created-By: Software Engineering project
```
<!-- @@ src/resources/META-INF/MANIFEST.MF @END -->

Commit file `MANIFEST.MF`:

```sh
git add src/resources
git commit -m "add META-INF/MANIFEST.MF"

git log --oneline                           # show commit log/history
```
```
c47b359 (HEAD -> main) add META-INF/MANIFEST.MF
3f49067 add unit tests
c71ab76 add src
f3c1706 add git submodule: 'libs', update .gitmodules
f1cdf73 add git submodule: '.vscode', update .gitmodules
e340ec6 add git submodule: '.env', add .gitmodules
2f68f2b (tag: root) root commit (empty)
```

Packaging invokes the `jar` command to package libraries from `libs`
and compiled Java code from `target/classes` to a resulting file
`application-1.0.0-SNAPSHOT.jar` placed in the `bin` folder:

```sh
mk package          # package 'target/classes' to the final '.jar'
```

```
package:
  jar -c -v -f "target/application-1.0.0-SNAPSHOT.jar" \
    --manifest=target/resources/META-INF/MANIFEST.MF \
    -C target/classes . $(packaged_content) &&
    [ -f ${P[target-jar]} ] &&
      echo -e "-->\\ncreated: ${P[target-jar]}" ||
      echo -e "-->\\nno compiled classes or manifest, no .jar created"
---
added manifest
added module-info: module-info.class
adding: application/(in = 0) (out= 0)(stored 0%)
adding: application/Application.class(in = 1913) (out= 940)(deflated 50%)
adding: application/package-info.class(in = 117) (out= 101)(deflated 13%)
adding: application/package_info.class(in = 333) (out= 260)(deflated 21%)
-->
created: target/application-1.0.0-SNAPSHOT.jar
```

Show the resulting `.jar` file in the `target` directory:

```sh
ls -la target
```

Output shows the packaged `.jar` file `application-1.0.0-SNAPSHOT.jar`:

```
total 16
drwxr-xr-x 1 svgr2 Kein    0 Oct 15 00:14 .
drwxr-xr-x 1 svgr2 Kein    0 Oct 15 00:09 ..
-rw-r--r-- 1 svgr2 Kein 2240 Oct 15 00:12 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1 svgr2 Kein    0 Oct 15 00:09 classes
drwxr-xr-x 1 svgr2 Kein    0 Oct 15 00:14 docs
drwxr-xr-x 1 svgr2 Kein    0 Oct 15 00:14 test-classes
```

The *MANFEST.MF* file specifies the main class to used when the
*.jar* file is executed. The class information is supplied during packaging
and can be seen in the "compiled" version of *MANFEST.MF* in `target`:

```sh
cat target/resources/META-INF/MANIFEST.MF       # show "compiled" MANFEST.MF
```
```
Manifest-Version: 1.0
Created-By: Software Engineering project
Main-Class: application.Application             <-- 'Main-Class' has been inserted
Class-Path: resources
```

Run `application-1.0.0-SNAPSHOT.jar`:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar 1 23 456
```
```
Hello, se1-play
 - arg: 1
 - arg: 23
 - arg: 456
```

Run jar with `mk`:

```sh
mk run-jar 1 23 456
```
```
run-jar: [1 23 456]
  java -jar "target/application-1.0.0-SNAPSHOT.jar" 1 23 456
---
Hello, se1-play
 - arg: 1
 - arg: 23
 - arg: 456
```

The `.jar` can now be released and distributed.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 12. Package as Stand-alone *.jar*

to follow...

<!-- 
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
``` -->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 13. Clean Project Build

to follow...


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 14. Push to Remote Repository

to follow...


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 15. Remarks to *VSCode*

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


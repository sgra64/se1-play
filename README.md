<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- A1 (SE-2)
-->
# Project: *se1-play*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
The assignment demonstates the structure and tools used of a professional
*Java* project.

The project is structured into several parts:

- `src/main` - folder with Java source code (`.java` files),

- `src/tests` - folder with unit test code (*JUnit* tests),

- `src/resources` - folder with non-*Java* sources and generated content,

- `target` - folder with compiled (`.class`) and packaged (`.jar`) files,

- `libs (*)` - folder with (`.jar`) libraries required by the project
    (imported packages),

- `.vscode (*)` - folder with project settings files for the
    [*VSCode*](https://code.visualstudio.com/)
    [*IDE*](https://en.wikipedia.org/wiki/Integrated_development_environment)
    (Integrated Development Environment),

- `.env (*)` - folder with the *sourcing script:* `env.sh` to set up the project
    environment with:
    
    - environment variables such as *CLASSPATH*, *MODULEPATH*, etc. and
    
    - project files that configure the *VSCode IDE*: `.project` and `.classpath`,

- `.git` - folder of the local *git* repository.
    [*Git*](https://en.wikipedia.org/wiki/Git) is a popular
    [*Version Control System (VCS)*](https://en.wikipedia.org/wiki/Version_control).

- `.gitmodules` file that describes
    [*Git Submodules*](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
    used in the project for folders (`.env`), (`.vscode`) and (`libs`) that are
    managed outside the project in separate *.git* repositories (see
    [*.env*-repo](https://github.com/sgra64/gitmodule-env.sh),
    [*.vscode*-repo](https://github.com/sgra64/gitmodule-vscode-java) and
    [*libs*-repo](https://github.com/sgra64/gitmodule-libs-jars)).

- `.gitignore` file with file and directory patterns for *git* to ignore.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

---

Project "*se1-play*" is created in several steps:

1. [Project Structure ("*Scaffold*")](#1-project-structure-scaffold)

1. [Project Location and Initialization](#2-project-location-and-initialization)

1. [Create a *.gitignore* - File](#3-create-a-gitignore---file)

1. [Import *git*-Submodules: *.env*, *.vscode*, *libs*](#4-import-git-submodules-env-vscode-libs)

1. [Inspect: *libs*](#5-inspect-libs)

1. [Adding Java Code](#6-adding-java-code)

1. [*"Sourcing"* the Project](#7-sourcing-the-project)

1. [Open Project in *VSCode*](#8-open-project-in-vscode)

1. [Project *Build* and *Run*](#9-project-build-and-run)

1. [*JUnit* Tests](#10-junit-tests)

1. [*Javadoc*](#11-javadoc)

1. [Package as *.jar*](#12-package-as-jar)

1. [Package as Stand-alone *.jar*](#13-package-as-stand-alone-jar)

1. [Clean Project Build](#14-clean-project-build)

1. [Push to Remote Repository](#15-push-to-remote-repository)

1. [Remarks to *VSCode*](#16-remarks-to-vscode)

1. [Summary](#17-summary)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Project Structure ("*Scaffold*")

The project resides in a project directory (or project folder) named `se1-play`
within a directory called `workspaces`. The directory `workspaces` usually
resides in the user's *HOME*-directory and may contain other projects.

Project `se1-play` is comprised of files and sub-directories. The directory
structure is called the project *"scaffold" :*

```sh
<workspaces>    # workspace folder with project directories
 |              # (files/folders marked with (*) are created during sourcing)
 |
 +-<se1-play>       # folder of the 'se1-play' project
   +--.project          # VSCode file with project settings (*)
   +--.classpath        # VSCode project file with CLASSPATH information (*)
   |
   +-<.env>             # git-module with the sourcing script: 'env.sh'
   |  +--env.sh         # sourcing script to set up the project environment
   |
   +-<.git>             # folder with the local git repository
   +--.gitignore        # file with patterns to ignore by git
   +--.gitmodules       # file about imported git modules
   |
   +-<.vscode>          # git-module with VSCode project settings and launch files
   |   +--settings.json         # project settings
   |   +--launch.json           # VSCode launch targets
   |   +--launch-coderunner     # Coderunner launch target (*)
   |   +--launch-terminal.json  # terminal launch settings
   |
   +-<libs>             # git-module with libraries required by the project
   |  |
   |  +-<jackson>           # library to process JSON data in Java
   |  +-<jacoco>            # code-coverage library for Java
   |  +-<jspecify>          # library supporting @Nullable annotations (used by JUnit6)
   |  |
   |  |  # JUnit test-runner .jar to run unit tests in the terminal
   |  +--junit-platform-console-standalone-6.1.0-M1.jar
   |  |
   |  +-<junit>             # JUnit unit test library
   |  |  +--apiguardian-api-1.1.2.jar
   |  |  +--junit-jupiter-api-6.1.0-M1.jar
   |  |  +--junit-platform-commons-6.1.0-M1.jar
   |  |  +--opentest4j-1.3.0.jar
   |  |
   |  +-<log4j>             # log4j2 logging library for Java
   |  +-<lombok>            # code-injection library for Java
   |  +-<runtime-SE>        # runtime library for SE1-projects
   |
   +-<src>              # <src> contains the project source code
   |  |
   |  +-<main>              # src/main: Java source code (*.java files)
   |  |
   |  +-<tests>             # src/tests: Unit tests
   |  |
   |  +-<resources>         # src/resources: non-Java sources, properties files
   |     +--application.properties  # application properties file
   |     +--log4j2.properties       # log4j logging properties file
   |     +-<META-INF>
   |        +--MANIFEST.MF  # included in packaged .jar
   |
   +-<target>           # folder with artifacts generated during the project build process
   |  |
   |  +-<classes>           # compiled source code (*.class files compiled from src/main)
   |  |
   |  +-<test-classes>      # compiled test code (*.class files compiled from src/tests)
   |  |
   |  +-<resources>         # content copied from src/resources
   |  |
   |  +-<javadoc>           # folder with compiled javadoc (.html)
   |  |  +--index.html      # file to open in browser
   |  |  +-- ...            # other .html and .css files
   |  |
   |  +-<coverage>          # folder with code coverage results
   |  |  +--jacoco.exec
   |  +-<coverage-report>   # folder with the code coverage report (html)
   |
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Project Location and Initialization

Create a new directory `workspaces` in your *HOME*-directory directory and
then create the project directory `se1-play` within:

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
cd se1-play                 # cd into the project directory
ls -la                      # show content of the 'se1-play' project directory
```
```
total 4                                         <-- project directory is empty
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:33 .
drwxr-xr-x 1 svgr2 Kein 0 Apr  6 18:29 ..
```

```sh
pwd                         # print working directory, show the path to the
                            # 'se1-play' project directory
```
```
.../workspaces/se1-play             <-- path to 'se1-play' on your laptop
```

The first step within the *se1-play* project is to create a local *git*
repository.

Test that you have a proper
[`.gitconfig`](https://github.com/sgra64/dotfiles/blob/main/.gitconfig)
file setup on your laptop:

```sh
cat $HOME/.gitconfig        # show content of .gitconfig file in the HOME-directory
```

Output:

```
[user]
    name = Sven Graupner                <-- your name
    email = sgraupner@bht-berlin.de     <-- your email address

[core]
    ignorecase = true       # ignore upper/lower case in file names
    autocrlf = false        # disable crlf conversion on checkout
    filemode = false        # ignore filemode (rwx) changes
    eol = lf                # always use newline '\n' as end-of-line

[init]
        defaultBranch = main
```

If not, repeat or perform steps from *assignment A:*
[*git-setup*](https://github.com/sgra64/se1-setup?tab=readme-ov-file#a4-git---setup).


Next, create a local *git* repository:

```sh
git init --initial-branch=main      # initialize local git repository
```

Show the project with the new local *git* repository:

```sh
ls -la                              # show the project directory
```
```
total 8
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:31 .
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 18:29 ..
drwxr-xr-x 1 svgr2 Kein    0 Apr  6 19:19 .git      <-- new local git repository
```

Next, create an empty root commit and tag as *"root"*:

```sh
git commit --allow-empty -m "root commit (empty)"       # create empty root commit
git tag root                                            # tag commit as 'root'

git log --oneline                                       # show the new commit
```
```
e9c43c5 (HEAD -> main, tag: root) root commit (empty)   <-- empty root commit
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Create a *.gitignore* - File

A *.gitignore* file should always be created for a new *git* project. Mind the
`.` (dot) in front of the name" `.gitignore`, which is a "*dot file*".
Avoid extensions such as `.txt`, which some editors may create.

*Atlasian* provides a good
[*"Introduction to .gitignore"*](https://www.atlassian.com/git/tutorials/saving-changes/gitignore).
Several [*collections*](https://github.com/github/gitignore) exist, including
[*.gitignore for Java*](https://github.com/github/gitignore/blob/main/Java.gitignore).

Install this [*.gitignore*](.gitignore) file in the project directory with an
editor or by download using the
[*curl*](https://brightdata.com/blog/web-data/what-is-curl) command:

```sh
# download file '.gitignore'
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
git add -f .gitignore               # stage the '.gitignore' file (mind '-f', with force)
git commit -m "add .gitignore"      # commit the '.gitignore' file with message (-m)

git log --oneline                   # show the commit log (commit history)
```
```
dcf9764 (HEAD -> main) add .gitignore                   <-- new commit
e9c43c5 (tag: root) root commit (empty)                 <-- initial root commit
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. Import *git*-Submodules: *.env*, *.vscode*, *libs*

[*Git Submodules*](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
(read the: [*article*](https://www.freecodecamp.org/news/how-to-use-git-submodules/))
is a mechanism to import content into a project that is maintained outside by
other people/teams in separate *git* repositories, here these are:

- project [*.env*-repo](https://github.com/sgra64/gitmodule-env.sh)
    for the `.env` folder with the *sourcing* script,

- project [*.vscode*-repo](https://github.com/sgra64/gitmodule-vscode-java)
    for the `.vscode` folder with *VSCode* settings files and

- project [*libs*-repo](https://github.com/sgra64/gitmodule-libs-jars)
    for the `libs` folder with project libraries.

*Git* modules should be kept separate from main project branches and
hence ignored and installed on a separate branch named *git-modules*, which
is branched off the *main* branch at the *"add .gitignore"* commit:

```sh
git switch -c git-modules           # create (-c) new branch: 'git-modules' off
                                    # the current commit "add .gitignore"

git branch              # show branches that currently exist in the git repository
```
```
* git-modules           <-- 'git-modules' is the current branch (green, plus: '*')
  main
```

The `*` marks the branch that is currently active, which means this branch will
receive subsequent commits.

Next, we import the three *git* *sub-modules* into the *se1-play* project.
*Git-modules* are imported from remote repositories with the `git submodules add`
command. After import (add), *git-modules* are committed to branch *git-modules*,
the currently active branch.

The first imported module is from the repository:
[*gitmodule-env.sh*](https://github.com/sgra64/gitmodule-env.sh):

```sh
# import git submodule '.env' from the remote 'gitmodule-env.sh.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
```

Show the new content:

```sh
ls -la                  # show imported content
```
```
total 17
drwxr-xr-x 1    0 Apr 19 19:33 ./
drwxr-xr-x 1    0 Apr 19 18:56 ../
drwxr-xr-x 1    0 Apr 19 19:33 .env/            <-- imported module
drwxr-xr-x 1    0 Apr 19 19:33 .git/
-rw-r--r-- 1 1074 Apr 19 19:30 .gitignore
-rw-r--r-- 1   86 Apr 19 19:33 .gitmodules      <-- new file with git-modules configurations
```

Remove file *.gitmodules* from the tracking index to avoid removal when
switching to other branches:

```sh
# remove file from the tracking index to avoid removal when switching branches
git rm --cached .gitmodules

git status              # show git status of the project
```
```
On branch git-modules
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   .env
```

Green coloring indicates that the git-module have already been staged.
Commit git-modules to branch *git-modules*:

```sh
# commit git-modules (already staged)
git commit -m "git submodules: .env"

# show the commit log
git log --oneline
```
```
5cf0596 (HEAD -> git-modules) git submodule: .env
dcf9764 (main) add .gitignore
e9c43c5 (tag: root) root commit (empty)
```


&nbsp;

Import two more modules:
[*gitmodule-vscode-java*](https://github.com/sgra64/gitmodule-vscode-java) and
[*gitmodule-libs-jars*](https://github.com/sgra64/gitmodule-libs-jars):

```sh
# import git submodule '.vscode' from the remote 'gitmodule-vscode-java.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode

git rm --cached .gitmodules && git commit -m "git submodule: .vscode"


# import git submodule '.libs' from the remote 'gitmodule-libs-jars.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars.git libs

git rm --cached .gitmodules && git commit -m "git submodule: libs"
```

Show the new content:

```sh
ls -la                  # show imported content
```
```
total 17
drwxr-xr-x 1    0 Apr 19 19:33 ./
drwxr-xr-x 1    0 Apr 19 18:56 ../
drwxr-xr-x 1    0 Apr 19 19:33 .env/            <-- imported module '.env'
drwxr-xr-x 1    0 Apr 19 19:33 .git/
-rw-r--r-- 1 1074 Apr 19 19:30 .gitignore
-rw-r--r-- 1   86 Apr 19 19:33 .gitmodules      <-- new file with git-modules configurations
drwxr-xr-x 1    0 Apr 19 19:42 .vscode/         <-- imported module '.vscode'
drwxr-xr-x 1    0 Apr 19 19:42 libs/            <-- imported module '.libs'
```

Show content of file *.gitmodules*, which contains the URLs of the
imported repositories:

```sh
cat .gitmodules
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
    url = https://github.com/sgra64/gitmodule-libs-jar.git
```

Show the complete git commit log:

```sh
# show the commit log
git log --oneline
```
```
99f8ede (HEAD -> git-modules) git submodule: libs   <-- branch 'git-modules'
adc62d6 git submodule: .vscode
5cf0596 git submodule: .env
dcf9764 (main) add .gitignore                       <-- branch 'main' points here
e9c43c5 (tag: root) root commit (empty)
```

<!-- 
```sh
# import git submodule '.env' from the remote 'gitmodule-env.sh.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
# 
# remove 'README.md' from submodule '.env'
(cd .env && git rm README.md && git commit -m "remove README.md")
# 
# commit submodule '.env' on branch 'git-modules'
git commit -m "add git submodule: '.env', add .gitmodules"

# import git submodule '.vscode' from the remote 'gitmodule-vscode-java.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode
(cd .vscode && git rm README.md && git commit -m "remove README.md")
git commit -m "add git submodule: '.vscode', update .gitmodules"

# import git submodule '.libs' from the remote 'gitmodule-libs-jars.git' repository
git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars.git libs
(cd libs && git rm README.md && git commit -m "remove README.md")
git commit -m "add git submodule: 'libs', update .gitmodules"
``` -->


&nbsp;

Test that *git-modules* have properly been imported:

```sh
git submodule               # list registered sub-modules
```
```
cdf8cb5d3a6de25ed2ba69a03a4594fe0c8beccc .env (heads/main)
d35f4cf3305c2927452f53675873b36a85583c66 .vscode (heads/main)
c96cf621126cd9563e147af57e9d0ebba848f8ab libs (heads/main)
```

Check updates that may have been published. Git will enter each *sub-module*
and invoke *git pull* asking for updates from the associated remote
repository:

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

Switch back to the *main* branch:

```sh
git switch main             # switch back to the main branch
```

Ignore warnings when switching back to the *main* branch:

```
warning: unable to rmdir '.env': Directory not empty
warning: unable to rmdir '.vscode': Directory not empty
warning: unable to rmdir 'libs': Directory not empty
Switched to branch 'main'
```

The current branch is now *main*:

```sh
git branch                  # show current branch
```
```
  git-modules
* main                      <-- main is the active branch (*)
```

Imported modules and file *.gitmodules* is present on the *main* branch:

```sh
ls -la
```
```
drwxr-xr-x 1    0 Apr 19 19:42 ./
drwxr-xr-x 1    0 Apr 19 18:56 ../
drwxr-xr-x 1    0 Apr 19 19:33 .env/            <-- module '.env'
drwxr-xr-x 1    0 Apr 19 19:52 .git/
-rw-r--r-- 1 1074 Apr 19 19:30 .gitignore
-rw-r--r-- 1  267 Apr 19 19:42 .gitmodules      <-- file '.gitmodules'
drwxr-xr-x 1    0 Apr 19 19:42 .vscode/         <-- git-module '.vscode'
drwxr-xr-x 1    0 Apr 19 19:42 libs/            <-- git-module 'libs'
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. Inspect: *libs*

Verify libraries have properly been installed:

```sh
find libs/                           # list content of 'libs' folder
```
```
libs/
libs/.git
libs/jackson                                        <-- 'jackson' JSON libraries for Java
libs/jackson/jackson-annotations-2.21.jar
libs/jackson/jackson-core-2.21.0.jar
libs/jackson/jackson-databind-2.21.0.jar
libs/jacoco                                         <-- 'jacoco' libraries for code coverage analysis
libs/jacoco/jacocoagent.jar
libs/jacoco/jacococli.jar
libs/jspecify                                       <-- 'jspecify' library with @Nullable annotations
libs/jspecify/jspecify-1.0.0.jar
libs/junit                                          <-- 'junit' library for JUnit tests
libs/junit/apiguardian-api-1.1.2.jar
libs/junit/junit-jupiter-api-6.1.0-M1.jar
libs/junit/junit-platform-commons-6.1.0-M1.jar
libs/junit/opentest4j-1.3.0.jar
libs/junit-platform-console-standalone-6.1.0-M1.jar     <-- 'junit' test runner
libs/log4j                                          <-- 'log4j' logging libraries
libs/log4j/log4j-api-2.24.3.jar
libs/log4j/log4j-core-2.24.3.jar
libs/log4j/log4j-slf4j2-impl-2.24.3.jar
libs/log4j/slf4j-api-2.0.17.jar
libs/lombok
libs/lombok/lombok-1.18.44.jar                      <-- 'lombok' code injection library
libs/README.md
libs/runtime-SE
libs/runtime-SE/runtimeSE-1.0.0-RELEASE.jar         <-- SE-1 runtime library
```

*Jar* files are distributed from a large central *"artifact repository"*, called the:
[*Maven Repository*](https://mvnrepository.com/). See example
[*junit-jupiter-api*](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
with version
[*junit-jupiter-api-6.1.0-M1*](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/6.1.0-M1),
which includes the downloadable
[*jar*](https://repo1.maven.org/maven2/org/junit/jupiter/junit-jupiter-api/6.1.0-M1/junit-jupiter-api-6.1.0-M1.jar)-file


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 6. Adding Java Code

Java source code is maintained under the `src/main` folder. This project is
created as a
[*Modular Java Project*](https://jenkov.com/tutorials/java/modules.html).
The module concept has been introduced in *Java 9* to create larger
Java projects that can be maintained separately, e.g. by different teams.
*Modules* allow to construct larger software from modules.

A *modular* Java project has new files:

- `module-info.java` contains the *module specification* with imported
    ("*required*") and "*exported*" packages and the module documentation.

- `package-info.java` as contains *package* documentation, which does not
    exist in non-modular Java projects.

The structure ("*scaffold*") of `<src/main>` of a *modular* Java project is:

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

Create the `src/main/application` path in the project directory:

```sh
mkdir -p src/main/application           # create path

ls -la
```
```
total 25
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:55 .
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 11:56 ..
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:00 .env
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:55 .git
-rw-r--r-- 1 svgr2 Kein 1262 Oct 21 12:07 .gitignore
-rw-r--r-- 1 svgr2 Kein  272 Oct 21 12:28 .gitmodules
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:47 .vscode
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:02 libs
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 12:55 src           <-- new 'src' folder
```

Install three source files into the `"src"` - directory:

- [`src/main/module-info.java`](https://github.com/sgra64/se1-play/blob/main/src/main/module-info.java) - the module description of the project.

- [`src/main/application/package-info.java`](https://github.com/sgra64/se1-play/blob/main/src/main/application/package-info.java) - the description of the *application* package.

- [`src/main/application/Application.java`](https://github.com/sgra64/se1-play/blob/main/src/main/application/Application.java) - a class with the `main()` method.

Install from the remote repository:

```sh
# define variable 'url' with the download URL
url="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main"

curl -o src/main/module-info.java $url/src/main/module-info.java

curl -o src/main/application/package-info.java $url/src/main/application/package-info.java

curl -o src/main/application/Application.java $url/src/main/application/Application.java

# show new files
find src/
```

```
src
src/main/application
src/main/application/Application.java       <-- new file in 'src/application'
src/main/application/package-info.java      <-- new file in 'src/application'
src/main/module-info.java                   <-- new file 'module-info.java' in 'src'
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. *"Sourcing"* the Project

*"Sourcing"* is jargon referring to executing a script with the
[*"source"*](https://superuser.com/questions/46139/what-does-source-do)
command in the context of the same *shell* process (not a sub-process).

*"Sourcing a Project"* means executing the  *"sourcing script"*
[`env.sh`](https://github.com/sgra64/se1-play/blob/env/env.sh) that
resides in the
[`.env`](https://github.com/sgra64/se1-play/tree/env)
*git module*.

The script will:

- set environment variables for the *shell* process:

  - `CLASSPATH`, `MODULEPATH`, `JDK_JAVAC_OPTIONS`, `JDK_JAVADOC_OPTIONS`,
    `JAR_PACKAGE_LIBS`

- create project files for the *VSCode* IDE:

  - `.classpath`, `.project`, `.vscode/launch-coderunner` and

- create functions used for the *build* process:

  - `show cmd [args] cmd [args] ...` -- show build commands,
    - example: `show` all commands or `show compile compile-tests package`
  
  - `mk [--show] cmd [args] cmd [args] ...` -- execute build commands,
    - example: `mk clean compile compile-tests package`
  
  - `wipe [--all|-a]` -- unset project environment variables and functions.

    - `--all | -a` -- remove everything including project files.

*Environment variables* and *functions* are set for the *executing shell* and
only last for the existence of that *shell* process (terminal).

When the *shell* process ends, e.g. when the terminal is closed, *functions*
and *environment variables* are lost (project files will remain). They need to
be *re-sourced* when a new terminal is opened (and *shell* process created).

It is important that IDE like *VSCode* respond to environment variables, e.g.
`CLASSPATH`. Therefore, it is advised to source the project before *VSCode*
is launched from the project directory. The *shell* process will pass
environment variables to *VSCode*. For this reason, we have not yet started
*VSCode* in the project.

*Source* the project in the project folder executing the `env.sh` script in `.env`:

```sh
source .env/env.sh          # source the project (execute script 'env.sh' in '.env')
```

Output shows the created environment:

```
----------------------------
 - created environment variables:
    - CLASSPATH
    - MODULEPATH
    - JDK_JAVAC_OPTIONS
    - JDK_JAVADOC_OPTIONS
    - JAR_PACKAGE_LIBS
 - created files:
    - .classpath
    - .project
    - .vscode/launch-coderunner
 - created functions:
    - show cmd [args] cmd [args] ...
    - mk [--show] cmd [args] cmd [args] ...
    - wipe [--all|-a]
----------------------------
--> success
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
  javac $(find src/main -name '*.java') -d target/classes

compile-tests:
  echo no tests present

run:
  java -p $MODULEPATH -m "se1_play/application.Application"

run-tests:
  echo no tests present

package:
  jar -c -v -f "target/application-1.0.0-SNAPSHOT.jar" \
    -C target/classes . $(packaged_content) &&
    [ -f ${P[target-jar]} ] &&
      echo -e "-->\\ncreated: target/application-1.0.0-SNAPSHOT.jar" ||
      echo -e "-->\\nno compiled classes or manifest, no .jar created"

run-jar:
  java -jar "target/application-1.0.0-SNAPSHOT.jar"

javadoc:
  rm -rf target/javadoc &&
  javadoc --source-path src/main -d target/javadoc \
    --module-path "$MODULEPATH" -version -author -Xdoclint:-missing \
    -noqualifier "java.*:application.*" \
    application &&
    echo -e "-->\\ncreated javadoc in: 'target/javadoc/index.html'"

clean:
  : nothing to clean
```

New environment variables that have been created in the executing *shell*
process can be listed:

```sh
# show value of the 'CLASSPATH' environment variable
echo $CLASSPATH                         # print CLASSPATH (as long ';' or ':' separated list)

echo $CLASSPATH | tr '[:;]' '\n'        # pretty-print ClASSPATH

echo $MODULEPATH                        # new environment variable
```

Output is a `:` (`;` on Windows) - separated list with paths pointing to folders with
`.class` files or `.jar` files:

```
# CLASSPATH:
target/classes;libs/jackson/jackson-annotations-2.21.jar;libs/jackson/jackson-core-2.21.0.jar;libs/jackson/jackson-databind-2.21.0.jar;libs/jspecify/jspecify-1.0.0.jar;libs/junit/junit-jupiter-api-6.1.0-M1.jar;libs/log4j/log4j-api-2.24.3.jar;libs/log4j/log4j-core-2.24.3.jar;libs/log4j/log4j-slf4j2-impl-2.24.3.jar;libs/log4j/slf4j-api-2.0.17.jar;libs/lombok/lombok-1.18.44.jar;libs/runtime-SE/runtimeSE-1.0.0-RELEASE.jar

target/classes
libs/jackson/jackson-annotations-2.21.jar
libs/jackson/jackson-core-2.21.0.jar
libs/jackson/jackson-databind-2.21.0.jar
libs/jspecify/jspecify-1.0.0.jar
libs/junit/junit-jupiter-api-6.1.0-M1.jar
libs/log4j/log4j-api-2.24.3.jar
libs/log4j/log4j-core-2.24.3.jar
libs/log4j/log4j-slf4j2-impl-2.24.3.jar
libs/log4j/slf4j-api-2.0.17.jar
libs/lombok/lombok-1.18.44.jar
libs/runtime-SE/runtimeSE-1.0.0-RELEASE.jar

# MODULEPATH:
target/classes;libs/jackson;libs/jspecify;libs/junit;libs/log4j;libs/lombok;libs/runtime-SE
```

New project configuration files have also appeared in the project directory:

```sh
ls -la . .vscode
```
```
total 30
drwxr-xr-x 1    0 Apr 19 21:29 ./
drwxr-xr-x 1    0 Apr 19 18:56 ../
-rw-r--r-- 1 2906 Apr 19 21:29 .classpath               <-- created
drwxr-xr-x 1    0 Apr 19 20:54 .env/
drwxr-xr-x 1    0 Apr 19 20:54 .git/
-rw-r--r-- 1 1074 Apr 19 20:54 .gitignore
-rw-r--r-- 1  272 Apr 19 20:54 .gitmodules
-rw-r--r-- 1  380 Apr 19 21:29 .project                 <-- created
drwxr-xr-x 1    0 Apr 19 21:29 .vscode/
drwxr-xr-x 1    0 Apr 19 20:54 libs/
drwxr-xr-x 1    0 Apr 19 21:25 src/
```

Function `wipe` removes all created environment variables. They can be rebuilt
any time:

```sh
wipe                    # unset all created environment variables
```
```
wiping:
 - unset CLASSPATH, MODULEPATH, JDK_JAVAC_OPTIONS, JDK_JAVADOC_OPTIONS,
   JAR_PACKAGE_LIBS
```
```sh
wipe --all              # in addition, created project files are removed
```
```
wiping:
 - rm -rf .classpath .project .vscode/launch-coderunner
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
<!-- 
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
``` -->

Re-source the project:

```sh
source .env/env.sh      # re-source the project
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 8. Open Project in *VSCode*

*VSCode* **must always be started** from within the project directory. *VSCode*
handles one project in one IDE/Editor instance. You cannot have multiple projects
open in the same *VSCode* IDE/Editor instance!

Verify that you are in the project directory:

```sh
pwd                         # print working directory

ls -la
```
```
.../workspaces/se1-play     <-- pwd shows path ending with 'workspaces/se1-play'

total 33                    <-- 'ls -la' shows the content of the project directory
# 
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 21:36 .
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 18:56 ..
-rw-r--r-- 1 svgr2 Kein 2906 Apr 19 21:34 .classpath
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 20:54 .env
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 20:54 .git
-rw-r--r-- 1 svgr2 Kein 1074 Apr 19 20:54 .gitignore
-rw-r--r-- 1 svgr2 Kein  272 Apr 19 20:54 .gitmodules
-rw-r--r-- 1 svgr2 Kein  660 Apr 19 21:36 .project
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 21:34 .vscode
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 20:54 libs
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 21:25 src
```

Start *VSCode* from the project directory:

```sh
# start VSCode IDE at this (dot, '.') directory, mind the dot '.'
code .
```

It may take few seconds for *VSCode* to start and initialize the project. Mind
messages shown in the lower-left corner of *VSCode*. Wait until *VSCode* has
finished setup.

Open file `src/main/module-info.java`:

<!-- @@ src/main/module-info.java @BEGIN -->

```java
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
```
<!-- @@ src/main/module-info.java @END -->


Modules have been introduced in *Java 9* (2017) to build software from modular
projects. Prior, applications could be built only from packages within a project.
See *Jakob Jenkov's* article:
[*Java Modules*](https://jenkov.com/tutorials/java/modules.html).

The presence of a module description in file `module-info.java` indicates a
*modular* Java project. It includes the module name: `se1_play`, external
modules required by this module and packages exported from this module to
other modules.
Locations of *required* modules are provided by the `MODULEPATH` environment
variable.

*"Opening"* a package makes it accessible to tools such as *Javadoc* and the
*JUnit test runner*. *Javadoc* requires packages open or exported.


&nbsp;

Open file `src/main/application/package-info.java`:

<!-- @@ src/main/application/package-info.java @BEGIN -->

```java
/**
 * The {@link application} package includes classes with a {@code main()} -
 * method as entry point for the <i>Java Virtual Machine (VM)</i>.
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
package application;

/**
 * Define variables used in <i>Javadoc</i>. {@code package-info.java}
 * has been introduced with <i>Modules</i> in <i>Java 9 (2017)</i> to
 * provide package-level documentation.
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

Files *package-info.java* have been introduced with *Java Modules* (2017) to
provide a place to store package-level documentation (describing a package
for *Javadoc*).

The file may also include static variables such as *"Author"* and *"Version"*
used in *Javadoc* doc strings.


&nbsp;

Open file `src/main/application/Application.java`:

<!-- @@ src/main/application/Application.java @BEGIN -->
```java
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
```
<!-- @@ src/main/application/Application.java @END -->

Class *Application.java* has a `main(String[] args)` method as entry point
for the *Java Virtual Machine (VM)*. It prints a greeting line and processes
`String[] args` command line arguments.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 9. Project *Build* and *Run*

*"Project Build"* is the process of:

- compiling source code from `src` to `target` (folder containing compiled
    classes),

- performing unit tests, and

- packaging compiled files to a `.jar` file that can be released and distributed.


We use the new `mk` function created during *sourcing* for the *build process*:

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
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java

target/
target/classes
target/classes/application
target/classes/application/Application.class    <-- compiled class for 'Application.java'
target/classes/application/package-info.class
target/classes/application/package_info.class
target/classes/module-info.class                <-- compiled class for 'module-info.java'
```

Try various methods to run the program:

```sh
java --class-path=target/classes application.Application 1 23 456

java application.Application 1 23 456

java -p "$MODULEPATH" -m se1_play/application.Application 1 23 456
```

The program outputs the parameters passed as command line arguments
(see the source code in *Application.java*):

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

When this works, commit source files to the *main* branch (current).
A commit always starts with staging the files to be committed:

```sh
git add src/main                # stage 'src' file

git status                      # show staged files
```

Staged files appear in green color:

```
On branch main
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   src/main/application/Application.java
        new file:   src/main/application/package-info.java
        new file:   src/main/module-info.java
```

Staged files can now be committed:

```sh
git commit -m "add src/main"    # commit 'src' file

git log --oneline               # show commit log
```
```
fe284be (HEAD -> main) add src/main                 <-- new commit on branch 'main'
dcf9764 add .gitignore
e9c43c5 (tag: root) root commit (empty)
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 10. *JUnit* Tests

[*JUnit Tests*](https://www.codeflow.site/de/article/junit-assertions#_4_junit_5_assertions)
test individual code units ("*units-under-test*") in isolation to other units.

A simple test class under `src/tests` is used to demonstrate unit tests.
As code, unit tests should run in both, in the terminal and in the IDE.

Create the tests folder and JUnit test class in the project directory:

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

Fill in content for file: `Application_0_always_pass_Tests.java`. Understand
annotations: `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`, `@Test`
and `@Order(n)`.

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
     * Method is executed after each @Test method
     * @throws Exception if any exception occurs
     */
    @AfterEach
    public void tearDownAfterEach() throws Exception {
        System.out.println("tearDownAfterEach() runs after each @Test method");
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


&nbsp;

Remove the comment in file *module-info.java* to require *org.junit.jupiter.api :*

```java
module se1_play {

    /*
     * External module required by this module (JUnit-5 module for JUnit testing).
     */
    requires org.junit.jupiter.api;     // <-- remove comment
}
```

Wipe and re-source the project. Wiping removes all environment variables, files
and functions created during *sourcing*:

```sh
wipe --all              # remove project environment variable and files
```
```
wiping:
 - unset CLASSPATH, MODULEPATH, JDK_JAVAC_OPTIONS, JDK_JAVADOC_OPTIONS,
   JAR_PACKAGE_LIBS
 - rm -rf target .classpath .project .vscode/launch-coderunner
 - unset -f command show mk wipe prepare_manifest packaged_content
```

*Re-sourcing* rebuilds the environment, now taking unit tests into account:

```sh
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
    - JUNIT_CLASSPATH           <-- new environment variable
    - JACOCO_AGENT_OPTIONS      <-- new environment variable
 - created files:
    - .classpath
    - .project
    - .vscode/launch-coderunner
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
  javac -cp $JUNIT_CLASSPATH $(find src/tests -name '*.java') \
    -d target/test-classes
```

Compare the `src` folder to the new `target` folder containing the compiled output:

```sh
find src target         # output 'target' and 'bin' folders
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java
src/tests                               <-- new test classes (.java file)
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java

target
target/classes
target/classes/application
target/classes/application/Application.class
target/classes/application/package-info.class
target/classes/application/package_info.class
target/classes/module-info.class
target/test-classes                     <-- new compiled test .class files
target/test-classes/application
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
  execute --scan-classpath
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
setUpBeforeClass() runs once before any @Test method
setUpBeforeEach() runs before each @Test method
tearDownAfterEach() runs after each @Test method
setUpBeforeEach() runs before each @Test method
tearDownAfterEach() runs after each @Test method
tearDownAfterClass() runs after all @Test methods have finished
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
└─ JUnit Vintage ✔

Test run finished after 255 ms
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
[         2 tests successful      ]     <-- 2 tests successful
[         0 tests failed          ]     <-- 0 tests failed
``` -->

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/junit-run-2.png" width="360"/>


&nbsp;

Make sure tests also run in the IDE. You may need to 
*Clean Java Language Server Workspace* to make *VSCode* to recognize tests:

Type `<Ctrl> + <Shift> + <P>`, then type: `"Clean Java Language Server..."` and accept
all further choices. This rebuilds the *VSCode's* state of the project.
After that, the test icon should appear in *VSCode* and show the test.

Run the test:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/vscode-4-unit-tests.png" width="800"/>


&nbsp;

When the test is passing, commit tests. Show the changes to commit first.
Red lines show new or modified content:

```sh
git status
```
```
On branch main
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   src/main/module-info.java

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        src/tests/

no changes added to commit (use "git add" and/or "git commit -a")
```

Recall what changes were made to modified file *src/main/module-info.java*:

```sh
git diff src/main/module-info.java
```

The difference shows the the *org.junit.jupiter.api* actication:

```diff
diff --git a/src/main/module-info.java b/src/main/module-info.java
index 24e46f9..a7703be 100644
--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -18,5 +18,5 @@ module se1_play {
     /*
      * External module required by this module (JUnit module for JUnit testing)
.
      */
-    // requires org.junit.jupiter.api;
+    requires org.junit.jupiter.api;
 }
```

Stage changes:

```sh
git add src/main/module-info.java src/tests

git status
```

Staged files now appear in green:

```
On branch main
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        modified:   src/main/module-info.java
        new file:   src/tests/application/Application_0_always_pass_Tests.java
```

Finally, commit staged files:

```sh
git commit -m "add src/tests unit tests"    # commit test files
```

The commit log now has 4 commits on the *main* branch:

```sh
git log --oneline                           # show commit log/history
```
```
5cf0f7f (HEAD -> main) add src/tests unit tests
fe284be add src/main
dcf9764 add .gitignore
e9c43c5 (tag: root) root commit (empty)
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 11. *Javadoc*

*Javadoc* is *Java's* documentation method and toolset based on
[*Java doc strings*](https://de.wikipedia.org/wiki/Javadoc), which are
provided as Java comments.

The `javadoc` compiler parses `.java` files and "*compiles*" *Java doc strings*
to HTML.

```sh
mk javadoc                  # generate javadoc in the 'docs' folder (new)
```

Output shows the command calling the `javadoc` compiler with output directed `-d` to the `docs` folder:

```
javadoc:
  rm -rf target/javadoc &&
  javadoc --source-path src/main -d target/javadoc \
    --module-path "$MODULEPATH" -version -author -Xdoclint:-missing \
    -noqualifier "java.*:application.*" \
    application &&
    echo -e "-->\\ncreated javadoc in: 'target/javadoc/index.html'"
```

Output of the `javadoc` compiler:

```
Loading source files for package application...
Constructing Javadoc information...
Creating destination directory: "target/javadoc\"
Building index for all the packages and classes...
Standard Doclet version 21+35-LTS-2513
Building tree for all the packages and classes...
Generating target\javadoc\se1_play\application\Application.html...
Generating target\javadoc\se1_play\application\package-summary.html...
Generating target\javadoc\se1_play\application\package-tree.html...
Generating target\javadoc\se1_play\module-summary.html...
Generating target\javadoc\overview-tree.html...
Building index for all classes...
Generating target\javadoc\allclasses-index.html...
Generating target\javadoc\allpackages-index.html...
Generating target\javadoc\index-all.html...
Generating target\javadoc\search.html...
Generating target\javadoc\index.html...
Generating target\javadoc\help-doc.html...
-->
created javadoc in: 'target/javadoc/index.html'
```

Output is compiled to the `target/javadoc` folder and can be displayed in
a web-browser from the `index.html` file.

```sh
ls -la target/javadoc               # show generated content of the 'docs' folder
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
chrome target/javadoc/index.html    # open web-browser with Javadoc
```

You may need to open the file in another way if the `chrome` command does not work.

The web-browser shows the Java documentation:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-1.png" width="800"/>

Navigate through: `application` -> `Application`:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/javadoc-2.png" width="800"/>


Make changes such that your name appears as *Author* - adjust in:
`src/application/package-info.java` and recompile *Javadoc*.

Since *Javadoc* is generated (compiled) content, it is not committed to the
*git* repository. Java source files have already been committed. 


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 12. Package as *.jar*

"*Packaging*" means collecting compiled code (`.class` files) in a `.jar`
(Java archive) file to release or distribute the developed software.

Packaging invokes the `jar` command to collect compiled Java code from
`target/classes` and assemble a resulting file `application-1.0.0-SNAPSHOT.jar`
placed in the `target` folder:

```sh
mk package          # package 'target/classes' to the final '.jar'
```

```
package:
  jar -c -v -f "target/application-1.0.0-SNAPSHOT.jar" \
    -C target/classes . $(packaged_content) &&
    [ -f ${P[target-jar]} ] &&
      echo -e "-->\\ncreated: target/application-1.0.0-SNAPSHOT.jar" ||
      echo -e "-->\\nno compiled classes or manifest, no .jar created"
---
added manifest
added module-info: module-info.class
adding: application/(in = 0) (out= 0)(stored 0%)
adding: application/Application.class(in = 1110) (out= 667)(deflated 39%)
adding: application/package-info.class(in = 117) (out= 101)(deflated 13%)
adding: application/package_info.class(in = 408) (out= 292)(deflated 28%)
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
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 23:21 .
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 22:57 ..
-rw-r--r-- 1 svgr2 Kein 2234 Apr 19 23:21 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 23:05 classes
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 23:18 javadoc
drwxr-xr-x 1 svgr2 Kein    0 Apr 19 23:05 test-classes
```

Run `application-1.0.0-SNAPSHOT.jar`. Mind that the *.jar* file is passed on
the *classpath* (`-cp`) requiring the class with the *main()* function to
execute to be specified explicitely (this will be a difference to a
*stand-alone .jar*, which includes which class with the *main()* function
to execute):

```sh
java -cp target/application-1.0.0-SNAPSHOT.jar application.Application 1 23 456
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
no main manifest attribute, in target/application-1.0.0-SNAPSHOT.jar
```

The *.jar* does not run directly without the class with the *main()* function
provided. This is fixed in the next step when the *.jar* is packaged as a
*stand-alone .jar*


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 13. Package as Stand-alone *.jar*

Packaging a *stand-alone .jar* requires the information of the class with the
*main()* function to executea be included in the *.jar* file in a file called
`MANIFEST.MF`, which we create in the project under the path
`src/resources/META-INF`:

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
1e17517 (HEAD -> main) add META-INF/MANIFEST.MF
5cf0f7f add src/tests unit tests
fe284be add src/main
dcf9764 add .gitignore
e9c43c5 (tag: root) root commit (empty)
```

The *MANFEST.MF* file specifies the class with the *main()* function in a
*.jar* file. The class information in the *MANFEST.MF* file is injected
during packaging in the *"copied"* version of *MANFEST.MF* under
`target/resources/META-INF/MANIFEST.MF`:

```sh
wipe --all                                      # rebuild project environment
source .env/env.sh                              # with MANIFEST.MF

mk package                                      # re-package with MANFEST.MF
```
```
package:
  jar -c -v -f "target/application-1.0.0-SNAPSHOT.jar" \
    --manifest=target/resources/META-INF/MANIFEST.MF \      <-- new MANIFEST.MF included
    -C target/classes . $(packaged_content) &&
    [ -f ${P[target-jar]} ] &&
      echo -e "-->\\ncreated: target/application-1.0.0-SNAPSHOT.jar" ||
      echo -e "-->\\nno compiled classes or manifest, no .jar created"
```

Show that MANIFEST.MF is now included in the .jar file:

```sh
jar tvf target/application-1.0.0-SNAPSHOT.jar
```

```
     0 Sun Apr 19 23:24:40 CEST 2026 META-INF/
   575 Sun Apr 19 23:24:40 CEST 2026 META-INF/MANIFEST.MF   <-- new MANIFEST.MF included
   236 Sun Apr 19 23:24:34 CEST 2026 module-info.class
     0 Sun Apr 19 23:24:34 CEST 2026 application/
  1110 Sun Apr 19 23:24:34 CEST 2026 application/Application.class
   117 Sun Apr 19 23:24:34 CEST 2026 application/package-info.class
   408 Sun Apr 19 23:24:34 CEST 2026 application/package_info.class
```

Show the content of *MANFEST.MF:*

```sh
cat target/resources/META-INF/MANIFEST.MF       # show MANFEST.MF supplemented with line:
                                                # "Main-Class: application.Application"
```
```
Manifest-Version: 1.0
Created-By: Software Engineering project
Main-Class: application.Application             <-- 'Main-Class' has been injected
Class-Path: resources
    libs/jackson/jackson-annotations-2.21.jar   <-- libraries added to Class-Path fiels
    libs/jackson/jackson-core-2.21.0.jar
    libs/jackson/jackson-databind-2.21.0.jar
    libs/jspecify/jspecify-1.0.0.jar
    libs/junit/junit-jupiter-api-6.1.0-M1.jar
    libs/log4j/log4j-api-2.24.3.jar
    libs/log4j/log4j-core-2.24.3.jar
    libs/log4j/log4j-slf4j2-impl-2.24.3.jar
    libs/log4j/slf4j-api-2.0.17.jar
    libs/lombok/lombok-1.18.44.jar
    libs/runtime-SE/runtimeSE-1.0.0-RELEASE.jar
```

Run the new *stand-alone .jar:*

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar 1 23 456
```
```
Hello, se1-play
 - arg: 1
 - arg: 23
 - arg: 456
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 14. Clean Project Build

A *"clean project build"* first removes all content that previously has been
created (*"clean"* stage). Next, all artifacts are rebuilt (*"build"* stage).

Using `mk`, the *build* command performs steps for the clean project build:

- `clean` - remove all previously built content from the project.

- `compile` - compile **.java* source code from *src/main* to *.class* files.

- `compile-tests` - compile tests from *src/tests* to *.class* files.

- `run-tests` - run unit tests.

- `package` - package compiled code, properties and MANIFEST.MF to a finally
    releasable (distributable) *.jar* file in *target*.

Show the commands of a *clean project build:*

```sh
show build
```
```
build:
  mk clean compile compile-tests run-tests package
```

Run the *clean project build:*

```sh
mk build
```
```
build:
  mk clean compile compile-tests run-tests package
```

The *build process* should always run without error, particularly before
commits are made. When *build* fails, the project can be reset to the state
of the last commint and the project should *build* again.

In professional software development, *project builds* are not only performed
on developer's laptops, but also on *build servers*, which are dedicated
server machines that continously or over-night fetch code from the source
repository and perform build- and test processes (test: perform unit tests).

Broken *builds* or *tests* are instantly detected independently of developer
activity.

Read article by *Robert Sheldon* and *Cameron McKenzie:*
[*"What is a build server?"*](https://www.techtarget.com/searchsoftwarequality/definition/Build-Server)
and understand the concept of
[*Continuous Integration (CI)*]() from the article.


<img src="https://www.techtarget.com/rms/onlineimages/continuous_integration-f.png" width="600"/>

From the article:

<!-- block-quote: put '>' in front -->
> "The *build server* is a key component of *continuous integration*, which
    is the practice of automatically and regularly integrating code changes
    from multiple developers working with the same codebase.
    *Continuous integration* is typically part of a larger *continuous
    integration/continuous delivery (CI/CD)* framework.
    *Continuous delivery* is concerned with deploying the software to
    *testing*, *staging* and *production* environments."


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 15. Push to Remote Repository

Use your account at
[*BHT GitLab*](https://gitlab.bht-berlin.de/) or
[*GitHub*](https://github.com)
(or another remote repository) to host the remote repository for the project.

A *Remote repository* is the server-version of *git*, which

- hosts multiple (many) user accounts and *git* repositories for those
    user accounts,

- receives *git commands* for hosted *git* repositories over the network,

- provides a web-user-interface for account maintenance and basic repository
    interations, such as creating new repositories, granting access, etc.

Each *"remote repository"* is a fully-fledged *git* repository operates on
the same principles and understands the same operations as a local *git*
repository.

*"Adding a remote"* to a local *git* repository means to register the URI
of the remote *git* repository to a local repository.

Create a new remote repository: `se1-play` in your account at *BHT GitLab*
or *GitHub*. Each repository will have a unique URI. Understand the
difference between formats:

- *SSH:* `git@github.com:<user-account>/se1-play.git`

- *HTTPS:* `https://github.com/<user-account>/se1-play.git`

*SSH* URI should be preferred. They assume a *public SSH key* is registered
with your account and a corresponding *private SSH key* exists on your
laptop in the *HOME* directory.

Verify you have a *public/private SSH key pair* in your *HOME* directory:

```sh
ls -la $HOME                # show content of the $HOME directory
```

The command lists your *HOME* directory, which should show a dotfile entry
`.ssh`, which is the directory that stores your *public/private SSH key pairs*:

```
drwxr-xr-x 1 svgr2 Kein     0 Oct  9 13:20 .ssh
```

```sh
ls -la $HOME/.ssh           # show content of $HOME/.ssh
```
```
total 46
drwxr-xr-x 1 svgr2 Kein    0 Oct  9 13:20 .
drwxr-xr-x 1 svgr2 Kein    0 Oct 21 03:02 ..
-rw-r--r-- 1 svgr2 Kein 1679 Oct  9 13:20 id_rsa        <-- private ssh-key
-rw-r--r-- 1 svgr2 Kein  415 Oct  9 13:20 id_rsa.pub    <-- public ssh-key
```

If you don't have `.ssh` in your $HOME directory, create a *public/private SSH
key pair:*

```sh
ssh-keygen                  # create public/private SSH key pair in $HOME/.ssh
```

Register your *public(!)* key at the remote git repository account (usually
under: account - settings). Find the *"SSH and GPG keys"* page and add a
new *SSH* key by copying the *public(!)* key into the registration field.

```sh
cat $HOME/.ssh/id_rsa.pub   # print public ssh key
```

Copy the entire text into the *SSH key* registration field:

```
ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDsYvrIXtxlPGjByzFbB2S/AgjFzaWCBQ+cot7rrXDLMWJHH+uyWNbh0QpXGtTPm43XXH6hx004ew3cv52k5atqeJgkKGPLb3Zzp9Kg1yVQg5u2dntuzPW8ghwLKdVkHPoYLSLrj/ivYXYqrqsahJ0Nt0QSgjNQ/BUJvLaWXpXGP2EKT5f2S5un61Sdw0u37z5n/KnGOUtwAcG+cJn7b/5BP7REC+HI6v8/RLtCN36vN7Mz+nUio2btFIFmkxSjnxj8G/y4318V8mf7p1W7jTZ9Pcc2ku41yz9JNBl/E4YyVJvEjTO/4yEJ/4AYSDnkL2g57chRhliw5pmVtzONCwX7 xxxxxxx@bht-berlin.de
```

With this done, register the *SSH-URI* of the remote repository at your local
git repository (in the `se1-play` project).

```sh
# Obtain the URI from your remote repository, preferably
# - git@github.com:<user-account>/se1-play.git or
# - https://github.com/<user-account>/se1-play.git
# 
# and register under the name "origin" with the local git repository:

git remote add origin git@github.com:<user-account>/se1-play.git

# show the registered remote repository:
git remote -v
```
```
origin  git@github.com:<user-account>/se1-play.git (fetch)
origin  git@github.com:<user-account>/se1-play.git (push)
```

With the remote *"orign"* being set-up, the local *main-branch* can be pushed
to the remote repository.
Flag `--` turns the local *main* branch to a *"tracking branch"* of the
associated remote *main* branch as the *"tracked branch"*:

```sh
# push local branch 'main' to the remote and link as 'tracking' branch
# to the remote 'tracked' branch 'remote/origin/main'
git push --set-upstream origin main
```

If the push is successfull, all local commits made during this assignment
on the *main* branch have been copied (pushed) to the *main* branch in the
remote. Refresh the repository web-site and committed files should appear.

*Git* exchanges commits between linked local and remote branches establishing
a *"global commit order"*. Every developer *"tracking"* a remote branch will
eventually have the same commit order. Commits on tracked branches only
*"move forward"*, which means once a commit is published, it cannot
(should not) be withdrawn (forced pushes using `-f` of altered commit chains
break the *global commit order* of the branch).

*Git* repositories can form distributed networks accross which commits made
to branches can be shared. *Git* follows a *"lazy"* policy to synchronize
shared branches: nothing happens automatically, a tracking branch must
explicitely *"pull"* changes (new commits) made by others.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 16. Remarks to *VSCode*

Wipe and re-source the project before launching *VSCode:*

```sh
cd <project-dir>        # cd into the project directory

# make sure you are in the project directory (which has .git)
[ -d .git ] && echo "Yes, I am in the project directory" ||
    echo "Sorry, I am lost. I am at $(pwd)"

wipe --all              # remove project environment variable and files

source .env/env.sh      # source the project
```

Launch *VSCode* from the project directory only and after "*sourcing*" such
that environment variables have been created and passed to *VSCode*.

```sh
code .                  # only then launch VSCode in the project directory
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

<!-- 
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
 -->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 17. Summary

Script [*build.sh*](build.sh) summarizes commands to build the *se1-play*
project.

<!--
#!/bin/bash
# commands to build the 'se1-play' project

git init --initial-branch=main
git commit --allow-empty -m "root commit (empty)"
git tag root

curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore
git add -f .gitignore
git commit -m "add .gitignore"

git switch -c git-modules
git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
git rm --cached .gitmodules && git commit -m "git submodule: .env"

git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode
git rm --cached .gitmodules && git commit -m "git submodule: .vscode"

git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars.git libs
git rm --cached .gitmodules && git commit -m "git submodule: libs"

git switch main

mkdir -p src/main/application
url="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main"
curl -o src/main/module-info.java $url/src/main/module-info.java
curl -o src/main/application/Application.java $url/src/main/application/Application.java
curl -o src/main/application/package-info.java $url/src/main/application/package-info.java

git add src/main
git commit -m "add src/main"

mkdir -p src/tests/application
curl -o src/tests/application/Application_0_always_pass_Tests.java \
$url/src/tests/application/Application_0_always_pass_Tests.java

# patch file 'module-info.java' removing the comment from line
# // requires org.junit.jupiter.api
# 
# patch -p1 ...
# git apply <patch> ...
# 
git apply <<< "diff --git a/src/main/module-info.java b/src/main/module-info.java
index 24e46f9..a7703be 100644
--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -18,5 +18,5 @@ module se1_play {
     /*
      * External module required by this module (JUnit module for JUnit testing).
      */
-    // requires org.junit.jupiter.api;
+    requires org.junit.jupiter.api;
 }" && echo "patch successfully applied to 'module-info.java'" ||
    echo "patch 'module-info.java' failed"

git add src/main/module-info.java src/tests
git commit -m "add src/tests unit tests"

mkdir -p src/resources/META-INF
curl -o src/resources/META-INF/MANIFEST.MF $url/src/resources/META-INF/MANIFEST.MF

git add src/resources
git commit -m "add META-INF/MANIFEST.MF"

-->

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Setup a Java project environment with environment variables, project files
# and functions supporting the project build process:
# \\
# Environment variables:
#  - PROJECT_PATH               ; path to git project (to wipe after cd-out)
#  - CLASSPATH, MODULEPATH      ; used by Java compiler and JVM
#  - JDK_JAVAC_OPTIONS          ; used by the Java compiler
#  - JDK_JAVADOC_OPTIONS        ; used by the Javadoc compiler
#  - JUNIT_CLASSPATH            ; used by the JUnit test runner
#  - JUNIT_OPTIONS              ; used by the JUnit test runner
#  - JACOCO_AGENT               ; JVM jacoco agent option for code coverage
# \\
# Project files for VSCode and eclipse IDE:
#  - .classpath, .project       ; used to set up the VSCode Java extension
#  - in .vscode: .classpath, .modulepath, .sources  ; for Java Code Runner
# 
# Executable functions:
# \\
#  - show [cmd1, cmd2...]       ; show commands
# 
#  - mk [cmd1, cmd2...] [args]  ; execute commands
# 
#  - wipe [--all|-a|-la]        ; unset project env variables and functions
#                               ; --all|-a: including project files
#                               ; -la: project files and 'libs' link or folder
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# VSCode commands:
#  - build: Ctrl-Shift-B
#  - run:   Ctrl-Alt-N
#  - clean Java Language Server Workspace: Ctrl-Shift-P
# 
# VSCode project cache:
#  - Windows: C:\Users\<USER>\AppData\Roaming\Code\User\workspaceStorage
#  - MacOS:   ~/Library/Application Support/Code/User/workspaceStorage
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# the following segment disables zsh to output ANSI escape characters in sub-
# processes such as in: wc $(find tmp -name '*.py') or colors="${colors:3}".
# setopt no_match: disables 'no matches found:' message in zsh.
[[ ! "$SHELL" =~ bash ]] && \
    trap "" DEBUG && \
    setopt no_nomatch

# global associative array with common values
declare -gA P=(
    [src]="src"
    [tests]="tests"
    [res]="resources"
    [manifest]="${P[res]}/META-INF/MANIFEST.MF"
    [lib]="libs"
    [lib-repo]="git clone -b libs --single-branch git@github.com:sgra64/se1-play.git"
    [target]="bin"
    [classes]="bin/classes"
    [test-classes]="bin/test-classes"
    [test-runner]="junit-platform-console-standalone-1.9.2.jar"
    [module]="se1.play"
    [run]="application.Application"
    [log-dir]="logs"
    [doc-dir]="docs"
    [cov-dir]="coverage"
    [env-script]=".env/env.sh"
    [jar]="bin/application-1.0.0-SNAPSHOT.jar"
    # 
    [is-zsh]=$(type setopt 2>/dev/null)
    [is-win]=$([[ "$(uname)" =~ (CYGWIN|MINGW) ]] && echo true)
    [created-env]=""
    [created-files]=""
)

function setup() {
    # 
    [ ! -d src -o ! -d .git ] && echo "must run in main project directory" && return 1
    # 
    [ -z "$PROJECT_PATH" ] && \
        export PROJECT_PATH="$(pwd)" && \
        created env "PROJECT_PATH: \"${PROJECT_PATH}\""
    # 
    local lib="${P[lib]}"
    if [ ! -d "$lib" ]; then
        local search_dirs=( .. ../.. branches ../branches )
        for p in ${search_dirs[@]}; do
            local p="$p/$lib"
            if [ -d "$p" ]; then
                # MinGW (GitBash) requires MSYS setting for symlinks
                # https://www.joshkel.com/2018/01/18/symlinks-in-windows/
                [ "${P[is-win]}" ] && export MSYS="winsymlinks:nativestrict"
                ln -s "$p" "$lib"
                created file ln -s "$p" "$lib"
                break
            fi
        done
        # if 'libs' could not be linked, check-out 'libs' branch from git repository
        [ ! -d "$lib" ] && \
            eval ${P[lib-repo]} "$lib" && \
            created file "${P[lib-repo]}" "$lib"
    fi

    if [ "$1" = "--setup-classpath" ]; then
        setup_classpath
        unset -f setup_classpath classpath_file template trlink
    fi

    [ -z "$JDK_JAVAC_OPTIONS" ] &&
        export JDK_JAVAC_OPTIONS="-Xlint:-options -Xlint:-module -d "${P[classes]}" --module-path \"$MODULEPATH\"" && \
        created env JDK_JAVAC_OPTIONS

    [ -z "$JDK_JAVADOC_OPTIONS" ] && local jdoc="--source-path "${P[src]}" -d "${P[doc-dir]} && \
        jdoc+=" --module-path \"$MODULEPATH\"" && \
        jdoc+=" -version -author -Xdoclint:-missing" && \
        jdoc+=" -noqualifier \"java.*:application.*:datamodel.*:system.*\"" && \
        export JDK_JAVADOC_OPTIONS="$jdoc" && \
        created env JDK_JAVADOC_OPTIONS

    [ -z "$JUNIT_OPTIONS" ] &&
        export JUNIT_OPTIONS="--details-theme=unicode" && \
        created env JUNIT_OPTIONS

    [ -z "$JACOCO_AGENT" ] &&
        agent_jar=$(tr ';' '\n' <<< $JUNIT_CLASSPATH | grep 'jacocoagent.jar') && \
        export JACOCO_AGENT="-javaagent:"$agent_jar"=output=file,destfile="${P[cov-dir]}"/jacoco.exec" && \
        created env JACOCO_AGENT
    # 
    [ "${P[is-zsh]}" ] && \
        trap "echo -ne '\e[m'" DEBUG    # reset formatting after command + ENTER
    # 
    created show
    # 
    return 0
}

function wipe() {
    local env=(PROJECT_PATH CLASSPATH JUNIT_CLASSPATH MODULEPATH \
                JDK_JAVAC_OPTIONS JDK_JAVADOC_OPTIONS JUNIT_OPTIONS JACOCO_AGENT \
    )
    local files=()  # probe files to remove are present
    [ -f .classpath ] && files+=(.classpath)
    [ -f .vscode/.classpath ] && files+=(.vscode/.classpath)
    [ -f .vscode/.modulepath ] && files+=(.vscode/.modulepath)
    [ -f .vscode/.sources ] && files+=(.vscode/.sources)
    [ -f .project ] && files+=(.project)
    # 
    local env2=()
    for e in ${env[@]}; do  # collect var that have been set
        [ "$(eval echo '$'$e)" ] && env2+=($e)
    done
    # 
    [[ "${#env2[@]}" -gt 0 ]] && \
        unset "${env2[@]}" && echo "unset variables: ${env2[@]}"
    # 
    [[ "$1" =~ (-a|--all|-la) ]] && \
        unset -f wipe && \
        rm -rf ${files[@]} && echo "removed files: ${files[@]}" && \
        [[ "$1" =~ (-la) ]] && \
            rm -rf "${P[lib]}" && echo "removed: ${P[lib]}"
}

function command() {
    case "$1" in

    clean) echo rm -rf ${P[target]} ${P[log-dir]} ${P[doc-dir]} ${P[cov-dir]}
        ;;

    compile)
        echo javac $\(find ${P[src]} -name \'*.java\'\) -d ${P[classes]}';'
        [ -d "${P[res]}" ] && command copy-resources
        ;;

    compile-tests) echo javac -cp \"\$JUNIT_CLASSPATH\" $\(find ${P[tests]} -name \'*.java\'\) -d ${P[test-classes]}';'
        [ -d "${P[res]}" ] && command copy-resources
        ;;

    copy-resources) echo copy ${P[res]} ${P[target]}/${P[res]}
        ;;

    run) shift;     # echo "echo java ${P[run]} $*"
        echo java -p \"\$MODULEPATH\" -m ${P[module]}/${P[run]} $*
        ;;

    run-tests) echo java -cp \"\$JUNIT_CLASSPATH\" \\
        echo " " org.junit.platform.console.ConsoleLauncher \$JUNIT_OPTIONS \\
        echo " " --scan-class-path
        ;;

    package|jar)
        echo tar cv ${P[lib]}/'{jackson,logging}*/*' "| tar -C ${P[target]} -xvf - "';'
        echo jar -c -v -f ${P[jar]} '\'
        [ -f ${P[manifest]} ] && \
            echo "     " $(echo -m ${P[manifest]}) '\'
        echo "     " -C ${P[classes]} . ';'
        # echo jar uvf ${P[jar]} -C ${P[target]} ${P[res]}
        ;;

    build)
        echo mk clean compile compile-tests run-tests package javadoc
        ;;

    tests)
        echo mk compile compile-tests run-tests
        ;;

    coverage)
        echo java $\(eval echo \$JACOCO_AGENT\) $\(eval echo \$JUNIT_CLASSPATH\) \\
        echo " " org.junit.platform.console.ConsoleLauncher $\(eval echo \$JUNIT_OPTIONS\) \\
        echo " " --scan-class-path';'
        echo echo coverage events recorded in: ${P[cov-dir]}/jacoco.exec
        ;;

    doc|docs|javadoc)
        echo javadoc -d ${P[doc-dir]} $\(eval echo \$JDK_JAVADOC_OPTIONS\) \\
        echo " " $\(builtin cd ${P[src]}\; find . -type d \| sed -e \'s!/!.!g\' -e \'s/^[.]*//\'\)
        ;;

    esac; return 0
}

function mk() {
    [ "${P[is-zsh]}" ] && trap "" DEBUG     # disable ANSI-codes for 'zsh'
    # 
    local cmds=(); local num=0
    for cmd in "$@"; do
        [ "$(command $cmd)" ] && cmds+=($cmd)
    done
    for cmd in "${cmds[@]}"; do
        shift               # shift $@ to obtain args after last command
        num=$((num + 1))    # detect last command before args[]
        [[ $num -ge ${#cmds[@]} ]] && local args="$@" && local last="true"
        # 
        command $cmd $args          # output command in terminal
        # 
        # line feed, except for last command; print "---" after run
        [ "$cmd" = "run" ] && echo "---" || { [ -z "$last" ] && echo; }
        # 
        eval $(command  $cmd $args | sed -e 's/\\$//' | tr -d '\n')
    done
    [ "${P[is-zsh]}" ] && trap "echo -ne '\e[m'" DEBUG; return 0
}

function show() {
    [ "${P[is-zsh]}" ] && trap "" DEBUG     # disable ANSI-codes for 'zsh'
    # 
    local cmds=(clean compile compile-tests run run-tests build package coverage javadoc)
    [ "$1" ] && local cmds=() && \
        for cmd in "$@"; do
            [ "$(command $cmd)" ] && cmds+=($cmd)
        done
    # 
    local num=0
    for cmd in "${cmds[@]}"; do
        shift               # shift $@ to obtain args after last command
        num=$((num + 1))    # detect last command before args[]
        [[ $num -ge ${#cmds[@]} ]] && local args="$@" && local last="true"
        # 
        echo "$cmd:"
        command $cmd $args | sed -e 's/.*/  &/'     # indent by 2 spaces
        [ -z "$last" ] && echo      # line feed, except for last command
    done
    [ "${P[is-zsh]}" ] && trap "echo -ne '\e[m'" DEBUG; return 0
}

# * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
# Copy content of (resource) directory passed as first argument to directory
# passed as second argumet, except 'META-INF' directory.
# Usage:
#   copy [from_dir] [to_dir]
# @param from_dir source directory from which content is recursively copied
# @param to_dir destination directory
function copy() {
    local from_dir="$1"
    local to_dir="$2"
    if [ -z "$from_dir" ] || [ -z "$to_dir" ]; then
        echo "use: copy <from-dir> <to-dir>"
    else
        [[ ! -d "$to_dir" ]] && mkdir -p $to_dir
        # find $from_dir ! -path '*META-INF*' -type f | xargs cp --parent -t $to_dir
        tar -cpf - -C $from_dir --exclude='META-INF' . | tar xpf - -C $to_dir
    fi
}

function created() {
    case "$1" in
    env)    shift; local args="$@"; P[created-env]+="$args@@" ;;
    file)   shift; local args="$@"; P[created-files]+="$args@@" ;;
    show)
        [ "${P[created-env]}" ] && \
            echo " - created environment variables:" && \
            sed -e 's/@@/\n/g' <<< ${P[created-env]} | sed -e 's/^.*$/    - &/' -e '$d'
        # 
        if [ "${P[created-files]}" ]; then
            echo " - created folders or files:"
            sed -e 's/@@/\n/g' <<< ${P[created-files]} | sed -e 's/^.*$/    - &/' -e '$d'
        fi ;;
    esac
    return 0
}

# 
# ------ setup-classpath: -----------------------------------------------------
# 
# https://stackoverflow.com/questions/51175148/nested-condition-of-if-statement-in-bash
if  [ -z "$CLASSPATH" -o -z "$JUNIT_CLASSPATH" -o -z "$MODULEPATH" -o \
      ! -f .classpath -o ! -f .project ] || \
        { [ -d .vscode ] && \
            [ ! -f .vscode/.classpath -o ! -f .vscode/.modulepath -o ! -f .vscode/.sources ]; }
then
    function setup_classpath() {
        # CLASSPATH seperator is ';' on Windows, any other system it is ':'
        [ "${P[is-win]}" ] && local sep=';' || local sep=':'
        # 
        # absolute path to libs and path with symbolic links resolved
        local libs_path_abs=$(builtin cd "${P[lib]}"; pwd)
        local libs_path_traced=$(trlink "${P[lib]}" "$libs_path_abs")
        # echo "trlink: --> "$(trlink "${P[lib]}" "$libs_path_abs")
        # 
        # jars with abs path, e.g. /cygdrive/c/home/svgr/libs/lombok/lombok-1.18.36.jar
        local jars_abs=($(find "$libs_path_abs" -name '*.jar'))
        local jars=($(find "$libs_path_traced" -name '*.jar'))
        # 
        local cp=""; local jcp=""
        if [ -z "$CLASSPATH" -o -z "$JUNIT_CLASSPATH" ]; then
            local sep2=""; local sep3=""
            for cpe in "${P[classes]}" "${P[test-classes]}" "${P[target]}/${P[res]}" ${jars[@]}; do
                # 
                # # following code does not properly work on MacOS (zsh):
                # # collect entries for CLASSPATH, exclude 'jacoco' jars with test-runner
                # [[ ! "$cpe" =~ (jacoco|test-classes|junit-platform|opentest|apiguardian) ]] && \
                #     cp+="$sep2$cpe" && sep2="$sep"
                # # 
                # # collect entries for JUNIT_CLASSPATH, exclude 'junit' jars
                # [[ ! "$cpe" =~ (/junit/) ]] &&
                #     jcp+="$sep3$cpe" && sep3="$sep"
                # 
                # instead, use case matching to sort CLASSPATH and JUNIT_CLASSPATH entries
                # 
                # echo $CLASSPATH | tr ';' '\n'
                # - bin/classes
                # - bin/resources
                # - libs/jackson/jackson-annotations-2.13.0.jar
                # - libs/jackson/jackson-core-2.13.0.jar
                # - libs/jackson/jackson-databind-2.13.0.jar
                # - libs/junit/junit-jupiter-api-5.9.3.jar
                # - libs/logging/log4j-api-2.23.1.jar
                # - libs/logging/log4j-core-2.23.1.jar
                # - libs/logging/log4j-slf4j2-impl-2.23.1.jar
                # - libs/logging/slf4j-api-2.0.16.jar
                # - libs/lombok/lombok-1.18.36.jar
                # 
                # echo $JUNIT_CLASSPATH | tr ';' '\n'
                # - bin/classes
                # - bin/test-classes
                # - bin/resources
                # - libs/jackson/jackson-annotations-2.13.0.jar
                # - libs/jackson/jackson-core-2.13.0.jar
                # - libs/jackson/jackson-databind-2.13.0.jar
                # - libs/jacoco/jacocoagent.jar
                # - libs/jacoco/jacococli.jar
                # - libs/junit-platform-console-standalone-1.9.2.jar
                # - libs/logging/log4j-api-2.23.1.jar
                # - libs/logging/log4j-core-2.23.1.jar
                # - libs/logging/log4j-slf4j2-impl-2.23.1.jar
                # - libs/logging/slf4j-api-2.0.16.jar
                # - libs/lombok/lombok-1.18.36.jar
                # 
                case "$cpe" in

                "${P[classes]}"|bin/resources|*/jackson/*.jar|*/logging/*.jar|*/lombok/*.jar)
                    # include in both, CLASSPATH and JUNIT_CLASSPATH
                    cp+="$sep2$cpe" && sep2="$sep"
                    jcp+="$sep3$cpe" && sep3="$sep"
                    ;;

                */junit/junit-jupiter-api*.jar)
                    # include in CLASSPATH only
                    cp+="$sep2$cpe" && sep2="$sep" ;;

                "${P[test-classes]}"|*/${P[test-runner]}|*/jacoco/*.jar)
                    # include in JUNIT_CLASSPATH only
                    jcp+="$sep3$cpe" && sep3="$sep" ;;
                esac
            done
        fi
        [ -z "$CLASSPATH" ] && export CLASSPATH="$cp" && created env CLASSPATH
        [ -z "$JUNIT_CLASSPATH" ] && export JUNIT_CLASSPATH="$jcp" && created env JUNIT_CLASSPATH

        if [ -z "$MODULEPATH" ]; then
            local mp=""; local sep2=""
            # cut trailing jar-name leaving dirname for modulepath with 'libs/*' (remove trailing 'libs' entry)
            for mod in "${P[classes]}" $(tr ' ' '\n' <<< ${jars[@]} | sed -e 's|/[^/]*$||' -e '/libs$/d' | uniq); do
                mp+="$sep2$mod"; sep2="$sep"
            done
            export MODULEPATH="$mp"
            created env MODULEPATH
        fi

        # on Windows, use abs path with C:/ for .classpath file
        if [ ! -f .classpath ]; then
            classpath_file $(for jar in ${jars_abs[@]}; do \
                [ "${P[is-win]}" ] && \
                    sed -e '/jacoco\|junit/d' -e 's!^/cygdrive!!' <<< "$jar" | sed -E 's!^/([[:alpha:]])/!\U\1:/!' || \
                    sed -e '/jacoco\|junit/d' <<< "$jar"; \
                done) > .classpath
            # 
            # remove 4 lines following match: 'path="tests"' if no 'tests' folder is present
            [ ! -d "${P[tests]}" ] && sed -e '/path="tests"/,+4d' < .classpath > .c && mv .c .classpath
            # 
            # remove line with 'path="resources"' if no 'resources' folder is present
            [ ! -d "${P[res]}" ] && sed -e '/path="resources"/d' < .classpath > .c && mv .c .classpath
            # 
            created file ".classpath"
        fi

        if [ -d .vscode ]; then
            [ ! -f .vscode/.classpath ] && \
                echo "$CLASSPATH" > .vscode/.classpath && created file ".vscode/.classpath"

            [ ! -f .vscode/.modulepath ] && \
                echo "$MODULEPATH" > .vscode/.modulepath && created file ".vscode/.modulepath"

            [ ! -f .vscode/.sources ] && \
                find ${P[src]} -type f -name '*.java' > .vscode/.sources && created file ".vscode/.sources"
        fi

        [ ! -f .project ] && \
            template .project > .project && created file ".project"
        # 
        return 0
    }

    function classpath_file() {
        # substitute template variables
        template .classpath | sed \
            -e 's!@target!'${P[target]}'!g' \
            -e 's!@classes!'${P[classes]}'!g' \
            -e 's!@test-classes!'${P[test-classes]}'!g' \
            -e 's!@resources!'${P[target]}/${P[res]}'!g'
        # 
        for jar in "$@"; do
            # put absolute paths in .classpath to be effective in VS Code launch.json
            template .classpath-entry | sed -e 's!@jar!'$jar'!g'
        done
        # 
        template .classpath-end
    }

    function template() {
        sed -n '/^# -- '"$1"'$/,/^# --/p' "${P[env-script]}" | \
            sed -e '1d' -e '$d' -e 's/^# //'
    }

    function trlink() {
        # 'realpath' command exists (may not on Mac), alt: $(readlink -e "$1")
        [ -f /usr/bin/realpath ] && \
            local tr=$(realpath "$1") && \
            [ "$tr" ] && realpath -s --relative-to="$(pwd)" "$tr" \
        || echo -n "$2"
    }
    # 
    # invoke setup with functions for setting up CLASSPATH, JUNIT_CLASSPATH, MODULEPATH
    # and files: .project, .classpath, .vscode/.classpath, .vscode/.modulepath, vscode/.sources
    setup --setup-classpath
else
    # skip setting up CLASSPATH and files
    setup
fi

unset -f setup created


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Files extractable by extract() separated by markers: '^# -- filename$' and
# '^# --$'.
# 
# -- .classpath
# <?xml version="1.0" encoding="UTF-8"?>
# <classpath>
#     <classpathentry kind="output" path="@target"/>
#     <classpathentry kind="src" path="src" output="@classes"/>
#     <classpathentry kind="src" path="resources" output="@resources" including="**/*.properties|**/*.yaml|**/*.yml"/>
#     <classpathentry kind="src" path="tests" output="@test-classes">
#         <attributes>
#             <attribute name="test" value="true"/>
#         </attributes>
#     </classpathentry>
#     <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER">
#         <attributes>
#             <attribute name="module" value="true"/>
#         </attributes>
#     </classpathentry>
#     <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/5">
#         <attributes>
#             <attribute name="module" value="true"/>
#         </attributes>
#     </classpathentry>
# --
# -- .classpath-entry
# <classpathentry kind="lib" path="@jar">
#     <attributes>
#         <attribute name="module" value="true"/>
#     </attributes>
# </classpathentry>
# --
# -- .classpath-end
# </classpath>
# --
# -- .project
# <?xml version="1.0" encoding="UTF-8"?>
# <projectDescription>
#     <name>$name</name>
#     <comment></comment>
#     <projects>
#     </projects>
#     <buildSpec>
#         <buildCommand>
#             <name>org.eclipse.jdt.core.javabuilder</name>
#             <arguments></arguments>
#         </buildCommand>
#     </buildSpec>
#     <natures>
#         <nature>org.eclipse.jdt.core.javanature</nature>
#     </natures>
# </projectDescription>
# --

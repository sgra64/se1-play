#!/bin/bash
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# Build project 'se1-play' up to goals:
#  - goal 'base' on branch 'main',
#  - goal 'optionals' on branch 'b1-optionals',
#  - goal 'numbers' on branch 'b2-numbers' and
#  - goal 'streams' on branch 'b3-streams'.
# 
# Goals 'numbers' and 'streams' require 'se1-play-patches.tar' with patches.
#  - usage: build goal
# 
#  - examples: source ../build.sh && build numbers
#              source ../build.sh && build base && build_numbers
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
function build() {
    local goal="$1"
    # 
    build_base          &&  [ "$goal" != "base" ] &&
    build_optionals     &&  [ "$goal" != "optionals" ] &&
    [ -f "$patches_tar" ] &&
    build_numbers       &&  [ "$goal" != "numbers" ] &&
    build_streams || return 0
}

patches_tar=$(
    # return location of patches, e.g. '../.patches/se1-play-patches.tar'
    # patches are not needed to build steps: 'build_base' and 'build_optionals'
    local name="se1-play"
    for p in . .. ../.. ../../.. ../../../.. ; do
        local tarfile=$(ls $p/.patches/$name*.tar 2>/dev/null | head -n 1)
        [ -f "$tarfile" ] && echo "$tarfile" && return 0
    done
)
remote="se1-repo"
remote_url="https://github.com/sgra64/se1-play.git"
remote_raw="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main"


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# build goal 'streams' on branch: 'b3-streams'
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# 
function build_streams() {
    local branch="b3-streams"

    [ -z "$patches_tar" ] && echo "no file 'se1-play-patches.tar' found, aborting" && return 1

    # [ "$(branch)" != "$branch" ] && {
    #     exists branch "$branch" &&
    #         exec git switch "$branch" || exec git switch -c "$branch" base
    # }
    gswitch -c "$branch" base

    return 0
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# build goal 'numbers' on branch: 'b2-numbers'
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# 
function build_numbers() {
    local branch="b2-numbers"

    [ -z "$patches_tar" ] && echo "no file 'se1-play-patches.tar' found, aborting" && return 1

    # [ "$(branch)" != "$branch" ] && {
    #     exists branch "$branch" &&
    #         exec git switch "$branch" || exec git switch -c "$branch" base
    # }
    gswitch -c "$branch" base

    ! exists remote "$remote" &&
        exec git remote add "$remote" "$remote_url"

    local msg="add package 'numbers' with interface 'Numbers' and class 'NumbersRunner'"
    ! exists commit "$msg" && {
        # 
        ! exists remote-branch "$remote/$branch" && local verbose="-v"
        exec $verbose git fetch "$remote" "$branch"

        exec git checkout "$remote/$branch" -- \
            src/main/numbers/Numbers.java \
            src/main/numbers/NumbersRunner.java \
            src/resources/application.properties

        # 
        # patch file 'src/main/module-info.java' to open the new package 'numbers'
        exec -v apply_patch "patch_numbers_moduleinfo" <<< \
"--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -15,4 +15,5 @@ module se1_play {
     /* Open package to JUnit test runner and the javadoc compiler. */
     opens application;
+    opens numbers;

     /*"
        exec git add src/main src/resources && exec git commit -m "\"$msg\""
    }

    msg="sum() methods complete" && ! exists commit "$msg" && {
        exec git checkout "$remote/$branch" -- src/main/numbers/NumbersImpl.java \
            src/tests/numbers/TestData.java \
            src/tests/numbers/Numbers_1_sum_Tests.java \
            src/tests/numbers/Numbers_2_sum_positive_even_Tests.java \
            src/tests/numbers/Numbers_3_sum_recursion_Tests.java
        # 
        # patch implementations of methods: sumPositiveEvenNumbers() and sumRecursive()
        exec -v apply_patch -f "NumbersImpl-1-sum.patch" <<< \
            $(tar xvf $patches_tar NumbersImpl-1-sum.patch -O)

        exec git add src/main src/tests && exec git commit -m "\"$msg\""
    }

    msg="find() methods complete" && ! exists commit "$msg" && {
        exec git checkout "$remote/$branch" -- src/tests/numbers/Matchers.java \
            src/tests/numbers/Numbers_4_find_first_Tests.java \
            src/tests/numbers/Numbers_5_find_last_Tests.java \
            src/tests/numbers/Numbers_6_find_all_Tests.java
        # 
        # patch implementations of methods: sumPositiveEvenNumbers() and sumRecursive()
        exec -v apply_patch -f "NumbersImpl-2-find.patch" <<< \
            $(tar xvf $patches_tar NumbersImpl-2-find.patch -O)

        exec git add src/main src/tests && exec git commit -m "\"$msg\""
    }

    msg="findSums() complete" && ! exists commit "$msg" && {
        exec git checkout "$remote/$branch" -- \
            src/tests/numbers/Numbers_7a_find_sums_Tests.java \
            src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
        # 
        # patch implementations of methods: sumPositiveEvenNumbers() and sumRecursive()
        exec -v apply_patch -f "NumbersImpl-3-findSums.patch" <<< \
            $(tar xvf $patches_tar NumbersImpl-3-findSums.patch -O)

        exec git add src/main src/tests && exec git commit -m "\"$msg\""
    }

    msg="findAllSums() complete" && ! exists commit "$msg" && {
        exec git checkout "$remote/$branch" -- \
            src/tests/numbers/Numbers_8a_find_all_sums_Tests.java \
            src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
        # 
        # patch implementations of methods: sumPositiveEvenNumbers() and sumRecursive()
        exec -v apply_patch "NumbersImpl-4-findAllSums.patch" <<< \
            $(tar xvf $patches_tar NumbersImpl-4-findAllSums.patch -O)
        # 
        exec tar -xf $patches_tar -C src/main/numbers NumbersImpl_FindAllSums.java

        exec git add src/main src/tests && exec git commit -m "\"$msg\""
    }
    return 0
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# build goal 'optionals' on branch: 'b1-optionals'
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# 
function build_optionals() {
    local branch="b1-optionals"

    # [ "$(branch)" != "$branch" ] && {
    #     exists branch "$branch" &&
    #         exec git switch "$branch" || exec git switch -c "$branch" base
    # }
    gswitch -c "$branch" base

    local msg="add package 'optionals'"
    ! exists commit "$msg" && {
        # 
        exec curl --create-dirs -o src/main/optionals/OptionalsRunner.java \
                        $remote_raw/src/main/optionals/OptionalsRunner.java
        # 
        # patch file 'src/main/module-info.java' to open the new package 'optionals'
        exec -v apply_patch "patch_optionals_moduleinfo" <<< \
"--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -15,4 +15,5 @@ module se1_play {
     /* Open package to JUnit test runner and the javadoc compiler. */
     opens application;
+    opens optionals;

     /*"

        # patch file 'src/main/optionals/OptionalsRunner.java' to handle non-present articles
        exec -v apply_patch "patch_optionals_optionalsrunner" <<< \
"--- a/src/main/optionals/OptionalsRunner.java
+++ b/src/main/optionals/OptionalsRunner.java
@@ -57,5 +57,5 @@ public class OptionalsRunner implements Runner {
-            lookupBuggy(article);
+            // lookupBuggy(article);
             // lookupFixedOldStyle(article);
             // lookupOptional(article);
-            // lookupOptionalFunctional(article);
+            lookupOptionalFunctional(article);
         }"

        exec git add src/main && exec git commit -m "\"$msg\""
    }
    return 0
}


# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# build goal 'base' on branch: 'main'
# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
# 
function build_base() {
    # 
    local msg="root commit (empty)"

    [ ! -d .git ] && {
        exec git init --initial-branch=main
        exec git commit --allow-empty -m "\"$msg\""
        exec git tag root
    }

    msg="add .gitignore" && ! exists commit "$msg" && {
        exec curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore
        exec git add -f .gitignore && exec git commit -m "\"$msg\""
    }

    # import modules: '.env', '.vscode', 'libs' to branch 'git-modules'
    # local branch="git-modules" && [ "$(branch)" != "$branch" ] && {
    #     exists branch "$branch" &&
    #         exec git switch "$branch" || exec git switch -c "$branch"
    # }
    local branch="git-modules" && gswitch -c "$branch"

    # on branch 'git-modules', import modules '.env', '.vscode', 'libs'
    msg="git submodule: .env" && ! exists commit "$msg" && {
        exec -v git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
        exec git rm --cached .gitmodules
        exec git commit -m "\"$msg\""
    } || exec -v git submodule update --remote --merge .env
    # 
    msg="git submodule: .vscode" && ! exists commit "$msg" && {
        exec -v git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode
        exec git rm --cached .gitmodules
        exec git commit -m "\"$msg\""
    } || exec -v git submodule update --remote --merge .vscode
    # 
    msg="git submodule: libs" && ! exists commit "$msg" && {
        exec -v git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars.git libs
        exec git rm --cached .gitmodules
        exec git commit -m "\"$msg\""
    } || exec -v git submodule update --remote --merge libs
    # 
    # commit possible updates causing dirty state
    local status=$(git status --porcelain)
    if [ "$status" ]; then
        exec echo "commit submodule updates: $status"
        exec git add . && exec git commit -m "\"update submodules, $status\""
    fi
    # 
    # switch back to 'main' branch
    exec git switch main

    msg="add src/main" && ! exists commit "$msg" && {
        exec mkdir -p src/main/application
        exec curl -o src/main/module-info.java $remote_raw/src/main/module-info.java
        exec curl -o src/main/application/Application.java $remote_raw/src/main/application/Application.java
        exec curl -o src/main/application/package-info.java $remote_raw/src/main/application/package-info.java
        # 
        exec git add src/main && exec git commit -m "\"$msg\""
    }

    msg="add unit tests src/tests" && ! exists commit "$msg" && {
        exec mkdir -p src/tests/application
        exec curl -o src/tests/application/Application_0_always_pass_Tests.java \
                $remote_raw/src/tests/application/Application_0_always_pass_Tests.java
        # 
        exec -v apply_patch "patch_moduleinfo_junit" <<< $(patch_moduleinfo_junit)
        exec git add src/main/module-info.java src/tests && exec git commit -m "\"$msg\""
    }

    msg="update package-info.java, javadoc @author" && ! exists commit "$msg" && {
        # patch file 'src/main/application/package-info.java' to adjust @author line
        exec -v apply_patch "patch_packageinfo_author" <<< $(patch_packageinfo_author)
        exec git add src/main/application/package-info.java && exec git commit -m "\"$msg\""
    }

    msg="add src/resources/META-INF/MANIFEST.MF, jar packaging" && ! exists commit "$msg" && {
        exec curl --create-dirs -o src/resources/META-INF/MANIFEST.MF $remote_raw/src/resources/META-INF/MANIFEST.MF
        exec git add src/resources && exec git commit -m "\"$msg\""
    }

    msg="require module 'runtime-SE' in module-info.java" && ! exists commit "$msg" && {
        exec curl -o src/main/application/Application.java $remote_raw/src/main/application/Application-runtime-java
        exec curl -o src/resources/application.properties $remote_raw/src/resources/application.properties
        exec -v apply_patch "patch_moduleinfo_runtimeSE" <<< $(patch_moduleinfo_runtimeSE)
        # 
        exec git add src/main src/resources && exec git commit -m "\"$msg\""
        exec git tag base
    }
    return 0
}

# patch file 'src/main/module-info.java' to require module 'org.junit.jupiter.api'
function patch_moduleinfo_junit() {
    echo "--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -18,6 +18,6 @@ module se1_play {
     /*
      * External modules required by this module.
      */
-    // requires org.junit.jupiter.api;
+    requires org.junit.jupiter.api;
     // requires transitive runtimeSE;
 }"
}

# patch file 'src/main/application/package-info.java' to adjust author name
function patch_packageinfo_author() {
    # 
    # mind \"...\" in patch and cut-off line endings
    # 
    echo "--- a/src/main/application/package-info.java
+++ b/src/main/application/package-info.java
@@ -16,6 +16,6 @@ class package_info {
     /**
      * Author attribute to appear in javadoc.
      */
-    static final String Author = \"sgraupner\";           // <-- adjust with your name
+    static final String Author = \"sgraupner\";

     /**"
}

# patch file 'src/main/module-info.java' to require module 'runtimeSE'
function patch_moduleinfo_runtimeSE() {
    echo "--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -19,5 +19,5 @@ module se1_play {
      * External modules required by this module.
      */
     requires org.junit.jupiter.api;
-    // requires transitive runtimeSE;
+    requires transitive runtimeSE;
 }"
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

# Apply patch passed through stdin.
# - usage: apply_patch name <<< patch
function apply_patch() {
    local name="$1"
    git apply --whitespace=nowarn &&
        echo "   --- patch '"$name"' successfully applied" ||
        echo "   *** ERROR: patch '"$name"' failed"
}

# Return 0 (true) is a string exists as a git remote, branch, remote-branch,
# commit message or tag.
# - usage: exists string
function exists() {
    case "$1" in
    "remote")   [ "$(git config --get "remote."$2".url")" ] && return 0 ;;
    "branch")   [ "$(git rev-parse --verify --quiet "$2")" ] && return 0 ;;
    "remote-branch") [ "$(git rev-parse --verify --quiet "$2")" ] && return 0 ;;
    "commit")   [ "$(git log --grep="$2" -n 1 --format=%H)" ] && return 0 ;;
    "tag")      [ "$( git tag -l "$2")" ] && return 0 ;;
    esac
    return 1
}

# Output the current branch.
function branch() {
    git rev-parse --abbrev-ref HEAD
}

# Test branch exists and switch to branch. Branch is created with '-c' off
# a given base (if provided).
# - usage: gswitch [-c] branch base
function gswitch() {
    case "$1" in
    "-c") shift && local create=true ;;     # -c create branch if not exits
    "--help"|"-h") echo "usage: gswitch [-c] branch"; return 0 ;;
    esac
    local branch="$1"; local base="$2"
    # 
    if [ "$(branch)" != "$branch" ]; then
        ! exists branch "$branch" && [ "$create" ] &&
            exec git switch -c "$branch" "$base" ||
            exec git switch "$branch"
    fi
}

# Log and execute command passed with arguments. Commands are logged to the
# terminal by default, unless '-q' (quiet) is used. Command stdout and stderr
# are suppressed by default, unless '-v' (verbose) is used.
# - usage: exec [-v,-q] command args
function exec() {
    case "$1" in
    "-q") shift && local quiet=true ;;      # -q quiet mode, suppress output
    "-v") shift && local verbose=true ;;    # -v verbose show output of the command
    "-qv"|"-vq") shift && local quiet=true && local verbose=true ;;
    "--help"|"-h") echo "usage: exec [-v,-q] command args"; return 0 ;;
    esac
    [ -z "$quiet" ] && local cmd=$@ && echo " -" ${cmd:0:76}    # limit to one line
    [ "$verbose" ] && eval $@ || eval $@ >/dev/null 2>&1
}

# - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

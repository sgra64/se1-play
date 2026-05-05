#!/bin/bash
# commands to build the 'se1-play' project

function build() {
    git init --initial-branch=main
    git commit --allow-empty -m "root commit (empty)"
    git tag root

    # download '.gitignore' and commit
    curl -o .gitignore https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main/.gitignore
    git add -f .gitignore
    git commit -m "add .gitignore"

    # import modules: '.env', '.vscode', 'libs' to branch 'git-modules', 
    git switch -c git-modules
    git submodule add -f -- https://github.com/sgra64/gitmodule-env.sh.git .env
    git rm --cached .gitmodules && git commit -m "git submodule: .env"

    git submodule add -f -- https://github.com/sgra64/gitmodule-vscode-java.git .vscode
    git rm --cached .gitmodules && git commit -m "git submodule: .vscode"

    git submodule add -f -- https://github.com/sgra64/gitmodule-libs-jars.git libs
    git rm --cached .gitmodules && git commit -m "git submodule: libs"

    git switch main

    # add 'src/main'
    mkdir -p src/main/application
    url="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/main"
    curl -o src/main/module-info.java $url/src/main/module-info.java
    curl -o src/main/application/Application.java $url/src/main/application/Application.java
    curl -o src/main/application/package-info.java $url/src/main/application/package-info.java

    git add src/main
    git commit -m "add src/main"

    # add 'src/tests'
    mkdir -p src/tests/application
    curl -o src/tests/application/Application_0_always_pass_Tests.java \
                $url/src/tests/application/Application_0_always_pass_Tests.java
    # 
    # patch file 'module-info.java' removing the comment from line
    apply_patch patch_moduleinfo_junit

    git add src/main/module-info.java src/tests
    git commit -m "add unit tests src/tests"

    # patch file 'src/main/application/package-info.java' to adjust @author line
    apply_patch patch_packageinfo_author

    git add src/main/application/package-info.java
    git commit -m "update application/package-info.java, javadoc @author"

    # add 'src/resources/META-INF/MANIFEST.MF' for jar packaging
    curl --create-dirs -o src/resources/META-INF/MANIFEST.MF $url/src/resources/META-INF/MANIFEST.MF

    git add src/resources
    git commit -m "add src/resources/META-INF/MANIFEST.MF, jar packaging"

    # update 'src/main/module-info.java' for runtime-SE
    curl -o src/main/application/Application.java $url/src/main/application/Application-runtime-java
    curl -o src/resources/application.properties $url/src/resources/application.properties
    # 
    apply_patch patch_moduleinfo_runtimeSE

    git add src/main src/resources
    git commit -m "update module-info.java to require module 'runtime-SE'"

    # add package 'optionals'
    # mkdir -p src/main/optionals
    curl --create-dirs -o src/main/optionals/OptionalsRunner.java $url/src/main/optionals/OptionalsRunner.java
    # 
    # patch file 'src/main/module-info.java' to export new package 'optionals'
    apply_patch patch_moduleinfo_export_optionals
    # 
    # patch file 'src/main/optionals/OptionalsRunner.java' to handle non-present articles
    apply_patch patch_OptionalsRunner
    # 
    git add src/main
    git commit -m "add package 'optionals'"
}

function apply_patch() {
    # patch -p1 ... or: git apply <patch> ...
    local patch=$1
    git apply <<< $($patch) &&
        echo "*** patch '"$patch"' successfully applied" ||
        echo "*** error: patch '"$patch"' failed"
}


# patch file 'src/main/module-info.java' to require module 'org.junit.jupiter.api'
# - remove comments
function patch_moduleinfo_junit() {
    echo "diff --git a/src/main/module-info.java b/src/main/module-info.java
--- a/src/main/module-info.java
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
# - remove comment at the end of line
function patch_packageinfo_author() {
    # mind \"...\" in patch and cut-off line endings
    echo "diff --git a/src/main/application/package-info.java b/src/main/application/package-info.java
--- a/src/main/application/package-info.java
+++ b/src/main/application/package-info.java
@@ -16,7 +16,7 @@ class package_info {
     /**
      * Author attribute to appear in javadoc.
      */
-    static final String Author = \"sgraupner\";           // <-- adjust with your name
+    static final String Author = \"sgraupner\";

     /**
      * Version attribute to appear in javadoc."
}

# patch file 'src/main/module-info.java' to require module 'runtimeSE'
function patch_moduleinfo_runtimeSE() {
    echo "diff --git a/src/main/module-info.java b/src/main/module-info.java
--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -19,5 +19,5 @@ module se1_play {
      * External modules required by this module.
      */
     requires org.junit.jupiter.api;
-    // requires transitive runtimeSE;
+    requires transitive runtimeSE;
 }"
}

# index 5b97258..e82a8a0 100644

# patch file 'src/main/module-info.java' to export new package 'optionals'
function patch_moduleinfo_export_optionals() {
    echo "diff --git a/src/main/module-info.java b/src/main/module-info.java
--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -11,6 +11,7 @@ module se1_play {
      * and runtime (use <i>open</i> for compile-time access only).
      */
     exports application;
+    exports optionals;

     /* Open package to JUnit test runner and the javadoc compiler. */
     opens application;"
}

# patch file 'src/main/optionals/OptionalsRunner.java' to handle non-present articles
function patch_OptionalsRunner() {
    echo "diff --git a/src/main/optionals/OptionalsRunner.java b/src/main/optionals/OptionalsRunner.java
--- a/src/main/optionals/OptionalsRunner.java
+++ b/src/main/optionals/OptionalsRunner.java
@@ -49,10 +49,10 @@ public class OptionalsRunner implements Runner {
     public void run(RuntimeSE runtime, String[] args) {
         // 
         for(String article : args) {
-            lookupBuggy(article);
+            // lookupBuggy(article);
             // lookupFixedOldStyle(article);
             // lookupOptional(article);
-            // lookupOptionalFunctional(article);
+            lookupOptionalFunctional(article);
         }
     }
 
"
}

# git log --graph --oneline --all --decorate
# 
# * 47108ca (HEAD -> main) add package 'optionals'
# * 9f99972 update module-info.java to require module 'runtime-SE'
# * 2edf88e add src/resources/META-INF/MANIFEST.MF, jar packaging
# * d0f01bc update application/package-info.java, javadoc @author
# * 18efb23 add unit tests src/tests
# * 699b617 add src/main
# |
# | * eef1e08 (git-modules) git submodule: libs
# | * 54321be git submodule: .vscode
# | * 2521bb2 git submodule: .env
# |/
# * 80ea5a8 add .gitignore
# * 000857e (tag: root) root commit (empty)

# git config alias.alog "log --all --decorate --oneline --graph"
# git config alias.alog "log --decorate --oneline --graph"


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

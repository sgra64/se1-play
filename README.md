<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B1 (SE-2)
-->
# B1: *se1-play*, branch: *b1-numbers*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

The assignment demonstrates *git* branches to isolate the development work of
this assignment from the previous parts of the [*se1-play*](../../tree/main)
project.

- The assignment also demonstrates Java *interfaces* and *implementation
    classes* and the use of the
    [*Singleton Pattern*](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples).

- Challenges from a catalog of
    [*coding interview*](https://github.com/jwasham/coding-interview-university#interview-prep-books)
    questions are presented.

<!-- &nbsp; -->

Steps:

1. [Create a new Branch: *b1-numbers*](#1-create-a-new-branch-b1-numbers)

1. [Supplement Content to the Branch](#2-supplement-content-to-the-branch)

1. [*Build* and *Run* the Project](#3-build-and-run-the-project)

1. [Implement: *sum()*](#4-implement-sum)

1. [Create *JUnit*-Tests for *sum()*](#5-create-junit-tests-for-sum)

1. [Implement more *Numbers*-Functions:](#6-implement-more-numbers-functions) (with tests):
    - `sum_positive_even_numbers(numbers[])`, [link](#2-sum_positive_even_numbers).
    - `sum_recursive(numbers[], int i)`, [link](#3-sum_recursive).
    - `findFirst(numbers[], int x)`, [link](#4-findfirst).
    - `findLast(numbers[], int x)`, [link](#5-findlast).
    - `findAll(numbers[], int x)`, [link](#6-findall).
    - `findSums(numbers[], int sum)`, [link](#7-findsums).
    - `findAllSums(numbers[], int sum)`, [link](#8-findallsums).
    - `findAllSums(), XXL`, [link](#9-findallsums-xxl).

1. [Final Result](#7-final-result)

Code and *JUnit-tests* must work in both environments, in the IDE and in the terminal.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Create a new Branch: *b1-numbers*

The main reason to use *branches* is to separate developments in a repository.

There are currently six commits on the `main` branch from the
[*previous assignment (A1)*](../../tree/main):

```sh
git log --oneline                   # show current commits on branch 'main'
```
```
79a79e3 (HEAD -> main) add src/tests, ...       <-- commit 6
5b9376a add src/resources                       <-- commit 5
cd172e6 add src/main                            <-- commit 4
57cb976 add .gitmodules                         <-- commit 3
4681caa add .gitignore                          <-- commit 2 (.gitignore)
ab7b126 (tag: root) root commit (empty)         <-- commit 1 (empty root commit)
```

The commit history (commit graph) is visualized:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-1.png" width="600"/>

The first commit was tagged as *"root"*. It is an empty commit with no
content. Project content was added with the following commits.

In order to isolate the development of this assignment (*"b1-numbers"*)
from the prior and from other later developments, a new branch `b1-bumbers`
will be used for this assignment:

```sh
git tag base                    # tag commit #6 as "base"

git switch -c b1-numbers        # create new branch 'b1-numbers' off the "base" commit
                                # and switch branch, '-c' creates a new branch
```

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-2.png" width="600"/>

A simple description is to see a
[*branch as simply a pointer*](https://stackoverflow.com/questions/34921623/git-branches-what-is-branch)
at which a commit chain continues.

The `HEAD`-pointer is another pointer that indicates to which commit the
project directory (the *"working tree"*) is *synchronized*, which refers
to the files and directories you see in the project. Changes made to the
*working tree* are reported with command: `git status`.

Show the current branch:

```sh
git branch                      # show branches, (*) marks the current branch
```
```
* b1-numbers                    <-- active branch (*)
  git-modules
  main
```

Branch `b1-numbers` is the *active branch* (also shown in green), which means
it will receive forthcoming commits. These commits will not impact commits of
the `main` branch.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Supplement Content to the Branch

The project directory of branch `b1-numbers` is supplemented with new content:

```sh
<se1-play>              # project directory
 | ...
 |
 # content from remote branch: 'b1-numbers' with new package 'numbers'
 +-<src>
 |  +-<main>                    # Java source code
 |  |  +--module-info.java          # description of module 'se1.play'
 |  |  |
 |  |  +-<application>              # existing package 'application'
 |  |  |  +--Application.java       # program with main()-method
 |  |  |  +--Runner.java            # new interface
 |  |  |  +--...
 |  |  |
 |  |  +-<numbers>                  # new package 'numbers' from remote branch 'b1-numbers'
 |  |     +--Numbers.java           # interface with methods to implement
 |  |     +--NumbersData.java       # numbers data sets
 |  |     +--NumbersRunner.java     # driver code to perform calculations from command line
 |  |     +--package-info.java      # package information
 |  |   
 |  +-<tests>                   # JUnit tests for 'b1-numbers'
 |  |  +-<numbers>
 |  |    +--Matchers.java
 |  |    +--Numbers_1_sum_Tests.java
 |  |    +--Numbers_2_sum_positive_even_Tests.java
 |  |    +--Numbers_3_sum_recursion_Tests.java
 |  |    +--Numbers_4_find_first_Tests.java
 |  |    +--Numbers_5_find_last_Tests.java
 |  |    +--Numbers_6_find_all_Tests.java
 |  |    +--Numbers_7a_find_sums_Tests.java
 |  |    +--Numbers_7b_find_sums_duplicates_Tests.java
 |  |    +--Numbers_8a_find_all_sums_Tests.java
 |  |    +--Numbers_8b_find_all_sums_XL_Tests.java
 |  |
 |  +-<resources>               # none-Java sources, properties files
 |     +--application.properties    # application configuration
 |     +--log4j2.properties         # logger configuration
 |     +-<META-INF>
 |        +--MANIFEST.MF            # packaging information for created .jar
```

We fetch content of the branch from an external repository under the name:
*se1-repo*:

```sh
# set remote repository URL under name: 'se1-remote-repo'
git remote add se1-repo https://github.com/sgra64/se1-play.git

git remote -v                       # show name and URL of the new remote

# fetch branch 'b1-numbers' from the remote repository
git fetch se1-repo b1-numbers
```

Content is fetched from the remote repository, but will not be visible in the
working tree (project directory) right away.

```
remote: Enumerating objects: 15, done.
remote: Counting objects: 100% (3/3), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 15 (delta 0), reused 0 (delta 0), pack-reused 12 (from 1)
Unpacking objects: 100% (15/15), 17.39 KiB | 214.00 KiB/s, done.
From https://github.com/sgra64/se1-play
 * branch            b1-numbers -> FETCH_HEAD
 * [new branch]      b1-numbers -> se1-repo/b1-numbers
```

Merge the arrived content into branch *b1-numbers*:

```sh
git branch                          # make sure you are on branch 'b1-numbers'

git status                          # make sure you have a clean working tree

# merge fetched branch 'se1-repo/b1-numbers' into local branch 'b1-numbers'
# accept different git roots, squash incoming commits into one and choose
# incoming content ("theirs") to auto-resolve merge conflicts
git merge se1-repo/b1-numbers \
    --allow-unrelated-histories \
    --squash \
    --strategy-option theirs
```
```
Auto-merging src/main/application/Application.java      <--
Auto-merging src/main/module-info.java                  <--
Squash commit -- not updating HEAD
Automatic merge went well; stopped before committing as requested
```

The files have been auto-merged: *Application.java* and *module-info.java*.

Find out the meanings of flags: `--allow-unrelated-histories`, `--squash` and
`--strategy-option theirs`.

Show the newly arrived content:

```sh
# the merge is open (uncommitted) at this stage, but new content has arrived in
# the working tree (project directory)

git status                      # show status of open merge with new content
                                # incoming from branch 'se1-repo/b1-numbers'
```
```
On branch b1-numbers
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   README.md
        modified:   src/main/application/Application.java   <-- file modified by merge
        new file:   src/main/application/Runner.java
        modified:   src/main/module-info.java               <-- file modified by merge
        new file:   src/main/numbers/Numbers.java
        new file:   src/main/numbers/NumbersData.java
        new file:   src/main/numbers/NumbersRunner.java
```

Remove unwanted content:

```sh
rm README.md && git rm README.md    # remove file 'README.md'

git status                          # show new content from branch 'se1-repo/b1-numbers'
```
File *README.md* is gone.

Commit and close the *"open merge"*:

```sh
git commit -m "merge commit se1-repo/b1-numbers"

git log --oneline               # show commit log
```
```
546c826 (HEAD -> b1-numbers) merge commit se1-repo/b1-numbers   <-- new merge commit on branch 'b1-numbers'
79a79e3 (main) add src/tests, update src/main/module-info.java  <-- branch 'main'
5b9376a add src/resources
cd172e6 add src/main
57cb976 add .gitmodules
4681caa add .gitignore
ab7b126 (tag: root) root commit (empty)
```

After the merge, the fetched remote branch can be removed:

```sh
git branch -rd se1-repo/b1-numbers      # remove fetched remote branch
```
```
Deleted remote-tracking branch se1-repo/b1-numbers (was 4ece164).
```

&nbsp;

Verify new content under *src*:

```sh
find src                        # show content under 'src'
```
```
src
src/main
src/main/application
src/main/application/Application.java       <-- modified file
src/main/application/package-info.java
src/main/application/Runner.java            <-- new file
src/main/module-info.java                   <-- modified file
src/main/numbers                        <-- new package
src/main/numbers/Numbers.java               <-- new file
src/main/numbers/NumbersData.java           <-- new file
src/main/numbers/NumbersRunner.java         <-- new file
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
```

Inspect the modification in file `src/main/application/Application.java` caused
by the incoming merge:

```sh
# show modifications in file 'Application.java' by incoming changes
git diff HEAD~1..HEAD -- src/main/application/Application.java
```

The *git diff* command shows lines in red that were removed from the original file
`Application.java` and new lines added during the merge in green:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-diff-merge-Application.png" width="520"/>


&nbsp;

Changes show new classes from the new `numbers` package in the *main()* - function:

```java
Numbers numbers = Numbers.getInstance();
Runner runner = Numbers.createRunner(numbers);
runner.run(args);
```

Inspect the modification in file `src/main/module-info.java` caused
by the incoming merge:

```sh
# show modifications in file 'Application.java' by incoming changes
git diff HEAD~1..HEAD -- src/main/module-info.java
```

The *"diff"* shows that one line: *"opens numbers"* was inserted by the
incoming merge:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-diff-merge-module-info.png" width="520"/>

<!-- 
```
diff --git a/src/main/module-info.java b/src/main/module-info.java
index 107cc64..a41004b 100644
--- a/src/main/module-info.java
+++ b/src/main/module-info.java
@@ -26,6 +26,7 @@ module se1_play {

     /* Open package to JUnit test runner and Javadoc compiler. */
     opens application;
+    opens numbers;             <-- new line inserted by the incoming merge

     /*
      * External module required by this module (JUnit-5 module for JUnit testing).
```
-->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. *Build* and *Run* the Project

*Source* and *Build* the project (no compile errors should appear):

```sh
source .env/env.sh                  # source the project (if not yet)

mk clean compile compile-tests      # re-build sources and tests
```

Run tests (should pass):

```sh
mk run-tests                        # run JUnit tests (only Application_0_always_pass_Tests)
```

Run the program:

```sh
mk run                              # running the program fails
```
```
run:
  java -p $MODULEPATH -m "se1.play/application.Application"
---
Hello, se1.play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Numbers'. Create an implementation class
and return.
        at se1.play/numbers.Numbers.getInstance(Numbers.java:101)
        at se1.play/application.Application.main(Application.java:28)
```

The reason is an un-implemented method in the interface
[*src/main/numbers/Numbers.java*](src/main/numbers/Numbers.java):

```java
package numbers;

import java.util.List;
import java.util.Set;

import application.Runner;

/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate the sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    long sum(int[] numbers);

    /*
     * Further methods to insert:
     * 
     * Aufgabe 2.) Calculate the sum of positive even numbers[].
     * - long sum_positive_even_numbers(int[] numbers);
     * 
     * Aufgabe 3.) Calculate the sum of numbers[] recursively without using loops
     * - long sum_recursive(int[] numbers, int i);
     * 
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * - int findFirst(int[] numbers, int x);
     * 
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * - int findLast(int[] numbers, int x);
     * 
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * - List<Integer> findAll(int[] numbers, int x);
     * 
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] matching a + b = sum.
     * - Set<Pair> findSums(int[] numbers, int sum);
     * 
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * - Set<Set<Integer>> findAllSums(int[] numbers, int sum);
     */

    /**
     * Static getter that returns an instance that implements the {@link Numbers}
     * interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Numbers'. Create an implementation class and return.");
    }

    /**
     * Factory method that creates an instance of the {@link Runner} interface.
     * @param numbers instance of the {@link Numbers} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Numbers numbers) {
        return new NumbersRunner(numbers);
    }
}
```

Resolve the problem by implementing a new class `NumbersImpl.java` in package
`numbers` according to the
[*"lazy" Singleton Pattern*](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)
that also implements the
[*Numbers*](src/main/numbers/Numbers.java) interface.

Change the *getInstance()* - method in `Numbers.java` to no longer throw
the *UnsupportedOperationException* and instead return the reference of the
*NumbersImpl* singleton instance.

Mind that class `NumbersImpl.java` should not be public.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. Implement: *Sum()*

Class [*NumbersData.java*](src/main/numbers/NumbersData.java) defines numbers
data sets named: *numb*, *numb_1*, *numb_2* and *numb_3*:

<!-- @@ src.main.numbers.NumbersData.java @BEGIN -->
```java
package numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Non-public class with numbers data accessible by name via the
 * {@link Map} interface.
 */
class NumbersData extends HashMap<String, List<Integer>> {

    /*
     * Numbers with negative numbers and duplicates.
     */
    static final List<Integer> numb = Arrays.asList(-2, 4, 9, 4, -3, 4, 9, 5);

    /*
     * Numbers with no negative numbers and no duplicates.
     */
    static final List<Integer> numb_1 = Arrays.asList(8, 10, 7, 2, 14, 5, 4);

    /*
     * Larger set of 24 numbers, no negatives, no duplicates.
     */
    static final List<Integer> numb_2 = Arrays.asList(   // 24 numbers
        371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
         27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
        125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
    ); // add more numbers to find more solutions

    /*
     * Even larger set of 63 numbers, no negatives, no duplicates.
     */
    static final List<Integer> numb_3 = Arrays.asList(
        799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
       1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
       1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
       4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
       2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
       3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
       1220, 3656,  500
    );

    /**
     * Constructor stores names with data in {@link Map}.
     */
    NumbersData() {
        super.put("numb", numb);
        super.put("numb_1", numb_1);
        super.put("numb_2", numb_2);
        super.put("numb_3", numb_3);
    }

    /**
     * Retrieve data by name and return as {@code int[]}.
     * Return empty array (not null) if name is not found.
     * @param key name of data to return
     * @return data set as {@code int[]}
     */
    public int[] getArr(String key) {
        return (Optional.ofNullable(get(key))
            .orElse(List.of()))
                .stream().mapToInt(i->i).toArray(); // convert to int[]
    }
}
```
<!-- @@ src.main.numbers.NumbersData.java @END -->

Implement method `long sum(int[] numbers)` in class *NumbersImpl.java*
and run the code:

```sh
mk compile                          # re-compile the code

mk run sum numb                     # run the program for sum() with data array 'numb'
```
```
Hello, se1.play (modular)
 - sum(numb) -> 30                  <-- method sum() returns 30 for data array 'numb'
```

Run the progra

```sh
mk run sum numb \
    sum numb_1 \
    sum numb_2 \
    sum numb_3
```
```
Hello, se1.play (modular)
 - sum(numb) -> 30
 - sum(numb_1) -> 50
 - sum(numb_2) -> 10984
 - sum(numb_3) -> 141466
```
<!-- 
Commit this state of development on branch `b1-branch`:

```sh
git status                          # working tree is "dirty"
```
```
On branch b1-numbers
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
        modified:   src/numbers/Numbers.java

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        src/numbers/NumbersImpl.java

no changes added to commit (use "git add" and/or "git commit -a")
```

Show changes in file `src/numbers/Numbers.java`:

```sh
git diff src/numbers/Numbers.java
```

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-diff-merge-Numbers.png" width="600"/>


&nbsp;

Commit changes with message: `"implemented sum()"`.

Show the commit log:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-log-sum.png" width="600"/> -->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. Create *JUnit*-Tests for *sum()*

After `sum(int[] numbers)` has been implemented, *JUnit*-tests are added to verify
the correct function.

Read about *JUnit-tests:*

- Carsten Gips, [*Testen mit JUnit5*](https://www.hsbi.de/elearning/data/FH-Bielefeld/lm_data/lm_1359639/testing/junit-basics.html).

- Tobias Trelle, [*JUnit 5*](https://www.codecentric.de/wissens-hub/blog/junit5-junit-5).


<!-- &nbsp; -->

Install a new test class `Numbers_1_sum_Tests.java` under `src/tests/numbers`.

```sh
mkdir -p src/tests/numbers          # create new package 'numbers' under 'src/tests'
touch src/tests/numbers/Numbers_1_sum_Tests.java

find src/tests/numbers              # show the new test class
```
```
src/tests/
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers
src/tests/numbers/Numbers_1_sum_Tests.java      <-- new test class (empty)
```

Test methods perform various tests for the `sum()` method for *regular, corner*
and *exception* cases:

<!-- @@ src.tests.numbers.Numbers_1_sum_Tests.java @BEGIN -->
```java
package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class of an instance that implements the {@link Numbers} interface.
 * Method under test: {@code long sum(int[] numbers)}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

    /*
     * tested object, instance that implements the {@link Numbers} interface
     */
    private final Numbers testObj;

    /*
     * test data used in tests
     */
    private final NumbersData testData;

    /**
     * Constructor that initializes test instances.
     */
    Numbers_1_sum_Tests() {
        this.testObj = Numbers.getInstance();
        this.testData = new NumbersData();
    }

    /**
     * Tests for 'regular' cases.
     */
    @Test @Order(100)
    void test100_sum_regular() {
        int[] testData_ = testData.getArr("numb");
        long expected = 30L;    // expected result of test
        long actual = testObj.sum(testData_);   // invoke sum()
        //
        // compare test results, test passes if expected==actual
        // make sure to compare 'long' values
        assertEquals(expected, actual);
    }

    @Test @Order(101)
    void test101_sum_regular() {
        assertEquals(50L, testObj.sum(testData.getArr("numb_1")));
    }

    @Test @Order(102)
    void test102_sum_regular() {
        assertEquals(10984L, testObj.sum(testData.getArr("numb_2")));
    }

    @Test @Order(103)
    void test103_sum_regular() {
        assertEquals(141466L, testObj.sum(testData.getArr("numb_3")));
    }

    /**
     * Tests for 'corner' cases.
     */

    /**
     * Tests for 'exception' cases.
     */
}
```
<!-- @@ src.tests.numbers.Numbers_1_sum_Tests.java @END -->

Compile tests and run:

```sh
# compile code and tests and run tests
mk clean compile compile-tests run-tests

# or run specific tests specified by the '-c' flag
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c application.Application_0_always_pass_Tests \
  -c numbers.Numbers_1_sum_Tests
```

Output:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Numbers_1_sum_Tests ✔            <-- new test class
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     └─ test103_sum_regular() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 187 ms
[         5 containers found      ]
[         0 containers skipped    ]
[         5 containers started    ]
[         0 containers aborted    ]
[         5 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         6 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed
```

Show that unit tests also run in the IDE:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/vscode-test-sum-1.png" width="800"/>


&nbsp;

Add tests for *"corner cases"* and make them work one after another.

Test: *(110)* explores cases of empty numbers arrays:

```java
/**
 * Tests for 'corner' cases.
 */
@Test @Order(110)
void test110_sum_corner_empty_array() {
    int[] testData = {};        // empty array
    assertEquals(0L, testObj.sum(testData));

    testData = new int[0];      // array of length 0
    assertEquals(0L, testObj.sum(testData));

    testData = new int[1];      // array of length 1
    testData[0] = 1;
    assertEquals(1L, testObj.sum(testData));
}
```

Test: *(120)* explores the case of a big (extreme) numbers arrays:

```java
@Test @Order(120)
void test120_sum_corner_big_array() {
    int big = Integer.MAX_VALUE;        // 32-bit, 0x7fffffff, 2147483647
    // --> java.lang.OutOfMemoryError: Requested array size exceeds VM limit
    // --> java.lang.OutOfMemoryError: Java heap space
    big = 2147483647;                   // Integer.MAX_VALUE for comparison
    big = 1000000000;                   // reduce to not throw heap space exception
    // big =  100000000;                   // reduce further to speed up test
    int[] testData = new int[big];      // big array
    for(int i=0; i < big; i++) {
        testData[i] = 1;    // initialize with 1's
    }
    long expected = big;
    long actual = testObj.sum(testData);
    assertEquals(expected, actual);
}
```

Test: *(122)* explores the cases of a big (extreme) numbers array filled
with a numbers sequence: `[0, 1, 2, 3, 4, 5, 6 ... ]` leading to a big
sum: *499,999,999,500,000,000*.

```java
@Test @Order(122)
void test122_sum_corner_big_array_number_series() {
    long big = 1000000000;
    // big =  100000000;                       // reduce to speed up test
    int[] testData = new int[Long.valueOf(big).intValue()];
    for(int i=0; i < big; i++) {
        testData[i] = i;
    }
    // big=1000000000: 499,999,999,500,000,000
    // big= 100000000:   4,999,999,950,000,000 (reduced to big=100,000,000)
    long expected = big * (big - 1) / 2;
    long actual = testObj.sum(testData);
    // System.out.println(String.format("-exp-> %d\n-act-> %d", expected, actual));
    assertEquals(expected, actual);
}
```

If tests fail, fix the code that is tested, which is method:
`long sum(int[] numbers)` in `NumbersImpl.java`.

Run tests in the terminal:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Numbers_1_sum_Tests ✔
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     ├─ test103_sum_regular() ✔
│     ├─ test110_sum_corner_empty_array() ✔
│     ├─ test120_sum_corner_big_array() ✔
│     └─ test122_sum_corner_big_array_number_series() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 4175 ms
[         9 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed
```

Show tests also in the IDE:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/vscode-test-sum-2.png" width="800"/>


&nbsp;

Add test for *"exception cases"*, which is calling *sum(null)* with the *null*
argument. The test defines the expected behavior of method *sum()*:

- method *sum()* is expected to throw an
    [*IllegalArgumentException*](https://docs.oracle.com/en/java/javase/25/docs/api//java.base/java/lang/IllegalArgumentException.html)

- carrying the exception message: *"illegal argument: null"*.

Add test *130* to *Numbers_1_sum_Tests.java:*

```java
/**
 * Tests for 'exception' cases.
 */
@Test @Order(130)
void test130_sum_exception_null_arg() {
    IllegalArgumentException ex =
        assertThrows(IllegalArgumentException.class,
            () -> testObj.sum(null));
    // 
    assertEquals("illegal argument: null", ex.getMessage());
}
```

Run tests. Test *130* may fail because method *sum()* may not yet implement
the expected bahavior:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/mk-run-test-130-fails.png" width="520"/>

Add the expected bahavior in method *sum()*:

```java
@Override
public long sum(int[] numbers) {
    if(numbers==null)
        throw new IllegalArgumentException(String.format("illegal argument: %s", numbers));
    // 
    return Arrays.stream(numbers).asLongStream().sum();
}
```

All tests should now pass:

```sh
# run tests 'Numbers_1_sum_Tests' selectively
mk run-tests -c numbers.Numbers_1_sum_Tests
```

Output shows only tests of class *Numbers_1_sum_Tests*

```
run-tests: [-c numbers.Numbers_1_sum_Tests]
  java -cp $JUNIT_CLASSPATH \
    org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
    -c numbers.Numbers_1_sum_Tests      <-- selected test-class
---
```

Run all tests:

```sh
mk run-tests            # run all tests
```
```
run-tests:
  java -cp $JUNIT_CLASSPATH \
    org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
    --scan-class-path           <-- run all discovered tests
---
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Numbers_1_sum_Tests ✔
│     ├─ test100_sum_regular() ✔
│     ├─ test101_sum_regular() ✔
│     ├─ test102_sum_regular() ✔
│     ├─ test103_sum_regular() ✔
│     ├─ test110_sum_corner_empty_array() ✔
│     ├─ test120_sum_corner_big_array() ✔
│     ├─ test122_sum_corner_big_array_number_series() ✔
│     └─ test130_sum_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 4175 ms
[        10 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed
```


&nbsp;

When all tests are passing, this stage of the development should be committed
to branch *b1-numbers*.

Show the changes to be committed and commit 

- with message: `"Aufgabe 1.) long sum(int[] numbers); src, tests"`

to branch *b1-numbers*.

Show the commit log:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/git-log-sum.png" width="520"/>

The commit has been added to branch *b1-numbers*, which now has advanced two
commits from the *main* branch.


&nbsp;

## 6. Implement more *Numbers*-Functions

Interface: [*Numbers.java*](src/numbers/Numbers.java) defines eight functions.
Implement functions one after another:

1. `sum(int[] numbers)` (already done).

1. `sum_positive_even_numbers(numbers[])`, [link](#2-sum_positive_even_numbers).

1. `sum_recursive(numbers[], int i)`, [link](#3-sum_recursive).

1. `findFirst(numbers[], int x)`, [link](#4-findfirst).

1. `findLast(numbers[], int x)`, [link](#5-findlast).

1. `findAll(numbers[], int x)`, [link](#6-findall).

1. `findSums(numbers[], int sum)`, [link](#7-findsums).

1. `findAllSums(numbers[], int sum)`, [link](#8-findallsums).

1. `findAllSums(), XXL`, [link](#9-findallsums-xxl).


<!-- @@ src.main.numbers.Numbers.java @BEGIN -->
```java
package numbers;

import java.util.List;
import java.util.Set;

import application.Runner;

/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate the sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    long sum(int[] numbers);

    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    long sum_positive_even_numbers(int[] numbers);

    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    long sum_recursive(int[] numbers, int i);

    /**
     * Aufgabe 4.) Return index of first occurrence of x in numbers[]
     * or return -1 if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    int findFirst(int[] numbers, int x);

    /**
     * Aufgabe 5.) Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    int findLast(int[] numbers, int x);

    /**
     * Aufgabe 6.) Return list of all indices of number x in numbers[].
     * Return empty list, if x was not found.
     * @param numbers input
     * @param x number to find
     * @return list with all indices of x in numbers[]
     */
    List<Integer> findAll(int[] numbers, int x);

    /**
     * Immutable pair of integer values a and b used by {@code Set<Pair>
     * findSums(int[] numbers, int sum)}.
     * @param a first element of pair
     * @param b second element of pair
     */
    record Pair(int a, int b) {
        public String toString() { return String.format("(%d,%d)", a, b); }
    };

    /**
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] matching a + b = sum.
     * Mirror copies (a, b), (b, a) are included once, either (a, b) or (b, a),
     * not both.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all pairs (a, b) that add to sum
     */
    Set<Pair> findSums(int[] numbers, int sum);

    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all combinations of numbers that add to sum
     */
    Set<Set<Integer>> findAllSums(int[] numbers, int sum);


    /**
     * Static getter that returns an instance that implements the {@link Numbers}
     * interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        // throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
        //     + "in interface 'Numbers'. Create an implementation class and return.");
        // 
        return NumbersImpl.getInstance();
    }

    /**
     * Factory method that creates an instance of the {@link Runner} interface.
     * @param numbers instance of the {@link Numbers} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Numbers numbers) {
        return new NumbersRunner(numbers);
    }
}
```
<!-- @@ src.main.numbers.Numbers.java @END -->

<!-- 
When you are done with one function,

- install the corresponding JUnit test from:
    [*tests/numbers*](../../tree/b1-numbers-tests/src/tests/numbers).

- also install class with JUnit test matchers:
    [*Matchers.java*](../../tree/b1-numbers-tests/src/tests/numbers/Matchers.java).
 -->


&nbsp;

### 2. *sum_positive_even_numbers()*

Function: `sum_positive_even_numbers(numbers[], int i)` returns the sum of
positive and even numbers from `numbers[]`.

Implement the function and demonstrate:

```sh
mk run sum_positive_even_numbers numb \
    sum_positive_even_numbers numb_1 \
    sum_positive_even_numbers numb_2 \
    sum_positive_even_numbers numb_3
```

Output shows the correct results:

```
java application.Runtime sum numbers sum numb_1 sum numb_2 sum numb_3
NumbersDriver executing NumbersImpl
 - sum_positive_even_numbers(numbers) -> 12
 - sum_positive_even_numbers(numb_1) -> 38
 - sum_positive_even_numbers(numb_2) -> 6492
 - sum_positive_even_numbers(numb_3) -> 80012
done.
```

Install the *JUnit*-test for the function
[*Numbers_2_sum_positive_even_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_2_sum_positive_even_Tests.java):

```sh
url="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/b1-numbers-tests/src/tests/numbers"
test="Numbers_2_sum_positive_even_Tests.java"

# install test from URL locally under 'src/tests/numbers'
curl -o "src/tests/numbers/$test" "$url/$test"
```

Alternatively, fetch remote branch 

```sh
# fetch tests from remote branch 'b1-numbers-tests'
git fetch se1-repo b1-numbers-tests

# checkout tests: 'Numbers_1_sum_Tests.java' to local branch 'b1-numbers'
git checkout b1-numbers-tests -- src/tests/numbers/Numbers_1_sum_Tests.java
```

Compile and run the test:

```sh
mk compile-tests run-tests \
  -c numbers.Numbers_2_sum_positive_even_Tests

# or run with
mk run-tests -c numbers.Numbers_2_sum_positive_even_Tests
```

Output shows all *200's* tests are passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_2_sum_positive_even_Tests ✔
│     ├─ test200_sum_positive_even_numbers_regular() ✔
│     ├─ test201_sum_positive_even_numbers_regular() ✔
│     ├─ test202_sum_positive_even_numbers_regular() ✔
│     ├─ test203_sum_positive_even_numbers_regular() ✔
│     ├─ test210_sum_positive_even_numbers_corner_empty_array() ✔
│     ├─ test220_sum_positive_even_numbers_corner_big_array() ✔
│     ├─ test222_sum_positive_even_numbers_corner_big_array_number_series() ✔
│     └─ test230_sum_positive_even_numbers_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

[         8 tests successful      ]     <-- all tests have been successful
[         0 tests failed          ]     <-- no test failed

```

Show tests also in the IDE.

Commit the state of the development with commit message:
`"Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests"`.

```sh
git add src                 # stage the commit

git commit -m "Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests"

git log --oneline           # show commit log
```
```
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 3. *sum_recursive()*

Function: `sum_recursive(numbers[], int i)` returns the sum of `numbers[]`,
but uses recursion instead of loops (`for`, `while`, `do-while`).

Implement the function (no loops!) and demonstrate:

```sh
mk run sum_recursive numb \
    sum_recursive numb_1 \
    sum_recursive numb_2 \
    sum_recursive numb_3
```

Output shows the correct results:

```
java application.Runtime sum numbers sum numb_1 sum numb_2 sum numb_3
NumbersDriver executing NumbersImpl
 - sum_recursive(numbers) -> 30
 - sum_recursive(numb_1) -> 50
 - sum_recursive(numb_2) -> 10984
 - sum_recursive(numb_3) -> 141466
done.
```

Install the *JUnit*-test class
[*Numbers_3_sum_recursion_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_3_sum_recursion_Tests.java)
and run the test:

```sh
mk compile-tests run-tests \
    -c numbers.Numbers_3_sum_recursion_Tests
```

Output shows *300's* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_3_sum_recursion_Tests ✔
│     ├─ test300_sum_recursion_regular() ✔
│     ├─ test300_sum_recursion_corner_empty_array() ✔
│     ├─ test301_sum_recursion_regular() ✔
│     ├─ test102_sum_recursion_regular() ✔
│     ├─ test103_sum_recursion_regular() ✔
│     ├─ test310_sum_recursion_corner_big_array() ✔
│     ├─ test312_sum_recursion_corner_big_array_number_series() ✔
│     └─ test340_sum_recursion_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 169 ms
[         8 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 3.) long sum_recursive(int[] numbers, int i), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 4. *findFirst()*

Function: `findFirst(numbers[], int x)` returns index of the first occurence
of `x` in `numbers[]` or `-1` if `x` is not found.

For example, number `4` occurs 3x in `numbers: [-2, 4, 9, 4, -3, 4, 9, 5]`.
Index `1` is the first occurence.

Implement and run the function:

```sh
mk run findFirst numb x=4 \
    findFirst numb x=-3 \
    findFirst numb x=1
```
```
 - findFirst(numbers, x=4) -> 1
 - findFirst(numbers, x=-3) -> 4
 - findFirst(numbers, x=1) -> -1
```

Add and run tests
[*Numbers_4_find_first_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_4_find_first_Tests.java):

```sh
mk run-tests -c numbers.Numbers_4_find_first_Tests
```

Output shows *400's* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_4_find_first_Tests ✔
│     ├─ test400_find_first_regular() ✔
│     ├─ test401_find_first_regular_neg_element() ✔
│     ├─ test402_find_first_regular_duplicates() ✔
│     ├─ test403_find_first_regular_last() ✔
│     ├─ test404_find_first_regular_not_present() ✔
│     ├─ test410_find_first_regular_numb_1() ✔
│     ├─ test412_find_first_regular_numb_2() ✔
│     ├─ test414_find_first_regular_numb_3() ✔
│     ├─ test420_find_first_corner_empty_array() ✔
│     ├─ test430_find_first_corner_big_array() ✔
│     └─ test440_find_first_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 482 ms
[        11 tests successful      ]
[         0 tests failed          ]

```

Commit the state of the development with commit message:
`"Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 5. *findLast()*

Function: `findLast(numbers[], int x)` returns index of the last occurence
of `x` in `numbers[]` or `-1` if `x` is not found.

For example, number `4` occurs 3x in `numb[]: [-2, 4, 9, 4, -3, 4, 9, 5]`.
Index `5` is the last occurence.

- *findFirst()* is an efficient function. It can immediately return
    when *x* is found - giving it a *"speed"* of *n/2* on average
    with *n* as the length of the array.

    - What is the efficiency of *findLast()*? Can it also return *"early"* or has
    it to visit the entire array - giving it a *"speed"* of *n*.

    - Can you find an implementation that runs at *"speed"* *n/2*?


Implement and run the function:

```sh
mk run findLast numb x=4 \
    findLast numb x=-3 \
    findLast numb x=1
```
```
 - findLast(numbers, x=4) -> 5
 - findLast(numbers, x=-3) -> 4
 - findLast(numbers, x=1) -> -1
```

Add and run tests
[*Numbers_5_find_last_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_5_find_last_Tests.java):

```sh
mk run-tests -c numbers.Numbers_5_find_last_Tests
```

Output shows *500's* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_5_find_last_Tests ✔
│     ├─ test500_find_last_regular() ✔
│     ├─ test501_find_last_regular_neg_element() ✔
│     ├─ test502_find_last_regular_duplicates() ✔
│     ├─ test503_find_last_regular_last() ✔
│     ├─ test504_find_last_regular_not_present() ✔
│     ├─ test510_find_last_regular_numb_1() ✔
│     ├─ test512_find_last_regular_numb_2() ✔
│     ├─ test514_find_last_regular_numb_3() ✔
│     ├─ test520_find_last_corner_empty_array() ✔
│     ├─ test530_find_last_corner_big_array() ✔
│     └─ test540_find_last_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 364 ms
[        11 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 5.) int findLast(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 6. *findAll()*

Function: `List<Integer> findAll(int[] numbers, int x)` returns indices of all
occurences of `x` in `numbers[]` as a `List<Integer>`.

Implement and run the function:

```sh
mk run findAll numb x=4 \
    findAll numb x=-3 \
    findAll numb x=1
```

Output shows that `x=4` was found 3x in `numbers[]: [-2, 4, 9, 4, -3, 4, 9, 5]`
at indices: `[1, 3, 5]`, `x=-3` was found once at index `[4]` and `x=1` was
not found returning an empty result `[]`.

```
 - findAll(numbers, x=4) -> [1, 3, 5]
 - findAll(numbers, x=-3) -> [4]
 - findAll(numbers, x=1) -> []
```

Unit tests need to compare collections (lists) as *expected* or *actual* values.

1. Install class:
[*Matchers.java*](../../tree/b1-numbers-tests/src/tests/numbers/Matchers.java)
for this purpose.

2. Then, install:
[*Numbers_6_find_all_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_6_find_all_Tests.java).

Run tests:

```sh
mk run-tests -c numbers.Numbers_6_find_all_Tests
```

Output shows *600's* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_6_find_all_Tests ✔
│     ├─ test600_find_all_regular() ✔
│     ├─ test601_find_all_regular() ✔
│     ├─ test602_find_all_regular() ✔
│     ├─ test603_find_all_regular() ✔
│     └─ test640_find_all_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 253 ms
[         5 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 7. *findSums()*

Function: `Set<Pair> findSums(int[] numbers, int sum)` returns a set of
pairs: `(x, y)` from `numbers[]` with `x + y = sum`.

For example: `sum=12` can be created from `numb_1[]: [8, 10, 7, 2, 14, 5, 4]`
with pairs: `[ (5,7), (4,8), (2,10) ]`.

Duplicates should be avoided and included only once, which means either
`(5,7)` or `(7,5)` and not both.

Interface `Numbers.java` defines `Pair` as Java-Record:

```java
/**
 * Immutable pair of integer values a and b.
 * @param a first element of pair
 * @param b second element of pair
 */
record Pair(int a, int b) {
    public String toString() { return String.format("(%d,%d)", a, b); }
};
```

Implementing the functions yields results using array `numb_1[]`:

```sh
mk run findSums numb_1 sum=10 \
    findSums numb_1 sum=12 \
    findSums numb_1 sum=15 \
    findSums numb_3 sum=500
```

Output shows that `sum=10` can be added by `2+8` from array
`numb_1[]: [8, 10, 7, 2, 14, 5, 4]`. There is only one solution.

There are three pairs of numbers from `numb_1[]` that add to `sum=12`:
`[ (5,7), (4,8), (2,10) ]` and two pairs that add to `sum=15`.

Sum `500` can be added from pairs: `[ (7,493), (485,15) ]` from array `numb_3[]`.

```
 - findSums(numb_1, sum=10) -> [(8,2)], solutions: 1
 - findSums(numb_1, sum=12) -> [(10,2), (8,4), (7,5)], solutions: 3
 - findSums(numb_1, sum=15) -> [(10,5), (8,7)], solutions: 2
 - findSums(numb_3, sum=500) -> [(15,485), (493,7)], solutions: 2
```

Add and run tests
[*Numbers_7a_find_sums_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_7a_find_sums_Tests.java)
and
[*Numbers_7b_find_sums_duplicates_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java):

Leave out tests `7b` if duplicate tests are not passing.

```sh
mk run-tests \
  -c numbers.Numbers_7a_find_sums_Tests \
  -c numbers.Numbers_7b_find_sums_duplicates_Tests
```

Output shows *700's* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  ├─ test706_find_sums_regular() ✔
│  │  └─ test720_find_sums_exception_null_arg() ✔
│  └─ Numbers_7b_find_sums_duplicates_Tests ✔
│     ├─ test710_find_sums_duplicates() ✔
│     ├─ test711_find_sums_same_duplicates() ✔
│     ├─ test712_find_sums_mirror_duplicates() ✔
│     └─ test713_find_sums_regular_duplicates() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 277 ms
[        12 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests"`.

```sh
git log --oneline           # show commit log
```
```
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 8. *findAllSums()*

Function: `Set<Set<Integer>> findAllSums(int[] numbers, int sum)` returns all
combinations of numbers from the array that add to `sum`.

For example: `sum=10` can be created from `numb_1[]: [8, 10, 7, 2, 14, 5, 4]`
with: `[10]` and `[2, 8]`,
`sum=14` can be created with: `[14], [4, 10], [2, 4, 8], [2, 5, 7]`.

Duplicates such as `[4,10]` or `[10,4]`should be avoided and included only once.

```sh
mk run \
    findAllSums numb_1 sum=10 \
    findAllSums numb_1 sum=12 \
    findAllSums numb_1 sum=14 \
    findAllSums numb_1 sum=15 \
    findAllSums numb_1 sum=20
```

```
 - findAllSums(numb_1, sum=10) -> {[10], [2, 8]}, solutions: 2
 - findAllSums(numb_1, sum=12) -> {[4, 8], [2, 10], [5, 7]}, solutions: 3
 - findAllSums(numb_1, sum=14) -> {[14], [4, 10], [2, 4, 8], [2, 5, 7]}, solutions: 4
 - findAllSums(numb_1, sum=15) -> {[7, 8], [5, 10], [2, 5, 8]}, solutions: 3
 - findAllSums(numb_1, sum=20) -> {[2, 8, 10], [5, 7, 8], [2, 4, 14]}, solutions: 3
```

Explore more combinations from the (larger) `numb_2[]` array:

```sh
mk run \
    findAllSums numb_2 sum=1000 \
    findAllSums numb_2 sum=999
```

```
 - findAllSums(numb_2, sum=1000) -> [
    - [226, 774],
    - [754, 87, 7, 27, 125],
    - [7, 27, 636, 125, 205],
    - [7, 651, 27, 125, 190],
    - [7, 27, 125, 205, 190, 446]
   ], solutions: 5

 - findAllSums(numb_2, sum=999) -> [
    - [226, 773],
    - [27, 972],
    - [371, 87, 541],
    - [170, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125]
   ], solutions: 13
```

Add and run tests
[*Numbers_8a_find_all_sums_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_8a_find_all_sums_Tests.java):

```sh
mk run-tests -c numbers.Numbers_8a_find_all_sums_Tests
```

Output shows *800(a)'s* tests passing:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_8a_find_all_sums_Tests ✔
│     ├─ test800_find_all_sums_regular() ✔
│     ├─ test801_find_all_sums_regular() ✔
│     ├─ test802_find_all_sums_regular() ✔
│     ├─ test802_find_all_sums_regular_no_match() ✔
│     ├─ test821_find_all_sums_regular_numb_2_sum999() ✔
│     └─ test830_find_all_sums_exception_null_arg() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 176 ms
[         6 tests successful      ]
[         0 tests failed          ]
```

Commit the state of the development with commit message:
`"Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)"`.

```sh
git log --oneline           # show commit log
```
```
8bdc9fe Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```


&nbsp;

### 9. *findAllSums(), XXL*

Array `numb_2[]` with `24` numbers is still small.

```java
/*
 * Larger set of 24 numbers, no negatives, no duplicates.
 */
static final int[] numb_2 = {   // 24 numbers
    371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
     27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
    125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
}; // add more numbers to find more solutions
```

Run the function for `sum=999` with 24 numbers from `numb_2[]`:

```sh
mk run findAllSums numb_2 sum=999
```

Output:

```
 - findAllSums(numb_2, sum=999) -> [
    - [27, 972],
    - [226, 773],
    - [371, 87, 541],
    - [170, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125]
   ], solutions: 13
```

Add numbers `225` und `463` to `numb_2[]` (remove comments) and repeat:

```sh
mk run findAllSums numb_2 sum=999
```

More solutions are found with the new numbers `225` und `463`:

```
 - findAllSums(numb_2, sum=999) -> [
    - [226, 773],
    - [225, 774],
    - [27, 972],
    - [371, 87, 541],
    - [170, 190, 639],
    - [163, 7, 190, 639],
    - [226, 27, 541, 205],
    - [163, 170, 125, 541],
    - [163, 170, 27, 639],
    - [225, 226, 7, 541],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [225, 226, 371, 7, 170],
    - [226, 371, 87, 125, 190],
    - [226, 163, 371, 7, 27, 205],
    - [226, 163, 371, 87, 27, 125],
    - [225, 87, 7, 27, 190, 463]
   ], solutions: 17
```

Incrementally add more numbers to `numb_2[]` and repeat:

- add number `286` --> 19 solutions,

- add number `569` --> 21 solutions,

- add number `384` --> 24 solutions,

- add number `9` --> 44 solutions:

```
-> findAllSums(sum=999, numb_2) -> [
    - [226, 773],                   - [371, 27, 125, 286, 190],
    - [27, 972],                    - [225, 226, 371, 7, 170],
    - [225, 774],                   - [226, 7, 569, 170, 27],
    - [170, 190, 639],              - [225, 370, 9, 205, 190],
    - [371, 87, 541],               - [226, 163, 371, 7, 27, 205],
    - [225, 569, 205],              - [226, 163, 371, 87, 27, 125],
    - [903, 87, 9],                 - [226, 163, 9, 125, 286, 190],
    - [226, 9, 125, 639],           - [225, 7, 9, 170, 125, 463],
    - [163, 9, 541, 286],           - [384, 7, 170, 27, 125, 286],
    - [163, 170, 27, 639],          - [384, 226, 163, 9, 27, 190],
    - [225, 226, 7, 541],           - [225, 7, 9, 27, 541, 190],
    - [163, 170, 125, 541],         - [225, 87, 7, 27, 190, 463],
    - [163, 7, 190, 639],           - [384, 226, 87, 7, 9, 286],
    - [773, 9, 27, 190],            - [7, 9, 125, 205, 190, 463],
    - [226, 27, 541, 205],          - [9, 170, 27, 125, 205, 463],
    - [163, 87, 286, 463],          - [225, 87, 9, 27, 205, 446],
    - [384, 27, 125, 463],          - [384, 226, 87, 7, 170, 125],
    - [226, 371, 87, 125, 190],     - [225, 370, 163, 9, 27, 205],
    - [226, 371, 170, 27, 205],     - [225, 371, 7, 9, 170, 27, 190],
    - [226, 371, 7, 205, 190],      - [163, 7, 9, 27, 125, 205, 463],
    - [163, 371, 9, 170, 286],      - [370, 226, 7, 9, 170, 27, 190],
    - [225, 87, 9, 651, 27],        - [384, 87, 7, 9, 170, 27, 125, 190]
   ], solutions: 44
```

A *"bruteforce"* algorithm will take increasing time from the 24th number
and not end if numbers continue to be added.

Alterative algorithms need to be used that can cope with larger number
arrays such as `numb_3`:

```java
/*
* Even larger set of 63 numbers, no negatives, no duplicates (n=3).
*/
static final int numb_3[] = {
     799, 2377,  936, 3498, 1342,  493, 1635, 4676, 1613, 3851,
    1445, 4506, 3346,    7, 2141, 2064, 1491,  908,   78, 3325,
    1756, 3691,   23, 1995, 1800,   15, 2784, 4305,   36, 2532,
    4292, 4802, 2522, 4183, 3261, 2610,  803, 2656,  498, 1668,
    2038, 2194,  440,  463, 4047, 4235, 3931,  756,  521, 4042,
    3302,  485, 1002,  408, 4691, 3387, 3104, 3658, 2241, 4382,
    1220, 3656,  500,
};
```

Try to find such algorithms (e.g. *"branch-and-bound"*) and implement
function `findAllSums(int sum)` such that it works with numbers from
`numb_3`, e.g. for `sum=999`:

```sh
mk run findAllSums numb_3 sum=999
```

Although there are only 10 solutions for `sum=999`, the number space
to explore is large: *2^63*.

```
 - findAllSums(numb_3, sum=999) -> [
    - [521, 463, 15],
    - [500, 36, 463],
    - [36, 7, 493, 463],
    - [498, 408, 78, 15],
    - [498, 23, 463, 15],
    - [23, 440, 521, 15],
    - [500, 36, 23, 440],
    - [36, 485, 463, 15],
    - [36, 23, 7, 440, 493],
    - [36, 485, 23, 440, 15]
   ], solutions: 10
```

There are only 5 solutions for `sum=1000`:

```sh
mk run findAllSums numb_3 sum=1000
```
```
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 463, 15],
    - [36, 23, 408, 440, 78, 15]
   ], solutions: 5
```

If you found an implementation that works with the `numb_3[]` array,
add and run tests
[*Numbers_8b_find_all_sums_XL_Tests.java*](../../tree/b1-numbers-tests/src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java):

```sh
mk run-tests -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

Output:

```
╷
├─ JUnit Jupiter ✔
│  └─ Numbers_8b_find_all_sums_XL_Tests ✔
│     ├─ test824_find_all_sums_XL_24_numbers() ✔
│     ├─ test825_find_all_sums_XL_25_numbers() ✔
│     ├─ test826_find_all_sums_XL_26_numbers() ✔
│     ├─ test827_find_all_sums_XL_27_numbers() ✔
│     ├─ test828_find_all_sums_XL_28_numbers() ✔
│     ├─ test829_find_all_sums_XL_29_numbers() ✔
│     ├─ test830_find_all_sums_XL_30_numbers() ✔
│     ├─ test840_find_all_sums_XL_numb_3_999() ✔
│     └─ test841_find_all_sums_XL_numb_3_1000() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 290 ms
[         9 tests successful      ]
[         0 tests failed          ]
```


&nbsp;

## 7. Final Result

The final result will show all tests passing. Leave out tests that are
not passing:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/b1-numbers-tests-vscode.png" width="800"/>


```sh
mk run-tests                    # run all tests
```

Or run tests selectively (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
mk run-tests \
  -c application.Application_0_always_pass_Tests \
  -c numbers.Numbers_1_sum_Tests \
  -c numbers.Numbers_2_sum_positive_even_Tests \
  -c numbers.Numbers_3_sum_recursion_Tests \
  -c numbers.Numbers_4_find_first_Tests \
  -c numbers.Numbers_5_find_last_Tests \
  -c numbers.Numbers_6_find_all_Tests \
  -c numbers.Numbers_7a_find_sums_Tests \
  -c numbers.Numbers_7b_find_sums_duplicates_Tests \
  -c numbers.Numbers_8a_find_all_sums_Tests \
  -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

The full [*test log*](https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/numbers/b1-numbers-tests-terminal.png)
shows all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Numbers_1_sum_Tests ✔
│  │  ├─ test100_sum_regular() ✔
│  │  ├─ test101_sum_regular() ✔
│  │  ├─ test102_sum_regular() ✔
│  │  ├─ test103_sum_regular() ✔
│  │  ├─ test110_sum_corner_empty_array() ✔
│  │  ├─ test120_sum_corner_big_array() ✔
│  │  ├─ test122_sum_corner_big_array_number_series() ✔
│  │  └─ test130_sum_exception_null_arg() ✔
│  ├─ Numbers_2_sum_positive_even_Tests ✔
│  │  ├─ test200_sum_positive_even_numbers_regular() ✔
│  │  ├─ test201_sum_positive_even_numbers_regular() ✔
│  │  ├─ test202_sum_positive_even_numbers_regular() ✔
│  │  ├─ test203_sum_positive_even_numbers_regular() ✔
│  │  ├─ test210_sum_positive_even_numbers_corner_empty_array() ✔
│  │  ├─ test220_sum_positive_even_numbers_corner_big_array() ✔
│  │  ├─ test222_sum_positive_even_numbers_corner_big_array_number_series() ✔
│  │  └─ test230_sum_positive_even_numbers_exception_null_arg() ✔
│  ├─ Numbers_3_sum_recursion_Tests ✔
│  │  ├─ test300_sum_recursion_regular() ✔
│  │  ├─ test300_sum_recursion_corner_empty_array() ✔
│  │  ├─ test301_sum_recursion_regular() ✔
│  │  ├─ test102_sum_recursion_regular() ✔
│  │  ├─ test103_sum_recursion_regular() ✔
│  │  ├─ test310_sum_recursion_corner_big_array() ✔
│  │  ├─ test312_sum_recursion_corner_big_array_number_series() ✔
│  │  └─ test340_sum_recursion_exception_null_arg() ✔
│  ├─ Numbers_4_find_first_Tests ✔
│  │  ├─ test400_find_first_regular() ✔
│  │  ├─ test401_find_first_regular_neg_element() ✔
│  │  ├─ test402_find_first_regular_duplicates() ✔
│  │  ├─ test403_find_first_regular_last() ✔
│  │  ├─ test404_find_first_regular_not_present() ✔
│  │  ├─ test410_find_first_regular_numb_1() ✔
│  │  ├─ test412_find_first_regular_numb_2() ✔
│  │  ├─ test414_find_first_regular_numb_3() ✔
│  │  ├─ test420_find_first_corner_empty_array() ✔
│  │  ├─ test430_find_first_corner_big_array() ✔
│  │  └─ test440_find_first_exception_null_arg() ✔
│  ├─ Numbers_5_find_last_Tests ✔
│  │  ├─ test500_find_last_regular() ✔
│  │  ├─ test501_find_last_regular_neg_element() ✔
│  │  ├─ test502_find_last_regular_duplicates() ✔
│  │  ├─ test503_find_last_regular_last() ✔
│  │  ├─ test504_find_last_regular_not_present() ✔
│  │  ├─ test510_find_last_regular_numb_1() ✔
│  │  ├─ test512_find_last_regular_numb_2() ✔
│  │  ├─ test514_find_last_regular_numb_3() ✔
│  │  ├─ test520_find_last_corner_empty_array() ✔
│  │  ├─ test530_find_last_corner_big_array() ✔
│  │  └─ test540_find_last_exception_null_arg() ✔
│  ├─ Numbers_6_find_all_Tests ✔
│  │  ├─ test600_find_all_regular() ✔
│  │  ├─ test601_find_all_regular() ✔
│  │  ├─ test602_find_all_regular() ✔
│  │  ├─ test603_find_all_regular() ✔
│  │  └─ test640_find_all_exception_null_arg() ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  ├─ test706_find_sums_regular() ✔
│  │  └─ test720_find_sums_exception_null_arg() ✔
│  ├─ Numbers_7b_find_sums_duplicates_Tests ✔
│  │  ├─ test710_find_sums_duplicates() ✔
│  │  ├─ test711_find_sums_same_duplicates() ✔
│  │  ├─ test712_find_sums_mirror_duplicates() ✔
│  │  └─ test713_find_sums_regular_duplicates() ✔
│  ├─ Numbers_8a_find_all_sums_Tests ✔
│  │  ├─ test800_find_all_sums_regular() ✔
│  │  ├─ test801_find_all_sums_regular() ✔
│  │  ├─ test802_find_all_sums_regular() ✔
│  │  ├─ test802_find_all_sums_regular_no_match() ✔
│  │  ├─ test821_find_all_sums_regular_numb_2_sum999() ✔
│  │  └─ test830_find_all_sums_exception_null_arg() ✔
│  └─ Numbers_8b_find_all_sums_XL_Tests ✔
│     ├─ test824_find_all_sums_XL_24_numbers() ✔
│     ├─ test825_find_all_sums_XL_25_numbers() ✔
│     ├─ test826_find_all_sums_XL_26_numbers() ✔
│     ├─ test827_find_all_sums_XL_27_numbers() ✔
│     ├─ test828_find_all_sums_XL_28_numbers() ✔
│     ├─ test829_find_all_sums_XL_29_numbers() ✔
│     ├─ test830_find_all_sums_XL_30_numbers() ✔
│     ├─ test840_find_all_sums_XL_numb_3_999() ✔
│     └─ test841_find_all_sums_XL_numb_3_1000() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 7401 ms
[        14 containers found      ]
[         0 containers skipped    ]
[        14 containers started    ]
[         0 containers aborted    ]
[        14 containers successful ]
[         0 containers failed     ]
[        80 tests found           ]
[         0 tests skipped         ]
[        80 tests started         ]
[         0 tests aborted         ]
[        80 tests successful      ]
[         0 tests failed          ]
```


Commit the state of the development with commit message:
`"Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum), XXL"`.

```sh
git log --oneline           # show commit log
```
```
8bdc9fe (HEAD -> b1-numbers) Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum), XXL
8bdc9fe Aufgabe 8.) Set<Set<Integer>> findAllSums(int[] numbers, int sum)
cd849e5 Aufgabe 7.) Set<Pair> findSums(int[] numbers, int sum), code and tests
14f7939 Aufgabe 6.) List<Integer> findAll(int[] numbers, int x), code and tests
efe6c64 Aufgabe 5.) int findLast(int[] numbers, int x), code and tests
095f5e5 Aufgabe 4.) int findFirst(int[] numbers, int x), code and tests
0050dfb Aufgabe 3.) long sum_recursive(int[] numbers, int i)
d58908f Aufgabe 2.) long sum_positive_even_numbers(int[] numbers), code and tests
d7ab4db Aufgabe 1.) long sum(int[] numbers), code and tests
d416fa7 merge commit se1-play/b1-numbers
79a79e3 (tag: base) add src/tests, update src/main/module-info.java
...
```

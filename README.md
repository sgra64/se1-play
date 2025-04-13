# Project: *se1-play*, branch *b1-numbers*

Branch *b1-numbers* contains code and tasks for the *b1-numbers* assignment.

Goals are:

- supplement the project with content from branches
    [*b1-numbers*](../../tree/b1-numbers) and
    [*b1-numbers-tests*](../../tree/b1-numbers-tests),

- rebuild the project,

- implement functions of the [*Numbers.java*](src/numbers/Numbers.java) interface and

- verify correctness through *JUnit-tests*.

Code and JUnit-tests must work in the IDE and in the terminal.


Steps:

1. [Project Structure](#1-project-structure)

1. [*Setup* and *Build* Project](#2-setup-and-build-project)

1. [Implement *sum()*](#3-implement-sum)

1. [*JUnit*-Tests](#4-junit---tests)

1. [Implement *Numbers*-Functions](#5-implement-numbers-functions)

1. [Final Result](#final-result)




&nbsp;

## 1. Project Structure

The *Numbers*-project is assembled from three parts:

- the content from the [*main*](../../tree/main) branch,

- branch: [*b1-numbers*](../../tree/b1-numbers) adds source code
    with package [*src/numbers*](src/numbers),

- branch: [*b1-numbers-tests*](../../tree/b1-numbers-tests) adds
    *JUnit-tests* with package [*tests/numbers*](tests/numbers)

The project content assembled from the three branches is:

```sh
<se1-play>              # project directory
 |
 # regular content of the project from the 'main'-branch
 +--.gitignore                  # files for git to ignore
 +-- README.md                  # this markup file
 | ...v
 |
 # content from branch: 'b1-numbers' with package 'numbers'
 +-<src>                        # source code for 'numbers'
 |  +--module-info.java         # description of module 'se1.play'
 |  |
 |  +-<application>             # existing package 'application'
 |  |  +--Application.java      # program with main()-method
 |  |  +--Runner.java           # new interface
 |  |  +--...
 |  |
 |  +-<numbers>                 # new package 'numbers'
 |    +--Numbers.java           # interface with methods to implement
 |    +--NumbersData.java       # numbers data sets
 |    +--NumbersRunner.java     # driver code to perform calculations from command line
 |    +--package-info.java      # package information
 |
 # content from branch: 'b1-numbers-tests' adding JUnit tests
 +-<tests>                      # source code for 'numbers' JUnit tests
 |  +-<numbers>
      +--Matchers.java
      +--Numbers_1_sum_Tests.java
      +--Numbers_2_sum_positive_even_Tests.java
      +--Numbers_3_sum_recursion_Tests.java
      +--Numbers_4_find_first_Tests.java
      +--Numbers_5_find_last_Tests.java
      +--Numbers_6_find_all_Tests.java
      +--Numbers_7a_find_sums_Tests.java
      +--Numbers_7b_find_sums_duplicates_Tests.java
      +--Numbers_8a_find_all_sums_Tests.java
      +--Numbers_8b_find_all_sums_XL_Tests.java
```



&nbsp;

## 2. *Setup* and *Build* Project

*Setup* of the project is assembling content from branches:

```sh
cd se1-play                 # change into the project directory

git status                  # make sure workspace is clean
```
```
On branch main
nothing to commit, working tree clean
```

```sh
# set remote URL to repository to fetch branches
git remote add se1-play-repo https://github.com/sgra64/se1-play.git

git remote -v               # show the new remote URL

# fetch branch 'b1-numbers' from remote repository 'se1-play-repo'
git fetch se1-play-repo b1-numbers
```
```
remote: Enumerating objects: 18, done.
remote: Counting objects: 100% (18/18), done.
remote: Compressing objects: 100% (16/16), done.
remote: Total 18 (delta 1), reused 17 (delta 0), pack-reused 0 (from 0)
Unpacking objects: 100% (18/18), 16.54 KiB | 164.00 KiB/s, done.
From https://github.com/sgra64/se1-play
 * branch            b1-numbers -> FETCH_HEAD
 * [new branch]      b1-numbers -> se1-play-repo/b1-numbers
```

*Fetch* only downloads content into the local *git* repository. It does not
merge it (*pull* does).

```sh
find src                    # verify content of 'src' before the merge
```
```
src
src/application
src/application/Application.java
src/application/package-info.java
src/module-info.java
```

Next, merge content from the fetched branch into the `main` branch:

```sh
# merge content of branch 'b1-numbers' into the 'main' branch of the project
# '-m' specifies the message for the resulting merge commit
# '--allow-unrelated-histories' allows merging from a repository with no shared history
# '--strategy-option theirs' resolves merge conflicts favoring incoming changes
# 
git merge se1-play-repo/b1-numbers \
    -m "merge branch se1-play-repo/b1-numbers" \
    --allow-unrelated-histories \
    --strategy-option theirs
```

The merge reports changes made to the `src` folder:

```
Auto-merging src/application/Application.java
Merge made by the 'ort' strategy.
 README.md                        | 1416 ++++++++++++++++++++++++++++++++++++++
 src/application/Application.java |   13 +-
 src/application/Runner.java      |   15 +
 src/numbers/Numbers.java         |  114 +++
 src/numbers/NumbersData.java     |   54 ++
 src/numbers/NumbersRunner.java   |  177 +++++
 6 files changed, 1785 insertions(+), 4 deletions(-)
 create mode 100644 README.md
 create mode 100644 src/application/Runner.java
 create mode 100644 src/numbers/Numbers.java
 create mode 100644 src/numbers/NumbersData.java
 create mode 100644 src/numbers/NumbersRunner.java
```

New content has arrived:

```sh
find src                    # verify new content of 'src' after the merge
```
```
src
src/application
src/application/Application.java    <-- modified
src/application/package-info.java
src/application/Runner.java         <-- new
src/module-info.java
src/numbers                         <-- new
src/numbers/Numbers.java            <-- new
src/numbers/NumbersData.java        <-- new
src/numbers/NumbersRunner.java      <-- new
```

A new commit has been created on the `main` branch:

```sh
git log --oneline           # show commit log after the merge
```
```
4a85589 (HEAD -> main) merge branch se1-play-repo/b1-numbers    <-- new merge commit
a2657c7 (tag: t4) add junit tests
9d78427 (tag: t3) add src
06dc610 (tag: t2) add .gitmodules
7294cc9 (tag: t1) add .gitignore
2d33392 (tag: root) root commit (empty)
f00adf4 (se1-play-repo/b1-numbers) add numbers src
6d070b4 add README.md
72f10ab add .gitignore
8106dc6 root commit (empty)
```

*Build* the project (no compile errors should appear):

```sh
mk compile                  # compile sources
```



&nbsp;

## 3. Implement *Sum()*

Interface: [*Numbers.java*](src/numbers/Numbers.java) defines a function
`sum(int[] numbers)`:

```java
/**
 * Interface defining functions for the <i>"b1-numbers"</i> assignment.
 */
public interface Numbers {

    /**
     * Aufgabe 1.) Calculate sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    int sum(int[] numbers);
}
```

Create a new, non-public implementation class `NumbersImpl.java` in package `numbers`
that implements the [*Numbers.java*](src/numbers/Numbers.java) interface.

Generate method stubs with the IDE for all interface methods such that `NumbersImpl.java`
compiles.

Next, return an instance of class `NumbersImpl.java` in interface `Numbers.java` in
the `getInstance()`-method:

```java
/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Static getter method that returns an instance of the {@link Numbers} interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Numbers'. Create an implementation class and return.");
        // return new NumbersImpl();
    }
}
```

Next, implement the first method `int sum(int[] numbers);` in the implementation class.

Class: [*NumbersData.java*](src/numbers/NumbersData.java) defines some numbers
data sets with names: *numb*, *numb_1*, *numb_2* and *numb_3*:

<!-- @@ src.numbers.NumbersData.java @BEGIN -->
```java
package numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
     * Constructor.
     */
    NumbersData() {
        super.put("numb", numb);
        super.put("numb_1", numb_1);
        super.put("numb_2", numb_2);
        super.put("numb_3", numb_3);
    }

    /**
     * Retrieve data from *key* (name) and return as {@code int[]}. Return
     * empty data (not null) if *key* is not found.
     * @param key name of data set to return
     * @return return data set as {@code int[]}
     */
    public int[] getArr(String key) {
        return (Optional.ofNullable(get(key))
            .orElse(List.of())) // empty data set
            .stream().mapToInt(i->i).toArray(); // convert to int[]
    }
}
```
<!-- @@ src.numbers.NumbersData.java @END -->

Rebuild the project and run the program with calculations specified on the
command line and executed by class:
[*NumbersRunner.java*](src/numbers/NumbersRunner.java).

```sh
mk run sum numb \
    sum numb_1 \
    sum numb_2 \
    sum numb_3
```

Output shows the correct results of sums of the data sets:

```
Hello, se1.play (modular)
 - sum(numb) -> 30
 - sum(numb_1) -> 50
 - sum(numb_2) -> 10984
 - sum(numb_3) -> 141466
```



&nbsp;

## 4. *JUnit* - Tests

After `sum(int[] numbers)` has been implemented, *JUnit*-tests can verify
its correct function.

Read about *JUnit-tests:*

- Carsten Gips, [*Testen mit JUnit5*](https://www.hsbi.de/elearning/data/FH-Bielefeld/lm_data/lm_1359639/testing/junit-basics.html).

- Tobias Trelle, [*JUnit 5*](https://www.codecentric.de/wissens-hub/blog/junit5-junit-5).


&nbsp;

Install test class `Numbers_1_sum_Tests.java` in package `tests/numbers`:

<!-- @@ tests.numbers.Numbers_1_sum_Tests.java @BEGIN -->
```java
package numbers;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class of Numbers class, sum() method.
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_1_sum_Tests {

    /*
     * tested object is an instance of the Numbers class
     */
    private static Numbers testObj;

    /*
     * tested object is an instance of the Numbers class
     */
    private static NumbersData testData;


    /**
     * Static setup method executed once for all tests. Creates
     * the test object.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeTests() throws Exception {
        testObj = Numbers.getInstance();
        testData = new NumbersData();
    }

    @Test
    @Order(100)
    void test100_sum_regular() {
        assertEquals(30, testObj.sum(testData.getArr("numb")));
    }

    @Test
    @Order(101)
    void test101_sum_regular() {
        assertEquals(50, testObj.sum(testData.getArr("numb_1")));
    }

    @Test
    @Order(102)
    void test102_sum_regular() {
        assertEquals(10984, testObj.sum(testData.getArr("numb_2")));
    }

    @Test
    @Order(103)
    void test103_sum_regular() {
        assertEquals(141466, testObj.sum(testData.getArr("numb_3")));
    }
}
```
<!-- @@ tests.numbers.Numbers_1_sum_Tests.java @END -->

Verify the correct location:

```sh
find tests                  # show tests
```
```
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/numbers
tests/numbers/Numbers_1_sum_Tests.java              <-- new
```

Compile tests and run:

```sh
mk compile-tests                # compile JUnit tests
mk run-tests                    # run JUnit-tests (all tests found)

# or run specific tests specified by the '-c' flag
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c application.Application_0_always_pass_Tests \
  -c numbers.Numbers_1_sum_Tests
```

Output:

```
java -cp "$JUNIT_CLASSPATH" \
  org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  --scan-class-path
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

Test run finished after 177 ms
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

&nbsp;

Install from branch
[*b1-numbers-tests*](../../tree/b1-numbers-tests/tests/numbers)
files (only these two):

- [*Matchers.java*](../../tree/b1-numbers-tests/tests/numbers/Matchers.java)

- [*Numbers_1_sum_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_1_sum_Tests.java)

into the proper place in the project (under: *tests/numbers* ).



&nbsp;

## 5. Implement *Numbers*-Functions

Implement [*Numbers.java*](src/numbers/Numbers.java) functions, one after another:

1. `sum(int[] numbers)` (already done).

1. `sum_positive_even_numbers(numbers[])`, [link](#2-sum_positive_even_numbers).

1. `sum_recursive(numbers[], int i)`, [link](#3-sum_recursive).

1. `findFirst(numbers[], int x)`, [link](#4-findfirst).

1. `findLast(numbers[], int x)`, [link](#5-findlast).

1. `findAll(numbers[], int x)`, [link](#6-findall).

1. `findSums(numbers[], int sum)`, [link](#7-findsums).

1. `findAllSums(numbers[], int sum)`, [link](#8-findallsums).

1. `findAllSums(), XXL`, [link](#9-findallsums-xxl).


Interface: [*Numbers.java*](src/numbers/Numbers.java) defines eight functions:

<!-- @@ src.numbers.Numbers.java @BEGIN -->
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
     * Aufgabe 1.) Calculate sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    int sum(int[] numbers);

    /**
     * Aufgabe 2.) Calculate sum of positive even numbers[].
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    int sum_positive_even_numbers(int[] numbers);

    /**
     * Aufgabe 3.) Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    int sum_recursive(int[] numbers, int i);

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
     * Aufgabe 7.) Return all pairs (a, b) in numbers[] with a + b = sum with
     * consolidating mirror copies such as (a, b) and (b, a) by returning
     * either (a, b) or (b, a), not both.
     * @param numbers input
     * @param sum to find
     * @return Set of all Pairs (a, b) that add to sum
     */
    Set<Pair> findSums(int[] numbers, int sum);

    /**
     * Aufgabe 8.) Find all combinations of numbers in numbers[] that add to sum.
     * @param numbers input
     * @param sum to find
     * @return set of all combinations of numbers that add to sum or empty list
     */
    Set<Set<Integer>> findAllSums(int[] numbers, int sum);


    /**
     * Static getter method that returns an instance of the {@link Numbers} interface.
     * @return instance of the {@link Numbers} interface
     */
    static Numbers getInstance() {
        // throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
        //     + "in interface 'Numbers'. Create an implementation class and return.");
        return new NumbersImpl();
    }

    /**
     * Factory method that creates instance of the {@link Runner} interface.
     * @param numbers instance of the {@link Numbers} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Numbers numbers) {
        return new NumbersRunner(numbers);
    }
}
```
<!-- @@ src.numbers.Numbers.java @END -->

After you are done with a function,

- install the corresponding JUnit test from:
    [*tests/numbers*](../../tree/b1-numbers-tests/tests/numbers).

- Also install file: [*Matchers.java*](../../tree/b1-numbers-tests/tests/numbers/Matchers.java).



&nbsp;

### 2. *sum_positive_even_numbers()*

Function: `sum_positive_even_numbers(numbers[], int i)` returns the sum of
only positive and even numbers from `numbers[]`.

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
[*Numbers_2_sum_positive_even_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_2_sum_positive_even_Tests.java)
and run the test:

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_2_sum_positive_even_Tests
```

Output shows all 200's tests passing:

```
├─ JUnit Jupiter ✔
|  |
│  └─ Numbers_2_sum_positive_even_Tests ✔
│     ├─ test200_sum_positive_even_numbers_regular() ✔
│     ├─ test201_sum_positive_even_numbers_regular() ✔
│     ├─ test202_sum_positive_even_numbers_regular() ✔
│     └─ test203_sum_positive_even_numbers_regular() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
[*Numbers_3_sum_recursion_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_3_sum_recursion_Tests.java)
and run the test:

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_3_sum_recursion_Tests
```

Output shows 300's tests passing:

```
├─ JUnit Jupiter ✔
│  └─ Numbers_3_sum_recursion_Tests ✔
│     ├─ test300_sum_recursion_regular() ✔
│     ├─ test301_sum_recursion_regular() ✔
│     ├─ test302_sum_recursion_regular() ✔
│     └─ test303_sum_recursion_regular() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
[*Numbers_4_find_first_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_4_find_first_Tests.java):

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_4_find_first_Tests
```

Output shows 400's tests passing:

```
├─ JUnit Jupiter ✔
│  └─ Numbers_4_find_first_Tests ✔
│     ├─ test400_find_first_regular() ✔
│     ├─ test401_find_first_regular() ✔
│     ├─ test402_find_first_regular() ✔
│     └─ test403_find_first_regular() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
[*Numbers_5_find_last_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_5_find_last_Tests.java):

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_5_find_last_Tests
```

Output shows 500's tests passing:

```
├─ JUnit Jupiter ✔
│  └─ Numbers_5_find_last_Tests ✔
│     ├─ test500_find_last_regular() ✔
│     ├─ test501_find_last_regular() ✔
│     ├─ test502_find_last_regular() ✔
│     └─ test503_find_last_regular() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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

Add and run tests
[*Numbers_6_find_all_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_6_find_all_Tests.java):

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_6_find_all_Tests
```

Output shows 600's tests passing:

```
├─ JUnit Jupiter ✔
│  └─ Numbers_6_find_all_Tests ✔
│     ├─ test600_find_all_regular() ✔
│     ├─ test601_find_all_regular() ✔
│     ├─ test602_find_all_regular() ✔
│     └─ test603_find_all_regular() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
 - findSums(numb_1, sum=10) -> [(2,8)], solutions: 1
 - findSums(numb_1, sum=12) -> [(5,7), (4,8), (2,10)], solutions: 3
 - findSums(numb_1, sum=15) -> [(7,8), (5,10)], solutions: 2
 - findSums(numb_3, sum=500) -> [(7,493), (485,15)], solutions: 2
```

Add and run tests
[*Numbers_7a_find_sums_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_7a_find_sums_Tests.java)
and
[*Numbers_7b_find_sums_duplicates_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java):

Leave out tests `7b` if duplicate tests are not passing.

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_7a_find_sums_Tests \
  -c numbers.Numbers_7b_find_sums_duplicates_Tests
```

Output shows 700's tests passing:

```
├─ JUnit Jupiter ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  └─ test706_find_sums_regular() ✔
|  |
│  └─ Numbers_7b_find_sums_duplicates_Tests ✔
│     ├─ test710_find_sums_duplicates() ✔
│     ├─ test711_find_sums_same_duplicates() ✔
│     ├─ test712_find_sums_mirror_duplicates() ✔
│     └─ test713_find_sums_regular_duplicates() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
[*Numbers_8a_find_all_sums_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_8a_find_all_sums_Tests.java):

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_8a_find_all_sums_Tests
```

Output shows 800(a)'s tests passing:

```
├─ JUnit Jupiter ✔
│  ├─ Numbers_8a_find_all_sums_Tests ✔
│  │  ├─ test800_find_all_sums_regular() ✔
│  │  └─ test821_find_all_sums_regular_nbrs_2_sum999() ✔
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
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
[*Numbers_8b_find_all_sums_XL_Tests.java*](../../tree/b1-numbers-tests/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java):

```sh
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
  -c numbers.Numbers_8b_find_all_sums_XL_Tests
```

Output:

```
├─ JUnit Jupiter ✔
|  |
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
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔
```



&nbsp;

## Final Result

The final result will show all tests passing. Leave out tests that are
not passing:

<img src="https://gitlab.bht-berlin.de/sgraupner/playground/-/raw/main/markup/img/numbers_junit_vscode_3.png" width="800"/>

```sh
mk run-tests                    # run all tests
```

Or run tests selectively (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
java -cp "$JUNIT_CLASSPATH" org.junit.platform.console.ConsoleLauncher $JUNIT_OPTIONS \
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

Output with all tests passing:

```
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Numbers_1_sum_Tests ✔
│  │  ├─ test100_sum_regular() ✔
│  │  ├─ test101_sum_regular() ✔
│  │  ├─ test102_sum_regular() ✔
│  │  └─ test103_sum_regular() ✔
│  ├─ Numbers_2_sum_positive_even_Tests ✔
│  │  ├─ test200_sum_positive_even_numbers_regular() ✔
│  │  ├─ test201_sum_positive_even_numbers_regular() ✔
│  │  ├─ test202_sum_positive_even_numbers_regular() ✔
│  │  └─ test203_sum_positive_even_numbers_regular() ✔
│  ├─ Numbers_3_sum_recursion_Tests ✔
│  │  ├─ test300_sum_recursion_regular() ✔
│  │  ├─ test301_sum_recursion_regular() ✔
│  │  ├─ test302_sum_recursion_regular() ✔
│  │  └─ test303_sum_recursion_regular() ✔
│  ├─ Numbers_4_find_first_Tests ✔
│  │  ├─ test400_find_first_regular() ✔
│  │  ├─ test401_find_first_regular() ✔
│  │  ├─ test402_find_first_regular() ✔
│  │  └─ test403_find_first_regular() ✔
│  ├─ Numbers_5_find_last_Tests ✔
│  │  ├─ test500_find_last_regular() ✔
│  │  ├─ test501_find_last_regular() ✔
│  │  ├─ test502_find_last_regular() ✔
│  │  └─ test503_find_last_regular() ✔
│  ├─ Numbers_6_find_all_Tests ✔
│  │  ├─ test600_find_all_regular() ✔
│  │  ├─ test601_find_all_regular() ✔
│  │  ├─ test602_find_all_regular() ✔
│  │  └─ test603_find_all_regular() ✔
│  ├─ Numbers_7a_find_sums_Tests ✔
│  │  ├─ test700_find_sums_regular() ✔
│  │  ├─ test701_find_sums_regular() ✔
│  │  ├─ test702_find_sums_regular() ✔
│  │  ├─ test703_find_sums_regular() ✔
│  │  ├─ test704_find_sums_regular() ✔
│  │  ├─ test705_find_sums_regular() ✔
│  │  └─ test706_find_sums_regular() ✔
│  ├─ Numbers_7b_find_sums_duplicates_Tests ✔
│  │  ├─ test710_find_sums_duplicates() ✔
│  │  ├─ test711_find_sums_same_duplicates() ✔
│  │  ├─ test712_find_sums_mirror_duplicates() ✔
│  │  └─ test713_find_sums_regular_duplicates() ✔
│  ├─ Numbers_8a_find_all_sums_Tests ✔
│  │  ├─ test800_find_all_sums_regular() ✔
│  │  └─ test821_find_all_sums_regular_nbrs_2_sum999() ✔
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

Test run finished after 4911 ms
[        14 containers found      ]
[         0 containers skipped    ]
[        14 containers started    ]
[         0 containers aborted    ]
[        14 containers successful ]
[         0 containers failed     ]
[        48 tests found           ]
[         0 tests skipped         ]
[        48 tests started         ]
[         0 tests aborted         ]
[        48 tests successful      ]
[         0 tests failed          ]
```

# Project: *se1-play*, branch *b2-streams*

Assignment *b2-streams* demonstrates the use of the
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html).
Code of this assignment is isolated from the other developments
in the project in branches *main* and *b1-numbers* of the
[*se1-play*](../../tree/main) project.

Steps:

1. [Introduction to the Java *Streams API*](#1-introduction-to-the-java-streams-api)

1. [Branch Structure](#2-branch-structure)

1. [Branch *Setup*](#3-branch-setup)

1. [*Build* and *Run*](#4-build-and-run)

1. [The *Stream* - Interface](#5-the-stream---interface)

1. [Implement: *tenRandomNumbers()*](#6-implement-tenrandomnumbers)

1. [Implement more *Streams*-Functions](#7-implement-more-streams-functions)

1. [Final Tests](#8-final-tests)

1. [Release](#9-release)



&nbsp;

## 1. Introduction to the Java *Streams API*

The
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html)
has been introduced with Java version 8 (2014) to support *data-streams* and *stream-based programming*.

A `Stream` consists of three parts:

1. a `Source` from where data originates,

    - can be a *Collection* (List, Array, ...), a *Range* or a *Supplier* (*Generator*),

1. a concatenated sequence of `Functions` applied to data objects,

    - any *none-terminal* method of the *Java Streams API*, e.g.
        *map()*, *filter()*, *findAny()*,

1. a `Sink` that *pulls data* from the stream producing a *result*

    - any *terminal* method of the *Java Streams API*, e.g.
        *reduce()*, *sum()*, *collect()*, *forEach()*.

<img src="https://s1.o7planning.com/web-rs/web-image/en/arf-1189995-vi.webp" width="600"/>



&nbsp;

## 2. Branch Structure

There are five commits from the previous assignment *A2* on the [*main*](../../tree/main)
branch. Another branch [*b1-numbers*](../../tree/b1-numbers) was added for assignment *B1*.

This assignment will be carried out in a third branch [*b2-streams*](../../tree/b2-streams)
branched off of the same *base* commit.

The commit graph shows the three branches:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-1.png" width="600"/>

Switch to the main branch and show the commit log:

```sh
git switch main                     # switch to branch 'main'

git log --oneline                   # show current commits on branch 'main'
```
```
772bc52 (HEAD -> main, tag: base) branch commit (empty)     <-- base commit
ef51f55 add junit tests                                     <-- commit 5
ff4e2b0 add src                                             <-- commit 4
e7f3fa5 add .gitmodules                                     <-- commit 3
9988b69 add .gitignore                                      <-- commit 2
e38d285 (tag: root) root commit (empty)                     <-- commit 1 (empty root commit)
```

Create a new branch: `b2-streams` and switch to the new branch.



&nbsp;

## 3. Branch *Setup*

The structure of the project directory ("*working tree*") on this branch is:

```sh
<se1-play>              # project directory
 |
 +--.gitignore              # files for git to ignore
 +-- README.md              # this markup file
 | ...
 |
 # content from remote branch: 'b2-streams' with new package 'streams'
 +-<src>                    # source code for 'streams'
 |  +-<applications>            # source code from the 'main'-branch
 |  +-<streams>                 # new package pulled from remote branch: 'b2-streams'
 |    +--Streams.java           # interface of methods to implement
 |    +--StreamsRunner.java     # driver that runs code
 |    +--package-info.java      # package documentation
 |
 # content from remote branch: 'b2-streams-tests' to add JUnit tests
 +-<tests>                  # code for 'streams' JUnit tests
    +-<applications>            # tests from the 'main'-branch
    +-<streams>                 # tests pulled from remote branch: 'b2-streams-tests'
      +--Streams_1_tenRandomNumbers_Tests.java
      +--Streams_2_tenEvenRandomNumbers_Tests.java
      +--Streams_3_tenSortedEvenRandomNumbers_Tests.java
      +--Streams_4_filteredNumbers_Tests.java
      +--Streams_5_filteredNames_Tests.java
      +--Streams_6_sortedNames_Tests.java
      +--Streams_7_sortedNamesByLength_Tests.java
      +--Streams_8_calculateOrderValue_Tests.java
      +--Streams_9_sortByOrderValue_Tests.java
```

Test that the URL of the remote repository has been set:

```sh
git remote -v
```
```
se1-play-repo   https://github.com/sgra64/se1-play.git (fetch)
se1-play-repo   https://github.com/sgra64/se1-play.git (push)
```

Set the URL of the remote repository if not:

```sh
# set URL to repository to fetch remote branches
git remote add se1-play-repo https://github.com/sgra64/se1-play.git
```

Two methods exist to pull content from the remote branch *b2-streams*:

- `fetch`, `merge` and `commit` or

- `pull` and `commit` ("*pull*" combines "*fetch*" and "*merge*").

Select one method to obtain content from the remote branch *b2-streams*:

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-play-repo b2-streams

# merge content of branch 'b2-streams' into the 'main' branch of the project with:
# '--squash' combine all incoming commits into one local commit
# '--allow-unrelated-histories' allows merging from a repository with no shared history
# '--strategy-option theirs' resolves merge conflicts favoring incoming changes
# 
git merge se1-play-repo/b2-streams \
    --squash \
    --allow-unrelated-histories \
    --strategy-option theirs
```

Alternatively, one can "*pull*" content from the remote branch. Mind the similarity
to the prior method:

```sh
# pull remote branch 'b2-streams'
git pull se1-play-repo b2-streams \
    --squash --allow-unrelated-histories --strategy-option theirs
```

In both cases, the *merge* is *open* with uncommitted changes:

```sh
git status                      # show status of open merge
```

*Git* shows new or modified files with green lines (staged) that have
not yet been committed:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-merge.png" width="600"/>


The *merge* can now be committed:

```sh
# commit the open merge
git commit -m "pull branch se1-play-repo/b2-streams"
```
```
[b2-streams 50afb96] pull branch se1-play-repo/b2-streams
 5 files changed, 336 insertions(+), 5 deletions(-)
 create mode 100644 src/application/Runner.java
 create mode 100644 src/streams/Streams.java
 create mode 100644 src/streams/StreamsRunner.java
 create mode 100644 src/streams/package-info.java
```



&nbsp;

## 4. *Build* and *Run*

*Build* the project and *run* the program:

```sh
mk compile run                  # build and run the program
```
```
Hello, se1.play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Streams'. Create an implementation class
and return.
        at se1.play/streams.Streams.getInstance(Streams.java:156)
        at se1.play/application.Application.main(Application.java:27)
```

Fix the problem. Consider how the problem was solved in
[*Step 4*](../../tree/b1-numbers?tab=readme-ov-file#4-build-and-run-the-project)
of the
[*B1 Numbers*](../../tree/b1-numbers) assignment.

*Build* and *run* the program again:

```sh
mk compile run                  # re-build and run the program
```
```
Hello, se1.play (modular)
```

Commit the state of the project with:

- commit message: `"add implementation class StreamsImpl.java"`

```sh
git log --oneline               # show commit log
```

Branch *b2-streams* has advanced showing the new commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-streamsimpl-added.png" width="600"/>

<!-- 
```
d95ae7c (HEAD -> b2-streams) add implementation class StreamsImpl.java
98cbaaa Streams.java, getInstance() returning implementation class
50afb96 pull branch se1-play-repo/b2-streams
772bc52 (tag: base, main) branch commit (empty) <-- base commit
cf8df12 add junit tests                         <-- prior commits
f082c29 add src
0d357a8 add .gitmodules
5c0e8fa add .gitignore
96fc66b (tag: root) root commit (empty)
```
-->



&nbsp;

## 5. The *Stream* - Interface

Interface [*src/streams/Streams.java*](../../tree/b2-streams/src/streams/Streams.java)
defines methods to implement in this assignment using the *Java Stream API*:

```java
package streams;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import application.Runner;


/**
 * Public interface with functions to implement for the <i>"b2-streams"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Streams {

    /**
     * Aufgabe 1: Return 10 random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 random numbers can be drawn
     */
    Stream<Integer> tenRandomNumbers();

    /**
     * Aufgabe 2: Return 10 even random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 even random numbers can be drawn
     */
    Stream<Integer> tenEvenRandomNumbers();

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 sorted even random numbers can be drawn
     */
    Stream<Integer> tenSortedEvenRandomNumbers();


    /**
     * Map of filter functions for filteredNumbers().
     * 
     * Add a function for name "prime3" to {@link filterFunctions} that returns
     * true for three-digit prime numbers.
     */
    static Map<String, Function<Integer, Boolean>> filterFunctions = Map.of(
        "even", n -> n % 2 == 0,    // filter even numbers
        "div3", n -> n % 3 == 0,    // filter numbers divisible by three
        "prime3", n -> true         // add: filter for three-digit prime numbers
    );

    /**
     * Aufgabe 4: Apply a function from map {@link filterFunctions} to a stream
     * of random integer numbers in the range [0..999] returning only numbers
     * matching the selected filter.
     * @param filter name of the filter function in {@link filterFunctions}
     * @param limit maximum amount of numbers returned
     * @return numbers matching the selected filter
     */
    List<Integer> filteredNumbers(String filter, int limit);


    /*
     * Names used in methods below.
     */
    static final List<String> names = List.of(
        "Hendricks", "Raymond", "Pena", "Gonzalez", "Nielsen", "Hamilton",
        "Graham", "Gill", "Vance", "Howe", "Ray", "Talley", "Brock", "Hall",
        "Gomez", "Bernard", "Witt", "Joyner", "Rutledge", "Petty", "Strong",
        "Soto", "Duncan", "Lott", "Case", "Richardson", "Crane", "Cleveland",
        "Casey", "Buckner", "Hardin", "Marquez", "Navarro"
    );

    /**
     * Aufgabe 5: Return a sub-list of names filtered by a regular expression
     * (see: {@link java.util.regex.Pattern}). The order of names remains unchanged.
     * @param names input names
     * @param regex regular expression according to {@link java.util.regex.Pattern}
     * @return list of names matching the regular expression
     */
    List<String> filteredNames(List<String> names, String regex);

    /**
     * Aufgabe 6: Return names alphabetically sorted up to a given limit.
     * @param names input names
     * @param limit maximum number of names returned
     * @return alphabetically sorted list of names up to the given limit
     */
    List<String> sortedNames(List<String> names, int limit);

    /**
     * Aufgabe 7: Return names sorted by name length as first criteria and
     * within same-length names alphabetically sorted as second criteria.
     * @param names input names
     * @return names sorted by name length
     */
    List<String> sortedNamesByLength(List<String> names);


    /**
     * Aufgabe 8: Class {@link Order} defines an order (Bestellung) of
     * n (units) of an article at a price per unit (in Cent).
     */
    class Order {
        private final String article;
        private final long units;
        private final long unitPrice;
        //
        public Order(String description, long units, long unitPrice) {
            this.article = description;
            this.units = units;
            this.unitPrice = unitPrice;
        }

        // getter methods
        public String article() { return article; }

        public long units() { return units; }

        public long unitPrice() { return unitPrice; }

        // text conversion method
        public String toString() {
            return String.format("%-7s %dx %4d = %6d", article + ",", units, unitPrice, units * unitPrice);
        }
    }

    /*
     * Orders used in methods below.
     */
    static final List<Order> orders = List.of(
        new Order("Becher", 2,  199),   // 2x  199 =  398
        new Order("Tasse",  7,  249),   // 7x  249 = 1743
        new Order("Stift",  4,   49),   // 4x   49 =  196
        new Order("Vase",   2,  999),   // 2x  999 = 1998
        new Order("Kanne",  5, 1499),   // 5x 1499 = 7495
        new Order("Lampe",  2, 1999),   // 2x 1999 = 3998
        new Order("Messer", 6,  789)    // 6x  789 = 4734
    );                                  // Summe:   20562 = 205,62€

    /**
     * Aufgabe 8: Calculate the total value of all orders.
     * @param orders list of orders to process
     * @return total value of orders
     */
    long calculateOrderValue(List<Order> orders);

    /**
     * Aufgabe 9: Return a list of orders sorted by order value (highest-value first).
     * @param orders list of orders to sort
     * @return orders sorted by order value (highest-value first)
     */
    List<Order> sortOrdersByValue(List<Order> orders);


    /**
     * Static getter method that returns an instance of an implementation class of
     * the {@link Streams} interface.
     * @return instance of an implementation class of the {@link Streams} interface
     */
    static Streams getInstance() {
        throw new UnsupportedOperationException("Unimplemented method 'getInstance()' "
            + "in interface 'Streams'. Create an implementation class and return.");
        // return new StreamsImpl();
    }

    /**
     * Factory method that creates instance of the {@link Runner} interface.
     * @param streams instance of the {@link Streams} interface used by the runner
     * @return instance of the {@link Runner} interface
     */
    static Runner createRunner(Streams streams) {
        return new StreamsRunner(streams);
    }
}
```



&nbsp;

## 6. Implement: *tenRandomNumbers()*

Implement the first method: *tenRandomNumbers()* in your implementation class.
The method should create a *Stream source* that generates a random number in
the range `[0..1000]` at each invocation with a limit of 10 numbers.

Rebuild and try the implementation:

```sh
mk compile run tenRandomNumbers
```
```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [275, 24, 206, 757, 283, 103, 180, 863, 975, 659]
```

Run with multiple function calls:

```sh
mk run tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers \
    tenRandomNumbers
```

Output will produce 4 sets of 10 random numbers in the range `[0..1000]`:

```
Hello, se1.play (modular)
 - tenRandomNumbers() -> [617, 546, 22, 470, 81, 796, 575, 124, 723, 312]
 - tenRandomNumbers() -> [274, 356, 844, 854, 502, 563, 29, 141, 310, 186]
 - tenRandomNumbers() -> [70, 994, 376, 664, 752, 719, 958, 415, 611, 899]
 - tenRandomNumbers() -> [178, 437, 686, 299, 199, 761, 28, 221, 218, 87]
```

Add JUnit-tests for the method. Fetch *JUnit-tests* from the remote branch
`b2-streams-tests`:

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-play-repo b2-streams-tests
```
```
remote: Enumerating objects: 14, done.
remote: Counting objects: 100% (2/2), done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 14 (delta 0), reused 2 (delta 0), pack-reused 12 (from 1)
Unpacking objects: 100% (14/14), 4.88 KiB | 31.00 KiB/s, done.
From github.com:sgra64/se1-play
 * branch            b2-streams-tests -> FETCH_HEAD
 * [new branch]      b2-streams-tests -> se1-play-repo/b2-streams-tests
```

A local copy of the remote branch was created. Show the new remote brach:

```sh
git branch -avv             # show all branches stored in the local git repository
```

The new remote branch `b2-streams-tests` is shown among other branches. It is not
yet merged into the current branch.

```
remotes/se1-play-repo/b2-streams-tests f184a3f update .gitignore, added .tgz
```

Create (restore) the *JUnit-test* for method *tenRandomNumbers()* in the current branch
from the remote branch:

```sh
git restore --source se1-play-repo/b2-streams-tests -- \
    tests/streams/Streams_1_tenRandomNumbers_Tests.java

find tests                  # show the new test under 'tests/streams'
```
```
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/streams
tests/streams/Streams_1_tenRandomNumbers_Tests.java
```

Compile and run tests:

```sh
mk clean compile compile-tests      # re-build the project with tests

mk run-tests                        # run tests
```
```
╷
├─ JUnit Jupiter ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  └─ Streams_1_tenRandomNumbers_Tests ✔
│     └─ test100_tenRandomNumbers_regular() ✔      <-- new test
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 235 ms
[         3 tests successful      ]
[         0 tests failed          ]
```

Run *JUnit-tests* also in the IDE.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/vscode-tests-tenRandomNumbers.png" width="600"/>


&nbsp;

When tests are passing, commit the state of the implemented method *tenRandomNumbers()*
to branch *b2-streams* with message:

- commit message: `"tenRandomNumbers() completed"`

Branch *b2-streams* has advanced showing the new commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-tenRandomNumbers.png" width="600"/>


The commit should only contain the affected files:

1. the modified implementation class: `StreamsImpl.java` and

1. the new test: `Streams_1_tenRandomNumbers_Tests.java`.

Verify by comparing the two last commits:

```sh
git diff HEAD~1..HEAD --name-status     # compare the two last commits
```
```
M  src/streams/StreamsImpl.java                         <-- M: modified file
A  tests/streams/Streams_1_tenRandomNumbers_Tests.java  <-- A: added file
```



&nbsp;

## 7. Implement more *Streams*-Functions

Implement the other methods of the *Streams.java* interface one after the other.

Commit each implemented function together with the corresponding *JUnit-test*.


<!--
```java
/**
 * Return even numbers from input in ascending order with duplicates
 * removed.
 * @param input numbers
 * @return even numbers in ascending order with no duplicates
 */
List<Integer> sortedEvenNumbersNoDuplicates(List<Integer> input) {
    return input.stream()           // Source: input list as stream source
        .filter(n -> n % 2 == 0)    // only even numbers pass
        .distinct()                 // remove duplicates from stream
        .sorted()                   // sort stream
        .toList();                  // Sink: collect results as List<Integer>
}
```
-->


<!-- Output of implemented methods:

```
- tenRandomNumbers():
    -> [339, 629, 521, 308, 979, 264, 725, 8, 819, 603]

- tenEvenRandomNumbers():
    -> [806, 182, 172, 510, 110, 294, 880, 212, 698, 814]

- tenSortedEvenRandomNumbers():
    -> [20, 48, 64, 188, 480, 692, 852, 890, 956, 990]

- filteredNumbers("even", 15):  // 15 random even numbers
    -> [266, 218, 366, 744, 840, 914, 170, 834, 86, 182, 966, 284, 98, 886, 24]

- filteredNumbers("div3", 15):  // 15 random numbers divisible by 3
    -> [657, 849, 885, 669, 177, 30, 195, 474, 963, 867, 513, 6, 366, 45, 288]

- filteredNumbers("prime3", 15):        // 15 random two-digit prime numbers
    -> [599, 839, 541, 167, 157, 457, 197, 233, 491, 941, 661, 977, 461, 491, 16
3]

- filteredNames(names, ".*ez$"):        // names ending with "ez"
    -> [Gonzalez, Gomez, Marquez]

- sortedNames(names, 8):        // first 8 names from sorted name list
    -> [Bernard, Brock, Buckner, Case, Casey, Cleveland, Crane, Duncan]

- sortedNamesByLength(names):   // names sorted by length
    -> [Ray, Case, Gill, Hall, Howe, Lott, Pena, Soto, Witt, Brock, Casey, Crane
, Gomez, Petty, Vance, Duncan, Graham, Hardin, Joyner, Strong, Talley, Bernard,
Buckner, Marquez, Navarro, Nielsen, Raymond, Gonzalez, Hamilton, Rutledge, Cleve
land, Hendricks, Richardson]

- calculateValue(orders):
    -> 20562

- sortOrdersByValue(orders):    // orders sorted by value
    ->
    - Kanne,  5x 1499 =   7495
    - Messer, 6x  789 =   4734
    - Lampe,  2x 1999 =   3998
    - Vase,   2x  999 =   1998
    - Tasse,  7x  249 =   1743
    - Becher, 2x  199 =    398
    - Stift,  4x   49 =    196
                      --------
                         20562
                      ========
done.
``` -->



&nbsp;

## 8. Final Tests

The resulting commit log on branch b2-streams will be:

```
ec7204c (HEAD -> b2-streams) sortedNamesByLength() completed
a4dc903 sortedNames() completed
923850a filteredNames() completed
2452bdf filteredNumbers() completed
34edba0 tenSortedEvenRandomNumbers() completed
392da89 tenEvenRandomNumbers() completed
153ea39 tenRandomNumbers() completed                <-- commit with tenRandomNumbers() completed
d95ae7c add implementation class StreamsImpl.java
98cbaaa Streams.java, getInstance() returning implementation class
50afb96 pull branch se1-play-repo/b2-streams
772bc52 (tag: base, main) branch commit (empty)     <-- base commit

cf8df12 add junit tests                             <-- prior commits
f082c29 add src
0d357a8 add .gitmodules
5c0e8fa add .gitignore
96fc66b (tag: root) root commit (empty)
```

The final result will show all tests passing. Leave out tests that are
not passing.

```sh
mk run-tests                    # run all tests
```

Or run tests selectively (remove tests that are failing):

```sh
mk clean compile compile-tests run-tests
```

<!-- ```sh
# or run tests selectively (remove tests that are failing)
java $(eval echo $JUNIT_CLASSPATH) org.junit.platform.console.ConsoleLauncher\
  $(eval echo $JUNIT_OPTIONS) \
  -c application.Application_0_always_pass_Tests \
  -c streams.Streams_1_tenRandomNumbers_Tests \
  -c streams.Streams_2_tenEvenRandomNumbers_Tests \
  -c streams.Streams_3_tenSortedEvenRandomNumbers_Tests \
  -c streams.Streams_4_filteredNumbers_Tests \
  -c streams.Streams_5_filteredNames_Tests \
  -c streams.Streams_6_sortedNames_Tests \
  -c streams.Streams_7_sortedNamesByLength_Tests \
  -c streams.Streams_8_calculateOrderValue_Tests \
  -c streams.Streams_9_sortByOrderValue_Tests
``` -->

Output with all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Streams_6_sortedNames_Tests ✔
│  │  ├─ test600_sortedNames_regular() ✔
│  │  ├─ test601_sortedNames_regular() ✔
│  │  ├─ test610_sortedNames_emptyNames() ✔
│  │  ├─ test690_sortedNames_irregularNamesNull() ✔
│  │  ├─ test691_sortedNames_irregularLimitNegativ() ✔
│  │  └─ test692_sortedNames_irregularNamesNullAndLimitNegativ() ✔
│  ├─ Streams_7_sortedNamesByLength_Tests ✔
│  │  ├─ test700_sortedNamesByLength_regular() ✔
│  │  ├─ test710_sortedNamesByLength_emptyNames() ✔
│  │  └─ test790_sortedNamesByLength_irregular_names_Null() ✔
│  ├─ Streams_2_tenEvenRandomNumbers_Tests ✔
│  │  └─ test200_tenEvenRandomNumbers_regular() ✔
│  ├─ Application_0_always_pass_Tests ✔
│  │  ├─ test_001_always_pass() ✔
│  │  └─ test_002_always_pass() ✔
│  ├─ Streams_9_sortByOrderValue_Tests ✔
│  │  ├─ test900_sortByOrderValue_regular() ✔
│  │  ├─ test901_sortByOrderValue_regular() ✔
│  │  ├─ test910_sortByOrderValue_emptyOrders() ✔
│  │  └─ test990_sortByOrderValue_irregular_orders_Null() ✔
│  ├─ Streams_4_filteredNumbers_Tests ✔
│  │  ├─ test400_filteredNumbers_50evenNumbers_regular() ✔
│  │  ├─ test410_filteredNumbers_50divisibleBy3Numbers_regular() ✔
│  │  ├─ test420_filteredNumbers_50primeNumbers_regular() ✔
│  │  ├─ test430_filteredNumbers_different_even_numbers_returned() ✔
│  │  ├─ test431_filteredNumbers_different_div_by_three_numbers_returned() ✔
│  │  ├─ test432_filteredNumbers_different_prime_numbers_returned() ✔
│  │  ├─ test490_filteredNumbers_50evenNumbers_illegalFilter_null() ✔
│  │  ├─ test491_filteredNumbers_50evenNumbers_illegalFilter_empty() ✔
│  │  ├─ test492_filteredNumbers_50evenNumbers_illegalFilter_unknown() ✔
│  │  └─ test495_filteredNumbers_50evenNumbers_illegalLimit_negativ() ✔
│  ├─ Streams_1_tenRandomNumbers_Tests ✔
│  │  └─ test100_tenRandomNumbers_regular() ✔
│  ├─ Streams_3_tenSortedEvenRandomNumbers_Tests ✔
│  │  └─ test300_tenSortedEvenRandomNumbers_regular() ✔
│  └─ Streams_8_calculateOrderValue_Tests ✔
│     ├─ test800_calculateValue_regular() ✔
│     ├─ test801_calculateValue_regular() ✔
│     ├─ test810_calculateValue_emptyOrders() ✔
│     └─ test890_calculateValue_irregular_orders_Null() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 270 ms
[        12 containers found      ]
[         0 containers skipped    ]
[        12 containers started    ]
[         0 containers aborted    ]
[        12 containers successful ]
[         0 containers failed     ]
[        32 tests found           ]
[         0 tests skipped         ]
[        32 tests started         ]
[         0 tests aborted         ]
[        32 tests successful      ]
[         0 tests failed          ]
```



&nbsp;

## 9. Release

When tests are passing, both branches `b1-numbers` and `b2-streams` will be
combined on a new branch named `release-prep` that is used to perform final
tests preping a release.

Create two new branches off the `base`-commit:

- Branch `release-prep` to merge branches `b1-numbers` and `b2-streams` and perform
    final tests.

- Branch `release` to hold the commit of the final release tagged with `"RELEASE-1.0.0"`.

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/img/git-release.png" width="1000"/>


Merge branches `b1-numbers` and `b2-streams` on brach `release-prep` and build the project:

```sh
git switch release-prep                         # switch to merge branch

find src                                        # show merged packages
```
```
src
src/application
src/application/Application.java
src/application/package-info.java
src/application/Runner.java
src/module-info.java
src/numbers                                     <-- merged from branch 'b1-numbers'
src/numbers/Numbers.java
src/numbers/NumbersData.java
src/numbers/NumbersImpl.java
src/numbers/NumbersImplAlgorithms.java
src/numbers/NumbersRunner.java
src/streams                                     <-- merged from branch 'b2-streams'
src/streams/package-info.java
src/streams/Streams.java
src/streams/StreamsImpl.java
src/streams/StreamsRunner.java
```

Build the project:

```sh
mk clean compile compile-tests                  # clean project build
```

Run the project with commands from both packages, *numbers* and *streams:*

```sh
 mk run \
    sum numb \
    findAllSums numb_2 sum=999 \
    tenRandomNumbers
```
```
Hello, se1.play (modular)

 - sum(numb) -> 30

 - findAllSums(numb_2, sum=999) -> [
    - [226, 773],
    - [27, 972],
    - [170, 190, 639],
    - [371, 87, 541],
    - [226, 27, 205, 541],
    - [163, 170, 541, 125],
    - [163, 170, 27, 639],
    - [163, 7, 190, 639],
    - [226, 371, 170, 27, 205],
    - [226, 371, 7, 205, 190],
    - [226, 371, 87, 125, 190],
    - [226, 371, 163, 7, 27, 205],
    - [226, 371, 163, 87, 27, 125]
   ], solutions: 13

 - tenRandomNumbers() -> [634, 247, 240, 213, 700, 802, 576, 516, 202, 966]
```

Perform tests combined from both packages:

```sh
find tests                                      # show merged packages

mk compile-tests                                # compile test packages
```
```
tests/
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/numbers                                   <-- tests merged from branch 'b1-numbers'
tests/numbers/Matchers.java
tests/numbers/Numbers_1_sum_Tests.java
tests/numbers/Numbers_2_sum_positive_even_Tests.java
tests/numbers/Numbers_3_sum_recursion_Tests.java
tests/numbers/Numbers_4_find_first_Tests.java
tests/numbers/Numbers_5_find_last_Tests.java
tests/numbers/Numbers_6_find_all_Tests.java
tests/numbers/Numbers_7a_find_sums_Tests.java
tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
tests/numbers/Numbers_8a_find_all_sums_Tests.java
tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
tests/streams                                   <-- tests merged from branch 'b2-stremas'
tests/streams/Streams_1_tenRandomNumbers_Tests.java
tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
tests/streams/Streams_4_filteredNumbers_Tests.java
tests/streams/Streams_6_sortedNames_Tests.java
tests/streams/Streams_7_sortedNamesByLength_Tests.java
tests/streams/Streams_8_calculateOrderValue_Tests.java
tests/streams/Streams_9_sortByOrderValue_Tests.java
```

Run tests with merged packages:

```sh
mk run-tests                                    # run JUnit tests
```

Tests from both packages are executed.

The complete [*test-log*](test-log.txt) shows 78 tests passing.

```
Test run finished after 478 ms
[        22 containers found      ]
[         0 containers skipped    ]
[        22 containers started    ]
[         0 containers aborted    ]
[        22 containers successful ]
[         0 containers failed     ]
[        78 tests found           ]
[         0 tests skipped         ]
[        78 tests started         ]
[         0 tests aborted         ]
[        78 tests successful      ]  <-- 78 tests passed
[         0 tests failed          ]  <--  0 tests failed
```

When all tests are passing, merge branch `release-prep` to branch `release`
as **one resulting commit** with message `merge final release 1.0.0` and tag
the commit with `"RELEASE-1.0.0"`.

```sh
git switch release                              # switch to merge branch

git log --oneline release                       # show commit log on branch 'release'
```
```
73338a1 (HEAD -> release, tag: RELEASE-1.0.0) merge final release 1.0.0   <-- ***
dc58b3d (tag: base, main) branch commit (empty)
67e6fe4 add junit tests
e725371 add src
60b115f add .gitmodules
2ad47db add .gitignore
2ed321f (tag: root) root commit (empty)
```

`***`: `HEAD -> release` - `HEAD` points to branch `release`. The last commit carries
the message `merge final release 1.0.0` and is tagged with `"RELEASE-1.0.0"`.

Perform a final project re-build and run tests:

```sh
mk clean compile compile-tests
mk run-tests
```
```
[        78 tests successful      ]  <-- 78 tests passed
[         0 tests failed          ]  <--  0 tests failed
```

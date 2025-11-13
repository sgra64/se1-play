<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B2 (SE-1)
-->
# B2: *se1-play*, branch *b2-streams*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

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

1. [Implement Remaining *Streams*-Functions](#7-implement-remaining-streams-functions)

1. [Final Tests](#8-final-tests)

1. [Release](#9-release)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Introduction to the Java *Streams API*

The
[*Java Streams API*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html)
has been introduced with Java version 8 (2014) to support *data-streams* and *stream-based programming*.

A `Stream` consists of three parts:

1. A streams starts with a `Source` from where data originates or is emitted,

    - e.g. a *Collection* (List, Array, ...), a *Range* or a *Supplier*.

1. A sequence of *chained*
    [*functions*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)
    that is applied to each data object passing through the stream,

    - examples: *map()*, *filter()*, *findAny()*, *sorted()*, etc.

1. A `Sink` that *pulls data* from the stream producing a *result* by applying a *terminal*
    [*function*](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Stream.html)

    - such as *reduce()*, *sum()*, *collect()*, *forEach()*.

<img src="https://s1.o7planning.com/web-rs/web-image/en/arf-1189995-vi.webp" width="600"/>


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 2. Branch Structure

This assignment will use a separate branch [*b2-streams*](../../tree/b2-streams)
starting from the same *base* commit.

The commit graph will have three branches:

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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Branch *Setup*

The structure of the project directory ("*working tree*") on this branch is:

```sh
<se1-play>              # project directory
 |
 # content of branch: 'b2-streams' with new package 'streams'
 +-<src>
 |  +-<main>                    # Java source code
 |  |  +--module-info.java          # description of module 'se1.play'
 |  |  |
 |  |  +-<application>              # existing package 'application'
 |  |  |  +--Application.java       # program with main()-method
 |  |  |  +--Runner.java            # new interface
 |  |  |  +--...
 |  |  |
 |  |  +-<streams>                 # new package 'streams' from remote branch 'b2-streams'
 |  |    +--Streams.java           # interface with methods to implement
 |  |    +--StreamsRunner.java     # driver code to run the application from the command line
 |  |    +--package-info.java      # package documentation
 |  | 
 |  |+-<tests>                  # 'streams' test code
 |  |  +-<streams>                  # package 'streams' with unit tests
 |  |     +--Streams_1_tenRandomNumbers_Tests.java
 |  |     +--Streams_2_tenEvenRandomNumbers_Tests.java
 |  |     +--Streams_3_tenSortedEvenRandomNumbers_Tests.java
 |  |     +--Streams_4_filteredNumbers_Tests.java
 |  |     +--Streams_5_filteredNames_Tests.java
 |  |     +--Streams_6_sortedNames_Tests.java
 |  |     +--Streams_7_sortedNamesByLength_Tests.java
 |  |     +--Streams_8_calculateOrderValue_Tests.java
 |  |     +--Streams_9_sortByOrderValue_Tests.java
 |  |
 |  +-<resources>               # none-Java sources, properties files
 |     +--application.properties    # application configuration
 |     +--log4j2.properties         # logger configuration
 |     +-<META-INF>
 |        +--MANIFEST.MF            # packaging information for created .jar
```

Test that the URL of the remote repository has been set:

```sh
git remote -v
```
```
se1-repo   https://github.com/sgra64/se1-play.git (fetch)
se1-repo   https://github.com/sgra64/se1-play.git (push)
```

If the URL is not present, set the URL of the remote repository:

```sh
# set URL to repository to fetch remote branches
git remote add se1-repo https://github.com/sgra64/se1-play.git
```

Two methods exist to pull content from the remote branch *b2-streams*:

- `fetch`, `merge` and `commit` or

- `pull` and `commit` ("*pull*" combines "*fetch*" and "*merge*").

Choose one method to obtain content from the remote branch *b2-streams*
and make it available in the new local branch: `b2-streams`.

```sh
# fetch branch 'b1-numbers' from the remote repository 'se1-play-repo'
git fetch se1-repo b2-streams

# merge content of branch 'b2-streams' into the 'main' branch of the project with:
# '--squash' combine all incoming commits into one local commit
# '--allow-unrelated-histories' allows merging from a repository with no shared history
# '--strategy-option theirs' resolves merge conflicts favoring incoming changes
# 
git merge se1-repo/b2-streams \
    --squash \
    --allow-unrelated-histories \
    --strategy-option theirs
```

Or *"pull"* content (*fetch* and *merge*) from the remote branch. Mind the
similarity to the prior method:

```sh
# pull remote branch 'b2-streams'
git pull se1-repo b2-streams \
    --squash --allow-unrelated-histories --strategy-option theirs
```
```
From github.com:sgra64/se1-play
 * branch            b2-streams -> FETCH_HEAD
Auto-merging src/main/application/Application.java
Squash commit -- not updating HEAD
Automatic merge went well; stopped before committing as requested
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
git commit -m "pull branch se1-repo/b2-streams"
```
```
[b2-streams 50afb96] pull branch se1-play-repo/b2-streams
 5 files changed, 336 insertions(+), 5 deletions(-)
 create mode 100644 src/application/Runner.java
 create mode 100644 src/streams/Streams.java
 create mode 100644 src/streams/StreamsRunner.java
 create mode 100644 src/streams/package-info.java
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. *Build* and *Run*

*Build* the project and *run* the program:

```sh
mk compile run                  # build and run the program
```
```
Hello, se1_play (modular)
Exception in thread "main" java.lang.UnsupportedOperationException: Unimplemente
d method 'getInstance()' in interface 'Streams'. Create an implementation class
and return.
        at se1_play/streams.Streams.getInstance(Streams.java:157)
        at se1_play/application.Application.main(Application.java:27)
```

Solve the problem. Consider how the problem was solved in
[*Step 3*](../../tree/b1-numbers?tab=readme-ov-file#4-build-and-run-the-project)
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
812dd09 (HEAD -> b1) add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. The *Stream* - Interface

Interface [*src/streams/Streams.java*](../../tree/b2-streams/src/streams/Streams.java)
defines methods to implement in this assignment using the *Java Stream API*:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import application.Runner;

/**
 * Public interface with functions for the <i>"b2-streams"</i> assignment.
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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

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
git fetch se1-repo b2-streams-tests
```
```
remote: Enumerating objects: 43, done.
remote: Counting objects: 100% (43/43), done.
remote: Compressing objects: 100% (15/15), done.
remote: Total 42 (delta 25), reused 37 (delta 22), pack-reused 0 (from 0)
Unpacking objects: 100% (42/42), 11.64 KiB | 28.00 KiB/s, done.
From github.com:sgra64/se1-play
 * branch            b2-streams-tests -> FETCH_HEAD
 * [new branch]      b2-streams-tests -> se1-repo/b2-streams-tests
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
git restore --source se1-repo/b2-streams-tests -- \
    src/tests/streams/Streams_1_tenRandomNumbers_Tests.java

find src/tests              # show the new test under 'tests/streams'
```
```
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/streams
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
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

- commit message: `"Aufgabe 1.) Stream<Integer> tenRandomNumbers()"`

Branch *b2-streams* has advanced showing the new commit:
<!-- 
<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-post-tenRandomNumbers.png" width="600"/>
-->
```
6fca743 (HEAD -> b2) Aufgabe 1.) Stream<Integer> tenRandomNumbers()
812dd09 add implementation class StreamsImpl.java
e58c38a pull branch se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
```

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


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. Implement Remaining *Streams*-Functions

Implement the remaining methods of the *Streams.java* interface one after the
other.

Commit each implemented function with the corresponding *JUnit-test*. Commit
only when the test passes.

After completion, the commit-log on branch `b2-streams` shows the following
commits:

```
d27dac1 (HEAD -> b2-streams) Aufgabe 9.) List<Order> sortOrdersByValue(List<Order> orders)
b81471b Aufgabe 8.) long calculateOrderValue(List<Order> orders)
fc3fb82 Aufgabe 7.) List<String> sortedNamesByLength(List<String> names)
3c41f04 Aufgabe 6.) List<String> sortedNames(List<String> names, int limit)
0fddc52 Aufgabe 5.) List<String> filteredNames(List<String> names, String regex)
97d8477 Aufgabe 4.) List<Integer> filteredNumbers(String filter, int limit)
2739663 Aufgabe 3.) Stream<Integer> tenSortedEvenRandomNumbers()
1f6780c Aufgabe 2.) Stream<Integer> tenEvenRandomNumbers()
d75654e Aufgabe 1.) Stream<Integer> tenRandomNumbers()
7e4ae57 merge commit se1-repo/b2-streams
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
...
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 8. Final Tests

The final result will show all tests passing. Leave out tests that are
not passing.

```sh
mk clean compile compile-tests run-tests    # run all tests
```

Or run tests individually (remove tests that are failing):

```sh
# or run tests selectively (remove tests that are failing)
mk run-tests \
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
```

Output with all tests passing:

```
╷
├─ JUnit Jupiter ✔
│  ├─ Streams_5_filteredNames_Tests ✔
│  │  ├─ test500_filteredNames_regular() ✔
│  │  ├─ test590_filteredNames_irregularNamesNull() ✔
│  │  ├─ test591_filteredNames_irregularRegexNull() ✔
│  │  └─ test592_filteredNames_irregularNamesAndRegexNull() ✔
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

Test run finished after 283 ms
[        36 tests successful      ]     <-- 36 tests are passing
[         0 tests failed          ]     <--  0 tests failed
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

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


&nbsp;

### 9.1 Create Branch *"release-prep"*

Merge branch `b1-numbers` to branch `release-prep` as a single commit.

Show *src* to see content of the merged branch has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
src/tests/numbers/Numbers_4_find_first_Tests.java
src/tests/numbers/Numbers_5_find_last_Tests.java
src/tests/numbers/Numbers_6_find_all_Tests.java
src/tests/numbers/Numbers_7a_find_sums_Tests.java
src/tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java
src/tests/numbers/Numbers_8a_find_all_sums_Tests.java
src/tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java
```

Make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[        80 tests successful      ]     <-- 80 tests from 'b1-numbers'
[         0 tests failed          ]     <--  0 tests failed
```

Test the final artifact with example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

If all this works, commit with merge with message `"merge b1-numbers"`.


&nbsp;

### 9.2 Merge branch *"b2-streams"* to Branch *"release-prep"*

Next, merge branch `b2-streams` to branch `release-prep` as single commit.
You will likely receive a *merge-conflict*:

```
Auto-merging src/main/application/Application.java
CONFLICT (content): Merge conflict in src/main/application/Application.java
Automatic merge failed; fix conflicts and then commit the result.
```

First, show *src* to see content of both merged branched has arrived:

```sh
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/application/Runner.java
src/main/module-info.java
src/main/numbers                        <-- package 'numbers' from branch 'b1-numbers'
src/main/numbers/Numbers.java
src/main/numbers/NumbersData.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
src/main/streams                        <-- package 'streams' from branch 'b2-streams'
src/main/streams/Streams.java
src/main/streams/StreamsImpl.java
src/main/streams/StreamsRunner.java
src/resources
src/resources/application.properties
src/resources/log4j2.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
src/tests/numbers                       <-- tests for 'numbers' from branch 'b1-numbers'
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
src/tests/numbers/Numbers_3_sum_recursion_Tests.java
...
src/tests/streams                       <-- tests for 'streams' from branch 'b2-streams'
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
src/tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
src/tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
...
```

Next, resolve the *merge conflict* such that both *Runners* created from *Numbers*
and from *Streams* run.


&nbsp;

### 9.3 Final Test on Branch *"release-prep"*

Then, make sure the merge builds and runs tests:

```sh
mk build                    # clean project build:
                            # - clean compile compile-tests run-tests package
```

The clean project build also runs tests:

```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *numbers*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar findAllSums numb_3 sum=1000
```
```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar tenSortedEvenRandomNumbers
```
```
Hello, se1-play
 - tenSortedEvenRandomNumbers() -> [18, 30, 48, 260, 310, 358, 492, 528, 618, 898]
```

If all this works, commit with merge with message `"merge b2-streams"` and
show the commit log:

```sh
git log --first-parent --oneline release-prep
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

```
e0bb53b (HEAD -> release-prep) merge b2-streams
ebc0c71 merge b1-numbers
1e53db5 (tag: base, release, main) add src/tests, update src/main/module-info.java
...
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```


&nbsp;

### 9.4 Release

For release, merge Branch *"release-prep"* to Branch *"release"* as a single
commit and tag with "*RELEASE-1.0.0*".

Perform a final test on Branch *"release"*:

```sh
mk build
```
```
Test run finished after 8295 ms
[       114 tests successful      ]     <-- 114 tests from 'b1-numbers' and 'b2-streams'
[         0 tests failed          ]     <--   0 tests failed
```

Test the final artifact with a *streams*-example:

```sh
java -jar target/application-1.0.0-SNAPSHOT.jar \
    findAllSums numb_3 sum=1000 \
    tenSortedEvenRandomNumbers
```

Output shows results for the *numbers* and *streams* examples:

```
Hello, se1-play
 - findAllSums(numb_3, sum=1000) -> [
    - [500, 7, 493],
    - [500, 485, 15],
    - [485, 7, 493, 15],
    - [36, 408, 78, 15, 463],
    - [36, 23, 440, 408, 78, 15]
   ], solutions: 5
 -
 - tenSortedEvenRandomNumbers() -> [18, 172, 290, 376, 594, 636, 686, 728, 880, 916]
```

If all this works, commit with merge with message `"merge prelease-prep"`.


&nbsp;

### 9.4.1 Release Notes

Add file `RELEASE-NOTES.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## Version 1.0.0 - First Release

We're proud to announce our software, designed to supercharge your productivity!

New features:

**Numbers processing**: perform powerful numbers processing tasks.

**Streams processing**: Gain deeper insights into your data.

**Unbeaten Performance**: We've turbocharged our software, resulting in 
   50% faster processing times.

**Bug Fixes and Improvements**:
   - Fixed: The pesky timezone issue affecting our international users
   - Improved: Concurrent editing now works seamlessly for team collaboration
   - Enhanced: GDPR compliance with new data export feature

❗ **Important**: This version drops support for Java 11. 
   Please upgrade to a modern Java JDK to enjoy all new features.

[Update Now] [Read Full Documentation]
```


&nbsp;

### 9.4.2 Changelog for Release

Add file `CHANGELOG.md` to the project directory
([*example*](https://blog.releasenotes.io/changelog-vs-release-notes/)):

```
## [2.1.0] - 2026-03-25
### Added
- New dark mode feature for improved nighttime viewing (#2468)
- API endpoint for exporting user data in compliance with GDPR (/api/v1/user/export)

### Changed
- Upgraded React.js to version 18.0 for improved performance (#3579)
- Refactored database queries to optimize load times on the dashboard

### Deprecated
- Legacy authentication method using API keys (to be removed in v3.0)

### Removed
- Support for Internet Explorer 11 (#4321)

### Fixed
- Resolved race condition in concurrent user edits (#5432)
- Corrected timezone handling for international users (#6543)

### Security
- Implemented rate limiting on login attempts to prevent brute force attacks
- Updated bcrypt library to address potential vulnerability (CVE-2024-XXXX)
```

Commit with message `"add RELEASE-NOTES.md, CHANGELOG.md"`.
Tag the commit with `RELEASE-1.0.0`.

Show the commit log:

```sh
git log --first-parent --oneline release
```

Output shows two commits added on branch *"release-prep"* that was started off
the *"base"* commit:

<img src="https://raw.githubusercontent.com/sgra64/se1-play/refs/heads/markup/streams/git-log-release.png" width="600"/>

<!-- 
```
fcbc4cc (HEAD -> release, tag: RELEASE-1.0.0) add RELEASE-NOTES.md, CHANGELOG.md
837396b merge prelease-prep
1e53db5 (tag: base, main) add src/tests, update src/main/module-info.java
3c9b586 add src/resources
d24d184 add src/main
a8f215c add .gitmodules
15d3c87 add .gitignore
cbc8dc0 (tag: root) root commit (empty)
```
-->

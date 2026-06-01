<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- B2 (SE-1)
-->
# B3: *streams*

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

The assignment introduces
[*Java Streams*](https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/)
(see [*Streams.java*](https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/util/stream/Stream.html)
Javadoc) using a new branch: `"b3-streams"` branched off the *main* branch at the
*"base"* commit:

<img src="markup/gitlog-b3a-branch-streams.png" width="720"/>
<!-- 
<img src="markup/numbers-1-fetch.png" width="600"/>
[*se1-play*](../../tree/main) project.
-->


&nbsp;

---

Assignment *"b3-streams"* will perform the following steps:

1. [Java *Streams API* Introduction](#1-java-streams-api-introduction)

1. [Setup new Branch: *"b3-streams"*](#2-setup-new-branch-b3-streams)

1. [Complete *randomNumbers* Methods](#3-complete-randomnumbers-methods)

1. [*Filter* Function Methods](#4-filter-function-methods)

1. [*Name* Methods](#5-name-methods)

1. [*Order* Methods](#6-order-methods)

7. [*Integration* and *Release*](#7-integration-and-release)

1. [Final Evaluation (Abnahme)](#8-final-evaluation-abnahme)


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 1. Java *Streams API* Introduction

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

## 2. Setup new Branch: *"b3-streams"*

Create a new branch off the *"base"* commit on the *main* branch. Verify no
content of other branches *b1-optionals* or *b2-numbers* is present on the
new branch:

```sh
# verify new branch 'b3-streams'
git log --oneline

# make sure 'src' does not contain packages 'optionals' or 'numbers'
find src

# after clean-project-build runs the program copying command line arguments
mk clean compile run A BB CCC
```
<img src="markup/git-log-1.png" width="600"/>

<img src="markup/git-streams-run-1.png" width="600"/>


<!-- &nbsp; -->

The *Java Streams* assignment is defined by the interface
[*Streams.java*](src/main/streams/Streams.java):

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Public interface for the <i>"b3-streams"</i> assignment.
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
     * lambda expression returning true if n is an even number.
     */
    static final Function<Integer, Boolean> evenFilter = n -> n > 0 && n % 2 == 0;

    /**
     * FILL-IN a lambda expression returning true if n is divisable by three.
     */
    static final Function<Integer, Boolean> div3Filter = n -> true;

    /**
     * FILL-IN a lambda expression returning true if n has three-digits and is a prime number.
     */
    static final Function<Integer, Boolean> primeFilter = n -> true;

    /**
     * Aufgabe 4: Apply {@code filterFunction} to a stream of random integer numbers
     * in the range of [0..999] that produces only numbers passing the filter.
     * @param filterFunction lambda expression that filters numbers
     * @param limit maximum amount of numbers produced
     * @return numbers matching the filterFunction
     */
    List<Integer> filteredNumbers(Function<Integer, Boolean> filterFunction, int limit);


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
}
```


&nbsp;

Install interface
[*Streams.java*](src/main/streams/Streams.java)
and class
[*StreamsRunner*](src/main/streams/StreamsRunner)
in the new package: `"streams"`.

Add a non-public implementation class *StreamsImpl.java* that implements
the first method *tenRandomNumbers()* that generates ten random numbers
in the range *[0..999]* using a random generator as supplier: 

```java
package streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Non-public implementation class of interface {@link Streams}.
 */
class StreamsImpl implements Streams {

    /**
     * Java's {@link java.util.Random} numbers generator.
     */
    private final Random rand = new Random();

    /**
     * Aufgabe 1: Return 10 random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 random numbers can be drawn
     */
    @Override
    public Stream<Integer> tenRandomNumbers() {
        // 
        return Stream.generate(() -> rand.nextInt(1000))
            .limit(10);
    }

    /* ... */
}
```

&nbsp;

Open the new package in file `module-info.java`:

```java
module se1_play {

    /* Open package to JUnit test runner and the javadoc compiler. */
    opens application;
    opens streams;      <-- open new package 'stream'

    /*
     * External modules required by this module.
     */
    requires org.junit.jupiter.api;
    requires transitive runtimeSE;
}
```


&nbsp;

Verify the project structure, rebuild and run the program:

```sh
# verify the new package 'streams'
find src

# after clean-project-build, the program runs 'StreamsRunner.java'
# invoking method 'tenRandomNumbers()'
mk clean compile run tenRandomNumbers
```

Output shows the new package *streams* and 10 random numbers generated by the
program:

<img src="markup/git-streams-run-2.png" width="600"/>


Once this is working, install test
[*Streams_1_tenRandomNumbers_Tests.java*](src/tests/streams/Streams_1_tenRandomNumbers_Tests.java)
and run the test:

```sh
# run the test
mk clean compile compile-tests \
    run-tests -c streams.Streams_1_tenRandomNumbers_Tests
```

<img src="markup/git-streams-test-1.png" width="600"/>


Commit the development to branch *b3-streams* with message:
`"add package 'streams'"`:

<img src="markup/git-log-2.png" width="600"/>


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 3. Complete *randomNumbers* Methods

Complete the remaining *randomNumbers* methods of the *Streams.java* interface
in the implementation class *StreamsImpl.java* using *Java Streams :*

```java
package streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Non-public implementation class of interface {@link Streams}.
 */
class StreamsImpl implements Streams {

    /**
     * Java's {@link java.util.Random} numbers generator.
     */
    private final Random rand = new Random();


    @Override
    public Stream<Integer> tenRandomNumbers() {
        // 
        return Stream.generate(() -> rand.nextInt(1000))
            .limit(10);
    }

    /**
     * Aufgabe 2: Return 10 even random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 even random numbers can be drawn
     */
    Stream<Integer> tenEvenRandomNumbers() {
        /* Java-Stream code here */
    }

    /**
     * Aufgabe 3: Return 10 even sorted random integer numbers in the range [0..999].
     * @return a {@code Stream<Integer>} from which 10 sorted even random numbers can be drawn
     */
    Stream<Integer> tenSortedEvenRandomNumbers() {
        /* Java-Stream code here */
    }
}
```

Run each method 3x and verify that random numbers are max. three digits ([0..999])
for *tenRandomNumbers*, are even for *tenEvenRandomNumbers* and are even and
sorted in ascending order for *tenSortedEvenRandomNumbers*:

```sh
mk run tenRandomNumbers repeat=3 \
    tenEvenRandomNumbers repeat=3 \
    tenSortedEvenRandomNumbers repeat=3
```
```
Hello, 'SE-1 Play' (streams)
 - tenRandomNumbers() -> [43, 339, 718, 439, 976, 479, 109, 186, 907, 954]
 - tenRandomNumbers() -> [849, 806, 566, 67, 815, 45, 953, 846, 90, 847]
 - tenRandomNumbers() -> [364, 977, 330, 115, 523, 982, 475, 721, 930, 380]

 - tenEvenRandomNumbers() -> [156, 396, 710, 404, 830, 852, 212, 148, 534, 692]
 - tenEvenRandomNumbers() -> [304, 752, 148, 666, 126, 352, 352, 480, 282, 940]
 - tenEvenRandomNumbers() -> [978, 96, 334, 382, 356, 82, 664, 100, 472, 18]

 - tenSortedEvenRandomNumbers() -> [92, 104, 120, 258, 308, 378, 394, 468, 522, 772]
 - tenSortedEvenRandomNumbers() -> [140, 300, 306, 494, 504, 582, 612, 914, 946, 952]
 - tenSortedEvenRandomNumbers() -> [60, 94, 126, 298, 398, 604, 678, 750, 820, 900]
```


&nbsp;

Fetch corresponding tests from the remote repository *"se1-repo"*:

- *Streams_2_tenEvenRandomNumbers_Tests.java* and

- *Streams_3_tenSortedEvenRandomNumbers_Tests.java*.

Test, remote repository *"se1-repo"* is still configured from the previous
assignments:

```sh
# show remote repository 'se1-repo' is present
git remote -v
```
```
se1-repo        https://github.com/sgra64/se1-play.git (fetch)
se1-repo        https://github.com/sgra64/se1-play.git (push)
```

Fetch branch *"b3-streams"* from the remote repository *"se1-repo"*:

```sh
# fetch branch 'b3-streams' from the remote repository 'se1-repo'
git fetch se1-repo b3-streams

# show all branches including remote branches
git branch -avv
```
<!-- 
```
remote: Enumerating objects: 19, done.
remote: Counting objects: 100% (19/19), done.
remote: Compressing objects: 100% (9/9), done.
remote: Total 18 (delta 6), reused 18 (delta 6), pack-reused 0 (from 0)
Unpacking objects: 100% (18/18), 8.40 KiB | 67.00 KiB/s, done.
From https://github.com/sgra64/se1-play
 * branch            b3-streams -> FETCH_HEAD
 * [new branch]      b3-streams -> se1-repo/b3-streams
```
-->

<img src="markup/git -branch-fetch-se1-repo-b3-streams.png" width="600"/>


Checkout tests from the remote branch *"se1-repo/b3-streams"*:

```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java \
    src/tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
```

Compile and run tests:

```sh
# compile and run random-number tests
mk clean compile compile-tests run-tests \
    -c streams.Streams_1_tenRandomNumbers_Tests \
    -c streams.Streams_2_tenEvenRandomNumbers_Tests \
    -c streams.Streams_3_tenSortedEvenRandomNumbers_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  │ 
│  ├─ Streams_1_tenRandomNumbers_Tests ✔
│  │  └─ test100_tenRandomNumbers_regular() ✔
│  │ 
│  ├─ Streams_2_tenEvenRandomNumbers_Tests ✔
│  │  └─ test200_tenEvenRandomNumbers_regular() ✔
│  │ 
│  └─ Streams_3_tenSortedEvenRandomNumbers_Tests ✔
│     └─ test300_tenSortedEvenRandomNumbers_regular() ✔
│ 
└─ JUnit Vintage ✔

Test run finished after 451 ms
[         3 tests successful      ]
[         0 tests failed          ]
```

Once working, commit the development to branch *b3-streams* with message:
`"random numbers methods complete"`.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 4. *Filter* Function Methods

Complete the next section of interface *Streams* that defines three lambda
expressions as variables used in method *filteredNumbers()*.

Complete expressions for `div3Filter` and `primeFilter` and then complete
method *filteredNumbers()* in the implementation class:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Public interface for the <i>"b3-streams"</i> assignment.
 */
public interface Streams {

    /**
     * lambda expression returning true if n is an even number.
     */
    static final Function<Integer, Boolean> evenFilter = n -> n > 0 && n % 2 == 0;

    /**
     * FILL-IN a lambda expression returning true if n is divisable by three.
     */
    static final Function<Integer, Boolean> div3Filter = n -> true;

    /**
     * FILL-IN a lambda expression returning true if n has three-digits and is a prime number.
     */
    static final Function<Integer, Boolean> primeFilter = n -> true;

    /**
     * Aufgabe 4: Apply {@code filterFunction} to a stream of random integer numbers
     * in the range of [0..999] that produces only numbers passing the filter.
     * @param filterFunction lambda expression that filters numbers
     * @param limit maximum amount of numbers produced
     * @return numbers matching the filterFunction
     */
    List<Integer> filteredNumbers(Function<Integer, Boolean> filterFunction, int limit);
}
```


&nbsp;

After completion, run code:

```sh
# output 3 sets of even numbers
mk run filteredNumbers filter=evenFilter limit=8 repeat=3

# output 3 sets of even numbers
mk run filteredNumbers filter=div3Filter limit=8 repeat=3

# output 3 sets of three-digit prime numbers
mk run filteredNumbers filter=primeFilter limit=8 repeat=3
```
```
Hello, 'SE-1 Play' (streams)
 - filteredNumbers(evenFilter) -> [64, 270, 702, 378, 676, 364, 504, 394]
 - filteredNumbers(evenFilter) -> [962, 674, 954, 570, 346, 460, 846, 308]
 - filteredNumbers(evenFilter) -> [614, 466, 112, 212, 882, 978, 124, 712]

 - filteredNumbers(div3Filter) -> [825, 3, 957, 987, 306, 378, 384, 669]
 - filteredNumbers(div3Filter) -> [66, 795, 414, 222, 171, 561, 837, 837]
 - filteredNumbers(div3Filter) -> [255, 738, 492, 168, 600, 702, 147, 981]

 - filteredNumbers(primeFilter) -> [607, 307, 887, 647, 599, 521, 577, 523]
 - filteredNumbers(primeFilter) -> [337, 239, 751, 331, 811, 113, 607, 379]
 - filteredNumbers(primeFilter) -> [733, 773, 281, 419, 307, 569, 461, 191]

```


&nbsp;

When this is working, checkout tests from the remote branch
*"se1-repo/b3-streams"*:

```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_4_filteredNumbers_Tests.java
```

Compile and run tests:

```sh
# compile and run random-number tests
mk clean compile compile-tests run-tests \
    -c streams.Streams_4_filteredNumbers_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  └─ Streams_4_filteredNumbers_Tests ✔
│     ├─ test400_filteredNumbers_50evenNumbers_regular() ✔
│     ├─ test410_filteredNumbers_50divisibleBy3Numbers_regular() ✔
│     ├─ test420_filteredNumbers_50primeNumbers_regular() ✔
│     ├─ test430_filteredNumbers_different_even_numbers_returned() ✔
│     ├─ test431_filteredNumbers_different_div_by_three_numbers_returned() ✔
│     └─ test432_filteredNumbers_different_prime_numbers_returned() ✔
│ 
└─ JUnit Vintage ✔

Test run finished after 451 ms
[         6 tests successful      ]
[         0 tests failed          ]
```

Once working, commit the development to branch *b3-streams* with message:
`"filter methods complete"`.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 5. *Name* Methods

*Name* methods demonstrate filtering and sorting names. Interface *Streams.java*
includes a list of names and defines three methods for names. Complete methods
in the implementation class:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Public interface for the <i>"b3-streams"</i> assignment.
 */
public interface Streams {

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
}
```

Implement the first method: `filteredNames()` according to the *Javadoc*
specification as a *Java Stream* and run examples:

```sh
# filter names that start with letter 'G'
mk run filteredNames regex="G.*"

# filter names that end with 'ez'
mk run filteredNames regex=".*ez"

# filter names that contain double 'l' letters
mk run filteredNames regex=".*ll.*"

# filter names with four letters
mk run filteredNames regex="^.{4}$"
```
```
 - filteredNames("G.*") -> [Gonzalez, Graham, Gill, Gomez]

 - filteredNames(".*ez") -> [Gonzalez, Gomez, Marquez]

 - filteredNames(".*ll.*") -> [Gill, Talley, Hall]

 - filteredNames("^.{4}$") -> [Pena, Gill, Howe, Hall, Witt, Soto, Lott, Case]
```

Check-out the corresponding test:

```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_5_filteredNames_Tests.java

# compile and run the test
mk clean compile compile-tests run-tests \
    -c streams.Streams_5_filteredNames_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  │ 
│  └─ Streams_5_filteredNames_Tests ✔
│     ├─ test500_filteredNames_regular() ✔
│     ├─ test590_filteredNames_irregularNamesNull() ✔
│     ├─ test591_filteredNames_irregularRegexNull() ✔
│     └─ test592_filteredNames_irregularNamesAndRegexNull() ✔

Test run finished after 246 ms
[         4 tests successful      ]
[         0 tests failed          ]
```


&nbsp;

Implement the second method: `sortedNames()` according to the *Javadoc*
specification as a *Java Stream* and run examples:

```sh
# return names sorted up to a limit
mk run sortedNames limit=100

mk run sortedNames limit=3
```
```
 - sortedNames(Streams.names, 100) -> [Bernard, Brock, Buckner, Case, Casey, Cleveland, Crane, Duncan, Gill, Gomez, Gonzalez, Graham, Hall, Hamilton, Hardin, Hendricks, Howe, Joyner, Lott, Marquez, Navarro, Nielsen, Pena, Petty, Ray, Raymond, Richardson, Rutledge, Soto, Strong, Talley, Vance, Witt]

 - sortedNames(Streams.names, 3) -> [Bernard, Brock, Buckner]
```

Check-out the corresponding test:

```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_6_sortedNames_Tests.java

# compile and run the test
mk clean compile compile-tests run-tests \
    -c streams.Streams_6_sortedNames_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  │ 
│  └─ Streams_6_sortedNames_Tests ✔
│     ├─ test600_sortedNames_regular() ✔
│     ├─ test601_sortedNames_regular() ✔
│     ├─ test610_sortedNames_emptyNames() ✔
│     ├─ test690_sortedNames_irregularNamesNull() ✔
│     ├─ test691_sortedNames_irregularLimitNegativ() ✔
│     └─ test692_sortedNames_irregularNamesNullAndLimitNegativ() ✔

Test run finished after 246 ms
[         6 tests successful      ]
[         0 tests failed          ]
```


&nbsp;

Implement the third method: `sortedNamesByLength()` according to the *Javadoc*
specification as a *Java Stream* and run examples.

Shortest names should appear first (sorted), followed by longer names that are
also sorted within their length category:

```
Ray, Case, Gill, Hall, Howe, Lott, Pena, Brock, Casey, Crane, Gomez, Vance, ...
   |                                   |                        
 3 |----- 4 letter names (sorted) -----|----- 5 letter names (sorted) ----- ...
```

Think about the sorting criteria to produce the sequence and how to implement it
in the `.sorted((n1, n2) -> ... )` *Java Stream* method.

Run the code:

```sh
# return names sorted up to a limit
mk run sortedNamesByLength
```

Output shows the list of names starting with 3-letter names (sorted), followed
by 4-letter names (sorted), etc.:

```
 - sortedNamesByLength(Streams.names) -> [Ray, Case, Gill, Hall, Howe, Lott, Pena, Soto, Witt, Brock, Casey, Crane, Gomez, Petty, Vance, Duncan, Graham, Hardin, Joyner, Strong, Talley, Bernard, Buckner, Marquez, Navarro, Nielsen, Raymond, Gonzalez, Hamilton, Rutledge, Cleveland, Hendricks, Richardson]
```

Check-out the corresponding test:

```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_7_sortedNamesByLength_Tests.java

# compile and run the test
mk clean compile compile-tests run-tests \
    -c streams.Streams_7_sortedNamesByLength_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  │ 
│  └─ Streams_7_sortedNamesByLength_Tests ✔
│     ├─ test700_sortedNamesByLength_regular() ✔
│     ├─ test710_sortedNamesByLength_emptyNames() ✔
│     └─ test790_sortedNamesByLength_irregular_names_Null() ✔

Test run finished after 282 ms
[         3 tests successful      ]
[         0 tests failed          ]
```
<!-- 
mk clean compile compile-tests run-tests \
    -c streams.Streams_5_filteredNames_Tests \
    -c streams.Streams_6_sortedNames_Tests \
    -c streams.Streams_7_sortedNamesByLength_Tests
 -->

When all tests (500'er, 600'er, 700'er) pass, commit the development to
branch *b3-streams* with message: `"name methods complete"`.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 6. *Order* Methods

Complete methods for order processing in the implementation class:

```java
package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Public interface for the <i>"b3-streams"</i> assignment.
 */
public interface Streams {

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
}
```



```sh
# checkout tests from the remote branch 'se1-repo/b3-streams'
git checkout se1-repo/b3-streams -- \
    src/tests/streams/Streams_8_calculateOrderValue_Tests.java \
    src/tests/streams/Streams_9_sortByOrderValue_Tests.java

# compile and run the test
mk clean compile compile-tests run-tests \
    -c streams.Streams_8_calculateOrderValue_Tests \
    -c streams.Streams_9_sortByOrderValue_Tests
```
```
╷
├─ JUnit Platform Suite ✔
├─ JUnit Jupiter ✔
│  │ 
│  ├─ Streams_8_calculateOrderValue_Tests ✔
│  │  ├─ test800_calculateValue_regular() ✔
│  │  ├─ test801_calculateValue_regular() ✔
│  │  ├─ test810_calculateValue_emptyOrders() ✔
│  │  └─ test890_calculateValue_irregular_orders_Null() ✔
│  │ 
│  └─ Streams_9_sortByOrderValue_Tests ✔
│     ├─ test900_sortByOrderValue_regular() ✔
│     ├─ test901_sortByOrderValue_regular() ✔
│     ├─ test910_sortByOrderValue_emptyOrders() ✔
│     └─ test990_sortByOrderValue_irregular_orders_Null() ✔

Test run finished after 275 ms
[         8 tests successful      ]
[         0 tests failed          ]
```

When all tests (500'er, 600'er, 700'er) pass, commit the development to
branch *b3-streams* with message: `"order methods complete"`.


&nbsp;

At this point, branches *"b1-optionals"*, *"b2-numbers"* and *"b3-streams"*
are complete. The branch topology is shown in the figure:

<img src="markup/gitlog-b3b-branch-streams.png" width="720"/>


&nbsp;

Show branches and commit logs:

```sh
# show all branches, including fetched remote branches
git branch -avv

# show the commit log of branch 'b3-streams'
git log --oneline
```

<img src="markup/git-streams-branches.png" width="720"/>

<img src="markup/git-streams-log.png" width="600"/>


The next step of the project is *integrating* and *releasing* the parts.


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 7. *Integration* and *Release*

Software releases require careful preparation. Various pieces of a software
project must be integrated and tested without deferring ongoing development.

For this reason,
[*"Code Freeze"*](https://en.wikipedia.org/wiki/Freeze_(software_engineering))
and *(continuous)*
[*"Integration"*](https://martinfowler.com/articles/continuousIntegration.html)
is initiated by the team, usually on a new branch.

We will create a new branch: `release-integration` off the *"base"* commit to
collect the developments on branches: *b1-optionals*, *b2-numbers* and *b3-streams*.

```sh
# create new branch 'release-integration' off the 'base'-commit
git switch -c release-integration base

# show that no prior content exists in the branch
find src
```
```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java
src/resources
src/resources/application.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
```


&nbsp;

### Step 1: Integration branch *b1-optionals*

In a first step, content of branch *b1-optionals* is carried over to branch
*release-integration*:

```sh
# carry over content from branch 'b1-optionals' to branch 'release-integration'
git checkout b1-optionals -- .

# show content carried over from branch 'b1-optionals'
git status

# show content from branch 'b1-optionals'
find src
```

Package *"optionals"* is now part of the branch:

```
src
src/main
src/main/application
src/main/application/Application.java
src/main/application/package-info.java
src/main/module-info.java
---
src/main/optionals                          <-- 'optionals' content
src/main/optionals/OptionalsRunner.java
---
src/resources
src/resources/application.properties
src/resources/META-INF
src/resources/META-INF/MANIFEST.MF
src/tests
src/tests/application
src/tests/application/Application_0_always_pass_Tests.java
```

*"Clean project build"* ensures that content build and works:

```sh
# clean project build - must work
mk build

# run 'b1-optionals' examples
mk run Kanne Becher Messer
```
```
Hello, 'SE-1 Play' (optionals)
Der Preis für 'Kanne' ist: 1999 €-Cent
Der Preis für 'Becher' ist: 749 €-Cent
Der Artikel 'Messer' konnte nicht gefunden werden
```

When everything works, the first part of the integration can be committed:

```sh
# create first commit on branch 'release-integration'
git commit -m "content branch b1-optionals"

# show the new commit on branch 'release-integration'
git log --oneline
```

The commit is recorded on branch *release-integration:*

```
50813ac (HEAD -> release-integration) content branch b1-optionals
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
...
```


&nbsp;

### Step 2: Integration branch *b2-numbers*

Branch *b2-numbers* cannot simply be *"checked-out"* since *check-out* overwrites
content and does not *"integrate"*.

[*"Git-merge"*](https://www.w3schools.com/git/git_branch_merge.asp)
is a mechanism to *"integrate"* content in files:

```sh
git merge b2-numbers
```

The *git-merge* creates a merge conflict in file *module-info.java:*

```
Auto-merging src/main/module-info.java
CONFLICT (content): Merge conflict in src/main/module-info.java
Automatic merge failed; fix conflicts and then commit the result.
```

The project does not compile with merge-conflict markings in files:

```
<<<<<<< HEAD
    opens optionals;
=======
    opens numbers;
>>>>>>> b2-numbers
```

The *merge*-conflict is shown in VSCode as *"current"* content (green) and
conflicting *"incoming"* content (blue)

```
opens optionals;        <-- 'current'
opens b2-numbers;       <-- 'incoming'
```

Both lines need to be *"integrated"*, which means the final content must be
determined and fixed. *VSCode* offers choices: *"accept incoming"* or
*"accept current"*. In our case, both lines need to be present in the final
file:

<img src="markup/release-merge-b2-numbers-conflict.png" width="1000"/>

```sh
# when code compiles, all merge-conflicts have been resolved
mk compile

# show status of the 'open commit'
git status
```

At this point, the *merge* is still open. A commit is finalized with a commit:

```sh
# finalize the 'open commit'
git commit -m "merge commit b2-numbers"

# show git log
git log --oneline
```

The commit log shows *all commits* of branch *b2-numbers* have been carried
over to branch *release-integration*.

```
d696b16 (HEAD -> release-integration) merge commit b2-numbers
50813ac content branch b1-optionals
72905f2 (b2-numbers) add test 641 test641_find_all_exception_null_arg
7f62390 findAllSums() complete
daf04a1 findSums() complete
a9451d5 find() methods complete
10011e9 sum() methods complete
159b0a1 add package 'numbers' with interface 'Numbers' and class 'NumbersRunner'
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
...
```

In most cases, only the *"compound content"* of a branch (the last commit)
is desired to be on an integration branch. For this, flag `--squash` must be
used with the merge-commit.

```sh
# undo the prior commit "merge commit b2-numbers"
git reset --hard HEAD~1

# perform a 'squash' merge that combines merged commits into one
git merge --squash b2-numbers

# resolve the merge conflict again and test the project builds
mk clean compile compile-tests

# run tests -> 81 tests from 'b2-numbers'
mk run-tests

# show the status of the open commit
git status

# stage the change from resolving the merge commit
git add src/main/module-info.java

# finalize the 'open commit'
git commit -m "merge commit b2-numbers"

# show git log
git log --oneline
```

The commit log now shows one commit for the merge from branch *b2-humbers*
(compare to the prior commit log with 9 commits from *base*).

```
3a8e268 (HEAD -> release-integration) merge commit b2-numbers
50813ac content branch b1-optionals
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
...
```

Integration often also requires to add code that binds pieces together. At this
point, the *numbers* - examples work, but not the *optionals* - examples:

```sh
# run 'b2-numbers' examples
mk run sum numbers=[1, 2, 3, 4]

# run 'b1-optionals' examples -> not yet integrated
mk run Kanne Becher Messer
```

In order to integrate the software, create a new package: `src/main/integration`
and add class `IntegrationRunner.java`:

```java
package integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import runtimeSE.CommandRunner;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;

import optionals.OptionalsRunner;
import numbers.NumbersRunner;

/**
 * Public class that implements the {@link Runner} and {@link CommandRunner}
 * interfaces to integrate commans executions.
 */
@Accessors(priority=10)
class IntegrationRunner implements Runner, CommandRunner {

    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        System.out.println(String.format("Hello, %s (integration)",
            runtime.properties().getProperty("application.name", "")
        ));
        // 
        CommandRunner.run(this, "b1-optionals, b2-numbers, b3-streams", String.join(" ", args));
    }


    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String command, KVArgs kvargs) {
        // 
        switch(command) {
        // 
        case "b1-optionals":
            var optionalsRunner = new OptionalsRunner();
            optionalsRunner.run(runtime, kvargs.keys().toArray(new String[0]));
            break;
        // 
        case "b2-numbers":
            var numbersRunner = new NumbersRunner();
            numbersRunner.run(runtime, args(kvargs));
            break;
        }
    }

    private String[] args(KVArgs kvargs) {
        List<String> args = new ArrayList<>();
        for(String k : kvargs.keys()) {
            args.add(Optional.ofNullable(kvargs.value(k))
                .map(v2 -> k + "=" + (v2.contains(",")? "[" + v2 + "]" : v2)).orElse(k));
        }
        return args.toArray(new String[0]);
    }
}
```

Change visibility of class `numbers/NumbersRunner.java` to *public* and
add line to file `module-info.java`:

```java
opens integration;      // add to 'module-info.java'
```

This new class allows to invoke both parts:

```sh
# run 'b1-optionals' example
mk run b1-optionals Kanne Becher Messer

# run 'b2-numbers' example
mk run b2-numbers sum numbers=[1, 2, 3, 4]
```

```
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (optionals)          <-- 'b1-optionals' example
Der Preis für 'Kanne' ist: 1999 €-Cent
Der Preis für 'Becher' ist: 749 €-Cent
Der Artikel 'Messer' konnte nicht gefunden werden

Hello, 'SE-1 Play' (numbers)            <-- 'b2-numbers' example
 - sum([1, 2, 3, 4]) -> 10
```

```sh
# show 'IntegrationRunner' changes
git status
```
```
On branch release-integration
  (use "git restore <file>..." to discard changes in working directory)
        modified:   src/main/module-info.java               <-- modification
        modified:   src/main/numbers/NumbersRunner.java     <-- modification
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        src/main/integration/                               <-- new content
```

Rebuild the project and run tests.

Commit with message: `add IntegrationRunner.java` and show the commit
log:

```
49542d9 (HEAD -> release-integration) add IntegrationRunner.java
3a8e268 merge commit b2-numbers
50813ac content branch b1-optionals
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
```


&nbsp;

### Step 3: Integration branch *b3-streams*

Integrate branch *b3-streams* to branch *release-integration*:

```sh
# show content with 'src/main/optionals', 'src/main/numbers', 'src/main/streams',
# 'src/tests/numbers' and 'src/tests/streams'
find src
```
```
src
src/main
src/main/integration
src/main/integration/IntegrationRunner.java
src/main/module-info.java
...
src/main/numbers                            <-- 'src/main/numbers'
src/main/numbers/Numbers.java
src/main/numbers/NumbersImpl.java
src/main/numbers/NumbersImpl_FindAllSums.java
src/main/numbers/NumbersRunner.java
...
src/main/optionals                          <-- 'src/main/optionals'
src/main/optionals/OptionalsRunner.java
...
src/main/streams                            <-- 'src/main/streams'
src/main/streams/Streams.java
src/main/streams/StreamsImpl.java
src/main/streams/StreamsRunner.java
...
src/tests/numbers                           <-- 'src/tests/numbers'
src/tests/numbers/Matchers.java
src/tests/numbers/Numbers_1_sum_Tests.java
src/tests/numbers/Numbers_2_sum_positive_even_Tests.java
...
src/tests/streams                           <-- 'src/tests/streams'
src/tests/streams/Streams_1_tenRandomNumbers_Tests.java
src/tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java
src/tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java
...
```

Resolve merge conflicts, rebuild the project and run tests:

```sh
# rebuild the project
mk clean compile compile-tests

# run tests
mk run-tests
```
```
Test run finished after 7492 ms
[       111 tests found           ]
[         0 tests skipped         ]
[       111 tests started         ]
[         0 tests aborted         ]
[       111 tests successful      ]     --> combined 111 tests
[         0 tests failed          ]
```

Commit changes with message: `merge commit b3-streams`.

```sh
git merge b2-numbers
```
```
9d15c6b (HEAD -> release-integration) merge commit b3-streams
49542d9 add IntegrationRunner.java
3a8e268 merge commit b2-numbers
50813ac content branch b1-optionals
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
```

Finally, update `IntegrationRunner.java` to also run *streams* examples:

```sh
# run 'b1-optionals' example
mk run b1-optionals Kanne Becher Messer

# run 'b2-numbers' example
mk run b2-numbers sum numbers=[1, 2, 3, 4, 5, 6]

# run 'b3-streams' example
mk run b3-streams tenEvenRandomNumbers repeat=3
```

```
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (optionals)          <-- 'b1-optionals' example
Der Preis für 'Kanne' ist: 1999 €-Cent
Der Preis für 'Becher' ist: 749 €-Cent
Der Artikel 'Messer' konnte nicht gefunden werden

Hello, 'SE-1 Play' (numbers)            <-- 'b2-numbers' example
 - sum([1, 2, 3, 4, 5, 6]) -> 21

Hello, 'SE-1 Play' (integration)        <-- 'b3-streams' example
Hello, 'SE-1 Play' (streams)
 - tenEvenRandomNumbers() -> [896, 108, 916, 894, 376, 710, 710, 856, 584, 38]
 - tenEvenRandomNumbers() -> [622, 336, 352, 190, 174, 306, 546, 642, 892, 570]
 - tenEvenRandomNumbers() -> [168, 384, 668, 16, 152, 772, 560, 488, 476, 460]
```

Commit final changes with message: `update IntegrationRunner to run b3-streams examples`.

The final commit log on branch *"release-integration"* is:

```
e9beed7 (HEAD -> release-integration) update IntegrationRunner to run b3-streams examples
9d15c6b merge commit b3-streams
49542d9 add IntegrationRunner.java
3a8e268 merge commit b2-numbers
50813ac content branch b1-optionals
0889bca (tag: base, main) require module 'runtime-SE' in module-info.java
```


<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

## 8. Final Evaluation (Abnahme)

The final structure in the *git* repository is shown below.
Finalize the *"release"* branch.

<img src="markup/git-log-b3i-integration.png" width="720"/>

<!-- 
&nbsp;

### 7.2 Changelog for Release

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
-->

Switch to branch *release* and demonstrate:

```sh
# switch to the 'release' branch
git switch release

# show all branches, including fetched remote branches
git branch -avv
```

<img src="markup/git-release-branches.png" width="720"/>


&nbsp;

```sh
# show the commit-log of all branches
git log --oneline --graph main b1-optionals b2-numbers b3-streams release-integration release
```

<img src="markup/git-release-log.png" width="720"/>


&nbsp;

Run a *clean project build*, run *tests* and run *examples:*

```sh
# show 'src' content
find src

# perform clean project build
mk clean compile compile-tests

# run tests -> show 111 successful tests
mk run-tests

# run 'b1-optionals' example
mk run b1-optionals Kanne Becher Messer

# run 'b2-numbers' example
mk run b2-numbers sum numbers=[1, 2, 3, 4, 5, 6]

# run 'b3-streams' example
mk run b3-streams tenEvenRandomNumbers repeat=3
```

```
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (optionals)          <-- 'b1-optionals' example
Der Preis für 'Kanne' ist: 1999 €-Cent
Der Preis für 'Becher' ist: 749 €-Cent
Der Artikel 'Messer' konnte nicht gefunden werden

Hello, 'SE-1 Play' (numbers)            <-- 'b2-numbers' example
 - sum([1, 2, 3, 4, 5, 6]) -> 21

Hello, 'SE-1 Play' (integration)        <-- 'b3-streams' example
Hello, 'SE-1 Play' (streams)
 - tenEvenRandomNumbers() -> [896, 108, 916, 894, 376, 710, 710, 856, 584, 38]
 - tenEvenRandomNumbers() -> [622, 336, 352, 190, 174, 306, 546, 642, 892, 570]
 - tenEvenRandomNumbers() -> [168, 384, 668, 16, 152, 772, 560, 488, 476, 460]
```

Show the finally packaged artifact:

```sh
# package final release-jar, rename to 'application-1.0.0-RELEASE.jar'
mk package
mv tar

# show the release-jar
ls -la target
```
```
total 84
drwxr-xr-x 1     0 Jun  1 10:59 ./
drwxr-xr-x 1     0 Jun  1 10:56 ../
-rw-r--r-- 1 70400 Jun  1 10:59 application-1.0.0-RELEASE.jar
drwxr-xr-x 1     0 Jun  1 10:59 classes/
drwxr-xr-x 1     0 Jun  1 10:57 resources/
drwxr-xr-x 1     0 Jun  1 10:57 test-classes/
```

Run examples with final release-jar:

```sh
# run 'b1-optionals' example with final release-jar
java -jar target/application-1.0.0-RELEASE.jar b1-optionals Kanne Becher Messer

# run 'b2-numbers' example with final release-jar
java -jar target/application-1.0.0-RELEASE.jar run b2-numbers sum numbers=[1, 2, 3, 4, 5, 6]

# run 'b3-streams' example with final release-jar
java -jar target/application-1.0.0-RELEASE.jar b3-streams tenEvenRandomNumbers repeat=3
```
```
$ java -jar target/application-1.0.0-RELEASE.jar b1-optionals Kanne Becher Messer
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (optionals)
Der Preis für 'Kanne' ist: 1999 €-Cent
Der Preis für 'Becher' ist: 749 €-Cent
Der Artikel 'Messer' konnte nicht gefunden werden

$ java -jar target/application-1.0.0-RELEASE.jar run b2-numbers sum numbers=[1, 2, 3, 4, 5, 6]
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (numbers)
 - sum([1, 2, 3, 4, 5, 6]) -> 21

$ java -jar target/application-1.0.0-RELEASE.jar b3-streams tenEvenRandomNumbers repeat=3
Hello, 'SE-1 Play' (integration)
Hello, 'SE-1 Play' (streams)
 - tenEvenRandomNumbers() -> [324, 214, 66, 804, 382, 750, 518, 654, 278, 610]
 - tenEvenRandomNumbers() -> [128, 770, 406, 380, 616, 440, 360, 752, 42, 44]
 - tenEvenRandomNumbers() -> [642, 118, 60, 786, 610, 480, 20, 372, 114, 150]
```

The final release-jar `application-1.0.0-RELEASE.jar` can now be distributed
through an
[*artifact repository*](https://jfrog.com/learn/devops/software-artifact-repository)
such as:

- [*Sonatype Nexus Repository*](): A highly popular repository that supports various package formats and focuses heavily on software supply chain security and component lifecycle management.

- [*JFrog Artifactory*](https://jfrog.com): A universal binary repository manager supporting over 27 package types. It acts as a caching proxy and integrates deeply with CI servers.

- *GitHub Packages:* Integrated directly into GitHub, this allows teams to host software packages and container images right alongside their source code.

- *AWS CodeArtifact:* A fully managed artifact repository service that integrates seamlessly with existing build tools and AWS services.

- *Google Artifact Registry:* A unified repository on Google Cloud designed to manage container images and language packages (like Maven and npm) securely.

[*Maven Repository*](https://mvnrepository.com)
is the largest, global artifact repository for Java `.jar` packages. It stores
all publicly available Java `.jar` packages since the beginning of Java (for over
25+ years).

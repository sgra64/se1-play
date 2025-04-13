# Project: *se1-play*, branch *b1-numbers-tests*

Branch *b1-numbers-tests* contains JUnit-tests for the
[*b1-numbers*](https://github.com/sgra64/se1-play/tree/b1-numbers)
assignment.

```sh
<se1-play>              # project directory
 |
 +--.gitignore                  # files for git to ignore
 +-- README.md                  # this markup file
 |
 +-<tests>                      # source code for 'numbers' JUnit tests
  |  +-<numbers>                # package 'numbers'
       +--Matchers.java         # helpers to match list and set results
       |
       | # JUnit test classes for tested class: NumbersImpl.java
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

### 1. Read (Repeat) about JUnit 5 Tests

Read the short introduction:
[*Tobias Trelle: "JUnit 5"*](https://www.codecentric.de/wissens-hub/blog/junit5-junit-5),
(2017).

**Important:** when tests fail:

1. Open the location in the IDE where a test failed.

1. Understand the test: which function (your code) is tested
    and with which parameters.

1. Understand input parameters passed to the tested function.

1. Understand what the `expected` result is for this input (see test-code).

1. Inspect which `actual` value was retured from the function.
    The IDE reports both: *expected* and *actual* values.

Example
(from [*Numbers_1_sum_Tests.java*](tests/numbers/Numbers_1_sum_Tests.java)):

```java
/*
 * from NumbersImpl.java:
 */
static final int[] numbers = {-2, 4, 9, 4, -3, 4, 9, 5};

/*
 * Test method: 'test100_sum_regular()' for tested method: 'sum()'.
 */
@Test
@Order(100)
void test100_sum_regular() {
    assertEquals(30, testObj.sum(NumbersImpl.numbers));
}
```

Variable `testObj` references an instance of class `NumbersImpl.java` as
test object, which includes the tested method: `sum()`.

When the test-method: `test100_sum_regular()` executes, it invokes method:
`sum()` on the test object with numbers: `[-2, 4, 9, 4, -3, 4, 9, 5]`.

In `assertEquals(<expected>, <actual>)`, the first argument defines the
*"expected value"* (30), the second argument is the *"actual result"*
of method: `sum()`.

The assertion compares both values and, if equal, passes the test (*"green"*).
The test fails (*"red"*) if both values are not equal.


&nbsp;

### 2. Installing and Performing Tests

Download test classes **one after another** and install into the
[*b1-numbers*](https://github.com/sgra64/se1-play/tree/b1-numbers)
project under: `tests/numbers`.

Install tests only when the prior tests passed (the corresponding function in
[NumbersImpl.java](https://github.com/sgra64/se1-play/-/blob/b1-numbers/src/numbers/NumbersImpl.java)
was implemented correctly).

1. Start with installing:
    [*Matchers.java*](tests/numbers/Matchers.java)
    and the first test class
    [*Numbers_1_sum_Tests.java*](tests/numbers/Numbers_1_sum_Tests.java).

1. When the previous tests passed, install the next test:
    [*Numbers_2_sum_positive_even_Tests.java*](tests/numbers/Numbers_2_sum_positive_even_Tests.java),

1. then, install:
    [*Numbers_2_sum_positive_even_Tests.java*](tests/numbers/Numbers_2_sum_positive_even_Tests.java),

    - then:
        [*Numbers_3_sum_recursion_Tests.java*](tests/numbers/Numbers_3_sum_recursion_Tests.java),

    - then:
        [*Numbers_4_find_first_Tests.java*](tests/numbers/Numbers_4_find_first_Tests.java),

    - then:
        [*Numbers_5_find_last_Tests.java*](tests/numbers/Numbers_5_find_last_Tests.java),

    - then:
        [*Numbers_6_find_all_Tests.java*](tests/numbers/Numbers_6_find_all_Tests.java),

    - then:
        [*Numbers_7a_find_sums_Tests.java*](tests/numbers/Numbers_7a_find_sums_Tests.java),

    - then:
        [*Numbers_7b_find_sums_duplicates_Tests.java*](tests/numbers/Numbers_7b_find_sums_duplicates_Tests.java),

    - then:
        [*Numbers_8a_find_all_sums_Tests.java*](tests/numbers/Numbers_8a_find_all_sums_Tests.java),

    - then:
        [*Numbers_8b_find_all_sums_XL_Tests.java*](tests/numbers/Numbers_8b_find_all_sums_XL_Tests.java).

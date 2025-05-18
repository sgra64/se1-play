# Project: *se1-play*, branch *b2-streams-tests*

Branch *b2-streams-tests* contains JUnit-tests for the
[*b2-streams*](https://github.com/sgra64/se1-play/tree/b2-streams)
assignment.

```sh
<se1-play>              # project directory
 |
 +--.gitignore                  # files for git to ignore
 +-- README.md                  # this markup file
 |
 +-<tests>                      # source code for 'streams' JUnit tests
  |  +-<streams>                # package 'streams'
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


&nbsp;

### Installing and Performing Tests

Download test classes **one after another** and install into the
project under: `tests/streams`.

Install tests only when the prior tests passed (the corresponding function in
[StreamsImpl.java](https://github.com/sgra64/se1-play/-/blob/b2-streams/src/streams/StreamsImpl.java)
was implemented correctly).

1. Start with installing the first test class
    [*Streams_1_tenRandomNumbers_Tests.java*](tests/streams/Streams_1_tenRandomNumbers_Tests.java).

1. When the previous tests passed, install the next test:
    [*Streams_2_tenEvenRandomNumbers_Tests.java*](tests/streams/Streams_2_tenEvenRandomNumbers_Tests.java),

1. then, install:
    [*Streams_3_tenSortedEvenRandomNumbers_Tests.java*](tests/streams/Streams_3_tenSortedEvenRandomNumbers_Tests.java),

    - then:
        [*Streams_4_filteredNumbers_Tests.java*](tests/streams/Streams_4_filteredNumbers_Tests.java),

    - then:
        [*Streams_5_filteredNames_Tests.java*](tests/streams/Streams_5_filteredNames_Tests.java),

    - then:
        [*Streams_6_sortedNames_Tests.java*](tests/streams/Streams_6_sortedNames_Tests.java),

    - then:
        [*Streams_7_sortedNamesByLength_Tests.java*](tests/streams/Streams_7_sortedNamesByLength_Tests.java),

    - then:
        [*Streams_8_calculateOrderValue_Tests.java*](tests/streams/Streams_8_calculateOrderValue_Tests.java),

    - then:
        [*Streams_9_sortByOrderValue_Tests.java*](tests/streams/Streams_9_sortByOrderValue_Tests.java).

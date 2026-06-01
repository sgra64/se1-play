

<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

&nbsp;

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

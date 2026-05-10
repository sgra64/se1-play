package numbers;

import java.util.List;
import java.util.Set;


/**
 * Public interface with functions for the <i>"b1-numbers"</i> assignment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Numbers {

    /**
     * Calculate the sum of numbers[].
     * @param numbers input
     * @return sum of numbers[]
     */
    long sum(int[] numbers);

    /**
     * Calculate sum of positive even numbers[].
     * @param numbers input
     * @return sum of positive even numbers[]
     */
    long sumPositiveEvenNumbers(int[] numbers);

    /**
     * Calculate sum of numbers[] recursively without using loops
     * (for, while, do/while).
     * @param numbers input numbers
     * @param i start index, calculate sum from index i in numbers[]
     * @return sum of numbers[]
     */
    long sumRecursive(int[] numbers, int i);

    /**
     * Return index of first occurrence of x in numbers[] or return -1
     * if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of first occurrence of x in numbers[] or -1 if not found
     */
    int findFirst(int[] numbers, int x);

    /**
     * Return index of last occurrence of x in numbers[]
     * or return -1 if x was not found.
     * @param numbers input
     * @param x number to find
     * @return index of last occurrence of x in numbers[] or -1 if not found
     */
    int findLast(int[] numbers, int x);

    /**
     * Return list of all indices of number x in numbers[].
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
     * Return all pairs (a, b) in numbers[] matching a + b = sum.
     * Mirror copies (a, b), (b, a) are included once, either (a, b) or (b, a),
     * not both.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all pairs (a, b) that add to sum
     */
    Set<Pair> findSums(int[] numbers, int sum);

    /**
     * Find all combinations of numbers in numbers[] that add to sum.
     * @param numbers input array of numbers
     * @param sum to match
     * @return all combinations of numbers that add to sum
     */
    Set<Set<Integer>> findAllSums(int[] numbers, int sum);
}

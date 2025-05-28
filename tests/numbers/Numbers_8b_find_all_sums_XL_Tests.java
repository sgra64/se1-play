package numbers;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit 5 test class of Numbers class, findAllSums() method (larger input sets).
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Numbers_8b_find_all_sums_XL_Tests {

    /*
     * Tested object as instance of the {@link Numbers} class.
     * Must not be static due to parallel execution of test methods.
     */
    private Numbers testObj;

    /*
     * Immutable test data (can be static).
     */
    private static NumbersData testData;


    /*
     * Immutable test data, numb_2[].
     */
    private final int[] nbr_2 = limit(testData.getArr("numb_2"), 24);

    // immutable test data, numb_2[] + 225
    final int[] nbr_2_225 = addNumbers(nbr_2, 225);

    // immutable test data, numb_2[] + 225 + 463
    final int[] nbr_2_225_463 = addNumbers(nbr_2, 225, 463);

    // immutable test data, numb_2[] + 225 + 463 + 286
    final int[] nbr_2_225_463_286 = addNumbers(nbr_2, 225, 463, 286);

    // immutable test data, numb_2[] + 225 + 463 + 286 + 596
    final int[] nbr_2_225_463_286_569 = addNumbers(nbr_2, 225, 463, 286, 569);

    // immutable test data, numb_2[] + 225 + 463 + 286 + 596 + 384
    final int[] nbr_2_225_463_286_569_384 = addNumbers(nbr_2, 225, 463, 286, 569, 384);

    // immutable test data, numb_1[] + 225 + 463 + 286 + 596 + 384 + 9 = 30 numbers
    final int[] nbr_2_225_463_286_569_384_9 = addNumbers(nbr_2, 225, 463, 286, 569, 384, 9);

    // immutable test data, numb_3[]
    final int[] nbr_3 = testData.getArr("numb_3");


    private int[] limit(int arr[], int limit) {
        List<Integer> arrL = new ArrayList<>();
        Collections.addAll(arrL, Arrays.stream(arr).boxed().limit(limit).toArray(Integer[]::new));
        return arrL.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] addNumbers(int arr[], int... args) {
        List<Integer> arrL = new ArrayList<>();
        List<Integer> argsL = new ArrayList<>();
        Collections.addAll(arrL, Arrays.stream(arr).boxed().toArray(Integer[]::new));
        Collections.addAll(argsL, Arrays.stream(args).boxed().toArray(Integer[]::new));
        arrL.addAll(argsL);
        return arrL.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Static setup method executed once befor all tests, often
     * used to setup immutable test data that can be static.
     * @throws Exception when test creation fails
     */
    @BeforeAll
    public static void setUpBeforeTests() throws Exception {
        testData = new NumbersData();
    }

    /**
     * Setup method executed before each @Test method is executed.
     * @throws Exception if any exception occurs
     */
    @BeforeEach
    public void setUpBeforeEach() throws Exception {
        testObj = Numbers.getInstance();
    }

    @Test
    @Order(824)
    void test824_find_all_sums_XL_24_numbers() {
        var actual = testObj.findAllSums(nbr_2, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {226, 773},
            {371, 87, 541},
            {170, 190, 639},
            {226, 27, 541, 205},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {226, 371, 170, 27, 205},
            {226, 371, 7, 205, 190},
            {226, 371, 87, 125, 190},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125}
        };
        assertEquals(13, actual.size());    // 13 solutions with 24 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(825)
    void test825_find_all_sums_XL_25_numbers() {
        var actual = testObj.findAllSums(nbr_2_225, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {226, 773},
            {371, 87, 541},
            {170, 190, 639},
            {226, 27, 541, 205},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {226, 371, 170, 27, 205},
            {226, 371, 7, 205, 190},
            {226, 371, 87, 125, 190},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            //
            {225, 774},
            {225, 226, 7, 541},
            {225, 226, 371, 7, 170}
        };
        assertEquals(16, actual.size());    // 16 solutions with 25 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(826)
    void test826_find_all_sums_XL_26_numbers() {
        var actual = testObj.findAllSums(nbr_2_225_463, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {225, 774},
            {226, 773},
            {170, 190, 639},
            {371, 87, 541},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {226, 27, 541, 205},
            {225, 226, 7, 541},
            {225, 226, 371, 7, 170},
            {226, 371, 7, 205, 190},
            {226, 371, 170, 27, 205},
            {226, 371, 87, 125, 190},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            //
            {225, 87, 7, 27, 190, 463}
        };
        assertEquals(17, actual.size());    // 17 solutions with 26 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(827)
    void test827_find_all_sums_XL_27_numbers() {
        var actual = testObj.findAllSums(nbr_2_225_463_286, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {225, 774},
            {226, 773},
            {170, 190, 639},
            {371, 87, 541},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {226, 27, 541, 205},
            {225, 226, 7, 541},
            {225, 226, 371, 7, 170},
            {226, 371, 7, 205, 190},
            {226, 371, 170, 27, 205},
            {226, 371, 87, 125, 190},
            {225, 87, 7, 27, 190, 463},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            //
            {371, 27, 125, 286, 190},
            {163, 87, 286, 463}
        };
        assertEquals(19, actual.size());    // 19 solutions with 27 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(828)
    void test828_find_all_sums_XL_28_numbers() {
        var actual = testObj.findAllSums(nbr_2_225_463_286_569, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {225, 774},
            {226, 773},
            {170, 190, 639},
            {371, 87, 541},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {163, 87, 286, 463},
            {226, 27, 541, 205},
            {225, 226, 7, 541},
            {225, 226, 371, 7, 170},
            {226, 371, 7, 205, 190},
            {226, 371, 170, 27, 205},
            {226, 371, 87, 125, 190},
            {371, 27, 125, 286, 190},
            {225, 87, 7, 27, 190, 463},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            //
            {226, 7, 569, 170, 27},
            {225, 569, 205}
        };
        assertEquals(21, actual.size());    // 21 solutions with 28 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(829)
    void test829_find_all_sums_XL_29_numbers() {
        var actual = testObj.findAllSums(nbr_2_225_463_286_569_384, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {225, 774},
            {226, 773},
            {170, 190, 639},
            {225, 569, 205},
            {371, 87, 541},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {163, 87, 286, 463},
            {226, 27, 541, 205},
            {225, 226, 7, 541},
            {225, 226, 371, 7, 170},
            {226, 7, 569, 170, 27},
            {226, 371, 7, 205, 190},
            {226, 371, 170, 27, 205},
            {226, 371, 87, 125, 190},
            {371, 27, 125, 286, 190},
            {225, 87, 7, 27, 190, 463},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            //
            {384, 27, 125, 463},
            {384, 226, 87, 7, 170, 125},
            {384, 7, 170, 27, 125, 286}
        };
        assertEquals(24, actual.size());    // 24 solutions with 29 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(830)
    void test830_find_all_sums_XL_30_numbers() {
        var actual = testObj.findAllSums(nbr_2_225_463_286_569_384_9, 999);
        //System.out.println(Matchers.toString(actual));
        int[][] expected = {
            {27, 972},
            {225, 774},
            {226, 773},
            {170, 190, 639},
            {225, 569, 205},
            {371, 87, 541},
            {163, 170, 125, 541},
            {163, 170, 27, 639},
            {163, 7, 190, 639},
            {163, 87, 286, 463},
            {226, 27, 541, 205},
            {225, 226, 7, 541},
            {384, 27, 125, 463},
            {225, 226, 371, 7, 170},
            {226, 7, 569, 170, 27},
            {226, 371, 7, 205, 190},
            {226, 371, 170, 27, 205},
            {226, 371, 87, 125, 190},
            {371, 27, 125, 286, 190},
            {225, 87, 7, 27, 190, 463},
            {226, 163, 371, 7, 27, 205},
            {226, 163, 371, 87, 27, 125},
            {384, 226, 87, 7, 170, 125},
            {384, 7, 170, 27, 125, 286},
            //
            {903, 87, 9},
            {163, 9, 541, 286},
            {226, 9, 125, 639},
            {773, 9, 27, 190},
            {163, 371, 9, 170, 286},
            {225, 370, 9, 205, 190},
            {225, 87, 9, 651, 27},
            {7, 9, 125, 205, 190, 463},
            {9, 170, 27, 125, 205, 463},
            {225, 370, 163, 9, 27, 205},
            {225, 7, 9, 170, 125, 463},
            {225, 7, 9, 27, 541, 190},
            {225, 87, 9, 27, 205, 446},
            {226, 163, 9, 125, 286, 190},
            {384, 226, 163, 9, 27, 190},
            {384, 226, 87, 7, 9, 286},
            {163, 7, 9, 27, 125, 205, 463},
            {225, 371, 7, 9, 170, 27, 190},
            {370, 226, 7, 9, 170, 27, 190},
            {384, 87, 7, 9, 170, 27, 125, 190}
        };
        assertEquals(44, actual.size());    // 44 solutions with 30 numbers
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(840)
    void test840_find_all_sums_XL_numb_3_999() {
        var actual = testObj.findAllSums(nbr_3, 999);
        int[][] expected = {
            {521, 463, 15},
            {500, 36, 463},
            {36, 7, 493, 463},
            {498, 408, 78, 15},
            {498, 23, 463, 15},
            {23, 440, 521, 15},
            {500, 36, 23, 440},
            {36, 485, 463, 15},
            {36, 23, 7, 440, 493},
            {36, 485, 23, 440, 15}
        };
        assertEquals(10, actual.size());    // 10 solutions for sum=999 with numb_3[]
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }

    @Test
    @Order(841)
    void test841_find_all_sums_XL_numb_3_1000() {
        var actual = testObj.findAllSums(nbr_3, 1000);
        int[][] expected = {
            {500, 7, 493},
            {500, 485, 15},
            {485, 7, 493, 15},
            {36, 408, 78, 463, 15},
            {36, 23, 408, 440, 78, 15}
        };
        assertEquals(5, actual.size());    // 5 solutions for sum=1000 with numb_3[]
        assertTrue(Matchers.matchIgnoreOrder(expected, actual));
    }
}
package numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import runtimeSE.RuntimeSE;


public class TestData extends HashMap<String, List<Integer>> {

    /*
     * Numbers with negative numbers and duplicates.
     */
    private List<Integer> numb = Arrays.asList(-2, 4, 9, 4, -3, 4, 9, 5);

    /*
     * Numbers with no negative numbers and no duplicates.
     */
    private List<Integer> numb_1 = Arrays.asList(8, 10, 7, 2, 14, 5, 4);

    /*
     * Larger set of 24 numbers, no negatives, no duplicates.
     */
    private List<Integer> numb_2 = Arrays.asList(   // 24 numbers
        371,  682,  446,  754,  205,  972,  600,  163,  541,  672,
         27,  170,  226,    7,  190,  639,   87,  773,  651,  370,
        125,  774,  903,  636//,225,  463,  286,  569,  384,    9,
    ); // add more numbers to find more solutions

    /*
     * Even larger set of 63 numbers, no negatives, no duplicates.
     */
    private List<Integer> numb_3 = Arrays.asList(
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
    TestData() {
        super.put("numb", numb);
        super.put("numb_1", numb_1);
        super.put("numb_2", numb_2);
        super.put("numb_3", numb_3);
    }

    int[] getArray(String key) {
        return getList(key).stream().mapToInt(Integer::intValue).toArray();
    }

    List<Integer> getList(String key) {
        return Optional.ofNullable(RuntimeSE.getInstance().properties().getProperty("numbers.data." + key))
            .map(data -> Arrays.asList(data.split("(\\s+|,|;)+")).stream()
                .map(n -> {
                    try {
                        return Optional.of(Integer.parseInt(n));
                    } catch(NumberFormatException nex) {
                        return Optional.<Integer>empty();
                    }
                })
                .flatMap(Optional::stream)
                .toList()
            )
            .orElse(Optional.ofNullable(super.get(key)).orElse(List.of()));
    }
}
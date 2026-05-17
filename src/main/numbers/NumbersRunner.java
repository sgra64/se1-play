package numbers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import runtimeSE.CommandRunner;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;

import numbers.Numbers.Pair;


/**
 * Non-public class that implements the {@link Runner} interface to execute
 * commands passed as command line arguments.
 */
@Accessors(priority=2)
class NumbersRunner implements Runner, CommandRunner {

    private final Numbers numbersImpl  = new NumbersImpl();


    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        System.out.println(String.format("Hello, %s (numbers)",
            runtime.properties().getProperty("application.name", "")
        ));
        // 
        CommandRunner.run(this,
            "numbers, sum, sumPositiveEvenNumbers, sumRecursive, findFirst, findLast, findAll, findSums, findAllSums",
            String.join(" ", args));
    }


    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String command, KVArgs kvargs) {
        // 
        StringBuffer display = new StringBuffer("[");
        String data_key = "";
        List<Integer> numbers = kvargs.asIntList("numbers");
        // 
        if(numbers.size()==0) {
            data_key = kvargs.value("numbers", "");
            data_key = data_key.length() > 0? data_key :
                kvargs.keys().stream()
                    .filter(k -> kvargs.value(k) == null)
                    .findFirst().orElse("unknown");
            // 
            // fetch numbers from 'application.properties'
            String data = Optional.ofNullable(runtime.properties().getProperty("numbers.data." + data_key)).orElse("0");
            String finalData_key = data_key;
            numbers = Arrays.stream(data.split("(\\s+|,|;)+"))
                .map(n -> {
                    try {
                        return Optional.of(Integer.parseInt(n));
                    } catch(NumberFormatException nex) {
                        runtime.logger().warn(String.format("%s: invalid number '%s' in data set '%s'", this.getClass().getSimpleName(), n, finalData_key));
                        return Optional.<Integer>empty();
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                // .flatMap(Optional::stream)
                // .peek(System.out::println)
                // .collect(Collectors.toList());
                // .mapToInt(Integer::intValue)
                // .toArray();
                .toList();
        }
        int linelen = 0;
        for(int i=0; i < numbers.size(); i++) {
            display.append(numbers.get(i));
            display.append(i==numbers.size()-1? "" : ", ");
            if(display.length() - linelen > 40) {
                display.append("\n  ");
                linelen = display.length();
            }
        }
        if(data_key.length()==0) {
            display.append("]");
        } else {
            display.setLength(0);
            display.append(data_key);
        }
        int[] numb_arr = numbers.stream().mapToInt(Integer::intValue).toArray();
        // 
        long res = 0L;
        boolean findFirst = false;
        String result = "";

        switch(command) {

        case "sum":
            res = numbersImpl.sum(numb_arr);
            result = String.format("%s(%s) -> %d", command, display, res);
            break;

        case "sumPositiveEvenNumbers":
            res = numbersImpl.sumPositiveEvenNumbers(numb_arr);
            result = String.format("%s(%s) -> %d", command, display, res);
            break;

        case "sumRecursive":
            res = numbersImpl.sumRecursive(numb_arr, 0);
            result = String.format("%s(%s) -> %d", command, display, res);
            break;

        case "findFirst": findFirst = true;
        case "findLast":
        case "findAll":
            int x = kvargs.asInt("x", 0);
            if(command.equals("findAll")) {
                result = String.format("%s(%s, x=%d) -> %s", command, display, x, numbersImpl.findAll(numb_arr, x));
            } else {
                res = findFirst? numbersImpl.findFirst(numb_arr, x) : numbersImpl.findLast(numb_arr, x);
                result = String.format("%s(%s, x=%d) -> %d", command, display, x, res);
            }
            break;

        case "findSums":
        case "findAllSums":
            int sum = kvargs.asInt("sum", 0);
            if(command.equals("findSums")) {
                Set<Pair> sums = numbersImpl.findSums(numb_arr, sum);
                result = prettyPrintPairs(sums);
            } else {
                Set<Set<Integer>> sums = numbersImpl.findAllSums(numb_arr, sum);
                result = prettyPrintNumberSet(sums);
            }
            result = String.format("%s(%s, sum=%d) -> %s", command, display, sum, result);
            break;
        }

        if( ! result.isEmpty()) {
            System.out.println(String.format(" - %s", result));
        }
    }

    /**
     * Pretty-print variable length {@code Set<Pair>} numbers.
     * @param pairs to print
     * @return pretty-printed pairs
     */
    private String prettyPrintPairs(Set<Pair> pairs) {
        StringBuffer sb = new StringBuffer("[");
        String numStr = pairs != null? pairs.toString() : "";
        boolean large = numStr.length() > 40;
        int j=0;
        for(Pair p : pairs) {
            sb.append(sb.length() > 1? ", " : "");
            if(large && j % 5 == 0) {
                sb.append("\n    - ");
            }
            sb.append(p.toString());
            j++;
        }
        sb.append(large? "\n   ], " : "], ");
        sb.append(String.format("solutions: %d", j));
        return sb.toString();
    }

    /**
     * Pretty-print variable length, nested {@code Set<Set<Integer>>} numbers.
     * @param nested set to print
     * @return pretty-printed nested sets
     */
    private String prettyPrintNumberSet(Set<Set<Integer>> numbers) {
        StringBuffer sb = new StringBuffer("[");
        String numStr = numbers != null? numbers.toString() : "";
        boolean large = numStr.length() > 40;
        var solutions = numbers.stream()
            .sorted((a, b) -> Integer.compare(a.size(), b.size()))
            .map(sol -> {
                sb.append(sb.length() > 1? ", " : "");
                sb.append(large? "\n    - " : "").append(sol.toString());
                return sol;
            }).collect(Collectors.toList());
        sb.append(large? "\n   ], " : "], ");
        sb.append(String.format("solutions: %d", solutions.size()));
        return sb.toString();
    }
}

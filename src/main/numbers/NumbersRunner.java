package numbers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import application.Runner;
import numbers.Numbers.Pair;


/**
 * Non-public class that implements the {@link Runner} interface to execute
 * commands passed as command line arguments.
 */
class NumbersRunner implements Runner {

    /*
     * Instance that performs calculations defined by the {@link Numbers} interface.
     */
    private final Numbers numbers;

    /*
     * Map that holds data sets of numbers: "numb", "numb_1", "numb_2", "numb_3".
     */
    private final NumbersData numData;


    /**
     * Constructor.
     * @param numbers instance used to perform actions defined by the {@link Numbers} interface
     */
    NumbersRunner(Numbers numbers) {
        this.numbers = numbers;
        this.numData = new NumbersData();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String[] args) {
        // 
        Queue<String> commands = new LinkedList<>(Arrays.asList(args));
        // 
        while( ! commands.isEmpty()) {
            // 
            String cmd = commands.poll();
            // 
            String argn = Optional.ofNullable(commands.isEmpty()? null : commands.poll()).orElse("numb");
            var data = numData.getArr(argn);
            // 
            String result = "";
            boolean ff = false;
            // System.out.println("--> cmd: " + cmd + " --> argn: " + argn);
            switch(cmd) {
            //
            case "sum":
                var res = numbers.sum(data);
                result = String.format("%s(%s) -> %d", cmd, argn, res);
                break;
            // 
            case "sum_positive_even_numbers":
                res = numbers.sum_positive_even_numbers(data);
                result = String.format("%s(%s) -> %d", cmd, argn, res);
                break;
            //
            case "sum_recursive":
                res = numbers.sum_recursive(data, 0);
                result = String.format("%s(%s) -> %d", cmd, argn, res);
                break;
            //
            case "findFirst": ff = true;
            case "findLast":
            case "findAll":
                // int x = Optional.ofNullable(argsMap.get("x")).filter(o -> o.isInt()).map(o -> o.intValue()).orElse(0);
                if( ! commands.isEmpty()) {
                    var p = commands.poll().split("=");
                    if(p.length > 1 && p[0].equals("x")) {
                        try {
                            int x = Integer.parseInt(p[1].trim());
                            if(cmd.equals("findAll")) {
                                var res2 = numbers.findAll(data, x);
                                result = String.format("%s(%s, x=%d) -> %s", cmd, argn, x, res2);
                            } else {
                                res = ff? numbers.findFirst(data, x) : numbers.findLast(data, x);
                                result = String.format("%s(%s, x=%d) -> %d", cmd, argn, x, res);
                            }
                            break;
                        } catch(NumberFormatException nex) { }
                    }
                }
                continue;
            //
            case "findSums":
                if( ! commands.isEmpty()) {
                    var p = commands.poll().split("=");
                    if(p.length > 1 && p[0].equals("sum")) {
                        try {
                            int sum = Integer.parseInt(p[1].trim());
                            var res2 = numbers.findSums(data, sum);
                            String res2Str = prettyPrintPairs(res2);
                            result = String.format("%s(%s, sum=%d) -> %s", cmd, argn, sum, res2Str);
                            break;
                        } catch(NumberFormatException nex) { }
                    }
                }
                continue;
            // 
            case "findAllSums":
                if( ! commands.isEmpty()) {
                    var p = commands.poll().split("=");
                    if(p.length > 1 && p[0].equals("sum")) {
                        try {
                            int sum = Integer.parseInt(p[1].trim());
                            var res3 = numbers.findAllSums(data, sum);
                            String res3Str = prettyPrintNumberSet(res3);
                            result = String.format("%s(%s, sum=%d) -> %s", cmd, argn, sum, res3Str);
                            break;
                        } catch(NumberFormatException nex) { }
                    }
                }
                continue;
            }
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

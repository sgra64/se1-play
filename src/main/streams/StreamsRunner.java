package streams;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import runtimeSE.CommandRunner;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;


/**
 * Non-public class that implements interfaces {@link Runner} and
 * {@link CommandRunner} processing commands from the command line.
 */
@Accessors(priority=3)
class StreamsRunner implements Runner, CommandRunner {

    private final Streams streams  = new StreamsImpl();

    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        System.out.println(String.format("Hello, %s (streams)",
            runtime.properties().getProperty("application.name", "")
        ));
        // 
        CommandRunner.run(this,
            "tenRandomNumbers, tenEvenRandomNumbers, tenSortedEvenRandomNumbers, " +
            "filteredNumbers, filteredNames, sortedNames, sortedNamesByLength, " +
            "calculateOrderValue, sortOrdersByValue", String.join(" ", args));
    }

    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String command, KVArgs kvargs) {
        // 
        int repeat = kvargs.asInt("repeat", 1);
        StringBuilder result = new StringBuilder();
        for(int i=0; i < repeat; i++) {
            switch(command) {
                //
                case "tenRandomNumbers":
                    var l1 = streams.tenRandomNumbers().toList();
                    result.append(String.format(" - %s() -> %s\n", command, l1));
                    break;

                case "tenEvenRandomNumbers":
                    var l2 = streams.tenEvenRandomNumbers().toList();
                    result.append(String.format(" - %s() -> %s\n", command, l2));
                    break;

                case "tenSortedEvenRandomNumbers":
                    var l3 = streams.tenSortedEvenRandomNumbers().toList();
                    result.append(String.format(" - %s() -> %s\n", command, l3));
                    break;
                
                case "filteredNumbers":
                    String filterName = kvargs.value("filter", "evenFilter");
                    int limit = kvargs.asInt("limit", 8);
                    //
                    final Map<String, Function<Integer, Boolean>> filterFunctions = Map.of(
                        "evenFilter", Streams.evenFilter,
                        "div3Filter", Streams.div3Filter,
                        "primeFilter", Streams.primeFilter
                    );
                    // 
                    Function<Integer, Boolean> filterFunction = Optional.ofNullable(
                        filterFunctions.get(filterName)).orElse(n2 -> true);
                    // 
                    var l4 = streams.filteredNumbers(filterFunction, limit);
                    // 
                    result.append(String.format(" - %s(%s) -> %s\n", command, filterName, l4));
                    break;

                case "filteredNames":
                    String regex = kvargs.value("regex", ".*ez$");
                    var l5 = streams.filteredNames(Streams.names, regex);
                    result.append(String.format(" - %s(\"%s\") -> %s\n", command, regex, l5));
                    break;

                case "sortedNames":
                    limit = kvargs.asInt("limit", 5);
                    var l6 = streams.sortedNames(Streams.names, limit);
                    result.append(String.format(" - %s(%s, %d) -> %s\n", command, "Streams.names", limit, l6));
                    break;
                
                case "sortedNamesByLength":
                    var l7 = streams.sortedNamesByLength(Streams.names);
                    result.append(String.format(" - %s(%s) -> %s\n", command, "Streams.names", l7));
                    break;

                case "calculateOrderValue":
                    long value = streams.calculateOrderValue(Streams.orders);
                    result.append(String.format(" - %s(%s) -> %d\n", command, "Streams.orders", value));
                    break;

                case "sortOrdersByValue":
                    StringBuilder orders = new StringBuilder("\n");
                    value = streams.calculateOrderValue(Streams.orders);
                    streams.sortOrdersByValue(Streams.orders)
                        .stream()
                        // .peek(System.out::println)
                        .forEach(order -> orders.append(String.format("    - %s\n", order.toString())));
                    //
                    orders.append(" ".repeat(22)).append("--------\n");
                    orders.append(" ".repeat(22)).append(String.format("%8d\n", value));
                    orders.append(" ".repeat(22)).append("=========");
                    //
                    result.append(String.format(" - %s() -> \n    \\\\%s\n", command, orders.toString()));
                    break;
                }
        }
        if( ! result.isEmpty()) {
            result.setLength(result.length() - 1);  // remove trailing '\n'
            System.out.println(String.format("%s", result));
        }
    }
}

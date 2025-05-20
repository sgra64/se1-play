package streams;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import application.Runner;


/**
 * Non-public class that implements the {@link Runner} interface to execute
 * commands passed as command line arguments.
 */
class StreamsRunner implements Runner {

    /*
     * Instance that performs calculations defined by the {@link Streams} interface.
     */
    private final Streams streams;

    /**
     * Constructor.
     * @param streams instance used to perform actions defined by the {@link Streams} interface
     */
    StreamsRunner(Streams streams) {
        this.streams = streams;
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
            String cmd = commands.poll();
            String result = "";
            // System.out.println("--> cmd: " + cmd + " --> argn: " + argn);
            switch(cmd) {
            //
            case "tenRandomNumbers":
                var l1 = streams.tenRandomNumbers().toList();
                result = String.format("%s() -> %s", cmd, l1);
                break;
            //
            case "tenEvenRandomNumbers":
                var l2 = streams.tenEvenRandomNumbers().toList();
                result = String.format("%s() -> %s", cmd, l2);
                break;
            //
            case "tenSortedEvenRandomNumbers":
                var l3 = streams.tenSortedEvenRandomNumbers().toList();
                result = String.format("%s() -> %s", cmd, l3);
                break;
            //
            case "filteredNumbers":
                String filter = getArg(commands).orElse("even");
                int size = getIntArg(commands).orElse(5);
                var l4 = streams.filteredNumbers(filter, size);
                result = String.format("%s(%s, %d) -> %s", cmd, filter, size, l4);
                break;
            //
            case "filteredNames":
                String regex = getArg(commands).orElse(".*ez$");
                var l5 = streams.filteredNames(Streams.names, regex);
                result = String.format("%s(%s) -> %s", cmd, regex, l5);
                break;
            //
            case "sortedNames":
                size = getIntArg(commands).orElse(5);
                var l6 = streams.sortedNames(Streams.names, size);
                result = String.format("%s(%s, %d) -> %s", cmd, "Streams.names", size, l6);
                break;
            //
            case "sortedNamesByLength":
                var l7 = streams.sortedNamesByLength(Streams.names);
                result = String.format("%s(%s) -> %s", cmd, "Streams.names", l7);
                break;
            //
            case "calculateOrderValue":
                long value = streams.calculateOrderValue(Streams.orders);
                result = String.format("%s(%s) -> %d", cmd, "Streams.orders", value);
                break;
            //
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
                orders.append(" ".repeat(22)).append("========");
                //
                // System.out.println(orders.toString());
                result = String.format("%s() -> \n    \\\\%s", cmd, orders.toString());
                break;
            }
            System.out.println(String.format(" - %s", result));
        }
    }

    /**
     * Pull next argument from {@code args} or return empty result.
     * @param args queue of arguments to pull from
     * @return next argument or return empty result
     */
    private Optional<String> getArg(Queue<String> args) {
        return Optional.ofNullable(args.isEmpty()? null : args.poll());
    }

    /**
     * Pull next argument from {@code args} and convert number or
     * return empty result.
     * @param args queue of arguments to pull from
     * @return next number argument or return empty result
     */
    private Optional<Integer> getIntArg(Queue<String> args) {
        return Optional.ofNullable(args.isEmpty()? null : args.poll())
            .map(arg -> {
                try {
                    return Integer.parseInt(arg);
                } catch(NumberFormatException e) { }
                return null;
            }).filter(i -> i != null);
    }
}
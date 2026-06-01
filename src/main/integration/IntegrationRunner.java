package integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import runtimeSE.CommandRunner;
import runtimeSE.Runner;
import runtimeSE.RuntimeSE;
import runtimeSE.Runner.Accessors;

import optionals.OptionalsRunner;
import numbers.NumbersRunner;

/**
 * Public class that implements the {@link Runner} and {@link CommandRunner}
 * interfaces to integrate commans executions.
 */
@Accessors(priority=10)
class IntegrationRunner implements Runner, CommandRunner {

    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String[] args) {
        // 
        System.out.println(String.format("Hello, %s (integration)",
            runtime.properties().getProperty("application.name", "")
        ));
        // 
        CommandRunner.run(this, "b1-optionals, b2-numbers, b3-streams", String.join(" ", args));
    }


    /** {@inheritDoc} */
    @Override
    public void run(RuntimeSE runtime, String command, KVArgs kvargs) {
        // 
        switch(command) {
        // 
        case "b1-optionals":
            var optionalsRunner = new OptionalsRunner();
            optionalsRunner.run(runtime, kvargs.keys().toArray(new String[0]));
            break;
        // 
        case "b2-numbers":
            var numbersRunner = new NumbersRunner();
            numbersRunner.run(runtime, args(kvargs));
            break;
        }
    }

    private String[] args(KVArgs kvargs) {
        List<String> args = new ArrayList<>();
        for(String k : kvargs.keys()) {
            args.add(Optional.ofNullable(kvargs.value(k))
                .map(v2 -> k + "=" + (v2.contains(",")? "[" + v2 + "]" : v2)).orElse(k));
        }
        return args.toArray(new String[0]);
    }
}

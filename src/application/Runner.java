package application;

/**
 * Public interface of a <i>command line Runner</i> that executes commands
 * passed as command line arguments. The {@code run(String[] args)} method
 * has {@code String[] args} not provided by the {@link Runnable} interface.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Runner {

    /**
     * Perform commands passed as command line arguments.
     * @param args commands passed from the command line
     */
    void run(String[] args);
}
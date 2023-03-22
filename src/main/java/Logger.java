import java.util.logging.Level;

public class Logger {
    private final java.util.logging.Logger logger;

    public Logger() {
        this.logger = java.util.logging.Logger.getLogger(Logger.class.getName());
    }

    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    public void debug(String message) {
        logger.log(Level.FINE, message);
    }

    public void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }
}

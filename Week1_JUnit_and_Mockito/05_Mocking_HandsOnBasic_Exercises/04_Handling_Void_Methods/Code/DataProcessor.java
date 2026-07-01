public class DataProcessor {
    private Logger logger;

    public DataProcessor(Logger logger) {
        this.logger = logger;
    }

    public void processData(String data) {
        logger.log("Processing data: " + data);

        logger.log("Data processed successfully");
    }

    public void handleError(String error) {
        logger.error("An error occurred: " + error);
    }

    public void executeDebugMode(String debugInfo) {
        logger.debug("Debug info: " + debugInfo);
    }

    public void processWithValidation(String data) {
        if (data == null || data.isEmpty()) {
            logger.error("Invalid data provided");
        } else {
            logger.log("Valid data: " + data);
        }
    }
}

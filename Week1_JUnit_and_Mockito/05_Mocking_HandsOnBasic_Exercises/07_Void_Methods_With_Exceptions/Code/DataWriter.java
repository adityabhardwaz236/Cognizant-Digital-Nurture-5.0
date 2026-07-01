public class DataWriter {
    private FileWriter fileWriter;
    private Logger logger;

    public DataWriter(FileWriter fileWriter, Logger logger) {
        this.fileWriter = fileWriter;
        this.logger = logger;
    }

    public void writeData(String filename, String data) {
        try {
            logger.log("Writing to file: " + filename);
            fileWriter.writeToFile(filename, data);
            logger.log("Successfully wrote data");
        } catch (Exception e) {
            logger.error("Error writing to file: " + e.getMessage());
        }
    }

    public void closeFile(String filename) {
        try {
            fileWriter.closeFile(filename);
            logger.log("File closed: " + filename);
        } catch (Exception e) {
            logger.error("Error closing file: " + filename);
        }
    }

    public void processFileOperation(String filename, String data) {
        fileWriter.writeToFile(filename, data);
        fileWriter.closeFile(filename);
    }
}

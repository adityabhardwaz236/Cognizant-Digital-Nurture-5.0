public interface Logger {
    void log(String message);
    void error(String message);
}

public interface FileWriter {
    void writeToFile(String filename, String content);
    void closeFile(String filename);
}

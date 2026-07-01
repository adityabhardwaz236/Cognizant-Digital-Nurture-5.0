public class FileService {
    private FileReader fileReader;
    private FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        return "Processed " + content;
    }

    public void copyFileContent() {
        String content = fileReader.read();
        fileWriter.write(content);
    }

    public String readAndProcess() {
        String content = fileReader.read();
        if (content != null && !content.isEmpty()) {
            fileWriter.append(content + " [Processed]");
            return "File processed successfully";
        }
        return "No content to process";
    }

    public int getLineCount() {
        return fileReader.countLines();
    }

    public String readSpecificLine(int lineNumber) {
        return fileReader.readLine(lineNumber);
    }

    public void backup() {
        String content = fileReader.read();
        fileWriter.write("[BACKUP] " + content);
    }
}

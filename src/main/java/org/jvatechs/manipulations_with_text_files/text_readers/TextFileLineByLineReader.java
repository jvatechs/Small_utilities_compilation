package org.jvatechs.manipulations_with_text_files.text_readers;

import org.jvatechs.manipulations_with_common_files.MyFileChooser;

import java.io.*;
import java.util.function.Consumer;

public class TextFileLineByLineReader {
    private final MyFileChooser myFileChooser;
    private String filename;
    public TextFileLineByLineReader() {
        myFileChooser = new MyFileChooser();
    }

    public TextFileLineByLineReader(String filesDescriptionForFileChooser, String... fileExtensions) {
        myFileChooser = new MyFileChooser(filesDescriptionForFileChooser, fileExtensions);
    }

    //reads line by line with your function parameter
    public void processFileWithConsumer(Consumer<String> lineProcessor) {
        File file;
        if (filename == null) {
            file = myFileChooser.chooseFile();
        } else {
            file = new File(filename);
        }

        if (file == null) {
            System.out.println("No file selected.");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineProcessor.accept(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File Not found.", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MyFileChooser getMyFileChooser() {
        return myFileChooser;
    }
}

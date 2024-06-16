package org.jvatechs.manipulations_with_textfiles_and_csv.text_readers;

import org.jvatechs.manipulations_with_common_files.MyFileChooser;

import java.io.*;
import java.util.function.Function;

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
    public void processFileWithFunction(Function<String, Void> lineProcessor) {
        File file;
        if (filename == null) {
            file = myFileChooser.chooseFile();
        } else {
            file = new File(filename);
        }

        if (file != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    lineProcessor.apply(line);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File Not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MyFileChooser getMyFileChooser() {
        return myFileChooser;
    }
}

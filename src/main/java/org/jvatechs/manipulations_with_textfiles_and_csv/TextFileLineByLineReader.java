package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.jvatechs.manipupulations_with_files.MyFileChooser;

import java.io.*;
import java.util.function.Function;

public class TextFileLineByLineReader {
    private final MyFileChooser myFileChooser;
    private String filename;

    public TextFileLineByLineReader(String filesDescriptionForFileChooser, String... fileExtensions) {
        myFileChooser = new MyFileChooser(filesDescriptionForFileChooser, fileExtensions);
    }

    public TextFileLineByLineReader() {
        myFileChooser = new MyFileChooser();
    }

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
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

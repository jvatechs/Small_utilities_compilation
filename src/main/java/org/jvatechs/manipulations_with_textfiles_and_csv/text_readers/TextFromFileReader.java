package org.jvatechs.manipulations_with_textfiles_and_csv.text_readers;

import org.jvatechs.loggers.Loggable;

import java.util.ArrayList;

public class TextFromFileReader implements Loggable {
    private String sourceFile;
    private final TextFileLineByLineReader lineReader = new TextFileLineByLineReader();


    public TextFromFileReader() {
    }

    public TextFromFileReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String readFromTxtToString() {
        StringBuilder text = new StringBuilder();
        if (sourceFile != null) lineReader.setFilename(sourceFile);
        lineReader.processFileWithFunction(line -> {
            text.append(line);
            text.append('\n');
            return null;
        });
        getLogger().info("Successfully read from file...");
        return text.toString();
    }

    public ArrayList<String> readFromTxtToArrayList() {
        ArrayList<String> wordList = new ArrayList<>();
        if (sourceFile != null) lineReader.setFilename(sourceFile);
        lineReader.processFileWithFunction(line -> {
            wordList.add(line);
            return null;
        });
        return wordList;
    }

    public void setDialogTitle(String dialogTitle) {
        lineReader.getMyFileChooser().setDialogTitle(dialogTitle);
    }

    public void setInitialDirectory(String initialDirectory) {
        lineReader.getMyFileChooser().setInitial_directory(initialDirectory);
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }
}

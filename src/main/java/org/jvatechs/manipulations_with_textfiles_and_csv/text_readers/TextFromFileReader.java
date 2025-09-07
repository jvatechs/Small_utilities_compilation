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
        lineReader.processFileWithConsumer(line -> {
            text.append(line);
            text.append('\n');
        });
        getLogger().info("Successfully read from file...");
        return text.toString();
    }

    public ArrayList<String> readFromTxtToArrayList() {
        ArrayList<String> wordList = new ArrayList<>();
        if (sourceFile != null) lineReader.setFilename(sourceFile);
        lineReader.processFileWithConsumer(wordList::add);
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

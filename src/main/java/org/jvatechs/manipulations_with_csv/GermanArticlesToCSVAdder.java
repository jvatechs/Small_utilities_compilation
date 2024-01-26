package org.jvatechs.manipulations_with_csv;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.HashMap;

/*
    Here we have CSV file like with lines like: "word in German;translate".
    The "word in German" can contain " f,"/" m,"/" n," markers in Nouns.
    But unfortunately we don't have the articles.
    So the part of code transform word in german like "word f/m/n," into "die/der/das word ..."
    For example,
    "wort n,..." => "das wort n,..."
 */

public class GermanArticlesToCSVAdder {
    private static final String INITIAL_DIRECTORY = "C:/Users/Owner/Documents";
    private static final String FILTER_DESCRIPTION = "CSV files ONLY (*.csv)";
    private static final String FILTER_EXTENSIONS = "csv";
    private HashMap<String, String> dictionaryMap;


    public void init(String newFileName) {
        createMapFromCSV();
        String readyText = createReadyText();
        saveResultIntoFile(readyText, newFileName);
    }

    private void createMapFromCSV() {
        dictionaryMap = new HashMap<>();
        File csvFile = chooseFile();
        if (csvFile != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {

                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();

                    String[] splittedLine = line.split(";");
                    String deutschWord = splittedLine[0];
                    dictionaryMap.put(addArticleBeforeWord(deutschWord), splittedLine[1]);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String addArticleBeforeWord(String deutschWord) {
        if (deutschWord.contains(" f,")) {
           return "die " + deutschWord;
        }
        if (deutschWord.contains(" m,")) {
            return "der " + deutschWord;
        }
        if (deutschWord.contains(" n,")) {
            return "das " + deutschWord;
        }
        return deutschWord;
    }

    private File chooseFile() {
        JFileChooser fileChooser = new JFileChooser(INITIAL_DIRECTORY);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter(FILTER_DESCRIPTION, FILTER_EXTENSIONS);
        fileChooser.setFileFilter(fnef);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private String createReadyText() {
        StringBuilder sb = new StringBuilder();
        dictionaryMap.forEach((deutsch, translate)
                -> sb.append(deutsch).append(";").append(translate).append('\n'));
        return sb.toString();
    }

    private void saveResultIntoFile(String CSVFormattedText, String newFileName) {
        try (PrintWriter printWriter = new PrintWriter(newFileName)) {
            printWriter.print(CSVFormattedText);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

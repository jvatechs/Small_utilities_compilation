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

    public static void main(String[] args) {
        System.out.println(new GermanArticlesToCSVAdder().addArticleBeforeWord("Wand f, -e-"));
        System.out.println(new GermanArticlesToCSVAdder().addArticleBeforeWord("Verein m, -e-"));
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
        String feminine = "f,";
        String masculine = "m,";
        String neutral = "n,";


        if (deutschWord.contains(feminine)) {
            return buildWordWithArticle(deutschWord, feminine, "die ");
        }
        if (deutschWord.contains(masculine)) {
            return buildWordWithArticle(deutschWord, masculine, "der ");
        }
        if (deutschWord.contains(neutral)) {
            return buildWordWithArticle(deutschWord, neutral, "das ");
        }
        return deutschWord;
    }

    private static String buildWordWithArticle(String deutschWord, String feminine, String x) {
        String word = deutschWord.substring(0, deutschWord.indexOf(feminine));
        String anotherPieceInBrackets =
                "(" + deutschWord.substring(deutschWord.indexOf(feminine)) + ")";
        return x + word + anotherPieceInBrackets;
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

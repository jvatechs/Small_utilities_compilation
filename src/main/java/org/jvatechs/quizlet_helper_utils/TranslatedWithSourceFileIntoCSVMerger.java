package org.jvatechs.quizlet_helper_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFromFileReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

import java.util.ArrayList;
/**
 * The class is used for merging to TXT files with words in each
 * new line. The first file contains english words, the second one -
 * translated words.
 * Every word's row in the 1st file is equal to translated word in the
 * 2nd line.
 *
 * The class used to merge the two files and get output *.csv file, which will
 * contain rows like "word;translation"
 * This is useful for Quizlet app.
 *
 */
public class TranslatedWithSourceFileIntoCSVMerger {
    private static final String TO_BE_TRANSLATED_FILE_FOLDER
            = "EnglishFile-Beginner-Vocabulary/";
    private static final String TO_BE_TRANSLATED_FILE_NAME
            = "English File 4_Beginner_words";
    private static final String TRANSLATED_FILE_PATH
            = TO_BE_TRANSLATED_FILE_FOLDER + "/Translated/"
            + TO_BE_TRANSLATED_FILE_NAME + "_translatedPairs.csv";
    private final TextFromFileReader textFromFileReader = new TextFromFileReader();
    private final TextIntoFileSaver textIntoFileSaver
            = new TextIntoFileSaver("translated_English_File.csv");
    private ArrayList<String> originalWords;
    private ArrayList<String> translatedWords;

    public void init() {
        readingTwoFiles();
        createCSVTranslatedFile();
    }

    public void readingTwoFiles() {
        textFromFileReader.setSourceFile(null);
        textFromFileReader.setDialogTitle("PLEASE CHOOSE TRANSLATION OF  TXT: ");
        translatedWords = textFromFileReader.readFromTxtToArrayList();

        textFromFileReader
                .setSourceFile(TO_BE_TRANSLATED_FILE_FOLDER + TO_BE_TRANSLATED_FILE_NAME + ".txt");
        originalWords = textFromFileReader.readFromTxtToArrayList();

    }

    public void createCSVTranslatedFile() {
        textIntoFileSaver
                .setFilename(TRANSLATED_FILE_PATH);
        textIntoFileSaver.saveTwoListsPairedIntoFile(originalWords, translatedWords);
    }

}

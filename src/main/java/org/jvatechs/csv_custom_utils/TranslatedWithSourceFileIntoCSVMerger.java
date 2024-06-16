package org.jvatechs.csv_custom_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFromFileReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

import java.util.ArrayList;

public class TranslatedWithSourceFileIntoCSVMerger {
    private final TextFromFileReader textFromFileReader = new TextFromFileReader();
    private final TextIntoFileSaver textIntoFileSaver
            = new TextIntoFileSaver("translated_English_File.csv");
    private ArrayList<String> originalWords;
    private ArrayList<String> translatedWords;
    private static final String TO_BE_TRANSLATED_FILE_FOLDER
            = "EnglishFile-Beginner-Vocabulary/";
    private static final String TO_BE_TRANSLATED_FILE_NAME
            = "English File 4_Beginner_words";
    private static final String TRANSLATED_FILE_PATH
            = TO_BE_TRANSLATED_FILE_FOLDER + "/Translated/"
            + TO_BE_TRANSLATED_FILE_NAME + "_translatedPairs.csv";





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

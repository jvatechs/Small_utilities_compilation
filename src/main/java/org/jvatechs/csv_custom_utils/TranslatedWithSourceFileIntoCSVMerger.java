package org.jvatechs.csv_custom_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFromFileReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

import java.util.ArrayList;
import java.util.HashMap;

public class TranslatedWithSourceFileIntoCSVMerger {
    private HashMap<String, String> translatedWordMap;
    private final TextFromFileReader textFromFileReader = new TextFromFileReader();
    private final TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver("translated_English_File.csv");
    private ArrayList<String> originalWords;
    private ArrayList<String> translatedWords;



    public void init() {
        readingTwoFiles();
        createCSVTranslatedFile();
    }

    public void readingTwoFiles() {
        textFromFileReader.setDialogTitle("PLEASE CHOOSE FIRST TXT: ");
        originalWords = textFromFileReader.readFromTxtToArrayList();

        textFromFileReader.setSourceFile("File 1_words.txt");
        translatedWords = textFromFileReader.readFromTxtToArrayList();
    }

    public void createCSVTranslatedFile() {
        textIntoFileSaver.saveTwoListsPairedIntoFile(originalWords, translatedWords);
    }

}

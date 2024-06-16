package org.jvatechs.csv_custom_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

public class LocalMain {
    public static void main(String[] args) {
//        CSVCustomParser csvCustomParser = new CSVCustomParser();
//        TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver();
//        for (int i = 4; i <= 12; i++) {
//            String partName = "File " + i;
//            String[] arrayByValue = csvCustomParser.getArrayByValue(partName);
//            textIntoFileSaver.setFilename("EnglishFile-Beginner-Vocabulary/English " + partName + "_Beginner_words.txt");
//            textIntoFileSaver.saveArrayStringIntoFile(arrayByValue);
//        }


        TranslatedWithSourceFileIntoCSVMerger translatedCSV
                = new TranslatedWithSourceFileIntoCSVMerger();
        translatedCSV.init();
    }
}

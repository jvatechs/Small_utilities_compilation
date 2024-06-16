package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.jvatechs.csv_custom_utils.CSVCustomParser;
import org.jvatechs.csv_custom_utils.TranslatedWithSourceFileIntoCSVMerger;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

public class LocalMain {
    public static void main(String[] args) {
//        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
//        String text = new StringFromFileReader().readFromTxt("word-to-txt.txt");
//        textIntoCSVFormatter.createCSVFormattedText(text);
//        textIntoCSVFormatter.saveResultIntoFile();

//        GermanArticlesToCSVAdder germanArticlesToCsvAdder = new GermanArticlesToCSVAdder();
//        germanArticlesToCsvAdder.init("translated.csv");

//        GermanArticlesToStartMover toStartMover = new GermanArticlesToStartMover();
//        toStartMover.init();

//        TextIntoCSVSpecificFormatter textIntoCSVSpecificFormatter = new TextIntoCSVSpecificFormatter();
//        textIntoCSVSpecificFormatter.init();

        CSVCustomParser csvCustomParser = new CSVCustomParser();
        TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver();
        for (int i = 4; i <= 12; i++) {
            String partName = "File " + i;
            String[] arrayByValue = csvCustomParser.getArrayByValue(partName);
            textIntoFileSaver.setFilename("English " + partName + "_Beginner_words.txt");
            textIntoFileSaver.saveArrayStringIntoFile(arrayByValue);
        }
//
//        String partName = "File 4";
//        String[] arrayByValue = csvCustomParser.getArrayByValue(partName);
//        TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver(partName + "_words.txt");
//        textIntoFileSaver.saveArrayStringIntoFile(arrayByValue);

//        TranslatedWithSourceFileIntoCSVMerger translatedCSV
//                = new TranslatedWithSourceFileIntoCSVMerger();
//        translatedCSV.init();
    }
}

package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.jvatechs.quizlet_helper_utils.custom_formatters.TextIntoCSVSpecificFormatter;

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

        TextIntoCSVSpecificFormatter textIntoCSVSpecificFormatter = new TextIntoCSVSpecificFormatter();
        textIntoCSVSpecificFormatter.init();

    }
}

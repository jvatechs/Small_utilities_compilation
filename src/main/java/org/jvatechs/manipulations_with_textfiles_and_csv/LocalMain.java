package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.jvatechs.word_docx_to_txt_parser.TextFromFileReader;

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

        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
        textIntoCSVFormatter.init();



    }
}

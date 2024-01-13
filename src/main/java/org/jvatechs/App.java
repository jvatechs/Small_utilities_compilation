package org.jvatechs;

import org.jvatechs.text_to_csv_formatter.TextIntoCSVFormatter;
import org.jvatechs.word_docx_to_txt_parser.DocxToTxtParser;

import java.util.logging.Logger;

public class App {
    Logger logger = Logger.getLogger("App.class");
    public static void main(String[] args) {
        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
        String text = new DocxToTxtParser().readFromTxt("word-to-txt.txt");
        textIntoCSVFormatter.createCSVFormattedText(text);
        textIntoCSVFormatter.saveResultIntoFile();

    }
}

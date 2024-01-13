package org.jvatechs.word_docx_to_txt_parser;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jvatechs.text_to_csv_formatter.TextIntoCSVFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.logging.Level;

public class DocxToTxtParser {
    private static final Logger LOGGER = LoggerFactory.getLogger("DocxToTxtParser.class");
    private static final String INITIAL_DIRECTORY = "C:/Users/Owner/Downloads";
    private static final String FILTER_DESCRIPTION = "Word Documents (*.docx)";
    private static final String FILTER_EXTENSIONS = "docx";

    private static final String DEST_FILENAME = "word-to-txt.txt";

    public static void main(String[] args) {
//        String text = new DocxParser().chooseFileAndReturnText();
//        new DocxParser().saveIntoTxt(text);

        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
        String text = new DocxToTxtParser().readFromTxt(DEST_FILENAME);
        textIntoCSVFormatter.createCSVFormattedText(text);
        textIntoCSVFormatter.saveResultIntoFile();

    }

    protected String chooseFileAndReturnText() {
        JFileChooser fileChooser = new JFileChooser(INITIAL_DIRECTORY);
        applyFilters(fileChooser);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            return getTextFromDocxInputStream(selectedFile);
        } else {
            return null;
        }
    }

    private String getTextFromDocxInputStream(File selectedFile) {
        String text = null;
        try {
            FileInputStream fis = new FileInputStream(selectedFile);

            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);

            text = extractor.getText();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File doesn't exist. Please check and try it again.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private void applyFilters(JFileChooser fileChooser) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILTER_DESCRIPTION, FILTER_EXTENSIONS);
        fileChooser.setFileFilter(filter);
    }

    private void saveIntoTxt(String text) {
        try (PrintWriter printWriter = new PrintWriter(DEST_FILENAME)){
            printWriter.println(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        }
    }

    public String readFromTxt(String fileName) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine());
                text.append('\n');
            }
            LOGGER.info("Successfully read from file...");
            return text.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package org.jvatechs.wordDocx_parser_into_console;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class DocxParser {
    private static final String INITIAL_DIRECTORY = "C:/Users/Owner/Downloads";
    private static final String FILTER_DESCRIPTION = "Word Documents (*.docx)";
    private static final String FILTER_EXTENSIONS = "docx";

    private static final String DEST_FILENAME = "word-to-txt.txt";

    public static void main(String[] args) {
//        String text = new DocxParser().chooseFileAndReturnText();
//        new DocxParser().saveIntoTxt(text);

        TextFormatter textFormatter = new TextFormatter();
        String text = new DocxParser().readFromTxt();
        textFormatter.CSVCreator(text);
        textFormatter.saveResultIntoFile();

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

    private String readFromTxt() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DEST_FILENAME))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine());
                text.append('\n');
            }
            return text.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

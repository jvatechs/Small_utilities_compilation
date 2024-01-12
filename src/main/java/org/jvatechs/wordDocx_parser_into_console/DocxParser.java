package org.jvatechs.wordDocx_parser_into_console;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class DocxParser {
    private static final String INITIAL_DIRECTORY = "C:/Users/Owner/Downloads";
    private static final String FILTER_DESCRIPTION = "Word Documents (*.docx)";
    private static final String FILTER_EXTENSIONS = "docx";

    public static void main(String[] args) {
        String text = new DocxParser().chooseFileAndReturnText();
        System.out.println(text);
    }

    private String chooseFileAndReturnText() {
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


}

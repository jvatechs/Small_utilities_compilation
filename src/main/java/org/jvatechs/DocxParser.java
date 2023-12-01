package org.jvatechs;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;

public class DocxParser {
    public static void main(String[] args) {
        String initialDirectory = "C:/Users/Owner/Downloads";

        JFileChooser fileChooser = new JFileChooser(initialDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Word Documents (*.docx)", "docx");
        // Show open dialog; this method does not return until the dialog is closed
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Create a FileInputStream to read the DOCX file
                FileInputStream fis = new FileInputStream(selectedFile);

                // Create XWPFDocument from the FileInputStream
                XWPFDocument document = new XWPFDocument(fis);

                // Create XWPFWordExtractor
                XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                // Extract and print the text
                String text = extractor.getText();
                System.out.println(text);

                // Close the InputStream
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

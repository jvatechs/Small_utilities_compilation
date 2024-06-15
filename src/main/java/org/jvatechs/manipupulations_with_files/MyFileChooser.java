package org.jvatechs.manipupulations_with_files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class MyFileChooser {
    private static final String INITIAL_DIRECTORY = "C:/Users/Owner/Documents";
    private String filterDescription;
    private String[] fileExtensions;
    public MyFileChooser() {
    }

    public MyFileChooser(String filterDescription, String... fileExtensions) {
        this.filterDescription = filterDescription;
        this.fileExtensions = fileExtensions;
    }

    public File chooseFile() {
        JFileChooser fileChooser = new JFileChooser(INITIAL_DIRECTORY);
        if (filterDescription != null && fileExtensions != null) {
            FileNameExtensionFilter fnef = new FileNameExtensionFilter(filterDescription, fileExtensions);
            fileChooser.setFileFilter(fnef);
        }
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
}

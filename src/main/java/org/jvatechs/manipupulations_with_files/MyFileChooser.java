package org.jvatechs.manipupulations_with_files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class MyFileChooser {
    private static String INITIAL_DIRECTORY = "C:/Users/Owner/Documents";
    private final String filterDescription;
    private final String[] fileExtensions;
    private File file;
    public MyFileChooser(String filterDescription, String... fileExtensions) {
        this.filterDescription = filterDescription;
        this.fileExtensions = fileExtensions;
    }

    public File chooseFile() {
        JFileChooser fileChooser = new JFileChooser(INITIAL_DIRECTORY);
        FileNameExtensionFilter fnef = new FileNameExtensionFilter(filterDescription, fileExtensions);
        fileChooser.setFileFilter(fnef);

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    public File getFile() {
        return file;
    }

    public static void setInitialDirectory(String initialDirectory) {
        INITIAL_DIRECTORY = initialDirectory;
    }
}

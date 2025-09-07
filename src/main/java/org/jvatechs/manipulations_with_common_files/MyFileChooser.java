package org.jvatechs.manipulations_with_common_files;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class MyFileChooser {
    private String initial_directory = "C:/Users/Owner/Desktop";
    private String dialogTitle;
    private String filterDescription;
    private String[] fileExtensions;
    public MyFileChooser() {
    }

    public MyFileChooser(String filterDescription, String... fileExtensions) {
        this.filterDescription = filterDescription;
        this.fileExtensions = fileExtensions;
    }

    public File chooseFile() {
        JFileChooser fileChooser = new JFileChooser(initial_directory);
        if (dialogTitle != null) fileChooser.setDialogTitle(dialogTitle);
        if (filterDescription != null && fileExtensions != null) {
            FileNameExtensionFilter filters = new FileNameExtensionFilter(filterDescription, fileExtensions);
            fileChooser.setFileFilter(filters);
        }
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }

    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setInitial_directory(String initial_directory) {
        this.initial_directory = initial_directory;
    }
}

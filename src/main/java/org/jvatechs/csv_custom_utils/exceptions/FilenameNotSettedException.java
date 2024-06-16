package org.jvatechs.csv_custom_utils.exceptions;

public class FilenameNotSettedException extends Exception {
    public FilenameNotSettedException() {
        super("Filename didn't set. Please set filename or use constructor with parameter.");
    }
}

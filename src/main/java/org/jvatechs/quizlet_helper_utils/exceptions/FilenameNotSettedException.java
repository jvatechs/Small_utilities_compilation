package org.jvatechs.quizlet_helper_utils.exceptions;

public class FilenameNotSettedException extends Exception {
    public FilenameNotSettedException() {
        super("Filename didn't set. Please set filename or use constructor with parameter.");
    }
}

package org.jvatechs.json_data_parser_from_html.exception;

public class DataFetchException extends Exception {
    private int drawNum;

    public DataFetchException(int drawNum) {
        super("Error when receiving data. Draw number: " + drawNum);
        this.drawNum = drawNum;
    }

    public DataFetchException(String message) {
        super(message);
    }

    public DataFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getDrawNum() {
        return drawNum;
    }
}

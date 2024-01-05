package org.jvatechs.json_data_parser_from_html.exception;

public class DataFetchException extends Exception {
    private int statusCode;

    public DataFetchException(int statusCode) {
        super("Error when receiving data. Error code: " + statusCode);
        this.statusCode = statusCode;
    }

    public DataFetchException(String message) {
        super(message);
    }

    public DataFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatusCode() {
        return statusCode;
    }
}

package org.jvatechs.sql_lite_parser_into_csv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class SQLParseIntoCSV {
    private Connection connect() {
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:src/main/resources/reword_de.backup";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void printSelectedData() {
        String sql = "SELECT WORD, RUS from WORD where WORD.ID in\n" +
                "(SELECT WORD_ID from WORD_CATEGORY where CATEGORY_ID = '12F95D422FEFC7CAC87E20AFD6230C22' OR CATEGORY_ID = 'BD56D3559D0C29916AC62710EEF01185')";

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            try (PrintWriter printWriter = new PrintWriter(new FileWriter("output.csv"))) {
                printWriter.println("Wort,Übersetzung");

//                System.out.println("Wort:" + '\t' + "Übersetzung:");
                while (resultSet.next()) {
                    String word = resultSet.getString("WORD");
                    String rus = resultSet.getString("RUS");
//                    System.out.println(word + '\t' + rus);

                    printWriter.println(word + "," + rus);
                }
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Data saved to output.csv");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SQLParseIntoCSV app = new SQLParseIntoCSV();
        app.printSelectedData();
    }
}

package org.jvatechs.manipulations_with_csv;

public class LocalMain {
    public static void main(String[] args) {
        GermanArticlesToCSVAdder germanArticlesToCsvAdder = new GermanArticlesToCSVAdder();
        germanArticlesToCsvAdder.init("translated.csv");
    }
}

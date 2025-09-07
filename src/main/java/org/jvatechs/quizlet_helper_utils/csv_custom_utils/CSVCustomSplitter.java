package org.jvatechs.quizlet_helper_utils.csv_custom_utils;

import org.jvatechs.manipulations_with_text_files.text_readers.TextFileLineByLineReader;
import org.jvatechs.manipulations_with_text_files.text_savers.TextIntoFileSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * For csv with rows like: "word;translation;part of book ENGLISHFILE"
 *
 * This class splits one big CSV for several small CSVs for easy usage in
 * Quizlet app, for example.
 * You can learn English File part, then upload the small CSV depends on EnglishFile part
 * and then to learn there the words.
 */
public class CSVCustomSplitter {
    private final TextFileLineByLineReader lineReader = new TextFileLineByLineReader();
    private final Map<String, ArrayList<String>> partsMap = new HashMap<>();
    private final TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver();

    public void init() {
        createCsvMap();
        splitCsvIntoParts();
    }
    private void createCsvMap() {
//        lineReader.setFilename("input.csv");

        lineReader.processFileWithConsumer(line -> {
            String[] columns = line.split(";");
            String partNumber = columns[2].trim();  // Номер части
            String pairOfWords = columns[0] + ";" + columns[1];
            partsMap.computeIfAbsent(partNumber, k -> new ArrayList<>()).add(pairOfWords);
        });
    }

    private void splitCsvIntoParts() {
        for (Map.Entry<String, ArrayList<String>> entry : partsMap.entrySet()) {
            String partNumber = entry.getKey();
            ArrayList<String> lines = entry.getValue();
            String outputFilePath = "EnglishFile-Beginner-Vocabulary/English " + partNumber + ".csv";
            textIntoFileSaver.setFilename(outputFilePath);
            textIntoFileSaver.saveListIntoFile(lines);
        }
    }
}


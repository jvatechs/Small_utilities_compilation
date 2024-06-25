package org.jvatechs.quizlet_helper_utils.csv_custom_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFileLineByLineReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * For csv with rows like: "word;part of book ENGLISHFILE"
 *
 */
public class CSVCustomParser {
    private final TextFileLineByLineReader lineReader = new TextFileLineByLineReader();
    private final HashMap<String, String> csvMap = new HashMap<>();
    private final HashMap<String, ArrayList<String>> listMap = new HashMap<>();
    private final StringBuilder stringBuilder = new StringBuilder();

    public CSVCustomParser() {
        createCSVMap();
        splitMapValuesIntoMapList();
    }

    public void createCSVMap() {
        lineReader.processFileWithFunction(line -> {
            String[] splitLine = line.split(";");
            csvMap.put(splitLine[0], splitLine[1]);
            stringBuilder.append(splitLine[0]);
            stringBuilder.append('\n');
            return null;
        });
    }



    private void splitMapValuesIntoMapList() {
        for (Map.Entry<String, String> entry : csvMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            listMap.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
    }

    public void saveStringBuilderIntoFile() {
        TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver("all_words.txt");
        textIntoFileSaver.saveIntoFile(stringBuilder.toString());
    }

}

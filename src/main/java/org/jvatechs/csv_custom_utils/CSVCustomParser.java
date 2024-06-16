package org.jvatechs.csv_custom_utils;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFileLineByLineReader;

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

    public CSVCustomParser() {
        createCSVMap();
        splitMapValuesIntoMapList();
        System.out.println(Arrays.toString(listMap.entrySet().toArray()));
    }

    private void createCSVMap() {
        lineReader.processFileWithFunction(line -> {
            String[] splitLine = line.split(";");
            csvMap.put(splitLine[0], splitLine[1]);
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

    public String[] getArrayByValue(String englishFilePart) {
        ArrayList<String> wordList = listMap.get(englishFilePart);
        String[] words = new String[wordList.size()];
        return wordList.toArray(words);
    }


}

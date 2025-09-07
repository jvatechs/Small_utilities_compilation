package org.jvatechs.manipulations_with_textfiles_and_csv.text_sorters;

import org.jvatechs.manipulations_with_textfiles_and_csv.text_readers.TextFileLineByLineReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.text_savers.TextIntoFileSaver;

import java.util.HashMap;

public class TextWithHashMapSorter {
    private final TextFileLineByLineReader lineReader =
            new TextFileLineByLineReader();
    private final HashMap<String,String> map = new HashMap<>();
    private final TextIntoFileSaver textSaver = new TextIntoFileSaver("sortedHashMap.txt");

// I have text file with rows like "key '\t' value", for example,
// "dog   0"
// "pig   1"
// "cat   1"
    private void createHashMapFromTextFile() {
        lineReader.processFileWithConsumer(line -> {
            String[] split = line.split("\t");
            map.put(split[0], split[1]);
        });
    }

    private String sortHashMapByValues() {
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((key,value)->{
            if(value.equals("1")) {
                stringBuilder.append(key).append("\t").append(value).append("\n");
            }
        });
        return stringBuilder.toString();
    }

    public void saveSortedHashMapIntoTextFile() {
        createHashMapFromTextFile();
        textSaver.saveIntoFile(sortHashMapByValues());
    }

    public HashMap<String,String> getMap() {
        return map;
    }

}

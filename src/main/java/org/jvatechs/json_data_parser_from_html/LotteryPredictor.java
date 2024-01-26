package org.jvatechs.json_data_parser_from_html;

import java.util.*;

public class LotteryPredictor {

    public static void main(String[] args) {
        // Fill previousResults with data about previous draws
        DrawResultParser drawResultParser = new DrawResultParser();
        drawResultParser.init();
        List<List<Integer>> previousResults = drawResultParser.getPreviousResults();
        int numPredictions = 5;
        List<Integer> predictedNumbers = predictLotteryResults(previousResults, numPredictions);

        System.out.println("Предсказанные числа: " + predictedNumbers);
    }

    public static List<Integer> predictLotteryResults(List<List<Integer>> previousResults, int numPredictions) {
        List<Integer> allNumbers = new ArrayList<>();
        for (List<Integer> result : previousResults) {
            allNumbers.addAll(result);
        }

        Map<Integer, Integer> numberCounts = new HashMap<>();
        for (int number : allNumbers) {
            numberCounts.put(number, numberCounts.getOrDefault(number, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> sortedCounts = new ArrayList<>(numberCounts.entrySet());
        sortedCounts.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Integer> predictedNumbers = new ArrayList<>();
        for (int i = 0; i < numPredictions && i < sortedCounts.size(); i++) {
            predictedNumbers.add(sortedCounts.get(i).getKey());
        }

        // Дополнительно: если нужно составить билет, используя предсказанные числа
        List<Integer> ticketNumbers = new ArrayList<>(predictedNumbers);
        Collections.shuffle(ticketNumbers);

        return predictedNumbers;
    }
}

package org.jvatechs.working_with_json_data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DrawResultParser {
    private static int drawsProcessedCounter = 0;
    private static final int MIN_DRAW = 500;
    private static final int MAX_DRAW = 742;
    private static final HttpClient CLIENT = HttpClient.newHttpClient();


    public static void main(String[] args) {
        String fileName = "all_draw_results.txt";
        //example link with json response
        String uri = "example.com";

        System.out.print("Progress: ");

        try (FileWriter fileWriter = new FileWriter(fileName); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (int drawId = MIN_DRAW; drawId <= MAX_DRAW; drawId++) {
                JsonObject jsonData = getData(drawId, uri);
                appendToFile(jsonData, bufferedWriter, drawId);
                drawsProcessedCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nReady! Data successfully saved!");
    }


    private static JsonObject getData(int drawId, String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri + drawId)).build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).getAsJsonObject();
            } else {
                System.out.println("Error when receiving data for the draw " + drawId + ". Error code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void appendToFile(JsonObject json, BufferedWriter bufferedWriter, int drawId) {
        int totalDraws = MAX_DRAW - MIN_DRAW + 1;

        if (json != null) {
            JsonArray winningNumbers = json.getAsJsonObject("data").getAsJsonArray("winningNumbers");

            try {
                bufferedWriter.write(winningNumbers.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();  // Flush the data to the file

                printProgressBar(drawsProcessedCounter, totalDraws);
                System.out.println("Data for the draw " + drawId + " successfully added to file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void printProgressBar(int drawsProcessed, int totalDraws) {
        int progressBarLength = 50;
        int progress = (int) ((double) drawsProcessed / totalDraws * progressBarLength);

        System.out.print("\r[");
        for (int i = 0; i < progressBarLength; i++) {
            if (i < progress) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] " + drawsProcessed + "/" + totalDraws + " (" + (int) ((double) drawsProcessed / totalDraws * 100) + "%)");
    }
}

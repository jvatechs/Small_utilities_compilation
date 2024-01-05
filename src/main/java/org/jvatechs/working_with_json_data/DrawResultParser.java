package org.jvatechs.working_with_json_data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DrawResultParser {
    public static void main(String[] args) {
        String fileName = "all_draw_results.txt";
        //example link, paste here your own
        String uri = "https://example.com/";
        for (int drawId = 500; drawId <= 750; drawId++) {
            getDataAndAppendToFile(drawId, fileName, uri);
        }
    }

    private static void getDataAndAppendToFile(int drawId, String fileName, String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + drawId))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonArray winningNumbers = json.getAsJsonObject("data").getAsJsonArray("winningNumbers");

                try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                    fileWriter.write(winningNumbers.toString() + System.lineSeparator());
                    System.out.println("Данные для розыгрыша " + drawId + " успешно добавлены в файл.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ошибка при получении данных для розыгрыша " + drawId + ". Код ошибки: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

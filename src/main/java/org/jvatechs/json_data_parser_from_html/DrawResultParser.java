package org.jvatechs.json_data_parser_from_html;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jvatechs.json_data_parser_from_html.exception.DataFetchException;
import org.jvatechs.json_data_parser_from_html.exception.JsonDataNullException;
import org.jvatechs.json_data_parser_from_html.exception.JsonNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.time.Duration;

public class DrawResultParser {
    private static final int MIN_DRAW = 500;
    private static final int MAX_DRAW = 742;
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private final static String RESULTS_TXT = "all_draw_results.txt";
    private final static String DRAW_URL = "https://your-example-link.com";
    private final static Logger LOGGER = LoggerFactory.getLogger(DrawResultParser.class.getName());


    public static void main(String[] args) {

        ProgressBar progressBar = new ProgressBar(MAX_DRAW - MIN_DRAW + 1);

        try (FileWriter fileWriter = new FileWriter(RESULTS_TXT); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            processDraws(bufferedWriter, progressBar);
        } catch (IOException e) {
            LOGGER.error("An error occurred while processing draws.", e);
        }
    }

    private static JsonObject getData(int drawId) throws DataFetchException {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DrawResultParser.DRAW_URL + drawId)).timeout(Duration.ofSeconds(5)).build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return JsonParser.parseString(response.body()).getAsJsonObject();
            } else {
                throw new DataFetchException("Error when receiving data for the draw " + drawId + ". Error code: " + response.statusCode());
            }
        } catch (HttpConnectTimeoutException e) {
            LOGGER.error("Request timeout for draw: " + drawId);
            throw new DataFetchException(drawId);
        } catch (IOException e) {
            LOGGER.error("The http address isn't found.");
            throw new UnresolvedAddressException();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DataFetchException("Data fetch interrupted for draw " + drawId, e);
        }
    }

    private static void processDraws(BufferedWriter bufferedWriter, ProgressBar progressBar) {
        boolean isSuccessful = true;
        for (int drawId = MIN_DRAW; drawId <= MAX_DRAW; drawId++) {
            try {
                JsonObject jsonData = getData(drawId);
                appendToFile(jsonData, bufferedWriter, drawId, progressBar);
            } catch (DataFetchException e) {
                System.out.println(e.getMessage());
                isSuccessful = false;
                break;
            }
        }

        if (isSuccessful) {
            LOGGER.info("\n +++Ready! Data successfully saved!+++");
        } else {
            LOGGER.error("\n ***Data saving process was interrupted due to an error.*** ");
        }
    }

    private static void appendToFile(JsonObject json, BufferedWriter bufferedWriter, int drawId, ProgressBar bar) {
        JsonArray winningNumbers = null;
        try {
            winningNumbers = json.getAsJsonObject("data").getAsJsonArray("winningNumbers");
        } catch (NullPointerException e) {
            LOGGER.error("JSON FILE is NULL!");
            throw new JsonNullException("JSON FILE is NULL!");
        } catch (ClassCastException e) {
            LOGGER.error("JSON data is NULL!");
            throw new JsonDataNullException("JSON data is NULL!");
        }

        try {
            bufferedWriter.write(winningNumbers.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();  // Flush the data to the file

            bar.increment();
            System.out.println("Data for the draw " + drawId + " successfully added to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

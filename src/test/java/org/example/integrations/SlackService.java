package org.example.integrations;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SlackService {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String SlackUrl = "https://hooks.slack.com/services/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";

    public void postNotification(String message) {
        try {
            var json = "{ \"text\": \"" + message + "\" }";
            var request = HttpRequest.newBuilder()
                    .uri(new URI(SlackUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

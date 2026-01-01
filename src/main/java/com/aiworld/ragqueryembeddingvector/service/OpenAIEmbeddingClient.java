package com.aiworld.ragqueryembeddingvector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIEmbeddingClient {

    private final WebClient webClient;

    public OpenAIEmbeddingClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Double> generateSingleEmbedding(String text) {

        String safeText = text.length() > 1000
                ? text.substring(0, 1000)
                : text;

        Map<String, Object> requestBody = Map.of(
                "model", "text-embedding-3-small",
                "input", safeText
        );

        Map response = webClient.post()
                .uri("/embeddings")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> data =
                (List<Map<String, Object>>) response.get("data");

        return (List<Double>) data.get(0).get("embedding");
    }
}

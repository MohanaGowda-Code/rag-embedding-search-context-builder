package com.aiworld.ragqueryembeddingvector.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MockEmbeddingClient {

    private static final int EMBEDDING_DIMENSION = 30;

    public List<Double> generateEmbedding(String text) {
        List<Double> embedding = new ArrayList<>(EMBEDDING_DIMENSION);

        // deterministic fake embedding (stable for same input)
        int seed = text.hashCode();

        Random random = new Random(seed);

        for (int i = 0; i < EMBEDDING_DIMENSION; i++) {
            embedding.add(random.nextDouble());
        }

        return embedding;
    }
}

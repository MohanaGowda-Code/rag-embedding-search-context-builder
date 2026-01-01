package com.aiworld.ragqueryembeddingvector.service;

import com.aiworld.ragqueryembeddingvector.model.DocumentChunk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class VectorStoreService {

    private final List<DocumentChunk> vectorStore = new ArrayList<>();

    public void save(List<DocumentChunk> chunks){
        vectorStore.addAll(chunks);
    }

    public List<DocumentChunk> search(List<Double> queryEmbedding, int topK){
        return vectorStore.stream()
                .sorted(Comparator.comparingDouble(
                        chunk -> -cosineSimilarity(
                                queryEmbedding,
                                chunk.getEmbedding()
                        )
                ))
                .limit(topK)
                .toList();
    }

    private double cosineSimilarity(List<Double> v1, List<Double> v2) {

        double dot = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < v1.size(); i++) {
            dot += v1.get(i) * v2.get(i);
            norm1 += Math.pow(v1.get(i), 2);
            norm2 += Math.pow(v2.get(i), 2);
        }

        double denominator = Math.sqrt(norm1) * Math.sqrt(norm2);
        return denominator == 0 ? 0.0 : dot / denominator;
    }

}

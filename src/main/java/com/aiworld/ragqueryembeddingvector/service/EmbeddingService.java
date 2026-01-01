package com.aiworld.ragqueryembeddingvector.service;


import com.aiworld.ragqueryembeddingvector.model.DocumentChunk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmbeddingService {

    private final OpenAIEmbeddingClient client;
    private final VectorStoreService vectorStore;
    private final MockEmbeddingClient mockEmbeddingClient;

    public EmbeddingService(OpenAIEmbeddingClient client,
                            VectorStoreService vectorStore, MockEmbeddingClient mockEmbeddingClient, ChatService chatService) {
        this.client = client;
        this.vectorStore = vectorStore;
        this.mockEmbeddingClient = mockEmbeddingClient;
    }

    public List<DocumentChunk> generateSingleEmbedding(
            List<DocumentChunk> chunks) {

        List<DocumentChunk> enriched = new ArrayList<>();
        for (DocumentChunk chunk : chunks) {
            List<Double> embedding =
                    mockEmbeddingClient.generateEmbedding(chunk.getContent());

            enriched.add(new DocumentChunk(
                    chunk.getIndex(),
                    chunk.getContent(),
                    embedding
            ));
        }

        vectorStore.save(enriched);
        return enriched;
    }

    public List<DocumentChunk> semanticSearch(String query, int topK) {
        List<Double> queryEmbedding =
                mockEmbeddingClient.generateEmbedding(query);
        return vectorStore.search(queryEmbedding, topK);
    }

}

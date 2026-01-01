package com.aiworld.ragqueryembeddingvector.service;


import com.aiworld.ragqueryembeddingvector.model.DocumentChunk;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

//    private final EmbeddingService embeddingService;
//    public ChatService(EmbeddingService embeddingService) {
//        this.embeddingService = embeddingService;
//    }

    public String chat(String question) {

        // 1️⃣ Retrieve relevant chunks
        List<DocumentChunk> chunks =null;
                //embeddingService.semanticSearch(question, 3);

        // 2️⃣ Build context (THIS IS WHERE)
        String context = buildContext(chunks);

        // 3️⃣ (Next step) Send context + question to LLM
        return "Context:\n" + context + "\n\nQuestion:\n" + question;
    }

    public String buildContext(List<DocumentChunk> chunks) {
        return chunks.stream()
                .map(DocumentChunk::getContent)
                .collect(Collectors.joining("\n\n"));
    }
}

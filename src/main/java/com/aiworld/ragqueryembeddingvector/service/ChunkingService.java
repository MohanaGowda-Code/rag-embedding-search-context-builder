package com.aiworld.ragqueryembeddingvector.service;


import com.aiworld.ragqueryembeddingvector.model.DocumentChunk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingService {

    private static final int MAX_CHUNK_SIZE = 500;   // characters
    private static final int OVERLAP_SIZE = 200;      // characters

    public List<DocumentChunk> chunkText(String text) {

        List<DocumentChunk> chunks = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return chunks;
        }

        //text = preprocessText(text);

        String[] sentences = text.split("(?<=[.!?])\\s+");

        StringBuilder currentChunk = new StringBuilder();
        int chunkIndex = 0;

        for (String sentence : sentences) {

            if (currentChunk.length() + sentence.length() > MAX_CHUNK_SIZE) {
                chunks.add(new DocumentChunk(chunkIndex++, currentChunk.toString()));

                // overlap
                String overlap = currentChunk.substring(
                        Math.max(0, currentChunk.length() - OVERLAP_SIZE)
                ).trim();

                currentChunk = new StringBuilder(overlap);
            }

            currentChunk.append(sentence).append(" ");
        }

        if (!currentChunk.isEmpty()) {
            chunks.add(new DocumentChunk(chunkIndex, currentChunk.toString().trim()));
        }

        return chunks;
    }

//    private String preprocessText(String text) {
//        if (text == null) return "";
//
//        // Remove sender/receiver labels
//        text = text.replaceAll("Message from [^:]+:", "");
//        text = text.replaceAll("From: [^\\n]+", "");
//
//        // Replace multiple spaces, newlines, tabs with single space
//        text = text.replaceAll("\\s+", " ").trim();
//
//        return text;
//    }


}

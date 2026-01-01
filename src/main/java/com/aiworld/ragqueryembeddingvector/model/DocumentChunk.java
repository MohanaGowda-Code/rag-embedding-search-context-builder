package com.aiworld.ragqueryembeddingvector.model;

import java.util.List;

public class DocumentChunk {

    private int index;
    private String content;
    private List<Double> embedding;

    public DocumentChunk(int index, String content) {
        this.index = index;
        this.content = content;
    }

    public DocumentChunk(int index, String content, List<Double> embedding) {
        this.index = index;
        this.content = content;
        this.embedding = embedding;
    }

    public int getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public List<Double> getEmbedding() {
        return embedding;
    }
}

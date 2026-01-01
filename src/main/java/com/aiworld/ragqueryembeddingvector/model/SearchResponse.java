package com.aiworld.ragqueryembeddingvector.model;

public class SearchResponse {

    private int index;
    private String content;

    public SearchResponse(int index, String content) {
        this.index = index;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }
}

package com.aiworld.ragqueryembeddingvector.model;

public class ChatRequest {

    private String question;

    // Default constructor (needed for @RequestBody deserialization)
    public ChatRequest() {
    }

    // Constructor with field
    public ChatRequest(String question) {
        this.question = question;
    }

    // Getter
    public String getQuestion() {
        return question;
    }

    // Setter
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "ChatRequest{" +
                "question='" + question + '\'' +
                '}';
    }
}

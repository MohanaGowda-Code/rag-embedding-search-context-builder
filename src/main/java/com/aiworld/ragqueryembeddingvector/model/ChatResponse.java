package com.aiworld.ragqueryembeddingvector.model;

public class ChatResponse {

    private String context;
    private String question;

    public ChatResponse() {
    }

    public ChatResponse(String context, String question) {
        this.context = context;
        this.question = question;
    }

    // Getters & setters
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}

# RAG Document Chat (Java + Spring Boot)
A Retrieval-Augmented Generation (RAG) based document chat application built using Java, Spring Boot, and vector embeddings.
This project focuses on document ingestion, semantic search, and context building as a strong foundation before integrating LLMs.

# Completed: Retrieval Pipeline
# The following capabilities are fully implemented and working:

📤 PDF Upload
📄 Text Extraction
✂️ Intelligent Chunking
🧠 Embedding Generation
🔍 Semantic Search (Top-K)
Context Building for LLM

# Architecture Overview
PDF Upload
    ↓
Text Extraction
    ↓
Chunking
    ↓
Embeddings
    ↓
Vector / Semantic Search (Top-K)
    ↓
Context Builder
    ↓
(Chat API Response)

# Tech Stack
Java 17
Spring Boot 3.x
Apache Tika (PDF text extraction)
Embedding Model (for vector generation)
Semantic Search
Maven

# API Endpoints
1️⃣ Upload PDF
POST /upload


# Description:
Uploads a PDF document
Extracts text
Splits content into chunks
Generates embeddings
Stores chunks for semantic search

# Chat with Documents
POST /chat

# Request Body:
{
  "question": "health not good"
}

# Response:
{
  "context": "Message from ",
  "question": "health not good"
}

## 👨‍💻 Author
**Mohana**  
Senior Java & Microservices Engineer  
Exploring AI, RAG, and LLM integration with Spring Boot


package com.aiworld.ragqueryembeddingvector.controller;

import com.aiworld.ragqueryembeddingvector.model.*;
import com.aiworld.ragqueryembeddingvector.service.ChatService;
import com.aiworld.ragqueryembeddingvector.service.ChunkingService;
import com.aiworld.ragqueryembeddingvector.service.DocumentService;
import com.aiworld.ragqueryembeddingvector.service.EmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    @Autowired
    EmbeddingService embeddingService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ChunkingService chunkingService;

    @Autowired
    ChatService chatService;

    @PostMapping("/upload")
    public List<DocumentChunk> upload(@RequestParam MultipartFile file) {
        String text = documentService.extractText(file);       // STEP-2
        List<DocumentChunk> chunks = chunkingService.chunkText(text); // STEP-3
        return embeddingService.generateSingleEmbedding(chunks);   // STEP-4
    }

    @PostMapping("/search")
    public List<SearchResponse> search(@RequestBody SearchRequest request) {
        return embeddingService.semanticSearch(
                request.getQuery(),
                request.getTopK()
        ).stream()
                .map(c -> new SearchResponse(c.getIndex(), c.getContent()))
                .toList();
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String question = request.getQuestion();
        List<DocumentChunk> chunks = embeddingService.semanticSearch(question, 3);
        String context = chatService.buildContext(chunks);
        return new  ChatResponse(context, question);
    }
}

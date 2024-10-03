package com.example.springaicasandravectordemo.controller;

import com.example.springaicasandravectordemo.service.AICassandraVectorService;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AIController {

    @Autowired
    AICassandraVectorService aiCassandraVectorService;

    @GetMapping("/load")
    public void loadDocuments() {
        aiCassandraVectorService.getDocuments();
    }

    @GetMapping("/search")
    public List<Document> searchDocuments(@RequestParam(value = "query", defaultValue = "The world is big") String query) {
        return aiCassandraVectorService.searchDocuments(query);
    }
}

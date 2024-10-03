package com.example.springaicasandravectordemo.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AICassandraVectorService {

    @Autowired
    VectorStore vectorStore;

    public void getDocuments() {
        List<Document> documents = List.of(
                new Document("Spring AI Rocks! Spring AI Rocks!", Map.of("country", "UK", "year", 2019)),
                new Document("The world is big and salvation around the corner!", Map.of("country", "BG", "year", 2018)),
                new Document("You walk forward facing to the past and you turn back towards the future.", Map.of("country", "NL", "year", 2023)),
                new Document("Technology shapes the future but leaves our past behind.", Map.of("category", "Technology")),
                new Document("The evolution of artificial intelligence is transforming industries.", Map.of("category", "Technology", "year", 2021)),
                new Document("Books are uniquely portable magic.", Map.of("author", "Stephen", "genre", "Literature"))
        );
        TextSplitter textSplitter = new TokenTextSplitter();
        documents.forEach(document -> {
            List<Document> splitDocuments = textSplitter.split(document);
            vectorStore.add(splitDocuments);
        });
    }

    public List<Document> searchDocuments(String query) {
        return vectorStore.similaritySearch(SearchRequest.query(query).withTopK(2));
    }
}

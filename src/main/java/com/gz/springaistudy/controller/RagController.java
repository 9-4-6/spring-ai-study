package com.gz.springaistudy.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rag")
public class RagController {
    private final VectorStore vectorStore;

    private final ChatClient deepSeekChatClient;

    public RagController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient,
                         VectorStore vectorStore) {
        this.deepSeekChatClient = deepSeekChatClient;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/query")
    public ChatResponse query() {
        List <Document> documents = List.of(
                new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
                new Document("The World is Big and Salvation Lurks Around the Corner"),
                new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2")));

        // Add the documents
        vectorStore.add(documents);


        // 请求大模型，加入提示词
        ChatResponse chatResponse = deepSeekChatClient.prompt()
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .user("what is the world")
                .call()
                .chatResponse();
        return chatResponse;
    }


}

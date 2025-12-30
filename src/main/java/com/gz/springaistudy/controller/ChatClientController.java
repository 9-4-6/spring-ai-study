package com.gz.springaistudy.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-client")
public class ChatClientController {

    /*private final ChatClient chatClient;

    public ChatClientController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }*/
    private final ChatClient deepSeekChatClient;

    public ChatClientController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }

    @GetMapping("/ai")
    String generation( @RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return this.deepSeekChatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}

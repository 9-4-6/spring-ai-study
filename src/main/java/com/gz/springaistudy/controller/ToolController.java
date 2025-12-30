package com.gz.springaistudy.controller;

import com.gz.springaistudy.tool.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tool")
public class ToolController {
    /*private final ChatClient chatClient;

    public ToolController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }*/

    private final ChatClient deepSeekChatClient;

    public ToolController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }

    @GetMapping("/ai")
    String generation() {
        return  deepSeekChatClient
                .prompt("What day is tomorrow?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}

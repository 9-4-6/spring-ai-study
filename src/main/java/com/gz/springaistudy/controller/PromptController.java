package com.gz.springaistudy.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prompt")
public class PromptController {

   /* private final ChatClient chatClient;

    public PromptController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }*/
    private final ChatClient deepSeekChatClient;

    public PromptController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }
    @GetMapping("/ai")
    String generation() {
        //用户信息
        String userText = """
             我叫什么
         """;
        Message userMessage = new UserMessage(userText);
        //系统信息
        String systemText = """
              我是一名数学老师，我的名字是{name}
          """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "郭忠"));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return deepSeekChatClient.prompt(prompt).call().content();
    }
}

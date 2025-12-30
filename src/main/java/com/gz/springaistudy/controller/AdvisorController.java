package com.gz.springaistudy.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志拦截器
 * 大家可以自定义拦截器，这里给介绍内置日志拦截器如何使用
 */

@RestController
@RequestMapping("/advisor-chat")
public class AdvisorController {

   // private final ChatClient chatClient;

  /*  public AdvisorController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }*/
  private final ChatClient deepSeekChatClient;

    public AdvisorController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }

    @GetMapping("/ai")
    String generation( @RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return this.deepSeekChatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user(message)
                .call()
                .content();
    }
}

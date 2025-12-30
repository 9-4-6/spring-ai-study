package com.gz.springaistudy.config;

import org.springframework.ai.chat.client.ChatClient;


import org.springframework.ai.deepseek.DeepSeekChatModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ChatClientConfig {
    /**
     * deepseek 客户端 如果存在多个大模型，就需要手动配置对应的客户端
     * @return
     */
    @Bean
    public ChatClient deepSeekChatClient(DeepSeekChatModel chatModel) {
        return ChatClient.create(chatModel);
    }


}

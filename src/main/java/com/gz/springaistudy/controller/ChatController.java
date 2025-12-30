package com.gz.springaistudy.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class ChatController {

    private final DeepSeekChatModel chatModel;

    public ChatController(DeepSeekChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", chatModel.call(message));
    }

    @GetMapping(value = "/generateStream", produces = "text/event-stream")
    public Flux<String> generateStream(
            @RequestParam(value = "message", defaultValue = "讲个笑话") String message) {

        Prompt prompt = new Prompt(new UserMessage(message));
        return chatModel.stream(prompt)
                .map(chatResponse -> {
                    // 获取第一个生成结果的内容
                    return chatResponse.getResult().getOutput().getText();
                })
                // 错误处理
                .onErrorResume(error -> Flux.just("AI 服务出错了: " + error.getMessage()));
    }
}

package com.gz.springaistudy.controller;

import com.gz.springaistudy.pojo.ActorsFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/structured")
public class StructuredOutputController {

   /* private final ChatClient chatClient;

    public StructuredOutputController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
*/
   private final ChatClient deepSeekChatClient;

    public StructuredOutputController(@Qualifier("deepSeekChatClient") ChatClient deepSeekChatClient) {
        this.deepSeekChatClient = deepSeekChatClient;
    }
    @GetMapping("/ai")
    List<ActorsFilms> generation() {
        List<ActorsFilms> actorsFilms = deepSeekChatClient.prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorsFilms>>() {});

        actorsFilms.forEach(actor -> {
            System.out.println("Actor: " + actor.actor());
            System.out.println("Movies: " + actor.movies());
        });

        return actorsFilms;
    }
}

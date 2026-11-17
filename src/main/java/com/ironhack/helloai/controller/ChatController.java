package com.ironhack.helloai.controller;


import com.ironhack.helloai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask/{question}")
    public String ask(@PathVariable String question){
        return chatService.ask(question);
    }

    @PostMapping("/chatbot/{conversationId}/{message}")
    public String chatbot(@PathVariable String conversationId, @PathVariable String message){
        return chatService.chatbot(conversationId, message);
    }
}

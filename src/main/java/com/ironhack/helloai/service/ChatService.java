package com.ironhack.helloai.service;

import com.ironhack.helloai.tools.DateTimeTools;
import com.ironhack.helloai.tools.EmailTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatClient chatClientWithMemory;
    private final ChatMemory chatMemory;
    private final DateTimeTools dateTimeTools;
    private final EmailTool emailTool;

    public ChatService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, DateTimeTools dateTimeTools, EmailTool emailTool) {
        this.chatClient = chatClientBuilder.build();
        this.chatMemory = chatMemory;
        this.dateTimeTools = dateTimeTools;
        this.emailTool = emailTool;
        this.chatClientWithMemory = chatClientBuilder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()) // we are configuring the memory as advisor
                .build(); // we are building the client
    }

    public String ask(String question) {
        return chatClient.prompt("answer the following question: " + question).call().content();
    }

    public String chatbot(String conversationId, String message) {
        return chatClientWithMemory
                .prompt(message)
                .system("you are an assistant that only answer in a professional way. Always.")
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(dateTimeTools, emailTool)
                .call()
                .content();
    }
}

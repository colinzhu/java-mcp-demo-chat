package mcp.chat;

import org.springframework.ai.chat.client.ChatClient;

public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient.prompt(userMessage).call().content();
    }
} 
package mcp.chat;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ChatService chatService(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {
        var chatClient = chatClientBuilder
                .defaultSystem("You are a professional assistant to reply user's questions.")
                .defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClients))
                .defaultAdvisors(new MessageChatMemoryAdvisor(MessageWindowChatMemory.builder().build()))
                .build();
        return new ChatService(chatClient);
    }
}
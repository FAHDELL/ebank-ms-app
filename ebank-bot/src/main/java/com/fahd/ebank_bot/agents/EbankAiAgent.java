package com.fahd.ebank_bot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EbankAiAgent {

    private ChatClient chatClient;

    public EbankAiAgent(ChatClient.Builder chatClient ,
                        ChatMemory chatMemory,
                        ToolCallbackProvider tools) {

        this.chatClient = chatClient
                .defaultSystem("""
                        Vous un assistant qui se charge de répondre aux question
                        de l'utilisateur a propos des clients et des comptes bancaires, en fonction du contexte fourni.
                        Si aucun contexte n'st pas fourni, répondre JE NE SAIS PAS
                        """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String chat (Prompt prompt){
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    public Flux<String> chatStream (Prompt prompt){
        return chatClient.prompt(prompt)
                .stream()
                .content();
    }
}

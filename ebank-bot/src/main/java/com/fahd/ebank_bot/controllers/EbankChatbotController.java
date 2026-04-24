package com.fahd.ebank_bot.controllers;

import com.fahd.ebank_bot.agents.EbankAiAgent;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.print.attribute.standard.Media;

@RestController
public class EbankChatbotController {

    private EbankAiAgent ebankAiAgent;

    public EbankChatbotController(EbankAiAgent ebankAiAgent) {
        this.ebankAiAgent = ebankAiAgent;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat (@RequestParam(name = "query" , defaultValue = "Bonjour") String query){
        return ebankAiAgent.chat(new Prompt(query));
    }

    @GetMapping(value = "/chatStream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chatStream (@RequestParam(name = "query" , defaultValue = "Bonjour") String query){
        return ebankAiAgent.chatStream(new Prompt(query));
    }
}

package com.fahd.ebank_bot.discord;

import com.fahd.ebank_bot.agents.EbankAiAgent;
import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.ai.chat.prompt.Prompt;


@DiscordController
public class DiscordBot {
    private EbankAiAgent agent;
    public DiscordBot(EbankAiAgent agent) {
        this.agent = agent;
    }

    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if (event.getAuthor().isBot()) return;
        String query = event.getMessage().getContentRaw();
        String response = agent.chat(new Prompt(query));
        event.getChannel().sendMessage(response).queue();
    }

}

package me.ry00001.jbot.core;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import lombok.Getter;

import me.ry00001.jbot.Jbot;

public class CommandContext {
    @Getter
    public MessageChannel channel;
    @Getter
    public Message message;
    @Getter
    public Member member;
    @Getter
    public User author;
    @Getter
    public Jbot bot;
    @Getter
    public MessageReceivedEvent event;

    public CommandContext(MessageReceivedEvent evt, Jbot bot) {
        this.channel = evt.getChannel();
        this.member = evt.getMember();
        this.author = evt.getAuthor();
        this.message = evt.getMessage();
        this.bot = bot;
        this.event = evt;
    }

    public void send(String content) {
        this.channel.sendMessage(content).queue();
    }
}
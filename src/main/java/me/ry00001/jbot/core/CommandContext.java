package me.ry00001.jbot.core;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.JDA;

import me.ry00001.jbot.Jbot;

import java.util.function.Consumer;

public class CommandContext {
    public MessageChannel channel;
    public Message message;
    public Member member;
    public User author;
    public Jbot bot;
    public MessageReceivedEvent event;
    public JDA jda;

    public CommandContext(MessageReceivedEvent evt, Jbot bot) {
        this.channel = evt.getChannel();
        this.member = evt.getMember();
        this.author = evt.getAuthor();
        this.message = evt.getMessage();
        this.jda = evt.getJDA();
        this.bot = bot;
        this.event = evt;
    }

    public void send(String content, Consumer<Message> callback, Consumer<Throwable> errcb) {
        this.channel.sendMessage(content).queue(callback, errcb);
    }

    public void send(String content, Consumer<Message> callback) {
        this.channel.sendMessage(content).queue(callback);
    }

    public void send(String content) {
        this.channel.sendMessage(content).queue();
    }
}
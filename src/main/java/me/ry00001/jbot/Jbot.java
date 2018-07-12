package me.ry00001.jbot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.ReadyEvent;
import org.reflections.Reflections;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.utils.*;
// o h h n o

public class Jbot extends ListenerAdapter {
    public HashMap<String, Command> commands;
    public Config config;

    public final Logger logger = LoggerFactory.getLogger(Jbot.class);

    public Jbot() {
        this.commands = new HashMap<String, Command>(); // ONE NEW BOI
        File cfg = new File("config.json");
        ConfigReader reader = new ConfigReader(cfg);
        try {
            this.config = reader.read();
        } catch (IOException e) {
            System.out.println("ERROR while reading config: "+e);
            System.exit(1);
        }
        Reflections reflections = new Reflections("me.ry00001.jbot.commands");
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);
        for (Class<? extends Command> i : commandClasses) {
            try {
                Command cls = i.getDeclaredConstructor().newInstance();
                this.commands.put(cls.name, cls);
            } catch (Exception e) {
                System.out.println("Error while loading commands: "+e);
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void start() {
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                          .setToken(this.config.token)
                          .addEventListener(this)
                          .buildBlocking();
        } catch (LoginException|InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Jbot().start();
    }

    @Override
    public void onReady(ReadyEvent event) {
        logger.info("Logged in and ready to receive commands.");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        long responseNumber = event.getResponseNumber();

        //Event specific information
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();


        String msg = message.getContentDisplay();

        boolean bot = author.isBot();
        if (bot) {
            return;
        }

        if (event.isFromType(ChannelType.TEXT))
        {

            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();

            String name;
            if (message.isWebhookMessage())
            {
                name = author.getName();
            }
            else
            {
                name = member.getEffectiveName();
            }                                    

            String prefix = this.config.prefix;
            if (!msg.startsWith(prefix)) return;
            String sliced = msg.substring(prefix.length());
            String[] split = sliced.split(" ");
            String command = split[0];
            ArrayList<String> args = new ArrayList<String>(Arrays.asList(split));
            args.remove(0);
            Command acmd = this.commands.get(command); // EXCEPTION-SAFE:tm: coding
            if (acmd == null) return;
            CommandContext ctx = new CommandContext(event, this); // construct context
            boolean isOwner = new OwnerChecker().isOwner(ctx);
            if (!isOwner && acmd.isOwnerOnly()) {
                ctx.send("You do not have permission to use this command.");
                return;
            }
            acmd.run(ctx, args);
        }
    }
}

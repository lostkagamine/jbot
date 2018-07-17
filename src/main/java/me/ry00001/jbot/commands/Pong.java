package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;

import java.time.temporal.ChronoUnit;

public class Pong extends Command {
    public Pong(Jbot jbot) {
        this.name = "pong";
        this.description = "it pongs";
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        ctx.send("...", m -> {
            long latency = ctx.event.getMessage().getCreationTime().until(m.getCreationTime(), ChronoUnit.MILLIS);
            m.editMessage("Ping! " + latency + "ms.\nGateway latency: " + ctx.jda.getPing() + "ms").queue();
        });
    }
}
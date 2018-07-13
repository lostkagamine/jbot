package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;

public class Pong extends Command {
    public Pong(Jbot jbot) {
        this.name = "pong";
        this.description = "it pongs";
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        ctx.channel.sendMessage("ping").queue();
    }
}
package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;

public class Restart extends Command {
    public Restart(Jbot jbot) {
        this.name = "restart";
        this.description = "restarts jbot";
        this.ownerOnly = true;
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        ctx.send("Restarting");
        ctx.bot.shutdown();
    }
}
package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;

import java.util.HashMap;

public class Help extends Command {
    public Help(Jbot bot) {
        this.name = "help";
        this.description = "Where you are.";
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        Jbot bot = ctx.bot;
        HashMap<String, Command> cmds = bot.getCommands();
        StringBuilder out = new StringBuilder();
        out.append("```\n");
        for (Command i: cmds.values()) {
            out.append(i.name + ": " + i.description);
        }
        out.append("```");
        ctx.send(out.toString());
    }
}
package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.DatabaseHandler;
import me.ry00001.jbot.Jbot;
import me.ry00001.jbot.core.Command;
import me.ry00001.jbot.core.CommandContext;

import java.util.ArrayList;

public class ModLogChannel extends Command {
    public ModLogChannel(Jbot jbot) {
        this.name = "modlogchannel";
        this.description = "Sets the modlog channel";
    }

    public void run(CommandContext ctx, ArrayList<String> args) {

    }
}
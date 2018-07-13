package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;

import net.dv8tion.jda.core.entities.Game;

public class SetGame extends Command {
    public SetGame(Jbot bot) {
        this.name = "setgame";
        this.description = "Sets the game.";
        this.ownerOnly = true;
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        Game game = Game.playing(String.join(" ", args));
        ctx.bot.jda.getPresence().setGame(game);
    }
}
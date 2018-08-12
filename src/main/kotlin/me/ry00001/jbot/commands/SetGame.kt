package me.ry00001.jbot.commands

import me.ry00001.jbot.core.*
import me.ry00001.jbot.Jbot
import java.util.ArrayList

import net.dv8tion.jda.core.entities.Game

class SetGame(bot: Jbot) : Command("setgame", "Sets the game.") {
    init {
        isOwnerOnly = true
    }

    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        val game = Game.playing(args.joinToString(" "))
        ctx.bot.jda.presence.game = game
    }
}
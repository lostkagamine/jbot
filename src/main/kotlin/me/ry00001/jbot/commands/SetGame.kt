package me.ry00001.jbot.commands

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core.Command
import me.ry00001.jbot.core.CommandContext
import net.dv8tion.jda.core.entities.Game
import java.util.*

class SetGame(bot: Jbot) : Command("setgame", "Sets the game.") {
    init {
        isOwnerOnly = true
    }

    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        val game = Game.playing(args.joinToString(" "))
        ctx.bot.jda.presence.game = game
    }
}
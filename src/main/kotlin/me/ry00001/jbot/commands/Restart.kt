package me.ry00001.jbot.commands

import me.ry00001.jbot.core.*
import me.ry00001.jbot.Jbot
import java.util.ArrayList

class Restart(jbot: Jbot) : Command("restart", "restarts jbot") {
    init {
        isOwnerOnly = true
    }

    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        ctx.send("Restarting")
        ctx.bot.shutdown()
    }
}
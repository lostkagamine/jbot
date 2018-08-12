package me.ry00001.jbot.commands

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core.Command
import me.ry00001.jbot.core.CommandContext
import java.time.temporal.ChronoUnit
import java.util.*

class Pong(jbot: Jbot) : Command("pong", "it pongs") {

    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        ctx.send("...") { m ->
            val latency = ctx.event.message.creationTime.until(m.creationTime, ChronoUnit.MILLIS)
            m.editMessage("Ping! " + latency + "ms.\nGateway latency: " + ctx.jda.ping + "ms").queue()
        }
    }
}
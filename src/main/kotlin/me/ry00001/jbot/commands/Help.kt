package me.ry00001.jbot.commands

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core.Command
import me.ry00001.jbot.core.CommandContext
import java.util.*


class Help(bot: Jbot) : Command("help", "Where you are.") {
    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        val bot = ctx.bot
        val cmds = bot.commands
        val out = StringBuilder()
        out.append("```\n")
        for (i in cmds.values) {
            out.append(i.name + ": " + i.description + "\n")
        }
        out.append("```")
        ctx.send(out.toString())
    }
}
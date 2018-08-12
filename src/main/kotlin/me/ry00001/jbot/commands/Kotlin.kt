package me.ry00001.jbot.commands;

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core.Command
import me.ry00001.jbot.core.CommandContext
import java.util.*

class Kotlin(val jbot: Jbot) : Command("kotlin", "this command is a .kt file") {

    override fun run(ctx: CommandContext, args: ArrayList<String>) {
        ctx.send("awau")
    }
}
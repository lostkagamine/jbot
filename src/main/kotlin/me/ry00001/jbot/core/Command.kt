package me.ry00001.jbot.core

import net.dv8tion.jda.core.Permission
import java.util.*

abstract class Command(val name: String, val description: String? = null) {
    // base command class for all bot commands

    var isOwnerOnly = false
    var permissions = arrayOf<Permission>()

    fun getDesc(): String {
        return this.description ?: "No description provided."
    }

    abstract fun run(ctx: CommandContext, args: ArrayList<String>)
}
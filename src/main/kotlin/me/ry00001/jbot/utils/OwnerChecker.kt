package me.ry00001.jbot.utils

import me.ry00001.jbot.core.CommandContext

object OwnerChecker {

    fun isOwner(ctx: CommandContext): Boolean {
        return ctx.bot.config.owners.contains(ctx.author.idLong)
    }
}
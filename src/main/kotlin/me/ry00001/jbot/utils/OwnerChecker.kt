package me.ry00001.jbot.utils

import me.ry00001.jbot.core.CommandContext
import java.util.stream.LongStream

class OwnerChecker {

    fun isOwner(ctx: CommandContext): Boolean {
        return LongStream.of(*ctx.bot.config.owners!!).anyMatch { x -> x == ctx.author.idLong }
    }
}
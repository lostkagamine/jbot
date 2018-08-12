package me.ry00001.jbot.utils

import java.util.stream.LongStream
import me.ry00001.jbot.core.CommandContext

class OwnerChecker {

    fun isOwner(ctx: CommandContext): Boolean {
        return LongStream.of(*ctx.bot.config.owners!!).anyMatch { x -> x == ctx.author.idLong }
    }
}
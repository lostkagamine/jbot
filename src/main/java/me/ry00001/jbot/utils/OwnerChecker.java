package me.ry00001.jbot.utils;

import java.util.stream.LongStream;
import me.ry00001.jbot.core.CommandContext;

public class OwnerChecker {
    public OwnerChecker() {}

    public boolean isOwner(CommandContext ctx) {
        return LongStream.of(ctx.bot.config.owners).anyMatch(x -> x == ctx.author.getIdLong());
    }
}
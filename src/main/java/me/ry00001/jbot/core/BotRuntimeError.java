package me.ry00001.jbot.core;

public class BotRuntimeError extends RuntimeException {
    public BotRuntimeError(String message) {
        super(message);
    }
}
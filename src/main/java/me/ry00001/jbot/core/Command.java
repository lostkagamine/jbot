package me.ry00001.jbot.core;

import java.util.ArrayList;

public abstract class Command {
    // base command class for all bot commands

    public String name;
    public String description;
    public boolean ownerOnly = false;

    public String getDescription() {
        return this.description != null ? this.description : "No description provided.";
    }

    public String getName() {
        return this.name;
    }

    public boolean isOwnerOnly() {
        return this.ownerOnly;
    }

    public abstract void run(CommandContext ctx, ArrayList<String> args);
}
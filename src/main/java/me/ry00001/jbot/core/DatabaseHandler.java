package me.ry00001.jbot.core;

// beep beep lettuce
import io.lettuce.core.api.sync.*;
import io.lettuce.core.*;
import io.lettuce.core.api.*;
import me.ry00001.jbot.Jbot;
import me.ry00001.jbot.core.Config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DatabaseHandler {
    public RedisCommands<String, String> redis;
    private StatefulRedisConnection<String, String> conn;
    private RedisClient client;

    private final Logger logger = LoggerFactory.getLogger(DatabaseHandler.class);

    public DatabaseHandler(Jbot bot) {
        logger.info("Connecting to Redis.");
        Config cfg = bot.getConfig();
        this.client = RedisClient.create(cfg.redis); // build dat redis
        this.conn = this.client.connect();
        this.redis = this.conn.sync();
        logger.info("Successful connection.");
    }

    public StatefulRedisConnection<String, String> getConnection() {
        return this.conn;
    }

    public RedisClient getClient() {
        return this.client;
    }

    public RedisCommands<String, String> getCommands() {
        return this.redis;
    }
}
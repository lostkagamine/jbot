package me.ry00001.jbot.core

// beep beep lettuce
import io.lettuce.core.api.sync.*
import io.lettuce.core.*
import io.lettuce.core.api.*
import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core.Config

import org.slf4j.LoggerFactory
import org.slf4j.Logger

class DatabaseHandler(bot: Jbot) {
    var commands: RedisCommands<String, String>
    val connection: StatefulRedisConnection<String, String>
    val client: RedisClient

    private val logger = LoggerFactory.getLogger(DatabaseHandler::class.java)

    init {
        logger.info("Connecting to Redis.")
        val cfg = bot.config
        this.client = RedisClient.create(cfg.redis) // build dat redis
        this.connection = this.client.connect()
        this.commands = this.connection.sync()
        logger.info("Successful connection.")
    }
}
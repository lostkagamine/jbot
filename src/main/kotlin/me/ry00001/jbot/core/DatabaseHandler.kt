package me.ry00001.jbot.core

// beep beep lettuce

import io.lettuce.core.RedisClient
import me.ry00001.jbot.Jbot
import org.slf4j.LoggerFactory

class DatabaseHandler(bot: Jbot) {
    private val logger = LoggerFactory.getLogger(DatabaseHandler::class.java)

    val client = RedisClient.create(bot.config.redis)
    val connection = client.connect()
    val commands = connection.sync()
}
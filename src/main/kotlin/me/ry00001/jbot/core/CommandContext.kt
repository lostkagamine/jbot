package me.ry00001.jbot.core

import me.ry00001.jbot.Jbot
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class CommandContext(val event: MessageReceivedEvent, val bot: Jbot) {
    val channel = event.channel
    val message = event.message
    val member = event.member
    val author = event.author
    val jda = event.jda

    fun send(content: String, callback: (Message) -> Unit) {
        this.channel.sendMessage(content).queue(callback)
    }

    fun send(content: String) {
        this.channel.sendMessage(content).queue()
    }
}
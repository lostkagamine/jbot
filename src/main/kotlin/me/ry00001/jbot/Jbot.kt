package me.ry00001.jbot

import me.ry00001.jbot.core.*
import me.ry00001.jbot.utils.OwnerChecker
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.entities.ChannelType
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import org.reflections.Reflections
import org.slf4j.LoggerFactory
import java.io.File
import java.io.IOException
import java.util.*
import javax.security.auth.login.LoginException

// o h h n o

class Jbot : ListenerAdapter() {
    val commands = hashMapOf<String, Command>()
    lateinit var config: Config

    lateinit var jda: JDA

    val logger = LoggerFactory.getLogger(Jbot::class.java)

    val db = DatabaseHandler(this)

    init {
        val cfg = File("config.json")
        val reader = ConfigReader(cfg)
        try {
            config = reader.read()
        } catch (e: IOException) {
            println("ERROR while reading config: $e")
            System.exit(1)
        }

        val reflections = Reflections("me.ry00001.jbot.commands")
        val commandClasses = reflections.getSubTypesOf(Command::class.java)
        for (i in commandClasses) {
            try {
                val cls = i.getDeclaredConstructor(Jbot::class.java).newInstance(this)
                commands[cls.name] = cls
            } catch (e: Exception) {
                logger.error("Error while loading commands: $e")
                e.printStackTrace()
                System.exit(1)
            }

        }
    }

    fun start() {
        try {
            jda = JDABuilder(AccountType.BOT)
                    .setAudioEnabled(true)
                    .setToken(config.token)
                    .addEventListener(this)
                    .buildBlocking()
        } catch (e: LoginException) {
            e.printStackTrace()
            System.exit(1)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            System.exit(1)
        }

    }

    fun shutdown() {
        // Shuts down.
        logger.info("Shutting down.")
        db.connection.close()
        db.client.shutdown()
        jda.shutdown()
        System.exit(0)
    }

    override fun onReady(event: ReadyEvent?) {
        logger.info("Logged in and ready to receive commands.")
    }

    override fun onMessageReceived(event: MessageReceivedEvent?) {
        val jda = event!!.jda
        val responseNumber = event.responseNumber

        //Event specific information
        val author = event.author
        val message = event.message
        val channel = event.channel


        val msg = message.contentDisplay

        val bot = author.isBot
        if (bot) {
            return
        }

        if (event.isFromType(ChannelType.TEXT)) {

            val guild = event.guild
            val textChannel = event.textChannel
            val member = event.member

            val name = if (message.isWebhookMessage) author.name else member.effectiveName

            val prefix = this.config.prefix
            if (!msg.startsWith(prefix!!)) return
            val sliced = msg.substring(prefix.length)
            val split = sliced.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val command = split[0]
            val args = ArrayList(Arrays.asList(*split))
            args.removeAt(0)
            val acmd = this.commands[command] ?: return // EXCEPTION-SAFE:tm: coding
            val ctx = CommandContext(event, this) // construct context
            val isOwner = OwnerChecker().isOwner(ctx)
            if (!isOwner && acmd.isOwnerOnly) {
                ctx.send("You do not have permission to use this command.")
                return
            }
            acmd.run(ctx, args)
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Jbot().start()
        }
    }
}

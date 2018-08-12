package me.ry00001.jbot.commands

import java.util.ArrayList

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core._

class Scala(jbot: Jbot) extends Command("scala", "this command was written in Scala, and not in Java or Kotlin") {

  def run(ctx: CommandContext, args: ArrayList[String]) {
    ctx.send("Hello from Scala")
  }
}
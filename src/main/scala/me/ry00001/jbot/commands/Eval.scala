package me.ry00001.jbot.commands

import java.util.ArrayList
import java.util.function.Consumer

import me.ry00001.jbot.Jbot
import me.ry00001.jbot.core._
import net.dv8tion.jda.core.entities.Message

import scala.collection.JavaConverters._
import scala.reflect.runtime.currentMirror
import scala.tools.reflect.{ToolBox, ToolBoxError}

class Eval(jbot: Jbot) extends Command("eval", "Compiles and runs arbitrary Scala code.") {
  ownerOnly = true
  val toolbox = currentMirror.mkToolBox()

  def run(ctx: CommandContext, args: ArrayList[String]) {
    var set = args.asScala.toSet
    var code = set.mkString(" ")
    ctx.send("<a:icworking:440090198500573184> Compiling...", new Consumer[Message]() {
      override def accept(m: Message) {
        try {
          var evalres = toolbox.eval(toolbox.parse(code)).asInstanceOf[String]
          m.editMessage(s"<:iccheck:435574370107129867> Success.\n```\n$evalres```").queue()
        } catch {
          case e: ToolBoxError =>
            m.editMessage(s"<:icerror:435574504522121216> Scala execution failed!\n```\n$e```").queue()
        }
      }
    })
  }
}
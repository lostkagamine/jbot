package me.ry00001.jbot.utils

/*
 code shamelessly stolen from mantaro
 kode i'm sorry
*/

import java.io.OutputStream
import java.io.PrintStream

class CompactPrintStream(out: OutputStream) : PrintStream(out) {

    override fun println(s: String) {
        val stackTrace = Thread.currentThread().stackTrace
        var current = stackTrace[2].toString()
        var i = 3
        while ((current.startsWith("sun.") || current.startsWith("java.")) && i < stackTrace.size)
            current = stackTrace[i++].toString()
        super.println("[$current]: $s")
    }
}
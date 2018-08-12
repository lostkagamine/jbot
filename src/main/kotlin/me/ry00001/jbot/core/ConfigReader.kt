package me.ry00001.jbot.core

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException

class ConfigReader(val file: File) {
    private val mapper = ObjectMapper()

    @Throws(IOException::class)
    fun read(): Config {
        return mapper.readValue(file, Config::class.java)
    }
}
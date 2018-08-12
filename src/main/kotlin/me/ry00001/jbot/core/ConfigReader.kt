package me.ry00001.jbot.core

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException

class ConfigReader(file: File) {
    var file: File? = null
    private val mapper: ObjectMapper

    init {
        this.file = file
        this.mapper = ObjectMapper()
    }

    @Throws(IOException::class)
    fun read(): Config {
        return this.mapper.readValue(this.file, Config::class.java)
    }
}
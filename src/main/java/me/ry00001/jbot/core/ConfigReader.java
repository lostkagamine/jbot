package me.ry00001.jbot.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ConfigReader {
    private File file;
    private ObjectMapper mapper;

    public ConfigReader(File file) {
        this.setFile(file);
        this.mapper = new ObjectMapper();
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public Config read() throws IOException {
        return this.mapper.readValue(this.file, Config.class);
    }
}
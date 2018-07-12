package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Eval extends Command {
    private ScriptEngine engine;
    private boolean babelEnabled = false;
    private final Logger logger = LoggerFactory.getLogger(Eval.class);

    public Eval() {
        this.name = "eval";
        this.description = "runs JS code";
        this.ownerOnly = true;

        engine = new ScriptEngineManager().getEngineByName("nashorn");
        if (getClass().getResource("/babel.min.js") != null) {
            logger.info("Now loading Babel, this may take a while...");
            try (Reader r = new InputStreamReader(getClass().getResourceAsStream("/babel.min.js"), "UTF-8")) {
                engine.put("logger", logger);
                engine.eval(r);
                babelEnabled = true;
                logger.info("Loaded Babel.");
            } catch (Exception e) {
                logger.error("Error loading Babel: " + e);
            }
        } else {
            logger.warn("Babel not loaded.");
        }
    }

    public void run(CommandContext ctx, ArrayList<String> args) {
        String[] script = args.toArray(new String[args.size()]);
        String joined = String.join(" ", script);
        engine.put("ctx", ctx);
        engine.put("jda", ctx.event.getJDA());
        engine.put("args", args);
        engine.put("bot", ctx.bot);
        engine.put("class", this);
        try {
            Object res;
            if (babelEnabled) { // NOTE- GABIWARE          
                engine.put("input", joined);
                String s = engine.eval("Babel.transform(input, { presets: ['es2015'] }).code").toString();
                res = engine.eval(s);
            } else {
                res = engine.eval(joined);
            }
            ctx.send("```\n"+res+"```");
        } catch (ScriptException e) {
            ctx.send("Oops, something happened:\n```" + e + "```");
        }
    }
}
package me.ry00001.jbot.commands;

import me.ry00001.jbot.core.*;
import me.ry00001.jbot.Jbot;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.io.Reader;

import net.dv8tion.jda.core.entities.Game;

import javax.script.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Eval extends Command {
    private ScriptEngine engine;
    private boolean babelEnabled = false;
    private final Logger logger = LoggerFactory.getLogger(Eval.class);

    public Eval(Jbot bot) {
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
        Object game = Game.class;
        engine.put("entities", game);
        engine.put("class", this);
        ctx.send("<a:icworking:440090198500573184> Compiling...", m -> {
            try {
                Object res;
                if (babelEnabled) { // NOTE- GABIWARE          
                    engine.put("input", joined);
                    String s = engine.eval("Babel.transform(input, { presets: ['es2015'] }).code").toString();
                    res = engine.eval(s);
                } else {
                    res = engine.eval(joined);
                }
                m.editMessage("<:iccheck:435574370107129867> Done!\n```"+res+"```").queue();
            } catch (ScriptException e) {
                m.editMessage("<:icerror:435574504522121216> An error has occurred:\n```" + e + "```").queue();
            }
        });
    }
}
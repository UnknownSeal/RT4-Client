package com.unknownseal.mixinlauncher;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MixinLogger {

    public static final Logger LOGGER = Logger.getLogger("MixinSystem");

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";

    static {
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String sourceClass = record.getSourceClassName();
                String prefix = "[UnknownMixin]";
                if (sourceClass != null) {
                    int lastDot = sourceClass.lastIndexOf('.');
                    String className = lastDot >= 0 ? sourceClass.substring(lastDot + 1) : sourceClass;
                    prefix = "[" + className + "]";
                }

                String color;
                Level level = record.getLevel();
                if (level == Level.SEVERE) {
                    color = RED;
                } else if (level == Level.WARNING) {
                    color = YELLOW;
                } else if (level == Level.INFO) {
                    color = GREEN;
                } else {
                    color = CYAN;
                }

                return color + prefix + " " + level + ": " + record.getMessage() + RESET + "\n";
            }
        });
        LOGGER.addHandler(handler);
    }
}

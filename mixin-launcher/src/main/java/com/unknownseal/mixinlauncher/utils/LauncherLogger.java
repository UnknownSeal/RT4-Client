package com.unknownseal.mixinlauncher.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LauncherLogger
{
    public static final Logger LOGGER = Logger.getLogger("Launcher");

    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN   = "\u001B[36m";
    private static final String GREEN  = "\u001B[32m";

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    static
    {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);

        handler.setFormatter(new Formatter()
        {
            @Override
            public String format(LogRecord record)
            {
                String time = TIME_FORMAT.format(new Date(record.getMillis()));
                String thread = Thread.currentThread().getName();

                String className = record.getSourceClassName();

                String prefix = "[" + className + "]";

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

                String levelName = String.format("%-7s", level.getName());

                return String.format(
                        "%s%s %s [%s] %s: %s%s%n",
                        color,
                        time,
                        thread,
                        levelName,
                        prefix,
                        record.getMessage(),
                        RESET
                );
            }
        });

        LOGGER.addHandler(handler);
    }

    public static void log(Level level, String msg)
    {
        String caller = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .filter(f -> !f.getClassName().equals(LauncherLogger.class.getName()))
                        .findFirst()
                        .map(StackWalker.StackFrame::getClassName)
                        .orElse("Unknown"));

        int lastDot = caller.lastIndexOf('.');
        String simpleName = lastDot >= 0 ? caller.substring(lastDot + 1) : caller;

        LOGGER.logp(level, simpleName, null, msg);
    }

    public static void info(String msg) {
        log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        log(Level.WARNING, msg);
    }

    public static void error(String msg) {
        log(Level.SEVERE, msg);
    }

    public static void debug(String msg) {
        log(Level.FINE, msg);
    }

    public static void trace(String msg) {
        log(Level.FINEST, msg);
    }
}

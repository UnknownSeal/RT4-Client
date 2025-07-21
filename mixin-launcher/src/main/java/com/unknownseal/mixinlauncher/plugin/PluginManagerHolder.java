package com.unknownseal.mixinlauncher.plugin;

public class PluginManagerHolder {
    private static PluginManager instance;

    public static void set(PluginManager mgr) {
        instance = mgr;
    }

    public static PluginManager get() {
        return instance;
    }
}
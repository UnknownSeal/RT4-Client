package com.unknownseal.mixinlauncher.plugin;

import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.api.ClientContextImpl;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PluginManager {

    private final List<Plugin> plugins = new ArrayList<>();
    private final EventBus eventBus;
    private final ClientContext clientContext = new ClientContextImpl();

    public PluginManager(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void loadPlugins(String basePackage) {
        try (ScanResult scan = new ClassGraph()
                .enableClassInfo()
                .acceptPackages(basePackage)
                .scan()) {
            scan.getClassesImplementing(Plugin.class.getName())
                    .loadClasses(Plugin.class)
                    .forEach(cls -> {
                        try {
                            Plugin plugin = cls.getDeclaredConstructor().newInstance();
                            register(plugin);
                        } catch (Exception e) {
                            LauncherLogger.error("Failed to instantiate plugin: " + cls.getName());
                        }
                    });
        }
    }

    public void register(Plugin plugin) {
        plugins.add(plugin);
        plugin.onStart(eventBus, clientContext);
        LauncherLogger.info("Plugin loaded: " + plugin.getName());
    }

    public void shutdown() {
        for (Plugin p : plugins) {
            p.onStop();
        }
    }

    public List<Plugin> getPlugins() {
        return Collections.unmodifiableList(plugins);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}

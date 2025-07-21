package com.unknownseal.mixinlauncher.plugin;

import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.eventbus.EventBus;

public interface Plugin {

    String getName();

    default void onStart(EventBus bus, ClientContext ctx) {}

    default void onStop() {}

}

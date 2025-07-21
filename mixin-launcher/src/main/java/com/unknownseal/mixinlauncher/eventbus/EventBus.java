package com.unknownseal.mixinlauncher.eventbus;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private final Map<Class<?>, List<Subscriber>> subscribers = new HashMap<>();

    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length != 1) continue;

                Class<?> eventType = params[0];
                method.setAccessible(true);
                subscribers
                        .computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                        .add(new Subscriber(listener, method));
            }
        }
    }

    public void unregister(Object listener) {
        for (List<Subscriber> list : subscribers.values()) {
            list.removeIf(s -> s.listener == listener);
        }
    }

    public void post(Object event) {
        List<Subscriber> subs = subscribers.get(event.getClass());
        if (subs != null) {
            for (Subscriber s : subs) {
                try {
                    s.method.invoke(s.listener, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Subscriber {
        final Object listener;
        final Method method;

        Subscriber(Object listener, Method method) {
            this.listener = listener;
            this.method = method;
        }
    }
}

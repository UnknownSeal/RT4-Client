package com.unknownseal.mixinlauncher.mixin;

import java.lang.reflect.Method;

public class HookInfo {
    private final Method method;
    private final String mixinClassName;

    public HookInfo(Method method, String mixinClassName) {
        this.method = method;
        this.mixinClassName = mixinClassName;
    }

    public Method getMethod() {
        return method;
    }

    public String getMixinClassName() {
        return mixinClassName;
    }
}

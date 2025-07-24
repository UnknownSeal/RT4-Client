package com.unknownseal.mixinlauncher.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Inject {
    String method();
    Class<?>[] args() default {};
    At at();
    Slice slice() default @Slice(from = @At("HEAD"), to = @At("TAIL"));
}
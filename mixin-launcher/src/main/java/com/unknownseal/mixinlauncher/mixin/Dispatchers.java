package com.unknownseal.mixinlauncher.mixin;

import com.unknownseal.mixinlauncher.annotations.This;
import com.unknownseal.mixinlauncher.mixin.HookInfo;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;

import net.bytebuddy.asm.Advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dispatchers {

    public static class HeadDispatcher {
        @Advice.OnMethodEnter
        public static void onEnter(@Advice.Origin Method origin,
                                   @Advice.AllArguments Object[] args,
                                   @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "HEAD", self, MixinEngine.methodRegistry);
        }
    }

    public static class TailDispatcher {
        @Advice.OnMethodExit
        public static void onExit(@Advice.Origin Method origin,
                                  @Advice.AllArguments Object[] args,
                                  @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "TAIL", self, MixinEngine.methodRegistry);
        }
    }

    public static class InvokeDispatcher {
        @Advice.OnMethodEnter
        public static void onInvoke(@Advice.Origin Method origin,
                                    @Advice.AllArguments Object[] args,
                                    @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "INVOKE", self, MixinEngine.methodRegistry);
        }
    }

    public static class ModifyArgDispatcher {
        @Advice.OnMethodEnter
        public static void modifyArgs(@Advice.Origin Method origin,
                                      @Advice.AllArguments(readOnly = false) Object[] args,
                                      @Advice.Local("replacement") Object replacement) throws Exception {
            String key = origin.getDeclaringClass().getName() + "." + origin.getName() + "@MODIFY_ARG";
            List<HookInfo> hooks = MixinEngine.methodRegistry.get(key);
            if (hooks != null) {
                for (HookInfo hookInfo : hooks) {
                    Method hook = hookInfo.getMethod();
                    com.unknownseal.mixinlauncher.annotations.ModifyArg annotation = hook.getAnnotation(com.unknownseal.mixinlauncher.annotations.ModifyArg.class);
                    int index = annotation.index();
                    Object original = args[index];
                    replacement = hook.invoke(null, original);
                    args[index] = replacement;
                }
            }
        }
    }

    public static void dispatch(Method origin, Object[] args, String at, Object self, Map<String, List<HookInfo>> methodRegistry) throws Exception {
        String key = origin.getDeclaringClass().getName() + "." + origin.getName() + "@" + at;
        List<HookInfo> hooks = methodRegistry.get(key);
        if (hooks != null) {
            for (HookInfo hookInfo : hooks) {
                Method hook = hookInfo.getMethod();
                String mixinName = hookInfo.getMixinClassName();

                Annotation[][] paramAnnotations = hook.getParameterAnnotations();
                Class<?>[] paramTypes = hook.getParameterTypes();
                List<Object> invokeArgs = new ArrayList<>();
                int argIndex = 0;

                for (int i = 0; i < paramTypes.length; i++) {
                    boolean isThis = false;
                    for (Annotation annotation : paramAnnotations[i]) {
                        if (annotation.annotationType() == This.class) {
                            isThis = true;
                            break;
                        }
                    }
                    if (isThis) {
                        invokeArgs.add(self);
                    } else {
                        if (argIndex < args.length) {
                            invokeArgs.add(args[argIndex++]);
                        } else {
                            invokeArgs.add(null);
                        }
                    }
                }

                try {
                    hook.invoke(null, invokeArgs.toArray());
                } catch (Exception e) {
                    Throwable cause = (e instanceof InvocationTargetException) ? ((InvocationTargetException) e).getCause() : e;

                    String errorDetail = cause.getClass().getSimpleName();
                    if (cause.getMessage() != null) {
                        errorDetail += ": " + cause.getMessage();
                    }

                    LauncherLogger.error("Error invoking hook in mixin " + mixinName + " for " +
                            origin.getDeclaringClass().getSimpleName() + "." + origin.getName() + "@" + at +
                            " → " + errorDetail);
                }
            }
        }
    }
}

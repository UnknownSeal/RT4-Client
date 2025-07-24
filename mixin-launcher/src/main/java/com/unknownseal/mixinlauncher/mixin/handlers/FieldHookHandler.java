package com.unknownseal.mixinlauncher.mixin.handlers;

import com.unknownseal.mixinlauncher.annotations.FieldHook;
import com.unknownseal.mixinlauncher.mixin.MixinEngine;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.*;
import net.bytebuddy.pool.TypePool;
import com.unknownseal.mixinlauncher.mixin.HookInfo;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FieldHookHandler {

    public static void handle(            Class<?> mixinClass,
                                          Class<?> targetClass,
                                          Method method,
                                          List<Runnable> mixins,
                                          Map<String, List<HookInfo>> methodRegistry,
                                          List<String> earlyFailures) {
        FieldHook fieldHook = method.getAnnotation(FieldHook.class);
        String fieldName = fieldHook.value();
        boolean before = fieldHook.before();

        mixins.add(() -> {
            try {
                ByteBuddy byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED);

                DynamicType.Builder<?> builder = byteBuddy.redefine(targetClass);

                builder = builder.visit(new AsmVisitorWrapper() {
                    @Override
                    public int mergeWriter(int flags) {
                        return flags | ClassWriter.COMPUTE_MAXS;
                    }

                    @Override
                    public int mergeReader(int flags) {
                        return flags | ClassReader.EXPAND_FRAMES;
                    }

                    @Override
                    public ClassVisitor wrap(TypeDescription instrumentedType,
                                             ClassVisitor classVisitor,
                                             Implementation.Context implementationContext,
                                             TypePool typePool,
                                             FieldList<FieldDescription.InDefinedShape> fields,
                                             MethodList<?> methods,
                                             int writerFlags,
                                             int readerFlags) {

                        return new ClassVisitor(Opcodes.ASM9, classVisitor) {
                            @Override
                            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                                return new MethodVisitor(Opcodes.ASM9, mv) {
                                    @Override
                                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                                        if ((opcode == Opcodes.PUTFIELD || opcode == Opcodes.PUTSTATIC) && name.equals(fieldName)) {
                                            try {
                                                if (before) {
                                                    if (opcode == Opcodes.PUTFIELD) {
                                                        mv.visitInsn(Opcodes.DUP2);
                                                        invokeHookMethod(mv, mixinClass, method);
                                                    } else {
                                                        mv.visitInsn(Opcodes.DUP);
                                                        invokeHookMethodStatic(mv, mixinClass, method);
                                                    }
                                                }
                                                super.visitFieldInsn(opcode, owner, name, desc);
                                            } catch (Throwable t) {
                                                LauncherLogger.error("[FieldHook ASM] Exception while injecting hook method for field '" + fieldName + "': " + t);
                                                super.visitFieldInsn(opcode, owner, name, desc);
                                            }
                                        } else {
                                            super.visitFieldInsn(opcode, owner, name, desc);
                                        }
                                    }

                                    private void invokeHookMethod(MethodVisitor mv, Class<?> mixinCls, Method hookMethod) {
                                        Class<?>[] params = hookMethod.getParameterTypes();
                                        if (params.length == 2) {
                                            mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                                                    Type.getInternalName(mixinCls),
                                                    hookMethod.getName(),
                                                    Type.getMethodDescriptor(hookMethod),
                                                    false);
                                        } else {
                                            LauncherLogger.warn("FieldHook method must have 2 params (owner, value) for instance fields");
                                        }
                                    }

                                    private void invokeHookMethodStatic(MethodVisitor mv, Class<?> mixinCls, Method hookMethod) {
                                        Class<?>[] params = hookMethod.getParameterTypes();
                                        if (params.length == 1) {
                                            mv.visitMethodInsn(Opcodes.INVOKESTATIC,
                                                    Type.getInternalName(mixinCls),
                                                    hookMethod.getName(),
                                                    Type.getMethodDescriptor(hookMethod),
                                                    false);
                                        } else {
                                            LauncherLogger.warn("FieldHook method must have 1 param (value) for static fields");
                                        }
                                    }
                                };
                            }
                        };
                    }
                });

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
            } catch (Exception e) {
                LauncherLogger.error("FieldHook injection failed for: " + targetClass.getName() + " → " + e);
            }
        });
    }

}

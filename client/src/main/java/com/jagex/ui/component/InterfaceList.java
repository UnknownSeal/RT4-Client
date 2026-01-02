package com.jagex.ui.component;

import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class InterfaceList {
    @OriginalMember(owner = "runetek4.client!th", name = "j", descriptor = "[[Lclient!be;")
    public static Component[][] interfaces;

    @OriginalMember(owner = "runetek4.client!tm", name = "b", descriptor = "(II)Z")
    public static boolean load(@OriginalArg(0) int interfaceId) {
        if (InterfaceManager.loadedComponents[interfaceId]) {
            return true;
        } else if (InterfaceManager.gameInterfaceJs5.isGroupReady(interfaceId)) {
            @Pc(25) int componentCount = InterfaceManager.gameInterfaceJs5.getGroupCapacity(interfaceId);
            if (componentCount == 0) {
                InterfaceManager.loadedComponents[interfaceId] = true;
                return true;
            }
            if (interfaces[interfaceId] == null) {
                interfaces[interfaceId] = new Component[componentCount];
            }
            for (@Pc(46) int componentIndex = 0; componentIndex < componentCount; componentIndex++) {
                if (interfaces[interfaceId][componentIndex] == null) {
                    @Pc(62) byte[] componentData = InterfaceManager.gameInterfaceJs5.getfile(interfaceId, componentIndex);
                    if (componentData != null) {
                        @Pc(74) Component component = interfaces[interfaceId][componentIndex] = new Component();
                        component.slot = componentIndex + (interfaceId << 16);
                        if (componentData[0] == -1) {
                            component.decodeIf3(new Packet(componentData));
                        } else {
                            component.decodeIf1(new Packet(componentData));
                        }
                    }
                }
            }
            InterfaceManager.loadedComponents[interfaceId] = true;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "(BI)Lclient!be;")
    public static Component list(@OriginalArg(1) int packedId) {
        @Pc(7) int interfaceId = packedId >> 16;
        @Pc(18) int componentId = packedId & 0xFFFF;
        if (interfaces[interfaceId] == null || interfaces[interfaceId][componentId] == null) {
            @Pc(33) boolean success = load(interfaceId);
            if (!success) {
                return null;
            }
            // todo: this should not be necessary, data/server-related?
            if (interfaces.length <= interfaceId || interfaces[interfaceId].length <= componentId) {
                return null;
            }
        }
        return interfaces[interfaceId][componentId];
    }

    @OriginalMember(owner = "runetek4.client!qf", name = "a", descriptor = "(BII)Lclient!be;")
    public static Component getComponent(@OriginalArg(1) int parentId, @OriginalArg(2) int childIndex) {
        @Pc(7) Component parentComponent = list(parentId);
        if (childIndex == -1) {
            return parentComponent;
        } else if (parentComponent == null || parentComponent.staticComponents == null || parentComponent.staticComponents.length <= childIndex) {
            return null;
        } else {
            return parentComponent.staticComponents[childIndex];
        }
    }
}

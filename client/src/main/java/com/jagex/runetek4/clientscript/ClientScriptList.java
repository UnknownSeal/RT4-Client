package com.jagex.runetek4.clientscript;

import com.jagex.runetek4.data.cache.cs.ClientScript;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.LruHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ClientScriptList {
    @OriginalMember(owner = "runetek4.client!ib", name = "d", descriptor = "Lclient!gn;")
    public static final LruHashTable scripts = new LruHashTable(128);

    @OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(IB)Lclient!qc;")
    public static ClientScript get(@OriginalArg(0) int scriptId) {
        @Pc(12) ClientScript clientScript = (ClientScript) scripts.get((long) scriptId);
        if (clientScript != null) {
            return clientScript;
        }
        @Pc(22) byte[] bytes = Client.js5Archive12.getfile(scriptId, 0);
        if (bytes == null) {
            return null;
        }
        clientScript = new ClientScript();
        @Pc(42) Packet buffer = new Packet(bytes);
        buffer.offset = buffer.data.length - 2;
        @Pc(53) int trailerLen = buffer.g2();
        @Pc(63) int trailerPos = buffer.data.length - trailerLen - 12 - 2;
        buffer.offset = trailerPos;
        @Pc(70) int opcodeCount = buffer.g4();
        clientScript.localIntCount = buffer.g2();
        clientScript.localStringCount = buffer.g2();
        clientScript.intArgs = buffer.g2();
        clientScript.stringArgs = buffer.g2();
        @Pc(98) int switches = buffer.g1();
        @Pc(107) int i;
        @Pc(114) int opcode;
        if (switches > 0) {
            clientScript.switchTables = new HashTable[switches];
            for (i = 0; i < switches; i++) {
                opcode = buffer.g2();
                @Pc(121) HashTable table = new HashTable(IntUtils.bitceil(opcode));
                clientScript.switchTables[i] = table;
                while (opcode-- > 0) {
                    @Pc(136) int key = buffer.g4();
                    @Pc(140) int value = buffer.g4();
                    table.put(new IntWrapper(value), (long) key);
                }
            }
        }
        buffer.offset = 0;
        clientScript.name = buffer.gjstrFast();
        clientScript.opcodes = new int[opcodeCount];
        clientScript.stringOperands = new JString[opcodeCount];
        i = 0;
        clientScript.intOperands = new int[opcodeCount];
        while (trailerPos > buffer.offset) {
            opcode = buffer.g2();
            if (opcode == 3) {
                clientScript.stringOperands[i] = buffer.gjstr();
            } else if (opcode >= 100 || opcode == 21 || opcode == 38 || opcode == 39) {
                clientScript.intOperands[i] = buffer.g1();
            } else {
                clientScript.intOperands[i] = buffer.g4();
            }
            clientScript.opcodes[i++] = opcode;
        }
        scripts.put(clientScript, (long) scriptId);
        return clientScript;
    }
}

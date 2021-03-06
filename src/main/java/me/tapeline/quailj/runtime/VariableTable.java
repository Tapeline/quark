package me.tapeline.quailj.runtime;

import me.tapeline.quailj.debugging.AssignTraceRecord;
import me.tapeline.quailj.parser.nodes.VariableNode;
import me.tapeline.quailj.types.QType;
import me.tapeline.quailj.types.RuntimeStriker;
import me.tapeline.quailj.types.modifiers.FinalModifier;
import me.tapeline.quailj.types.modifiers.VariableModifier;

import java.util.*;
import java.util.function.BiConsumer;

public class VariableTable {

    public static boolean recordMemoryActions = false;
    public static List<AssignTraceRecord> assigns = new ArrayList<>();
    public Map<String, QType> data = new HashMap<>();
    public String scope = "undefined";
    public Map<String, List<VariableModifier>> mods = new HashMap<>();

    public VariableTable() {}

    public VariableTable(String sc) {
        scope = sc;
    }

    public QType put(Runtime r, String a, QType d) throws RuntimeStriker {
        if (mods.get(a) == null || mods.get(a) != null && VariableNode.match(mods.get(a), r, d)) {
            if (mods.get(a) != null) {
                for (VariableModifier vm : mods.get(a))
                    if (vm instanceof FinalModifier) {
                        if (((FinalModifier) vm).flag)
                            throw new RuntimeStriker("assign:attempt to assign data to final variable (" +
                                    mods.toString() + " = " + d.getClass().getSimpleName() + ")");
                        else
                            ((FinalModifier) vm).flag = true;
                    }
            }
            if (recordMemoryActions) assigns.add(new AssignTraceRecord(a, data.get(a), d, scope));
            data.put(a, d);
        } else throw new RuntimeStriker("assign:attempt to assign data with wrong type to clarified variable (" +
                mods.toString() + " = " + d.getClass().getSimpleName() + ")");
        return d;
    }

    public QType put(String a, QType d, List<VariableModifier> m) {
        if (recordMemoryActions) assigns.add(new AssignTraceRecord((m == null? "null" : m).toString() +
                " " + a, data.get(a), d, scope));
        data.put(a, d);
        mods.put(a, m == null? new ArrayList<>() : m);
        return d;
    }

    public QType put(String a, QType d) {
        List<VariableModifier> m = new ArrayList<>();
        if (recordMemoryActions) assigns.add(new AssignTraceRecord(m.toString() +
                " " + a, data.get(a), d, scope));
        data.put(a, d);
        mods.put(a, m);
        return d;
    }

    public boolean containsKey(String a) {
        return data.containsKey(a);
    }

    public void remove(String a) {
        data.remove(a);
        mods.remove(a);
    }

    public QType get(String a) {
        return data.get(a);
    }

    public void putAll(VariableTable v) {
        v.data.forEach((k, d) -> {
            data.put(k, d);
            mods.put(k, v.mods.get(k));
        });
    }

    public void forEach(BiConsumer<String, QType> c) {
        data.forEach(c);
    }
    public Set<String> keySet() {
        return data.keySet();
    }

    public int size() {
        return data.size();
    }

    public Collection<QType> values() {
        return data.values();
    }

}

package me.tapeline.quailj.language.types;

import me.tapeline.quailj.interpretertools.Runtime;

import java.util.List;

public abstract class BuiltinFuncType extends QType {
    public abstract QType run(Runtime runtime, List<QType> args);

    @Override
    public String toString() {
        return "native function";
    }

    public abstract List<QType> metaRun(Runtime runtime, List<QType> metaArgs);
}
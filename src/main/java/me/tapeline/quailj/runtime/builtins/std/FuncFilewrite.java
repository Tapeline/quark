package me.tapeline.quailj.runtime.builtins.std;

import me.tapeline.quailj.platformspecific.IOManager;
import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;

import java.util.Collections;
import java.util.List;

public class FuncFilewrite extends FuncType {

    public FuncFilewrite() {
        super("filewrite", Collections.singletonList(""), null);
    }

    @Override
    public QValue run(Runtime runtime, List<QValue> a) throws RuntimeStriker {
        if (a.size() < 2)
            throw new RuntimeStriker("fileexists:invalid args length");
        IOManager.fileSet(a.get(0).v.toString(), a.get(1).v.toString());
        return new QValue();
    }

    @Override
    public QType copy() {
        return new FuncFilewrite();
    }
}
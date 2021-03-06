package me.tapeline.quailj.runtime.builtins.std;

import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;
import me.tapeline.quailj.utils.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadFuncStart extends FuncType {

    public ThreadFuncStart() {
        super("start", Collections.singletonList("code"), null);
    }

    @Override
    public QType run(Runtime runtime, List<QType> a) throws RuntimeStriker {
        Assert.require(a.size() == 1, "thread start:invalid args size");
        Assert.require(a.get(0) instanceof JavaType
                && ((JavaType<?>) a.get(0)).value instanceof QThread,
                "thread start:not a thread");
        ((QThread) ((JavaType<?>) a.get(0)).value).start();
        return QType.V();
    }

    @Override
    public QType copy() {
        return new ThreadFuncStart();
    }
}

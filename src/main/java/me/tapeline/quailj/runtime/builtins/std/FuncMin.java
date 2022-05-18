package me.tapeline.quailj.runtime.builtins.std;

import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;

import java.util.Collections;
import java.util.List;

public class FuncMin extends FuncType {

    public FuncMin() {
        super("min", Collections.singletonList(""), null);
    }

    @Override
    public QType run(Runtime runtime, List<QType> a) throws RuntimeStriker {
        if (a.size() < 2 || !QType.isNum(a.get(0), a.get(1)))
            throw new RuntimeStriker("func min:specify a numeric value!");
        return QType.V(Math.min(((NumType) a.get(0)).value, ((NumType) a.get(1)).value));
    }

    @Override
    public QType copy() {
        return new FuncMin();
    }
}

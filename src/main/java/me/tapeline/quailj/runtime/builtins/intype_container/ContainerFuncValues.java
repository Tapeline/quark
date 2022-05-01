package me.tapeline.quailj.runtime.builtins.intype_container;

import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;
import me.tapeline.quailj.utils.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainerFuncValues extends FuncType {

    public ContainerFuncValues() {
        super("values", Arrays.asList("c"), null);
    }

    @Override
    public QValue run(Runtime runtime, List<QValue> a) throws RuntimeStriker {
        Assert.size(a, 1, "container values:invalid args size");
        Assert.require(a.get(0).v instanceof ContainerType, "container values:invalid arg0 type");
        return new QValue(new ListType(new ArrayList<>(a.get(0).v.table.values())));
    }

    @Override
    public QType copy() {
        return new ContainerFuncValues();
    }
}
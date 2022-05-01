package me.tapeline.quailj.runtime.builtins.intype_string;

import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;
import me.tapeline.quailj.types.QType;
import me.tapeline.quailj.types.StringType;
import me.tapeline.quailj.utils.Assert;

import java.util.Arrays;
import java.util.List;

public class StringFuncLower extends FuncType {

    public StringFuncLower() {
        super("lower", Arrays.asList("str"), null);
    }

    @Override
    public QValue run(Runtime runtime, List<QValue> a) throws RuntimeStriker {
        Assert.size(a, 1, "string lower:invalid args size");
        Assert.require(a.get(0).v instanceof StringType, "string lower:invalid arg0 type");
        return new QValue(((StringType) a.get(0).v).value.toLowerCase());
    }

    @Override
    public QType copy() {
        return new StringFuncLower();
    }
}
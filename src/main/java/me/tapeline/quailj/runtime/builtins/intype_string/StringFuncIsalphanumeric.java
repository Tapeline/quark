package me.tapeline.quailj.runtime.builtins.intype_string;

import me.tapeline.quailj.runtime.Runtime;
import me.tapeline.quailj.types.*;
import me.tapeline.quailj.utils.Assert;

import java.util.Arrays;
import java.util.List;

public class StringFuncIsalphanumeric extends FuncType {

    public StringFuncIsalphanumeric() {
        super("isalphanumeric", Arrays.asList("str"), null);
    }

    @Override
    public QType run(Runtime runtime, List<QType> a) throws RuntimeStriker {
        Assert.size(a, 1, "string isalphanumeric:invalid args size");
        Assert.require(a.get(0) instanceof StringType, "string isalphanumeric:invalid arg0 type");
        String sv = ((StringType) a.get(0)).value;
        for (char c : sv.toCharArray())
            if (!Character.isDigit(c) && !Character.isLetter(c) && c != '.') return QType.V(false);
        return QType.V(true);
    }

    @Override
    public QType copy() {
        return new StringFuncIsalphanumeric();
    }
}

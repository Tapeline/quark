package me.tapeline.quailj.utils;

import me.tapeline.quailj.types.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Utilities {
    public static HashMap<String, String> opToString = new HashMap<>();

    public static void init() {
        opToString.put("+", "_add");
        opToString.put("-", "_sub");
        opToString.put("//", "_divint");
        opToString.put("/", "_div");
        opToString.put("*", "_mul");
        opToString.put("^", "_pow");
        opToString.put("%", "_mod");
        opToString.put("==", "_cmpeq");
        opToString.put("!=", "_cmpuneq");
        opToString.put("<=", "_cmplet");
        opToString.put(">=", "_cmpget");
        opToString.put(">", "_cmpgt");
        opToString.put("<", "_cmplt");
        opToString.put("get", "_get");
        opToString.put("tostring", "_tostring_");
        opToString.put("tonumber", "_tonumber");
        opToString.put("tobool", "_tobool");
        opToString.put("not", "_not");
        opToString.put("!", "_not");
        opToString.put("index", "_index");
        opToString.put("setindex", "_setindex");
        opToString.put("call", "_call");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }

    public static int[] getLine(int pos, String code) {
        List<String> split = Arrays.asList(code.split("\n"));
        int sum = 0;
        for (int i = 0; i < split.size(); i++) {
            sum += split.get(i).length();
            if (sum <= pos && sum + split.get(i+1).length() > pos)
                return new int[] {i, pos - sum + 1};
        }
        return new int[] {1, pos};
    }

    public static BoolType compare(QValue a, QValue b) {
        if (QValue.isBool(a, b))
            return new BoolType( ((BoolType) a.v).value == ((BoolType) b.v).value);
        else if (QValue.isNum(a, b))
            return new BoolType( ((NumType) a.v).value == ((NumType) b.v).value);
        else if (QValue.isStr(a, b))
            return new BoolType( ((StringType) a.v).value.equals(((StringType) b.v).value));
        else if (QValue.isList(a, b))
            return ListUtils.compare(((ListType) a.v).values, ((ListType) b.v).values);
        else if (QValue.isCont(a, b)) {
            if (a.v.table.size() != b.v.table.size()) return new BoolType(false);
            for (String key : a.v.table.keySet())
                if (!compare(a.v.table.get(key), b.v.table.get(key)).value)
                    return new BoolType(false);
            return new BoolType(true);
        }
        return new BoolType(false);
    }

    public static int[] getLine(String text, int pos) {
        String[] lines = text.split("\n");
        int ch = 0;
        for (int i = 0; i < lines.length; i++) {
            ch += lines[i].length();
            if (ch > pos) return new int[] {i + 1, pos - ch};
        }
        return new int[] {lines.length, 0};
    }
}
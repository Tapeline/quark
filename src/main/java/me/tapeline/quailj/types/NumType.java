package me.tapeline.quailj.types;

import me.tapeline.quailj.runtime.VariableTable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class NumType extends QType {

    public static VariableTable tableToClone = new VariableTable("Default NumType");

    public double value = 0D;

    public NumType(double d) {
        value = d;
        this.table = new VariableTable();
        table.putAll(tableToClone);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(table);
        oos.writeDouble(value);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        table = (VariableTable) ois.readObject();
        value = ois.readDouble();
    }

    @Override
    public QType copy() {
        NumType v = new NumType(this.value);
        v.table.putAll(this.table);
        return v;
    }

    @Override
    public String toString() {
        // Round value if possible
        return value % 1 == 0? Long.toString(Math.round(value)) : Double.toString(value);
    }

}

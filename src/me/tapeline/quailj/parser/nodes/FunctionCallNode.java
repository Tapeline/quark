package me.tapeline.quailj.parser.nodes;

public class FunctionCallNode implements Node {

    public int codePos = 0;
    public final Node id;
    public final Node args;

    public FunctionCallNode(Node id, Node args, int pos) {
        this.id = id;
        this.args = args;
        this.codePos = pos;
    }

    @Override
    public String toString() {
        return id.toString() + (args instanceof MultiElementNode? args.toString() : "(" +
                args.toString() + ")");
    }
}

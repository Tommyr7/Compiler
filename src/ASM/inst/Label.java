package ASM.inst;

import java.io.PrintStream;

public class Label extends Inst {
    public String id;

    public Label(String _id) {
        id = _id;
    }

    @Override
    public void print(PrintStream prt) {
        prt.println("." + id + ":");
    }
}

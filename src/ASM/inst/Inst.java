package ASM.inst;

import java.io.PrintStream;

public abstract class Inst {
    public abstract void print(PrintStream prt);

    public String printVreg(int id) {
        return id + "(sp)";
    }
}

package ASM.inst;

import java.io.PrintStream;

public abstract class Inst {
    public abstract void print(PrintStream prt);

    public String printVreg(int id) {
        if (id<2048) return id + "(sp)";
        else
        {
            System.out.println("\tli s1, "+id);
            System.out.println("\tadd s1, s1, sp");
            return "0(s1)";
        }
    }
}

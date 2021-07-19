package ASM.inst;

import ASM.operand.*;

import java.io.PrintStream;

public class Li extends Inst {
    // Load immediate number
    public Operand rd, imm; // x[rd]=imm

    public Li(Operand _rd, Operand _imm) {
        rd = _rd;
        imm = _imm;
    }

    @Override
    public void print(PrintStream prt) {
        if ((rd instanceof Vreg||rd instanceof Symbol)&&rd.color==null) rd.color=new Preg("t3");
        if (imm instanceof Symbol) {
            // const string
            prt.println("\tla "+rd.toString()+", " + ((Symbol) imm).id);
        } else prt.println("\tli "+rd.toString()+", " + ((Immediate) imm).val);
        if (rd instanceof Address) {
            prt.println("\tlw t4, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw "+rd.toString()+", 0(t4)");
        } else if (rd instanceof Symbol) prt.println("\tsw "+rd.toString()+", " + (((Symbol) rd).id) + ", t4");
        else if (rd instanceof Vreg) prt.println("\tsw "+rd.toString()+", " + printVreg(4 * (1 + ((Vreg) rd).id)));
        rd.color=null;
        imm.color=null;
    }
}

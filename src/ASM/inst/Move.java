package ASM.inst;

import ASM.operand.Address;
import ASM.operand.Operand;
import ASM.operand.Symbol;
import ASM.operand.Vreg;

import java.io.PrintStream;

public class Move extends Inst {
    public Operand rs, rd; // x[rd]=x[rs]

    public Move(Operand _rs, Operand _rd) {
        rs = _rs;
        rd = _rd;
    }

    @Override
    public void print(PrintStream prt) {
        if (rs instanceof Vreg) prt.println("\tlw t1, " + printVreg(4 * (1 + ((Vreg) rs).id)));
        if (rs instanceof Symbol) prt.println("\tlw t1, " + ((Symbol) rs).id);
        if (rs instanceof Address) prt.println("\tlw t1, 0(t1)");
        if (rd instanceof Address) {
            prt.println("\tlw t3, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw t1, 0(t3)");
        } else if (rd instanceof Symbol) prt.println("\tsw t1, " + (((Symbol) rd).id) + ", t3");
        else if (rd instanceof Vreg) prt.println("\tsw t1, " + printVreg(4 * (1 + ((Vreg) rd).id)));
    }
}

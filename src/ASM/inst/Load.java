package ASM.inst;

import ASM.operand.Address;
import ASM.operand.Operand;
import ASM.operand.Symbol;
import ASM.operand.Vreg;

import java.io.PrintStream;

public class Load extends Inst {
    public Operand rd, addr; // x[rd]=mem[addr]

    public Load(Operand _rd, Operand _addr) {
        rd = _rd;
        addr = _addr;
    }

    @Override
    public void print(PrintStream prt) {
        if (addr instanceof Vreg) prt.println("\tlw t1, " + printVreg(4 * (1 + ((Vreg) addr).id)));
        if (addr instanceof Symbol) prt.println("\tlw t1, " + ((Symbol) addr).id);
        if (addr instanceof Address) prt.println("\tlw t1, 0(t1)");
        prt.println("\tlw t2, 0(t1)");
        if (rd instanceof Address) {
            prt.println("\tlw t3, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw t2, 0(t3)");
        } else if (rd instanceof Symbol) prt.println("\tsw t2, " + (((Symbol) rd).id) + ", t3");
        else if (rd instanceof Vreg) prt.println("\tsw t2, " + printVreg(4 * (1 + ((Vreg) rd).id)));
    }
}

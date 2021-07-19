package ASM.inst;

import ASM.operand.Address;
import ASM.operand.Operand;
import ASM.operand.Symbol;
import ASM.operand.Vreg;

import java.io.PrintStream;

public class Calculation extends Inst {
    public Operand rd, rs1, rs2; // x[rd] = x[rs1] op x[rs2]
    public String op;

    public Calculation(Operand _rd, Operand _rs1, Operand _rs2, String _op) {
        rd = _rd;
        rs1 = _rs1;
        rs2 = _rs2;
        op = _op;
    }

    @Override
    public void print(PrintStream prt) {
        if (rs1 instanceof Vreg) prt.println("\tlw t1, " + printVreg(4 * (1 + ((Vreg) rs1).id)));
        if (rs1 instanceof Symbol) prt.println("\tlw t1, " + ((Symbol) rs1).id);
        if (rs1 instanceof Address) prt.println("\tlw t1, 0(t1)");
        if (rs2 instanceof Vreg) prt.println("\tlw t2, " + printVreg(4 * (1 + ((Vreg) rs2).id)));
        if (rs2 instanceof Symbol) prt.println("\tlw t2, " + ((Symbol) rs2).id);
        if (rs2 instanceof Address) prt.println("\tlw t2, 0(t2)");
        prt.println("\t" + op + " t3, t1" + (rs2 == null ? "" : ", t2"));
        if (rd instanceof Address) {
            prt.println("\tlw t4, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw t3, 0(t4)");
        } else if (rd instanceof Symbol) prt.println("\tsw t3, " + (((Symbol) rd).id) + ", t4");
        else if (rd instanceof Vreg) prt.println("\tsw t3, " + printVreg(4 * (1 + ((Vreg) rd).id)));
    }
}

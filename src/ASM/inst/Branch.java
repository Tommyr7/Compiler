package ASM.inst;

import ASM.operand.Address;
import ASM.operand.Operand;
import ASM.operand.Symbol;
import ASM.operand.Vreg;

import java.io.PrintStream;

public class Branch extends Inst {
    public Operand rs1, rs2;
    public String destination; // jump to which code block
    public String op;

    public Branch(Operand _rs1, Operand _rs2, String _destination, String _op) {
        rs1 = _rs1;
        rs2 = _rs2;
        destination = _destination;
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
        prt.println("\t" + op + " t1" + (rs2 == null ? "" : ", t2") + ", ." + destination);
    }
}

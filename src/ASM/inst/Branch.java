package ASM.inst;

import ASM.operand.*;

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
        if (rs1 instanceof Vreg && rs1.color==null)
        {
            prt.println("\tlw t3, " + printVreg(4 * (1 + ((Vreg) rs1).id)));
            rs1.color=new Preg("t3");
        }
        if (rs1 instanceof Symbol && rs1.color==null)
        {
            prt.println("\tlw t3, " + ((Symbol) rs1).id);
            rs1.color=new Preg("t3");
        }
        if (rs1 instanceof Address) prt.println("\tlw "+rs1.toString()+", 0("+rs1.toString()+")");
        if (rs2 instanceof Vreg && rs2.color==null)
        {
            prt.println("\tlw t4, " + printVreg(4 * (1 + ((Vreg) rs2).id)));
            rs2.color=new Preg("t4");
        }
        if (rs2 instanceof Symbol && rs2.color==null)
        {
            prt.println("\tlw t4, " + ((Symbol) rs2).id);
            rs2.color=new Preg("t4");
        }
        if (rs2 instanceof Address) prt.println("\tlw "+rs2.toString()+", 0("+rs2.toString()+")");
        prt.println("\t" + op + " "+rs1.toString()+ (rs2 == null ? "" : ", "+rs2.toString()) + ", ." + destination);
        rs1.color=null;
        if (rs2!=null) rs2.color=null;
    }
}

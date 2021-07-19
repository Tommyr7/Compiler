package ASM.inst;

import ASM.operand.*;

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
        if ((rd instanceof Vreg||rd instanceof Symbol)&&rd.color==null) rd.color=new Preg("t5");
        prt.println("\t" + op + " "+rd.toString()+", "+rs1.toString()+ (rs2 == null ? "" : ", "+rs2.toString()));
        if (rd instanceof Address) {
            prt.println("\tlw t6, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw "+rd.toString()+", 0(t6)");
        } else if (rd instanceof Symbol) prt.println("\tsw "+rd.toString()+", " + (((Symbol) rd).id) + ", t6");
        else if (rd instanceof Vreg) prt.println("\tsw "+rd.toString()+", " + printVreg(4 * (1 + ((Vreg) rd).id)));
        rs1.color=null;
        if (rs2!=null) rs2.color=null;
        rd.color=null;
    }
}

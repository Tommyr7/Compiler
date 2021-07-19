package ASM.inst;

import ASM.operand.*;

import java.io.PrintStream;

public class Move extends Inst {
    public Operand rs, rd; // x[rd]=x[rs]

    public Move(Operand _rs, Operand _rd) {
        rs = _rs;
        rd = _rd;
    }

    @Override
    public void print(PrintStream prt) {
        if (rs instanceof Vreg && rs.color==null)
        {
            prt.println("\tlw t3, " + printVreg(4 * (1 + ((Vreg) rs).id)));
            rs.color=new Preg("t3");
        }
        if (rs instanceof Symbol && rs.color==null)
        {
            prt.println("\tlw t3, " + ((Symbol) rs).id);
            rs.color=new Preg("t3");
        }
        if (rs instanceof Address) prt.println("\tlw "+rs.toString()+", 0("+rs.toString()+")");
        if ((rd instanceof Vreg||rd instanceof Symbol)&&rd.color==null) rd.color=new Preg("t4");
        prt.println("\tmv "+rd.toString()+","+rs.toString());
        if (rd instanceof Address) {
            prt.println("\tlw t5, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw "+rs.toString()+", 0(t5)");
        } else if (rd instanceof Symbol) prt.println("\tsw "+rs.toString()+", " + (((Symbol) rd).id) + ", t5");
        else if (rd instanceof Vreg) prt.println("\tsw "+rs.toString()+", " + printVreg(4 * (1 + ((Vreg) rd).id)));
        rs.color=null;
        rd.color=null;
    }
}

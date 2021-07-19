package ASM.inst;

import ASM.operand.*;

import java.io.PrintStream;

public class Load extends Inst {
    public Operand rd, addr; // x[rd]=mem[addr]

    public Load(Operand _rd, Operand _addr) {
        rd = _rd;
        addr = _addr;
    }

    @Override
    public void print(PrintStream prt) {
        if (addr instanceof Vreg && addr.color==null)
        {
            prt.println("\tlw t3, " + printVreg(4 * (1 + ((Vreg) addr).id)));
            addr.color=new Preg("t3");
        }
        if (addr instanceof Symbol && addr.color==null)
        {
            prt.println("\tlw t3, " + addr.toString());
            addr.color=new Preg("t3");
        }
        if (addr instanceof Address) prt.println("\tlw "+addr.toString()+", 0("+addr.toString()+")");
        if ((rd instanceof Vreg||rd instanceof Symbol)&&rd.color==null) rd.color=new Preg("t4");
        prt.println("\tlw "+rd.toString()+", 0("+addr.toString()+")");
        if (rd instanceof Address) {
            prt.println("\tlw t5, " + printVreg(4 * (1 + ((Address) rd).id)));
            prt.println("\tsw "+rd.toString()+", 0(t5)");
        } else if (rd instanceof Symbol) prt.println("\tsw "+rd.toString()+", " + (((Symbol) rd).id) + ", t5");
        else if (rd instanceof Vreg) prt.println("\tsw "+rd.toString()+", " + printVreg(4 * (1 + ((Vreg) rd).id)));
        rd.color=null;
        addr.color=null;
    }
}

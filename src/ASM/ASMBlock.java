package ASM;

import ASM.inst.Inst;

import java.io.PrintStream;
import java.util.ArrayList;

public class ASMBlock {
    public ArrayList<Inst> inst = new ArrayList<>();
    public String id;
    public int cnt = 1; // number of registers

    public ASMBlock(String _id) {
        id = _id;
    }

    public void print(PrintStream prt) {
        prt.println("\t.globl\t" + id);
        prt.println("\t.type\t" + id + ", @function");
        prt.println(id + ":");
        int size = 4 * (cnt + 2);
        if (size<2048) prt.println("\taddi\tsp,sp,-" + String.valueOf(size));
        else
        {
            prt.println("\tli s1, -"+size);
            prt.println("\tadd sp, sp, s1");
        }
        prt.println("\tsw\tra,0(sp)");
        inst.forEach(x -> x.print(prt));
        prt.println(".Return_" + id + ":");
        prt.println("\tlw\tra,0(sp)");
        if (size<2048) prt.println("\taddi\tsp, sp, "+String.valueOf(size));
        else
        {
            prt.println("\tli s1, "+size);
            prt.println("\tadd sp, sp, s1");
        }
        prt.println("\tjr\tra");
        prt.println("\t.size\t" + id + ", .-" + id);
    }
}

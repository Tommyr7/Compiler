package ASM;

import java.io.PrintStream;
import java.util.ArrayList;

public class ASMBlockList {
    public ArrayList<ASMBlock> blocks = new ArrayList<>();
    public ArrayList<String> strings = new ArrayList<>();
    public ArrayList<String> vars = new ArrayList<>();

    public ASMBlockList() {
    }

    public void print(PrintStream prt) {
        prt.println("\t.section\t.rodata");
        for (int i = 0; i < strings.size(); i++) {
            prt.println(".const_string" + i + ":");
            prt.println("\t.string\t" + strings.get(i));
        }
        prt.println("\t.section\t.bss");
        for (int i = 0; i < vars.size(); i++) {
            String str = vars.get(i);
            prt.println("\t.globl\t" + str);
            prt.println("\t.type\t" + str + ", @object");
            prt.println(str + ":");
            prt.println("\t.zero\t4");
            prt.println("\t.size\t" + str + ", 4");
        }
        prt.println("\t.text");
        blocks.forEach(x -> x.print(prt));
    }
}

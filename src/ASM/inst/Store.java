package ASM.inst;

import ASM.operand.Operand;

import java.io.PrintStream;

public class Store extends Inst {
    public Operand rd, addr; // mem[addr]=x[rd]

    public Store(Operand _rd, Operand _addr) {
        rd = _rd;
        addr = _addr;
    }

    @Override
    public void print(PrintStream prt) {
        // There is no store instruction at all!!!
        // do nothing
    }
}

package ASM.inst;

import java.io.PrintStream;

public class FuncCall extends Inst {
    public String func_id;

    public FuncCall(String _func_id) {
        func_id = _func_id;
    }

    @Override
    public void print(PrintStream prt) {
        prt.println("\tcall " + func_id);
    }
}

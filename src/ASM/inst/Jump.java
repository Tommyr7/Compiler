package ASM.inst;

import java.io.PrintStream;

public class Jump extends Inst {
    public String destination;

    public Jump(String _destination) {
        destination = _destination;
    }

    @Override
    public void print(PrintStream prt) {
        prt.println("\tj ." + destination);
    }
}

package ASM.operand;

public class Immediate extends Operand {
    public int val;

    public Immediate(int _val) {
        val = _val;
    }

    @Override
    public String toString() {
        return val+"";
    }
}

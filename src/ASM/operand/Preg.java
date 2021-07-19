package ASM.operand;

public class Preg extends Reg {
    public String id;

    public Preg(String _id) {
        id = _id;
    }

    @Override
    public String toString() {
        return id;
    }
}

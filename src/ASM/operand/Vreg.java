package ASM.operand;

public class Vreg extends Reg {
    public int id;

    public Vreg(int _id) {
        id = _id;
    }

    @Override
    public String toString() {
        return color.id;
    }
}

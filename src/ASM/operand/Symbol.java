package ASM.operand;

public class Symbol extends Operand {
    public String id;

    public Symbol(String _id) {
        id = _id;
    }

    @Override
    public String toString() {
        if (color!=null) return color.id; else return id;
    }
}

import java.util.*;
public class Procedura {
    private Naglowek naglowek;

    private Macchiato program;
    private List<Instrukcja> instrukcje;
    public Procedura(Macchiato program ,String nazwa, List<Character> argumenty) {
        this.program = program;
        this.naglowek = new Naglowek(nazwa, this, argumenty);
    }

    public Naglowek getNaglowek() {
        return naglowek;
    }

    public Macchiato getProgram() {
        return program;
    }
}

import java.util.*;
public class Procedura {
    private Naglowek naglowek;

    private Macchiato program;
    private List<Instrukcja> instrukcje;
    public Procedura(Macchiato program, String nazwa, List<Character> argumenty, List<Instrukcja> instrukcje) {
        this.program = program;
        this.naglowek = new Naglowek(nazwa, this, argumenty);
        this.instrukcje = instrukcje;
        System.out.println("Ile instrukcji v2 " + instrukcje.size());

    }

    public Naglowek getNaglowek() {
        return naglowek;
    }

    public List<Instrukcja> getInstrukcje() {
        return instrukcje;
    }

    public Macchiato getProgram() {
        return program;
    }
}

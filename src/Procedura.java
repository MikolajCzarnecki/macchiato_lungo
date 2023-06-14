import java.util.*;
public class Procedura {
    private Naglowek naglowek;

    private Macchiato program;
    private List<Instrukcja> instrukcje;
    public Procedura(Macchiato program, String nazwa, List<Character> argumenty) {
        this.program = program;
        this.naglowek = new Naglowek(nazwa, this, argumenty);
        this.instrukcje = new LinkedList<Instrukcja>();
    }

    public Naglowek getNaglowek() {
        return naglowek;
    }

    public void dodajInstrukcje(Instrukcja instrukcja) {
        if (instrukcja.getBlokWyzej() == null) {
            instrukcja.setBlokWyzej(null);
            instrukcja.setProgram(this.getProgram());
        }
        this.instrukcje.add(instrukcja);
    }

    public List<Instrukcja> getInstrukcje() {
        return instrukcje;
    }

    public Macchiato getProgram() {
        return program;
    }
}

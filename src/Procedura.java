import java.util.*;
public class Procedura {
    private Naglowek naglowek;
    private List<Instrukcja> instrukcje;
    public Procedura(String nazwa) {
        this.naglowek = new Naglowek(nazwa);
    }

    public Naglowek getNaglowek() {
        return naglowek;
    }
}

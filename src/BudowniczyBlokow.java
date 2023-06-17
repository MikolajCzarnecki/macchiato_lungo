import java.util.*;
public class BudowniczyBlokow {
    private Blok glownyBlok;
    public BudowniczyBlokow() {
        this.glownyBlok = new Blok();
    }

    public Blok zbuduj() {
        return this.glownyBlok;
    }
    public void dodajDeklaracje( char zmienna, Wyrazenie wartosc) {
        this.glownyBlok.dodajDeklaracjeLubProcedure(new Deklaracja(zmienna, wartosc));
    }

    public void dodajWypisanie(Wyrazenie wyrazenie) {
        this.glownyBlok.dodajInstrukcje(new Wypisz(wyrazenie));
    }

    public void dodajDeklaracjeProcedury(String nazwa, List<Character> listaArgum, Blok instrukcje) {
        this.glownyBlok.dodajDeklaracjeLubProcedure(new DeklaracjaProcedury(nazwa, listaArgum, List.of(instrukcje)));
    }

    public void dodajWarunkowa(Wyrazenie wyr1, OpLog oplog, Wyrazenie wyr2,
                               Instrukcja jesliTrue, Instrukcja jesliFalse) {
        this.glownyBlok.dodajInstrukcje(new Warunkowa(wyr1, oplog, wyr2, jesliTrue, jesliFalse));
    }

    public void dodajPrzypisanie(char zmienna, Wyrazenie wyrazenie) {
        this.glownyBlok.dodajInstrukcje(new Przypisanie(zmienna, wyrazenie));
    }

    public void dodajPetle(char zmienna, Wyrazenie wyrazenie, Blok instrukcje) {
        this.glownyBlok.dodajInstrukcje(new Petla(zmienna, wyrazenie, List.of(instrukcje)));
    }

    public void dodajWywolanieProcedury(String nazwa, List<Wyrazenie> argumenty) {
        this.glownyBlok.dodajInstrukcje(new WywolanieProcedury(nazwa, argumenty));
    }
}

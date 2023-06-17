import java.util.*;
public class BudowniczyBlokow {
    private Blok glownyBlok;
    public BudowniczyBlokow() {
        this.glownyBlok = new Blok();
    }

    public Blok zbuduj() {
        return this.glownyBlok;
    }
    public BudowniczyBlokow dodajDeklaracje( char zmienna, Wyrazenie wartosc) {
        this.glownyBlok.dodajDeklaracjeLubProcedure(new Deklaracja(zmienna, wartosc));
        return this;
    }

    public BudowniczyBlokow dodajWypisanie(Wyrazenie wyrazenie) {
        this.glownyBlok.dodajInstrukcje(new Wypisz(wyrazenie));
        return this;
    }

    public BudowniczyBlokow dodajDeklaracjeProcedury(String nazwa, List<Character> listaArgum, Blok instrukcje) {
        this.glownyBlok.dodajDeklaracjeLubProcedure(new DeklaracjaProcedury(nazwa, listaArgum, List.of(instrukcje)));
        return this;
    }

    public BudowniczyBlokow dodajWarunkowa(Wyrazenie wyr1, OpLog oplog, Wyrazenie wyr2,
                               Instrukcja jesliTrue, Instrukcja jesliFalse) {
        this.glownyBlok.dodajInstrukcje(new Warunkowa(wyr1, oplog, wyr2, jesliTrue, jesliFalse));
        return this;
    }

    public BudowniczyBlokow dodajPrzypisanie(char zmienna, Wyrazenie wyrazenie) {
        this.glownyBlok.dodajInstrukcje(new Przypisanie(zmienna, wyrazenie));
        return this;
    }

    public BudowniczyBlokow dodajPetle(char zmienna, Wyrazenie wyrazenie, Blok instrukcje) {
        this.glownyBlok.dodajInstrukcje(new Petla(zmienna, wyrazenie, List.of(instrukcje)));
        return this;
    }

    public BudowniczyBlokow dodajWywolanieProcedury(String nazwa, List<Wyrazenie> argumenty) {
        this.glownyBlok.dodajInstrukcje(new WywolanieProcedury(nazwa, argumenty));
        return this;
    }
}

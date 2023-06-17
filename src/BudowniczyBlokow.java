public class BudowniczyBlokow {
    private Blok glownyBlok;
    public BudowniczyBlokow() {
        this.glownyBlok = new Blok();
    }

    public void dodajDeklaracje( char zmienna, Wyrazenie wartosc) {
        this.glownyBlok.dodajDeklaracjeLubProcedure(new Deklaracja(zmienna, wartosc));
    }

    public void dodajWypisanie(Wyrazenie wyrazenie) {
        this.glownyBlok.dodajInstrukcje(new Wypisz(wyrazenie));
    }

    public void dodajDeklaracjeProcedury()
}

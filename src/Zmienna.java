public class Zmienna extends Wyrazenie{
    private char nazwa;

    public Zmienna(char nazwa) {
        super();
        this.nazwa = nazwa;
    }

    public int oblicz(InstrukcjaBlokowa blok) {
        this.setBlok(blok);
        this.getBlok().getZmienna(nazwa);
        return this.getBlok().getZmienna(nazwa);
    }

}

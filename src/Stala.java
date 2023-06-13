public class Stala extends Wyrazenie{
    private int wartosc;

    Stala(int wart) {
        super();
        this.wartosc = wart;
    }
    @Override
    public int oblicz(InstrukcjaBlokowa blok) {
        return (this.wartosc);
    }
}

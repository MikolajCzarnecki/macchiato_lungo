public class Odejmowanie extends OperacjaArytmetyczna{
    Wyrazenie odjemna;
    Wyrazenie odjemnik;
    Odejmowanie(Wyrazenie o1, Wyrazenie o2) {
        super();
        this.odjemna = o1;
        this.odjemnik = o2;
    }
    @Override
    public int oblicz(InstrukcjaBlokowa blok) {
        this.setBlok(blok);
        return (this.odjemna.oblicz(blok) - this.odjemnik.oblicz(blok));
    }
}

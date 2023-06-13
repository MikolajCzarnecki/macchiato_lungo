public class Modulo extends OperacjaArytmetyczna{
    private Wyrazenie dzielna;
    private Wyrazenie dzielnik;

    public Modulo(Wyrazenie d1, Wyrazenie d2) {
        super();
        this.dzielna = d1;
        this.dzielnik = d2;
    }
    @Override
    public int oblicz(InstrukcjaBlokowa blok) {
        this.setBlok(blok);
        int obecnyDzielnik = this.dzielnik.oblicz(blok);
        if (obecnyDzielnik == 0) {
            throw new ArithmeticException("Dzielna nie moze byc rowna zeru");
        }
        return (this.dzielna.oblicz(blok) % obecnyDzielnik);
    }
}

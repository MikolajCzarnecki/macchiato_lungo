public class Mnozenie extends OperacjaArytmetyczna{
    private Wyrazenie s1;
    private Wyrazenie s2;
    Mnozenie(Wyrazenie s11, Wyrazenie s22) {
        super();
        this.s1 = s11;
        this.s2 = s22;
    }
    @Override
    public int oblicz(InstrukcjaBlokowa blok) {
        this.setBlok(blok);
        return (this.s1.oblicz(blok) * this.s2.oblicz(blok));
    }

    public static Mnozenie of(Wyrazenie czynnik1, Wyrazenie czynnik2) {
        return new Mnozenie(czynnik1, czynnik2);
    }
}

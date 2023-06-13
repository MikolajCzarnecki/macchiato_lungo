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

}

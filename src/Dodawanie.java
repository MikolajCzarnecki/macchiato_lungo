public class Dodawanie extends OperacjaArytmetyczna{
    private Wyrazenie s1;
    private Wyrazenie s2;

    Dodawanie(Wyrazenie wyr1, Wyrazenie wyr2) {
        super();
        this.s1 = wyr1;
        this.s2 = wyr2;
    }
    @Override
    public int oblicz(InstrukcjaBlokowa blok) {
        this.setBlok(blok);
        return (this.s1.oblicz(blok) + this.s2.oblicz(blok));
    }

}

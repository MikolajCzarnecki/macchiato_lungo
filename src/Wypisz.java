public class Wypisz extends InstrukcjaWBloku{
    private Wyrazenie wyrazenie;

    public Wypisz(Wyrazenie wyrazenie) {
        super();
        this.wyrazenie = wyrazenie;
    }
    @Override
    public String getImie() {
        return "Wypisz";
    }
    @Override
    public void wykonaj() {
        try {
            this.wyrazenie.oblicz(this.getBlokWyzej());
        } catch (RuntimeException e) {
            throw new RuntimeException("Wypisz");
        }
        int wartosc = this.wyrazenie.oblicz(this.getBlokWyzej());
        System.out.println(wartosc);
    }
}

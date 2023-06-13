public class Deklaracja extends InstrukcjaWBloku{
    private char zmienna;
    private Wyrazenie wartosc;

    public Deklaracja (char zmienna, Wyrazenie wartosc) {
        super();
        this.zmienna = zmienna;
        this.wartosc = wartosc;
    }
    @Override
    public String getImie() {
        return "Deklaracja";
    }
    @Override
    public void wykonaj () {
        int nrZmiennej = (int) this.zmienna - (int) 'a';
        if (this.getBlokWyzej().getZmienneAktywne()[nrZmiennej]) {System.out.println("NADAL AKTYWNA"); throw new RuntimeException("Deklaracja");}
        else {
            this.getBlokWyzej().setZmienneAktywne(this.zmienna, true);
            try {
                this.wartosc.oblicz(this.getBlokWyzej());
            } catch (RuntimeException e) {
                throw new RuntimeException("Deklaracja");
            }
            try {
                this.getBlokWyzej().setZmienna(this.zmienna, this.wartosc.oblicz(this.getBlokWyzej()));
            } catch (RuntimeException e) {
                throw new RuntimeException("Deklaracja");
            }
        }
    }
}

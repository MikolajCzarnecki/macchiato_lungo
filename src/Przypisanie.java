public class Przypisanie extends InstrukcjaWBloku{
    char zmienna;
    Wyrazenie wartosc;
    public Przypisanie(char zmienna, Wyrazenie wartosc){
        super();
        this.zmienna = zmienna;
        this.wartosc = wartosc;
    }

    @Override
    public String getImie() {
        return "Przypisanie";
    }
    @Override
    public void wykonaj() {
        try {
            this.wartosc.oblicz(this.getBlokWyzej());
        } catch (RuntimeException e) {
            throw new RuntimeException("Przypisanie");
        } try {
            this.getBlokWyzej().setZmienna(this.zmienna, this.wartosc.oblicz(this.getBlokWyzej()));
        } catch (RuntimeException e) {
            throw new RuntimeException("Przypisanie");
        }
    }

}

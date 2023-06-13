public abstract class Wyrazenie {
    private InstrukcjaBlokowa blok;

    public abstract int oblicz(InstrukcjaBlokowa blok);

    public void setBlok(InstrukcjaBlokowa blok) {
        this.blok = blok;
    }

    public InstrukcjaBlokowa getBlok() {
        return this.blok;

    }
    public Wyrazenie() {
        this.blok = null;
    }
}

public abstract class InstrukcjaBlokowa extends Instrukcja{
    private Boolean[] zmienneAktywne;
    private Instrukcja doWykonania;
    private int[] zmienne;

    public InstrukcjaBlokowa(InstrukcjaBlokowa blokWyzej, Macchiato program) {
        super(blokWyzej, program);
        this.zmienneAktywne = new Boolean[26];
        for (int i = 0; i < 26; i++) {
            this.zmienneAktywne[i] = false;
        }
        this.zmienne = new int[26];
        this.doWykonania = null;
    }

    public int[] getZmienne() {return this.zmienne;}
    public abstract int getZmienna(char zmienna);
    public abstract void setZmienna(char zmienna, int wartosc);
    public abstract void wykonajDeklaracje();
    public void setZmienneAktywne(char zmienna, boolean truefalse) {
        int nrZmiennej = (int) zmienna - (int) 'a';
        this.zmienneAktywne[nrZmiennej] = truefalse;
    }

    public Boolean[] getZmienneAktywne(){
        return this.zmienneAktywne;
    }

    public void wezZmienneZWyzszegoPoziomu(int glebokosc) {
        if (glebokosc == 0) this.wypiszWszystkie();
        else {
            if (this.getBlokWyzej() == null) {
                System.out.println("Za duza glebokosc!");
            } else {
                this.getBlokWyzej().wezZmienneZWyzszegoPoziomu(glebokosc - 1);
            }
        }
    }

    public void wypiszWszystkie() {
        for (int i = 0; i < 26; i++) {
            if (this.getZmienneAktywne()[i]) {
                char zmienna = (char) (i + (int) 'a');
                System.out.println(zmienna + " - " + this.getZmienne()[i]);
            }
        }
    }
}

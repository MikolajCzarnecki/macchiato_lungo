import java.util.*;
public class Petla extends InstrukcjaBlokowa{
    private char zmienna;
    private Wyrazenie wyrazenie;
    private int wartoscZmiennej = 0;
    private List<Instrukcja> instrukcje;
    private int ileRazy;
    public Petla(char zmienna, Wyrazenie wyrazenie, InstrukcjaBlokowa blokWyzej) {
        super(blokWyzej, blokWyzej.getProgram());
        this.zmienna = zmienna;
        this.wyrazenie = wyrazenie;
        this.instrukcje = new LinkedList<>();
        this.setZmienneAktywne(zmienna, true);
    }

    @Override
    public String getImie() {
        return "Petla";
    }

    public void dodajDoPetli(Instrukcja instrukcja) {
        instrukcja.setBlokWyzej(this);
        instrukcja.setProgram(this.getProgram());
        this.instrukcje.add(instrukcja);
    }
    @Override
    public void setZmienna(char zmienna, int wartosc) {
        if (zmienna == this.zmienna) {
            this.wartoscZmiennej = wartosc;
        } else {
            this.getBlokWyzej().setZmienna(zmienna, wartosc);
        }
    }
    @Override
    public int getZmienna(char zmienna) {
        if (zmienna == this.zmienna) return wartoscZmiennej;
        else {
            if (this.getBlokWyzej() == null) {
                throw new RuntimeException("Petla");
            } else {
                return this.getBlokWyzej().getZmienna(zmienna);
            }
        }
    }

    @Override
    public void wykonajDeklaracje(){
        try {
            this.wyrazenie.oblicz(this);
        } catch (RuntimeException e) {
            throw new RuntimeException("Petla");
        }
        this.ileRazy = this.wyrazenie.oblicz(this);
    }
    @Override
    public void wykonaj() {
        this.wykonajDeklaracje();
        Odpluskwiacz odpluskwiacz = this.getProgram().getOdpluskwiacz();
        int ileInstrukcji = this.instrukcje.size();
        for (int i = 0; i < this.ileRazy; i++) {
            if (this.getProgram().getCzyZakonczonyProgram()) return;
            this.wartoscZmiennej = i;
            for (int j = 0; j < ileInstrukcji; j++) {
                if (this.getProgram().getCzyZakonczonyProgram()) return;
                odpluskwiacz.oflaguj(this.instrukcje.get(j));
            }
        }
    }

}

import java.util.*;
public class Blok extends InstrukcjaBlokowa{
    private List<Deklaracja> deklaracje;
    private List<Instrukcja> instrukcje;

    public Blok(InstrukcjaBlokowa blokWyzej, Macchiato program) {
        super(blokWyzej, program);

        this.deklaracje = new LinkedList<Deklaracja>();
        this.instrukcje = new LinkedList<Instrukcja>();
    }
    @Override
    public String getImie() {
        return "Blok";
    }
    @Override
    public int getZmienna(char zmienna) {
        InstrukcjaBlokowa blokWyzej = this.getBlokWyzej();
        int nrZmiennej = (int) zmienna - (int) 'a';
        if (this.getZmienneAktywne()[nrZmiennej]) {
            return this.getZmienne()[nrZmiennej];
        } else {
            if (blokWyzej == null) {
                throw new RuntimeException("Zmienna nie istnieje");
            } else {
                return blokWyzej.getZmienna(zmienna);
            }
        }
    }
    @Override
    public void setZmienna(char zmienna, int wartosc) {
        int nrZmiennej = (int) zmienna - (int) 'a';
        if (this.getZmienneAktywne()[nrZmiennej]) this.getZmienne()[nrZmiennej] = wartosc;
        else {
            if (this.getBlokWyzej() == null) throw new RuntimeException("Nie ma zmiennej do przypisania");
            else this.getBlokWyzej().setZmienna(zmienna, wartosc);
        }
    }

    public void dodajDeklaracje(Deklaracja deklaracja) {
        deklaracja.setBlokWyzej(this);
        deklaracja.setProgram(this.getProgram());
        this.deklaracje.add(deklaracja);
    }

    public void dodajInstrukcje(Instrukcja instrukcja) {
        if (instrukcja.getBlokWyzej() == null) {
            instrukcja.setBlokWyzej(this);
            instrukcja.setProgram(this.getProgram());
        }
        this.instrukcje.add(instrukcja);
    }
    @Override
    public void wykonajDeklaracje() {
        int rozmiar = this.deklaracje.size();
        int i = 0;
        while (i < rozmiar && !this.getProgram().getCzyBlad()) {
            try {
                this.deklaracje.get(i).wykonaj();
            } catch (RuntimeException e) {
                if (this.getBlokWyzej() != null) throw new RuntimeException("Runtime exception wyzej");
                if (!this.getProgram().getCzyBlad()) {
                    System.out.println(e.getMessage());
                    this.wypiszWszystkie();
                    this.getProgram().setCzyBladTrue();
                }
            }
            i++;
        }
    }

    public void wyczyscLokalne() {
        for (int i = 0; i < 26; i++) {
            if (this.getZmienneAktywne()[i]) {
                char zmienna = (char)(i + (int)'a');
                this.setZmienneAktywne(zmienna,false);
            }
        }
    }
    @Override
    public void wykonaj() {
        this.wykonajDeklaracje();
        int rozmiar = this.instrukcje.size();
        Odpluskwiacz odpluskwiacz = this.getProgram().getOdpluskwiacz();
        int i = 0;
        while (
                i < rozmiar &&
                !this.getProgram().getCzyBlad() &&
                !this.getProgram().getCzyZakonczonyProgram()
        ) {
            try {
                odpluskwiacz.oflaguj(this.instrukcje.get(i));
                i++;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                if (this.getBlokWyzej() != null) throw new RuntimeException("");
                if (!this.getProgram().getCzyBlad()) {
                    System.out.println(e.getMessage());
                    this.wypiszWszystkie();
                    this.getProgram().setCzyBladTrue();
                    return;
                }
            }
        }
        this.wyczyscLokalne();
    }
}

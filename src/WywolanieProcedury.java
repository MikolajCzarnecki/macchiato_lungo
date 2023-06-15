import java.util.*;
public class WywolanieProcedury extends InstrukcjaBlokowa{
    private String nazwa;
    private List<Wyrazenie> argumenty;
    private List<Deklaracja> deklaracje;
    private Procedura wywolywana;

    private List<Instrukcja> instrukcje;
    public WywolanieProcedury(String nazwa, List<Wyrazenie> argumenty) {
        super();
        this.nazwa = nazwa;
        this.argumenty = argumenty;
    }

    public void zbudujBlok() {
        try{
            this.wywolywana = this.getBlokWyzej().dajProcedure(this.nazwa);
        } catch(RuntimeException e) {
            throw (new RuntimeException("WywolanieProcedury"));
        }
        System.out.println("PROCEDURA DANA");
        if (this.argumenty.size() != this.wywolywana.getNaglowek().getArgumenty().size()) {
            throw (new RuntimeException("WywolanieProcedury"));
        }

        this.deklaracje = new LinkedList<Deklaracja>();
        for (int i = 0; i < this.argumenty.size(); i++) {
            this.deklaracje
                    .add(new Deklaracja(this.wywolywana.getNaglowek().getArgumenty().get(i), this.argumenty.get(i)));
        }

        this.instrukcje = new LinkedList<Instrukcja>();
        for (int i = 0; i < this.wywolywana.getInstrukcje().size(); i++) {
            Instrukcja doDodania = this.wywolywana.getInstrukcje().get(i);
            doDodania.setBlokWyzej(this);
            this.instrukcje.add(doDodania);
        }
    }
    @Override
    public void wykonajDeklaracje() {
        int rozmiar = this.deklaracje.size();
        int j = 0;
        while (j < rozmiar && !this.getProgram().getCzyBlad()) {
            try {
                this.deklaracje.get(j).setBlokWyzej(this);
                this.deklaracje.get(j).setProgram(this.getProgram());
                this.deklaracje.get(j).wykonaj();
            } catch (RuntimeException e) {
                if (this.getBlokWyzej() != null) throw new RuntimeException("Runtime exception wyzej");
                if (!this.getProgram().getCzyBlad()) {
                    System.out.println(e.getMessage());
                    this.wypiszWszystkie();
                    this.getProgram().setCzyBladTrue();
                }
            }
            j++;
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

    public void wyczyscLokalne() {
        for (int i = 0; i < 26; i++) {
            if (this.getZmienneAktywne()[i]) {
                char zmienna = (char)(i + (int)'a');
                this.setZmienneAktywne(zmienna,false);
            }
        }
    }
    @Override
    public String getImie() {
        return("WywolanieProcedury");
    }

    @Override
    public void wykonaj() {
        System.out.println("PRZED ZBUDOWANIEM BLOKU");
        this.zbudujBlok();
        System.out.println("PRZED DEKLARACJAMI, rozmiar deklaracji "+ this.deklaracje.size());
        this.wykonajDeklaracje();
        System.out.println("instrukcje " + this.instrukcje.size());
        int rozmiar = this.instrukcje.size();
        Odpluskwiacz odpluskwiacz = this.getProgram().getOdpluskwiacz();
        int i = 0;
        while (
                i < rozmiar &&
                        !this.getProgram().getCzyBlad() &&
                        !this.getProgram().getCzyZakonczonyProgram()
        ) {
            try {
                System.out.println("wykonywanie " + this.instrukcje.get(i).getClass().getName());
                this.instrukcje.get(i).setBlokWyzej(this);
                this.instrukcje.get(i).setProgram(this.getProgram());
                odpluskwiacz.oflaguj(this.instrukcje.get(i));
                i++;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                if (this.getBlokWyzej() != null) throw new RuntimeException("wyzej");
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

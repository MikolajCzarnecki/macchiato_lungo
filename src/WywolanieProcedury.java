import java.util.*;
public class WywolanieProcedury extends InstrukcjaBlokowa{
    private List<Deklaracja> deklaracje;
    private Procedura wywolywana;

    private List<Instrukcja> instrukcje;
    public WywolanieProcedury(List<Wyrazenie> argumenty, String nazwa, InstrukcjaBlokowa blokWyzej, Macchiato program) {
        super(blokWyzej, program);
        try{
            this.wywolywana = this.getProgram().dajProcedure(nazwa);
        } catch(RuntimeException e) {
            System.out.println("WywolanieProcedury");
            this.getProgram().setCzyBladTrue();
        }

        if (argumenty.size() != this.wywolywana.getNaglowek().getArgumenty().size()) {
            System.out.println("WywolanieProcedury");
            this.getProgram().setCzyBladTrue();
        }

        this.deklaracje = new LinkedList<Deklaracja>();
        for (int i = 0; i < argumenty.size(); i++) {
            this.deklaracje
                    .add(new Deklaracja(this.wywolywana.getNaglowek().getArgumenty().get(i), argumenty.get(i)));
        }

        this.instrukcje = new LinkedList<Instrukcja>();
        for (int i = 0; i < this.wywolywana.getInstrukcje().size(); i++) {
            Instrukcja doDodania = this.wywolywana.getInstrukcje().get(i);
            doDodania.setBlokWyzej(this);

        }

    }

    @Override
    public void wykonajDeklaracje() {
        int rozmiar = this.deklaracje.size();
        int j = 0;
        while (j < rozmiar && !this.getProgram().getCzyBlad()) {
            try {
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
        this.wykonajDeklaracje();
        int rozmiar =
    }
}

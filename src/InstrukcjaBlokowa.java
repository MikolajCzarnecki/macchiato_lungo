import java.util.*;
import java.io.*;

public abstract class InstrukcjaBlokowa extends Instrukcja{
    private Boolean[] zmienneAktywne;
    private Instrukcja doWykonania;
    private int[] zmienne;

    private List<Procedura> procedury;

    public InstrukcjaBlokowa() {
        super(null, null);
        this.zmienneAktywne = new Boolean[26];
        for (int i = 0; i < 26; i++) {
            this.zmienneAktywne[i] = false;
        }
        this.zmienne = new int[26];
        this.doWykonania = null;
        this.procedury = new LinkedList<Procedura>();
    }
    public Procedura dajProcedure(String szukanaNazwa) {
        int iloscProcedur = this.procedury.size();
        for (int i = 0; i < iloscProcedur; i++) {
            Procedura obecnaBadana = this.procedury.get(i);
            if (obecnaBadana.getNaglowek().getNazwa().compareTo(szukanaNazwa) == 0) {
                return obecnaBadana;
            }
        }
        if (this.getBlokWyzej() == null) {
            throw new RuntimeException("Nie ma procedury");
        } else {
            return this.getBlokWyzej().dajProcedure(szukanaNazwa);
        }
    }
    public List<Procedura> getProcedury() {
        return this.procedury;
    }
    public void addProcedura(Procedura procedura) {
        this.procedury.add(procedura);
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
    public void wypiszDoPliku(String nazwa){
        File doWpisania = new File(nazwa);
        FileWriter skryba = null;
        try {
            skryba = new FileWriter(doWpisania);
            skryba.write("ZMIENNE:\n");
            for (int i = 0; i < 26; i++) {
                if (this.getZmienneAktywne()[i]) {
                    char zmienna = (char) (i + (int) 'a');
                    skryba.write(zmienna + " - " + this.getZmienne()[i] + "\n");
                }
            }
            skryba.write("\n\nPROCEDURY:\n");
            int i = 0;
            while (i < this.getProcedury().size()) {
                String nazwaProcedury = this.getProcedury().get(i).getNaglowek().getNazwa();
                skryba.write(" " + nazwaProcedury + ":\n");
                int j = 0;
                while (j < this.getProcedury().get(i).getNaglowek().getArgumenty().size()) {
                    skryba.write("-  " + this.getProcedury().get(i).getNaglowek().getArgumenty().get(j) + "\n");
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("BUG W PISANIU");
        } finally {
            try {
                skryba.close();
            } catch (IOException e) {
                System.out.println("BUG W PISANIU");
            }
        }

    }
}

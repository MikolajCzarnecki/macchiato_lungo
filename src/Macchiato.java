import java.util.Scanner;
import java.util.*;
public class Macchiato {
    private List<Procedura> procedury;
    private Instrukcja instrukcje;

    private Boolean czyBlad;

    private Scanner scanner;

    private Boolean czyOdpluskwianie;
    private Boolean czyZakonczonyProgram;
    private Odpluskwiacz odpluskwiacz;
    public Macchiato(){
        this.czyZakonczonyProgram = false;
        this.czyBlad = false;
        this.scanner = null;
        this.odpluskwiacz = new Odpluskwiacz(this);
    }
    public Procedura dajProcedure(String szukanaNazwa) {
        int iloscProcedur = this.procedury.size();
        for (int i = 0; i < iloscProcedur; i++) {
            Procedura obecnaBadana = this.procedury.get(i);
            if (obecnaBadana.getNaglowek().getNazwa().compareTo(szukanaNazwa) == 0) return obecnaBadana;
        }
        throw new RuntimeException("Nie ma procedury");
    }
    public Odpluskwiacz getOdpluskwiacz() {
        return odpluskwiacz;
    }

    public boolean getCzyBlad() {
        return this.czyBlad;
    }

    public Boolean getCzyOdpluskwianie() {
        return czyOdpluskwianie;
    }

    public void setCzyOdpluskwianie(Boolean bool) {
        this.czyOdpluskwianie = bool;
    }

    public Boolean getCzyZakonczonyProgram() {
        return czyZakonczonyProgram;
    }

    public void setCzyZakonczonyProgramTrue() {
        this.czyZakonczonyProgram = true;
    }

    public Scanner getScanner() {
        return this.scanner;
    }
    public void setCzyBladTrue() {
        this.czyBlad = true;
    }

    public void wykonaj(Blok blok, Boolean odpluskwianie) {

        this.czyOdpluskwianie = odpluskwianie;
        if (odpluskwianie) this.scanner = new Scanner(System.in);
        blok.wykonaj();
    }
}

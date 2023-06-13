import java.util.Scanner;
public class Odpluskwiacz {
    private Macchiato program;
    private int ileKrokow;

    public Odpluskwiacz(Macchiato program) {
        this.program = program;
        this.ileKrokow = 0;
    }

    public void zewaluujPolecenie(char pol, Instrukcja instrukcja) {
        switch (pol) {
            case 'c':
                this.program.setCzyOdpluskwianie(false);
                instrukcja.wykonaj();
                break;
            case 'e':
                this.program.setCzyZakonczonyProgramTrue();
                break;
            case 's':
                this.ileKrokow = this.program.getScanner().nextInt();
                this.oflaguj(instrukcja);
                break;
            case 'd':
                int jakGleboko = this.program.getScanner().nextInt();
                instrukcja.getBlokWyzej().wezZmienneZWyzszegoPoziomu(jakGleboko);
                this.oflaguj(instrukcja);
        }
    }
    public void oflaguj(Instrukcja instrukcja) {
        if (!this.program.getCzyOdpluskwianie()) {
            instrukcja.wykonaj();
            return;
        }

        if (this.ileKrokow > 0) {
            this.ileKrokow--;
            instrukcja.wykonaj();
        } else {
            System.out.println(instrukcja.getImie());
            char polecenie = this.program.getScanner().next().charAt(0);
            this.zewaluujPolecenie(polecenie, instrukcja);
        }
    }
}

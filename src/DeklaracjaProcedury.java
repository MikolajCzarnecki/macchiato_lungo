import java.util.*;
public class DeklaracjaProcedury extends InstrukcjaDeklaracyjna{
    private List<Character> argumenty;
    private List<Instrukcja> instrukcje;
    private String nazwa;

    private Boolean czyDobrzeOpisana;
    Procedura zadeklarowanaProcedura;
    public DeklaracjaProcedury(String nazwa, List<Character> argumenty, List<Instrukcja> instrukcje) {
        super();
        this.instrukcje = new LinkedList<Instrukcja>();
        for (int i = 0; i < instrukcje.size(); i++) {
            this.instrukcje.add(instrukcje.get(i));
        }
        this.czyDobrzeOpisana = true;
        for (int i = 0; i < argumenty.size(); i++) {
            for (int j = i + 1; j < argumenty.size(); j++) {
                if (argumenty.get(i) == argumenty.get(j)) {
                    this.czyDobrzeOpisana = false;
                }
            }
        }
        this.nazwa = nazwa;
        this.argumenty = argumenty;
    }

    public void dodajInstrukcje(Instrukcja doDodania) {this.instrukcje.add(doDodania);}
    public void dodajArgument(Character doDodania) {
        for (int i = 0; i < this.argumenty.size(); i++) {
            if (this.argumenty.get(i) == doDodania) {
                this.czyDobrzeOpisana = false;
            }
        }
        this.argumenty.add(doDodania);
    }

    public String getImie(){return "DeklaracjaProcedury";}

    public void wykonaj() {
        if (!this.czyDobrzeOpisana) {
            throw (new RuntimeException("DeklaracjaProcedury"));
        }
        for (int i = 0; i < this.getBlokWyzej().getProcedury().size(); i++) {
            Procedura obecnaBadana = this.getBlokWyzej().getProcedury().get(i);
            if (obecnaBadana.getNaglowek().getNazwa().compareTo(this.nazwa) == 0)
                throw(new RuntimeException("DeklaracjaProcedury"));
        }
        this.zadeklarowanaProcedura = new Procedura(this.getProgram(), this.nazwa, this.argumenty, this.instrukcje);
        this.getBlokWyzej().addProcedura(this.zadeklarowanaProcedura);
    }
}

import java.util.*;
public class DeklaracjaProcedury extends InstrukcjaDeklaracyjna{
    private List<Character> argumenty;
    private List<Instrukcja> instrukcje;
    private String nazwa;
    Procedura zadeklarowanaProcedura;
    public DeklaracjaProcedury(String nazwa, List<Character> argumenty, List<Instrukcja> instrukcje) {
        super();
        this.nazwa = nazwa;
        this.argumenty = argumenty;
        this.instrukcje = instrukcje;
    }

    public String getImie(){return "DeklaracjaProcedury";}

    public void wykonaj() {
        for (int i = 0; i < this.getBlokWyzej().getProcedury().size(); i++) {
            Procedura obecnaBadana = this.getBlokWyzej().getProcedury().get(i);
            if (obecnaBadana.getNaglowek().getNazwa().compareTo(this.nazwa) == 0)
                throw(new RuntimeException("DeklaracjaProcedury"));
            
        }
        this.zadeklarowanaProcedura = new Procedura(this.getProgram(), this.nazwa, this.argumenty, this.procedury);

    }
}

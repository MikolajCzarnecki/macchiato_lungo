import java.util.*;
public class WywolanieProcedury extends InstrukcjaBlokowa{
    private List<Wyrazenie> argumenty;
    private Procedura wywolywana;
    public WywolanieProcedury(List<Wyrazenie> argumenty, String nazwa, InstrukcjaBlokowa blokWyzej, Macchiato program) {
        super(blokWyzej, program);
        try{
            this.wywolywana = this.getProgram().dajProcedure(nazwa);
            this.argumenty = argumenty;
        } catch(RuntimeException e) {
            System.out.println("WywolanieProcedury");
            this.getProgram().setCzyBladTrue();
        }
        if (this.argumenty.size() != this.wywolywana.getNaglowek().getArgumenty().size()) {
            System.out.println("WywolanieProcedury");
            this.getProgram().setCzyBladTrue();
        }
    }


}

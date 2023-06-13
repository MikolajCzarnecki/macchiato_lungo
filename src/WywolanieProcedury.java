import java.util.*;
public class WywolanieProcedury extends InstrukcjaBlokowa{
    private List<Deklaracja> deklaracje;
    private Procedura wywolywana;
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

    }

    @Override
    public String getImie() {
        return("WywolanieProcedury");
    }
}

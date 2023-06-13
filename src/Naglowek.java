import java.util.*;
public class Naglowek {
    private String nazwa;
    private List<Character> argumenty;
    private Procedura procedura;

    public Naglowek(String nazwa, Procedura procedura, List<Character> argumenty) {
        this.procedura = procedura;
        for (int i = 0; i < nazwa.length(); i++) {
            int charToInt = nazwa.charAt(i);
            if (charToInt < (int) 'a' || charToInt > (int) 'z') {
                System.out.println("Procedura");
                this.procedura.getProgram().setCzyBladTrue();
            }
        }
        this.nazwa = nazwa;
        for (int i = 0; i < argumenty.size(); i++) {
            for (int j = i + 1; j < argumenty.size(); j++) {
                if (argumenty.get(i) == argumenty.get(j) ||
                        (int) argumenty.get(j) > (int) 'z' || (int) argumenty.get(j) < (int) 'a') {
                    System.out.println("Procedura");
                    this.procedura.getProgram().setCzyBladTrue();
                }
            }
        }
    }

    public String getNazwa() {
        return this.nazwa;
    }


    public List<Character> getArgumenty() {
        return this.argumenty;
    }
}

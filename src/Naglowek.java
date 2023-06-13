import java.util.*;
public class Naglowek {
    private String nazwa;
    private List<Character> argumenty;

    public Naglowek(String nazwa) {
        for (int i = 0; i < nazwa.length(); i++) {
            int charToInt = nazwa.charAt(i);
            if (charToInt < (int) 'a' || charToInt > (int) 'z') throw new RuntimeException("Zla litera w nazwie");
        }

        this.nazwa = nazwa;
        this.argumenty = new LinkedList<Character>();
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public void addArgument(Character newArg) {
        if (this.argumenty.contains(newArg)) throw new RuntimeException("Argument juz podany.");
        this.argumenty.add(newArg);
    }

    public List<Character> getArgumenty() {
        return this.argumenty;
    }
}

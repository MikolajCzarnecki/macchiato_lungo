//import "java."
public class Main {
    public static void main(String[] args) {
        Macchiato program1 = new Macchiato();

        Blok b1 = new Blok(null, program1);
        b1.dodajDeklaracje(new Deklaracja('n', new Stala(30)));
        Odejmowanie od1 = new Odejmowanie(new Zmienna('n'), new Stala(1));

        Petla p1 = new Petla('k', od1, b1);
        b1.dodajInstrukcje(p1);

        Blok b2 = new Blok(p1, program1);
        p1.dodajDoPetli(b2);

        b2.dodajDeklaracje(new Deklaracja('p', new Stala(1)));

        Dodawanie dodawanieWB2 = new Dodawanie(new Zmienna('k'), new Stala(2));
        b2.dodajInstrukcje(new Przypisanie('k', dodawanieWB2));

        Odejmowanie od2 = new Odejmowanie(new Zmienna('k'), new Stala(2));
        Petla p2 = new Petla('i', od2, b2);
        b2.dodajInstrukcje(p2);

        Dodawanie do1 = new Dodawanie(new Zmienna('i'), new Stala(2));
        p2.dodajDoPetli(new Przypisanie('i', new Dodawanie(new Zmienna('i'), new Stala(2))));

        Modulo mod1 = new Modulo(new Zmienna('k'), new Zmienna('i'));
        p2.dodajDoPetli(
                new Warunkowa(
                        mod1, OpLog.EQ, new Stala(0),
                        new Przypisanie('p', new Stala(0)),
                        null
                        )
        );
        b2.dodajInstrukcje(
                new Warunkowa(
                        new Zmienna('p'), OpLog.EQ, new Stala(1),
                        new Wypisz(new Zmienna('k')),
                        null
                )
        );


        program1.wykonaj(b1, true);
    }
}

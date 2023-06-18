//import "java."
import java.util.*;
public class Main {
    public static void main(String[] args) {
//        Macchiato program1 = new Macchiato();
//
//        Blok b1 = new Blok(null, program1);
//        b1.dodajDeklaracje(new Deklaracja('n', new Stala(30)));
//        Odejmowanie od1 = new Odejmowanie(new Zmienna('n'), new Stala(1));
//
//        Petla p1 = new Petla('k', od1, b1);
//        b1.dodajInstrukcje(p1);
//
//        Blok b2 = new Blok(p1, program1);
//        p1.dodajDoPetli(b2);
//
//        b2.dodajDeklaracje(new Deklaracja('p', new Stala(1)));
//
//        Dodawanie dodawanieWB2 = new Dodawanie(new Zmienna('k'), new Stala(2));
//        b2.dodajInstrukcje(new Przypisanie('k', dodawanieWB2));
//
//        Odejmowanie od2 = new Odejmowanie(new Zmienna('k'), new Stala(2));
//        Petla p2 = new Petla('i', od2, b2);
//        b2.dodajInstrukcje(p2);
//
//        Dodawanie do1 = new Dodawanie(new Zmienna('i'), new Stala(2));
//        p2.dodajDoPetli(new Przypisanie('i', new Dodawanie(new Zmienna('i'), new Stala(2))));
//
//        Modulo mod1 = new Modulo(new Zmienna('k'), new Zmienna('i'));
//        p2.dodajDoPetli(
//                new Warunkowa(
//                        mod1, OpLog.EQ, new Stala(0),
//                        new Przypisanie('p', new Stala(0)),
//                        null
//                        )
//        );
//        b2.dodajInstrukcje(
//                new Warunkowa(
//                        new Zmienna('p'), OpLog.EQ, new Stala(1),
//                        new Wypisz(new Zmienna('k')),
//                        null
//                )
//        );
//
//
//        program1.wykonaj(b1, true);
//    }
        Macchiato testProc = new Macchiato();
//        Blok b1 = new BudowniczyBlokow()
//                .dodajDeklaracje('a', Stala.of(13))
//                .dodajDeklaracjeProcedury("dodaj50", List.of('c'),
//                        new BudowniczyBlokow()
//                                .dodajWypisanie(Dodawanie.of(Zmienna.of('c'), Stala.of(50)))
//                                .zbuduj()
//                )
//                .dodajWywolanieProcedury("dodaj50", List.of(Zmienna.of('a')))
//                .zbuduj();
        Blok b2 = new BudowniczyBlokow()
                .dodajDeklaracje('x', Stala.of(101))
                .dodajDeklaracje('y', Stala.of(1))
                .dodajDeklaracjeProcedury("out", List.of('a'),
                        new BudowniczyBlokow()
                                .dodajWypisanie(
                                        Dodawanie.of(
                                            Zmienna.of('a'), Zmienna.of('x'))
                                        )
                                .zbuduj()
                )
                .dodajPrzypisanie('x', Odejmowanie.of(
                        Zmienna.of('x') , Zmienna.of('y')
                        )
                )
                .dodajWywolanieProcedury("out", List.of(Zmienna.of('x')))
                .dodajWywolanieProcedury("out", List.of(Stala.of(100)))
                .dodajBlok(
                        new BudowniczyBlokow()
                                .dodajDeklaracje('x', Stala.of(10))
                                .dodajWywolanieProcedury("out", List.of(Stala.of(100)))
                                .zbuduj()
                )
                .zbuduj();
        testProc.wykonaj(b2, true);
    }
}

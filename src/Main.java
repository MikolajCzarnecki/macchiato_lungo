//import "java."
import java.util.*;
public class Main {
    public static void main(String[] args) {

        Macchiato testProc = new Macchiato();

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

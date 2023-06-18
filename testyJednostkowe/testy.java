import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.OutputStream.*;
import java.io.PrintStream;
import java.util.*;

import java.io.ByteArrayOutputStream;
import java.lang.*;
public class testy {
    private Macchiato testProgram;
    private ByteArrayOutputStream output;
    private PrintStream stdOut;
    private Blok pustyBlok;

    @BeforeEach
    public void inicjowanie() {
        testProgram = new Macchiato();
        output = new ByteArrayOutputStream();
        stdOut = System.out;
        System.setOut(new PrintStream(output));
        pustyBlok = new Blok();
        pustyBlok.setBlokWyzej(null);
        pustyBlok.setProgram(testProgram);
    }

    @Test
    public void testStalej() {
        int testnum =  Stala.of(50).oblicz(pustyBlok);

        assertEquals(50, testnum);
    }

    @Test
    public void testDodawania() {
        int testnum =  Dodawanie.of(Stala.of(50), Stala.of(27)).oblicz(pustyBlok);

        assertEquals(77, testnum);
    }
    @Test
    public void testMnozenia() {
        int testnum =  Mnozenie.of(Stala.of(33), Stala.of(20)).oblicz(pustyBlok);

        assertEquals(660, testnum);
    }
    @Test
    public void testDzielenia() {
        int testnum =  Dzielenie.of(Stala.of(33), Stala.of(3)).oblicz(pustyBlok);

        assertEquals(11, testnum);
    }
    @Test
    public void testModulo() {
        int testnum =  Modulo.of(Stala.of(27), Stala.of(6)).oblicz(pustyBlok);

        assertEquals(3, testnum);
    }
    @Test
    public void testOdejmowania() {
        int testnum =  Odejmowanie.of(Stala.of(33), Stala.of(20)).oblicz(pustyBlok);

        assertEquals(13, testnum);
    }
    @Test
    public void testZmiennej() {
        pustyBlok.setZmienneAktywne('a', true);
        pustyBlok.setZmienna('a', 123);
        int testnum =  Zmienna.of('a').oblicz(pustyBlok);

        assertEquals(123, testnum);
    }
    @Test
    public void testWypisania() {
        Blok b1 = new BudowniczyBlokow()
                .dodajWypisanie(Stala.of(50))
                .zbuduj();
        testProgram.wykonaj(b1, false);

        assertEquals(0, "50\r\n".compareTo(output.toString()));
    }

    @Test
    public void testDeklaracji() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracje('w', Stala.of(33))
                .dodajWypisanie(Zmienna.of('w'))
                .zbuduj();
        testProgram.wykonaj(b1, false);

        assertEquals(0, "33\r\n".compareTo(output.toString()));
    }

    @Test
    public void testPrzypisania() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracje('w', Stala.of(33))
                .dodajPrzypisanie('w', Stala.of(12))
                .dodajWypisanie(Zmienna.of('w'))
                .zbuduj();
        testProgram.wykonaj(b1, false);

        assertEquals(0, "12\r\n".compareTo(output.toString()));
    }

    @Test
    public void testPrzyslaniania() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracje('w', Stala.of(33))
                .dodajBlok(
                        new BudowniczyBlokow()
                                .dodajDeklaracje('w', Stala.of(22))
                                .dodajWypisanie(Zmienna.of('w'))
                                .zbuduj()
                )
                .zbuduj();
        testProgram.wykonaj(b1, false);

        assertEquals(0, "22\r\n".compareTo(output.toString()));
    }
    @Test
    public void testWarunkowej() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracje('w', Stala.of(33))
                .dodajWarunkowa(Zmienna.of('w'), OpLog.G, Stala.of(30),
                        new Przypisanie('w', Stala.of(444))
                    ,
                        new Przypisanie('w', Stala.of(555))
                )
                .dodajWypisanie(Zmienna.of('w'))
                .zbuduj();
        testProgram.wykonaj(b1, false);

        assertEquals(0, "444\r\n".compareTo(output.toString()));
    }

    @Test
    public void testDeklaracjiProcedury() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracjeProcedury("testproc", List.of('a','b','c') ,
                        new BudowniczyBlokow()
                                .dodajDeklaracje('z', Stala.of(12))
                                .dodajPrzypisanie('z', Zmienna.of('a'))
                                .zbuduj()
                )
                .dodajWywolanieProcedury("testproc",
                        List.of(Stala.of(1), Stala.of(2), Stala.of(3)))
                .zbuduj();

        System.out.print("a");
        testProgram.wykonaj(b1, false);
        System.out.print("a");
        assertEquals(0, "aa".compareTo(output.toString()));
    }

    @Test
    public void testNiezadeklarowanejProcedury() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracjeProcedury("testproc", List.of('a','b','c') ,
                        new BudowniczyBlokow()
                                .dodajDeklaracje('z', Stala.of(12))
                                .dodajPrzypisanie('z', Zmienna.of('a'))
                                .zbuduj()
                )
                .dodajWywolanieProcedury("niematakiejprocedury",
                        List.of(Stala.of(1), Stala.of(2), Stala.of(3)))
                .zbuduj();

        System.out.print("a");
        testProgram.wykonaj(b1, false);
        System.out.print("a");
        assertEquals(0, "aWywolanieProcedury\r\na".compareTo(output.toString()));
    }

    @Test
    public void testWywolaniaProcedury() {
        Blok b1 = new BudowniczyBlokow()
                .dodajDeklaracjeProcedury("testproc", List.of('a','b','c') ,
                        new BudowniczyBlokow()
                                .dodajDeklaracje('z', Stala.of(12))
                                .dodajPrzypisanie('z', Zmienna.of('a'))
                                .dodajWypisanie(Zmienna.of('z'))
                                .zbuduj()
                )
                .dodajWywolanieProcedury("testproc",
                        List.of(Stala.of(1), Stala.of(2), Stala.of(3)))
                .zbuduj();

        testProgram.wykonaj(b1, false);
        assertEquals(0, "1\r\n".compareTo(output.toString()));
    }

    @Test
    public void testPetli() {
        Blok b1 = new BudowniczyBlokow()
                .dodajPetle('k', Stala.of(4),
                            new BudowniczyBlokow()
                                    .dodajWypisanie(Zmienna.of('k'))
                                    .zbuduj()
                        )
                .zbuduj();

        testProgram.wykonaj(b1, false);
        assertEquals(0, "0\r\n1\r\n2\r\n3\r\n".compareTo(output.toString()));
    }
}

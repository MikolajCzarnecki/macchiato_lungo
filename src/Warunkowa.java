public class Warunkowa extends InstrukcjaWBloku{
    private Wyrazenie wyrazenie1;
    private Wyrazenie wyrazenie2;
    private Instrukcja jesliTrue;
    private Instrukcja jesliFalse;
    private OpLog operator;

    public Warunkowa(
            Wyrazenie wyrazenie1, OpLog op, Wyrazenie wyrazenie2, Instrukcja jesliTrue,
            Instrukcja jesliFalse
    ) {
        super();
        this.wyrazenie1 = wyrazenie1;
        this.wyrazenie2 = wyrazenie2;
        this.jesliTrue = jesliTrue;
        this.jesliFalse = jesliFalse;
        this.operator = op;
    }

    @Override
    public String getImie() {
        return "Warunkowa";
    }

    @Override
    public void wykonaj() {
        Odpluskwiacz odpluskwiacz = this.getProgram().getOdpluskwiacz();
        OpLog operator = this.operator;
        switch (operator) {
            case G:
                if (this.wyrazenie1.oblicz(this.getBlokWyzej()) > this.wyrazenie2.oblicz(this.getBlokWyzej())) {
                    if (this.jesliTrue == null) return;
                    this.jesliTrue.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliTrue);
                }
                else {
                    if (this.jesliFalse == null) return;
                    this.jesliFalse.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliFalse);
                }
                break;
            case L:
                if (this.wyrazenie1.oblicz(this.getBlokWyzej()) < this.wyrazenie2.oblicz(this.getBlokWyzej())) {
                    if (this.jesliTrue == null) return;
                    this.jesliTrue.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliTrue);
                }
                else {
                    if (this.jesliFalse == null) return;
                    this.jesliFalse.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliFalse);
                }
                break;
            case EQ:
                if (this.wyrazenie1.oblicz(this.getBlokWyzej()) == this.wyrazenie2.oblicz(this.getBlokWyzej())) {
                    if (this.jesliTrue == null) return;
                    this.jesliTrue.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliTrue);
                }
                else {
                    if (this.jesliFalse == null) return;
                    this.jesliFalse.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliFalse);
                }
                break;
            case GEQ:
                if (this.wyrazenie1.oblicz(this.getBlokWyzej()) >= this.wyrazenie2.oblicz(this.getBlokWyzej())) {
                    if (this.jesliTrue == null) return;
                    this.jesliTrue.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliTrue);
                }
                else {
                    if (this.jesliFalse == null) return;
                    this.jesliFalse.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliFalse);
                }
                break;
            case LEQ:
                if (this.wyrazenie1.oblicz(this.getBlokWyzej()) <= this.wyrazenie2.oblicz(this.getBlokWyzej())) {
                    if (this.jesliTrue == null) return;
                    this.jesliTrue.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliTrue);
                }
                else {
                    if (this.jesliFalse == null) return;
                    this.jesliFalse.setBlokWyzej(this.getBlokWyzej());
                    odpluskwiacz.oflaguj(this.jesliFalse);
                }
                break;
        }
    }


}

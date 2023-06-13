public abstract class Instrukcja {
    private InstrukcjaBlokowa blokWyzej;
    private Macchiato program;
    public Instrukcja(InstrukcjaBlokowa blokWyzej, Macchiato program) {
        this.blokWyzej = blokWyzej;
        this.program = program;

    }

    public abstract String getImie();
    public InstrukcjaBlokowa getBlokWyzej() {
        return this.blokWyzej;
    }
    public void setBlokWyzej(InstrukcjaBlokowa blokowa) {
        this.blokWyzej = blokowa;
    }
    public void setProgram(Macchiato program) {this.program = program;}
    public Macchiato getProgram() {return this.program;}

    public abstract void wykonaj();
}

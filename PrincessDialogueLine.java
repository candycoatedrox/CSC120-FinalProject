public class PrincessDialogueLine extends DialogueLine {

    private boolean isMoundEcho;

    // --- CONSTRUCTORS ---

    public PrincessDialogueLine(boolean isMoundEcho, String line, boolean isInterrupted) {
        super(line, isInterrupted);
        this.isMoundEcho = isMoundEcho;
    }

    public PrincessDialogueLine(boolean isMoundEcho, String line) {
        this(isMoundEcho, line, false);
    }

    public PrincessDialogueLine(String line, boolean isInterrupted) {
        this(false, line, isInterrupted);
    }

    public PrincessDialogueLine(String line) {
        this(false, line, false);
    }

    // --- UTILITY ---

    @Override
    public String toString() {
        String s = "";

        if (this.isMoundEcho) s += "(";
        s += '"' + this.line + '"';
        if (this.isMoundEcho) s += ")";

        return s;
    }
    
}

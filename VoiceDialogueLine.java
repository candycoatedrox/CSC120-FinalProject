public class VoiceDialogueLine extends DialogueLine {
    
    private Voice speaker;

    // --- CONSTRUCTORS ---

    public VoiceDialogueLine(Voice speaker, String line, boolean isInterrupted) {
        super(line, isInterrupted);
        this.speaker = speaker;
    }

    public VoiceDialogueLine(Voice speaker, String line) {
        this(speaker, line, false);
    }

    public VoiceDialogueLine(String line, boolean isInterrupted) {
        this(Voice.NARRATOR, line, isInterrupted);
    }

    public VoiceDialogueLine(String line) {
        this(Voice.NARRATOR, line, false);
    }

    // --- UTILITY ---

    @Override
    public String toString() {
        return this.speaker.getDialogueTag() + ": " + this.line;
    }

}

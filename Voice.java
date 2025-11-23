public enum Voice {
    NARRATOR("The Narrator"),
    PRINCESS("The Princess"), // exclusively used in Spectre, Wraith, Princess and the Dragon

    HERO("Voice of the Hero"),
    BROKEN("Voice of the Broken"),
    CHEATED("Voice of the Cheated"),
    COLD("Voice of the Cold"),
    CONTRARIAN("Voice of the Contrarian"),
    HUNTED("Voice of the Hunted"),
    OPPORTUNIST("Voice of the Opportunist"),
    PARANOID("Voice of the Paranoid"),
    SKEPTIC("Voice of the Skeptic"),
    SMITTEN("Voice of the Smitten"),
    STUBBORN("Voice of the Stubborn");

    private String dialogueTag;

    // --- CONSTRUCTOR ---

    private Voice(String tag) {
        this.dialogueTag = tag;
    }

    // --- ACCESSORS ---
    
    public String getDialogueTag() {
        return this.dialogueTag;
    }
}

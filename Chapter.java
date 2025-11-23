public enum Chapter {
    CH1(1, "The Hero and the Princess"),

    ADVERSARY(2, "The Adversary"),
    TOWER(2, "The Tower"),
    SPECTRE(2, "The Spectre"),
    NIGHTMARE(2, "The Nightmare"),
    RAZOR(2, "The Razor"),
    BEAST(2, "The Beast"),
    WITCH(2, "The Witch"),
    STRANGER(2, "The Stranger"),
    PRISONER(2, "The Prisoner"),
    DAMSEL(2, "The Damsel"),

    NEEDLE(3, "The Eye of the Needle"),
    FURY(3, "The Fury"),
    APOTHEOSIS(3, "The Apotheosis"),
    DRAGON(3, "The Princess and the Dragon", true),
    WRAITH(3, "The Wraith"),
    CLARITY(3, "The Moment of Clarity"),
    ARMSRACE(3, "The Arms Race"),
    NOWAYOUT(3, "No Way Out"),
    DEN(3, "The Den"),
    WILD(3, "The Wild"),
    THORN(3, "The Thorn"),
    CAGE(3, "The Cage"),
    GREY(3, "The Grey"),
    HAPPY(3, "Epilogue", "Happily Ever After"),
    
    MUTUALLYASSURED(4, "Mutually Assured Destruction"),
    EMPTYCUP(4, "The Empty Cup"),

    SPACESBETWEEN,
    ENDOFEVERYTHING;

    private boolean specialTitle;
    private int number;
    private String prefix;
    private String title;

    // --- CONSTRUCTORS ---

    private Chapter(int number, String title, boolean specialTitle) {
        this.specialTitle = specialTitle;
        this.number = number;
        this.title = title;

        this.prefix = "Chapter ";
        switch(number) {
            case 1:
                this.prefix += "I";
                break;
            case 2:
                this.prefix += "II";
                break;
            case 3:
                this.prefix += "III";
                break;
            case 4:
                this.prefix += "IV";
        }
    }

    private Chapter(int number, String title) {
        this.specialTitle = false;
        this.number = number;
        this.title = title;

        this.prefix = "Chapter ";
        switch(number) {
            case 1:
                this.prefix += "I";
                break;
            case 2:
                this.prefix += "II";
                break;
            case 3:
                this.prefix += "III";
                break;
            case 4:
                this.prefix += "IV";
        }
    }

    private Chapter(int realNumber, String specialPrefix, String title, boolean specialTitle) {
        this.specialTitle = specialTitle;
        this.number = realNumber;
        this.prefix = specialPrefix;
        this.title = title;
    }

    private Chapter(int realNumber, String specialPrefix, String title) {
        this(realNumber, specialPrefix, title, false);
    }

    private Chapter() {
        this.specialTitle = true;
        this.number = 0;
    }

    // --- ACCESSORS & CHECKS ---

    public boolean hasSpecialTitle() {
        return this.specialTitle;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getTitle() {
        /* if (this != CLARITY) {
            return this.prefix + ": " + this.title;
        } else {
            return this.title;
        } */

        return this.title;
    }

    public String getFullTitle() {
        switch (this) {
            case CLARITY: return "Chapter ??? - " + this.title;
            case SPACESBETWEEN: return "The Spaces Between";
            case ENDOFEVERYTHING: return "The End of Everything";
            default: return this.prefix + " - " + this.title;
        }
    }

    public boolean hasContentWarnings() {
        return this.number != 0 && this.number != 1;
    }

    public String getContentWarnings(ChapterEnding prevEnding) {
        if (this == GREY && prevEnding == ChapterEnding.LADYKILLER) {
            return "self-immolation; burning to death; body horror"; // burned warnings
        } else if (this == GREY && prevEnding == ChapterEnding.COLDLYRATIONAL) {
            return "description of a bloated corpse; drowning; body horror"; // drowned warnings
        } else {
            return this.getContentWarnings();
        }
    }

    public String getContentWarnings() {
        switch (this) {
            case ADVERSARY: return "mutilation; disembowelment";
            case TOWER: return "loss of bodily autonomy; forced self-mutilation; forced suicide; gore";
            case SPECTRE: return "exposed organs";
            case NIGHTMARE: return "loss of bodily autonomy; starvation";
            case RAZOR: return "dismemberment; self-dismemberment; unreality; suicide; excessive gore";
            case BEAST: return "being eaten alive; disembowelment; suffocation; gore";
            case WITCH: return "gore; strangulation; being crushed to death";
            case STRANGER: return "derealization; unreality; Cronenberg-esque body horror";
            case PRISONER: return "self-decapitation; gore";
            case DAMSEL: return "derealization; forced suicide; gore";

            case NEEDLE: return "gore";
            case FURY: return "self-degloving; flaying; disembowelment; gore; extreme body-horror; existential horror; physical and psychological torture";
            case APOTHEOSIS: return "psychological torture; eye-puncturing";
            case DRAGON: return "gore; dismemberment";
            case WRAITH: return "body horror; loss of bodily autonomy; jumpscare";
            case CLARITY: return "derealization; unreality; loss of control; memory loss";
            case ARMSRACE: return "dismemberment; self-dismemberment; unreality; suicide; excessive gore";
            case NOWAYOUT: return "dismemberment; self-dismemberment; unreality; suicide; excessive gore";
            case DEN: return "cannibalism; extreme gore; extreme violence; starvation";
            case WILD: return "gore; body horror";
            case THORN: return "self-mutilation";
            case CAGE: return "Existentialism; decapitation; gore";
            case GREY: return "Self-immolation; description of a bloated corpse; drowning; burning to death; body horror"; // combined warnings
            case HAPPY: return "derealization; existentialism; end-of-relationship; some people have experienced triggers related to emotional domestic abuse while playing this chapter";

            case MUTUALLYASSURED: return "dismemberment; self-dismemberment; unreality; suicide; excessive gore";
            case EMPTYCUP: return "dismemberment; self-dismemberment; unreality; suicide; excessive gore";
        }

        return "";
    }
}

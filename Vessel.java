public enum Vessel {
    ADVERSARY(Chapter.ADVERSARY),
    TOWER(Chapter.TOWER),
    SPECTRE(Chapter.SPECTRE),
    NIGHTMARE(Chapter.NIGHTMARE),
    BEAST(Chapter.BEAST),
    WITCH(Chapter.WITCH),
    STRANGER(Chapter.STRANGER),
    PRISONERHEAD(Chapter.PRISONER, "The Prisoner's Head"),
    PRISONER(Chapter.PRISONER),
    DAMSEL(Chapter.DAMSEL),
    DECONDAMSEL(Chapter.DAMSEL, "The Deconstructed Damsel"),

    NEEDLE(Chapter.NEEDLE),
    FURY(Chapter.FURY),
    REWOUNDFURY(Chapter.FURY, "The Rewound Fury"),
    APOTHEOSIS(Chapter.APOTHEOSIS),
    PATD(Chapter.DRAGON, "The Princess"),
    STENCILPATD(Chapter.DRAGON, "The Stenciled Princess"),
    WRAITH(Chapter.WRAITH),
    CLARITY(Chapter.CLARITY),
    RAZORFULL(Chapter.RAZOR, "The Razor (Full)"),
    RAZORHEART(Chapter.RAZOR, "The Razor's Heart"),
    DEN(Chapter.DEN),
    NETWORKWILD(Chapter.WILD, "The Networked Wild"),
    WOUNDEDWILD(Chapter.WILD, "The Wounded Wild"),
    THORN(Chapter.THORN),
    WATCHFULCAGE(Chapter.CAGE),
    OPENCAGE(Chapter.CAGE),
    BURNEDGREY(Chapter.GREY, "The Burned Grey"),
    DROWNEDGREY(Chapter.GREY, "The Drowned Grey"),
    HAPPY(Chapter.HAPPY),
    HAPPYDRY(Chapter.HAPPY);

    private Chapter fromChapter;
    private String name;

    // --- CONSTRUCTORS ---

    private Vessel(Chapter c, String name) {
        this.fromChapter = c;
        this.name = name;
    }

    private Vessel(Chapter c) {
        this(c, c.getTitle());
    }

    // --- ACCESSORS ---

    public String getName() {
        return this.name;
    }
}

public enum ChapterEnding {
    /*
    ChapterEnding names are pulled from, in order:
      1. achievement names;
      2. if there is no achievement for the ending, the name given by the unofficial wiki;
      3. if the wiki does not make a distinction between endings, whatever name I think is appropriate
    */

    // The Hero and the Princess
    TOADVERSARY(Chapter.ADVERSARY, Voice.STUBBORN),
    TOTOWER(Chapter.TOWER, Voice.BROKEN),
    TOTOWERUNHARMED(Chapter.TOWER, Voice.BROKEN),
    TOTOWERPATHETIC(Chapter.TOWER, Voice.BROKEN),
    GOODENDING,
    TOSPECTRE(Chapter.SPECTRE, Voice.COLD),
    TONIGHTMARE(Chapter.NIGHTMARE, Voice.PARANOID),
    TONIGHTMAREFLED(Chapter.NIGHTMARE, Voice.PARANOID),
    TORAZOR(Chapter.RAZOR, Voice.CHEATED),
    TORAZORMUTUAL(Chapter.RAZOR, Voice.CHEATED),
    TORAZORREVIVAL(Chapter.RAZOR, Voice.CHEATED),
    TOBEAST(Chapter.BEAST, Voice.HUNTED),
    TOWITCH(Chapter.WITCH, Voice.OPPORTUNIST),
    TOWITCHMUTUAL(Chapter.WITCH, Voice.OPPORTUNIST),
    TOWITCHBETRAYAL(Chapter.WITCH, Voice.OPPORTUNIST),
    TOWITCHLOCKED(Chapter.WITCH, Voice.OPPORTUNIST),
    TOSTRANGER(Chapter.STRANGER, Voice.CONTRARIAN),
    TOPRISONER(Chapter.PRISONER, Voice.SKEPTIC),
    TODAMSEL(Chapter.DAMSEL, Voice.SMITTEN),
    
    // The Adversary
    THATWHICHCANNOTDIE(Vessel.ADVERSARY),
    STRIKEMEDOWN(Chapter.FURY, Voice.COLD),
    HEARNOBELL(Chapter.FURY, Voice.CONTRARIAN),
    DEADISDEAD(Chapter.FURY, Voice.BROKEN),
    THREADINGTHROUGH(Chapter.NEEDLE, Voice.HUNTED),
    FREEINGSOMEONE(Chapter.NEEDLE, Voice.SKEPTIC),

    // The Tower
    OBEDIENTSERVANT(Vessel.TOWER),
    GODKILLER(Chapter.FURY, Voice.STUBBORN),
    APOBLADE(Chapter.APOTHEOSIS, Voice.CONTRARIAN),
    APOUNARMED(Chapter.APOTHEOSIS, Voice.PARANOID),

    // The Spectre
    HITCHHIKER(Vessel.SPECTRE),
    HEARTRIPPERLEAVE(Chapter.WRAITH, Voice.PARANOID),
    HEARTRIPPER(Chapter.WRAITH, Voice.CHEATED),
    EXORCIST(Chapter.DRAGON, Voice.OPPORTUNIST),
    
    // The Nightmare
    WORLDOFTERROR(Vessel.NIGHTMARE),
    HOUSEOFNOLEAVE(Chapter.WRAITH, Voice.COLD),
    TERMINALVELOCITY(Chapter.WRAITH, Voice.OPPORTUNIST),
    MONOLITHOFFEAR(Chapter.CLARITY),

    // The Razor
    TOARMSRACEFIGHT(Chapter.ARMSRACE, Voice.STUBBORN),
    TOARMSRACEBORED(Chapter.ARMSRACE, Voice.BROKEN),
    TOARMSRACELEFT(Chapter.ARMSRACE, Voice.PARANOID),
    TONOWAYOUTBORED(Chapter.NOWAYOUT, Voice.BROKEN),
    TONOWAYOUTLEFT(Chapter.NOWAYOUT, Voice.PARANOID),

    // The Arms Race
    TOMUTUALLYASSURED(Chapter.MUTUALLYASSURED),

    // Mutually Assured Destruction
    MUTUALLYASSURED(Vessel.RAZORFULL),

    // No Way Out
    TOEMPTYCUP(Chapter.EMPTYCUP),

    // The Empty Cup
    EMPTYCUP(Vessel.RAZORHEART),

    // The Beast
    DISSOLVINGWILL(Vessel.BEAST),
    DISSOLVINGWILLACCIDENT(Vessel.BEAST),
    FIGHT(Chapter.DEN, Voice.STUBBORN),
    FLIGHT(Chapter.DEN, Voice.SKEPTIC),
    OPOSSUM(Chapter.WILD, Voice.CONTRARIAN),
    AHAB(Chapter.WILD, Voice.OPPORTUNIST),
    SLAYYOURSELF(Chapter.WILD, Voice.STUBBORN),
    DISSOLVED(Chapter.WILD, Voice.BROKEN),

    // The Witch
    SCORPION(Vessel.WITCH),
    FROG(Vessel.WITCH),
    FROGLOCKED(Vessel.WITCH),
    KNIVESOUTMASKSOFF(Chapter.WILD, Voice.STUBBORN),
    KNIVESOUTMASKSOFFGIVEUP(Chapter.WILD, Voice.CHEATED),
    PLAYINGITSAFE(Chapter.WILD, Voice.PARANOID),
    PASTLIFEGAMBITSPECIAL(Chapter.THORN, Voice.SMITTEN),
    PASTLIFEGAMBIT(Chapter.THORN, Voice.CHEATED),
    
    // The Stranger
    ILLUSIONOFCHOICE(Vessel.STRANGER),

    // The Prisoner
    TALKINGHEADS(Vessel.PRISONERHEAD),
    PRISONEROFMIND(Vessel.PRISONER),
    COLDLYRATIONAL(Chapter.GREY, Voice.COLD),
    RESTLESSFORCED(Chapter.CAGE, Voice.CHEATED),
    RESTLESSSELF(Chapter.CAGE, Voice.PARANOID),
    RESTLESSGIVEIN(Chapter.CAGE, Voice.BROKEN),

    // The Damsel
    ROMANTICHAZE(Vessel.DAMSEL),
    ANDTHEYLIVEDHAPPILY(Vessel.DECONDAMSEL),
    LADYKILLER(Chapter.GREY, Voice.COLD),
    CONTENTSOFOURHEARTDECON(Chapter.HAPPY, Voice.SKEPTIC),
    CONTENTSOFOURHEARTUPSTAIRS(Chapter.HAPPY, Voice.OPPORTUNIST),

    // Misc.
    NEWCYCLE(Chapter.CH1),
    ABORTED,
    OBLIVION,
    DEMOENDING;

    private boolean isFinal;

    private Chapter nextChapter;
    private Voice newVoice;

    private Vessel vessel;

    // --- CONSTRUCTORS ---

    /**
     * Standard constructor
     * @param nextChapter the Chapter this ending leads to
     * @param newVoice the Voice gained at the start of the next Chapter
     */
    private ChapterEnding(Chapter nextChapter, Voice newVoice) {
        this.isFinal = false;
        this.nextChapter = nextChapter;
        this.newVoice = newVoice;
    }

    /**
     * Constructor for gaining all Voices at the start of the next Chapter or the beginning of a Cycle
     * @param nextChapter the Chapter this ending leads to
     */
    private ChapterEnding(Chapter nextChapter) {
        this(nextChapter, null);
    }

    /**
     * Constructor for special Chapters (aborting a Chapter or game endings)
     */
    private ChapterEnding() {
        this.isFinal = true;
        this.nextChapter = Chapter.CH1;
    }

    /**
     * Constructor for endings where a Vessel has been claimed
     * @param v the Vessel claimed in this ending
     */
    private ChapterEnding(Vessel v) {
        this.isFinal = true;
        this.nextChapter = Chapter.SPACESBETWEEN;

        this.vessel = v;
    }

    // --- ACCESSORS ---

    /**
     * Accessor for isFinal
     * @return whether this ending represents the end of a Cycle or leads to a new Chapter
     */
    public boolean isFinal() {
        return this.isFinal;
    }

    /**
     * Accessor for nextChapter
     * @return returns the Chapter that this ending leads to
     */
    public Chapter getNextChapter() {
        return this.nextChapter;
    }

    /**
     * Accessor for newVoice
     * @return the Voice gained at the start of the next Chapter
     */
    public Voice getNewVoice() {
        return this.newVoice;
    }

    /**
     * Accessor for vessel
     * @return the Vessel claimed in this ending
     */
    public Vessel getVessel() {
        return this.vessel;
    }
}

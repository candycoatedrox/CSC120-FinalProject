import java.util.ArrayList;

public class EndOfEverything extends Cycle {

    private Vessel[] vessels;
    private boolean strangerHeart;

    // --- CONSTRUCTOR ---

    public EndOfEverything(GameManager manager, ArrayList<Vessel> vessels, IOHandler parser) {
        super(manager, parser);
        this.clearVoices();

        if (vessels.size() != 5) {
            throw new IllegalArgumentException("Incorrect amount of vessels");
        }

        this.vessels = vessels.toArray(new Vessel[5]);
        this.strangerHeart = this.vessels[0] == Vessel.STRANGER;
    }

    // --- CYCLE MANAGEMENT ---

    @Override
    public ChapterEnding runCycle() {
        // PLACEHOLDER
        return this.openingConversation();
    }

    // --- SCENES ---

    private ChapterEnding openingConversation() {
        // PLACEHOLDER
        return this.debate();
    }

    private ChapterEnding debate() {
        // PLACEHOLDER
        return this.heartCabin();
    }

    private ChapterEnding heartCabin() {
        // PLACEHOLDER
        return ChapterEnding.PATHINTHEWOODS;
    }

    private ChapterEnding heartCabinStrange() {
        // PLACEHOLDER
        return ChapterEnding.PATHINTHEWOODS;
    }
    
    // --- COMMAND OVERRIDES ---

    @Override
    protected String go(String argument, boolean secondPrompt) {
        switch (argument) {
            case "forward":
            case "forwards":
            case "f":
                switch (this.currentLocation.getForward()) {
                    case STAIRS: return "GoStairs";
                    case BASEMENT: return "GoBasement";
                    default: return "GoFail";
                }
            
            case "back":
            case "backward":
            case "backwards":
            case "b":
                switch (this.currentLocation.getBackward()) {
                    case HILL: return "GoHill";
                    case CABIN: return "GoCabin";
                    case STAIRS: return "GoStairs";
                    default: return "GoFail";
                }

            case "inside":
            case "in":
            case "i":
                return (this.currentLocation.canGoInside()) ? this.go("forward") : "GoFail";

            case "outside":
            case "out":
            case "o":
                return (this.currentLocation.canGoOutside()) ? this.go("back") : "GoFail";

            case "down":
            case "d":
                return (this.currentLocation.canGoDown()) ? this.go("forward") : "GoFail";

            case "up":
            case "u":
                return (this.currentLocation.canGoUp()) ? this.go("back") : "GoFail";

            case "":
                if (secondPrompt) {
                    manager.showCommandHelp("go");
                    return "Fail";
                } else {
                    parser.printDialogueLine("Where do you want to go?", true);
                    return this.go(parser.getInput(), true);
                }

            default:
                manager.showCommandHelp("go");
                return "Fail";
        }
    }

    @Override
    public String enter(String argument) {
        switch (argument) {
            case "": return this.go("inside");

            case "cabin": return "EnterFail";

            case "basement":
                switch (this.currentLocation) {
                    case CABIN:
                    case STAIRS: return this.go("forward");
                    default: return "EnterFail";
                }

            default:
                manager.showCommandHelp("enter");
                return "Fail";
        }
    }

    public String leave(String argument) {
        switch (argument) {
            case "": this.go("back");

            case "woods":
            case "path": return "LeaveFail";

            case "cabin":
                return (this.currentLocation == GameLocation.CABIN) ? this.go("back") : "LeaveFail";

            case "basement":
                switch (this.currentLocation) {
                    case STAIRS:
                    case BASEMENT: return this.go("back");
                    default: return "LeaveFail";
                }

            default:
                manager.showCommandHelp("leave");
                return "Fail";
        }
    }

    @Override
    public String approach(String argument) {
        switch (argument) {
            case "the mirror":
            case "mirror":
                return "ApproachFail";
            
            default:
                return "ApproachInvalidFail";
        }
    }

    @Override
    

    protected String slay(String argument, boolean secondPrompt) {
        switch (argument) {
            case "the princess":
            case "princess":
                if (!this.withPrincess) {
                    return "SlayNoPrincessFail";
                } else if (!this.hasBlade) {
                    return "SlayPrincessNoBladeFail";
                } else if (!this.canSlayPrincess) {
                    return "SlayPrincessFail";
                } else {
                    return "SlayPrincess";
                }

            case "yourself":
            case "self":
            case "you":
            case "myself":
            case "me":
            case "ourself":
            case "ourselves":
            case "us":
                if (!this.hasBlade) {
                    return "SlaySelfNoBladeFail";
                } else {
                    return "SlaySelfFail";
                }
            
            case "":
                if (secondPrompt) {
                    manager.showCommandHelp("slay");
                    return "Fail";
                } else {
                    parser.printDialogueLine("Who do you want to slay?", true);
                    return this.slay(parser.getInput(), true);
                }

            default:
                manager.showCommandHelp("slay");
                return "Fail";
        }
    }

    @Override
    protected void giveDefaultFailResponse(String outcome) {
        switch (this.currentLocation) {
            case CABIN:
            case STAIRS:
            case BASEMENT: break;
            
            default: super.giveDefaultFailResponse(outcome);
        }

        // responses here depend on whether you have normal heart (Hero) or stranger heart (Hero + Contrarian)
        switch (outcome) {
            case "cGoFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cEnterFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;
                
            case "cLeaveFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cApproachFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;
            
            case "cApproachInvalidFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;
                

            case "cSlayNoPrincessFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cSlayPrincessNoBladeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cSlayPrincessFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cSlaySelfNoBladeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cSlaySelfFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "We can't just end it here. We have to see this through."));
                }
                
                break;
                

            case "cTakeHasBladeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }

                break;
            
            case "cTakeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cDropNoBladeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cDropFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;

            case "cThrowNoBladeFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }
                
                break;
                
            case "cThrowFail":
                if (this.strangerHeart) {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.CONTRARIAN, "XXXXXXXX"));
                } else {
                    parser.printDialogueLine(new VoiceDialogueLine(Voice.HERO, "XXXXXXXX"));
                }

                break;
        }
    }

}

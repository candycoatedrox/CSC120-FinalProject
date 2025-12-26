import java.io.File;
import java.util.ArrayList;

public class ScriptScanner extends Script {

    private ArrayList<ScriptError> errorsFound;
    private ArrayList<ScriptIssue> issuesFound;
    
    /**
     * Constructor
     * @param manager the GameManager to link this Script to
     * @param parser the IOHandler to link this Script to
     * @param source the file containing the text of this Script
     */
    public ScriptScanner(GameManager manager, IOHandler parser, File source) {
        super(manager, parser, source);

        this.errorsFound = new ArrayList<>();
        this.issuesFound = new ArrayList<>();

        for (int i = 0; i < this.nLines(); i++) {
            this.scanLine(i);
        }
    }

    /**
     * Constructor
     * @param manager the GameManager to link this Script to
     * @param parser the IOHandler to link this Script to
     * @param fileDirectory the file path of the file containing the text of this Script
     */
    public ScriptScanner(GameManager manager, IOHandler parser, String fileDirectory) {
        this(manager, parser, getFromDirectory(fileDirectory));
    }

    /**
     * Scans a single line of this script for errors and potential issues
     * @param lineIndex the index of the scanned line
     */
    private void scanLine(int lineIndex) {
        String lineContent = this.getLine(lineIndex).trim();
        String[] split = lineContent.split(" ", 2);

        String prefix = split[0];
        String argument;
        String[] args;
        try {
            argument = split[1];
            args = argument.split(" ", 2);
        } catch (IndexOutOfBoundsException e) {
            argument = "";
            args = new String[0];
        }

        int nArgs = args.length;
        ArrayList<String> extraInfo = new ArrayList<>();

        switch (prefix) {
            case "//":
            case "label":
            case "":
                break;

            case "linebreak":
                try {
                    Integer.parseInt(argument);
                } catch (NumberFormatException e) {
                    if (!argument.equals("")) errorsFound.add(new ScriptError(lineIndex, 1));
                }

                break;

            case "jumpto":
                try {
                    int jumpTarget = Integer.parseInt(argument);
                    if (jumpTarget >= this.nLines()) errorsFound.add(new ScriptError(lineIndex, 2, 0));
                } catch (NumberFormatException e) {
                    if (!argument.equals("NOJUMP") && !this.hasLabel(argument)) errorsFound.add(new ScriptError(lineIndex, 2, 1, argument));
                }

                break;

            case "firstswitch":
            case "claim":
                if (args.length == 0) {
                    errorsFound.add(new ScriptError(lineIndex, 3, 0));
                } else {
                    if (!this.hasLabel(argument + "FirstVessel")) extraInfo.add(argument + "FirstVessel");
                    if (!this.hasLabel(argument + "NotFirstVessel")) extraInfo.add(argument + "NotFirstVessel");
                    if (!extraInfo.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 3, 1, extraInfo));
                }

                break;

            case "bladeswitch":
                switch (args.length) {
                    case 0:
                        errorsFound.add(new ScriptError(lineIndex, 3, 2));
                        break;

                    case 1:
                        if (!this.hasLabel(argument + "Blade")) extraInfo.add(argument + "Blade");
                        if (!this.hasLabel(argument + "NoBlade")) extraInfo.add(argument + "NoBlade");
                        break;
                    
                    default:
                        if (!this.hasLabel(args[0] + "Blade" + args[1])) extraInfo.add(args[0] + "Blade" + args[1]);
                        if (!this.hasLabel(args[0] + "NoBlade" + args[1])) extraInfo.add(args[0] + "NoBlade" + args[1]);
                }

                
                if (!extraInfo.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 3, 3, extraInfo));
                break;
            
            case "switchjump":
                if (nArgs == 0) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 0));
                    break;
                } else if (nArgs == 1) {
                    issuesFound.add(new ScriptIssue(lineIndex, 0, 0));
                    if (!this.hasLabel(argument)) extraInfo.add(argument);
                } else {
                    if (nArgs > 2) issuesFound.add(new ScriptIssue(lineIndex, 0, 1));
                    if (!this.hasLabel(args[0])) extraInfo.add(args[0]);
                    if (!this.hasLabel(args[1])) extraInfo.add(args[1]);
                }

                if (!extraInfo.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 4, 1, extraInfo));
                break;
            
            case "numswitchjump":
                if (argument.isEmpty()) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 2));
                } else {
                    for (String label : args) {
                        if (!this.hasLabel(label)) extraInfo.add(label);
                    }
                }

                if (!extraInfo.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 4, 3, extraInfo));
                break;
            
            case "stringswitchjump":
                if (nArgs == 0) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 4));
                    break;
                } else if (nArgs == 1) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 5));
                    break;
                } else if (nArgs % 2 != 0) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 6));
                }

                for (int i = 1; i < nArgs; i += 2) {
                    if (!this.hasLabel(args[i])) extraInfo.add(args[i]);
                }

                if (!extraInfo.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 4, 7, extraInfo));
                break;

            case "numautojump":
                if (argument.isEmpty()) {
                    errorsFound.add(new ScriptError(lineIndex, 4, 8));
                } else {
                    boolean labelExists = false;
                    for (int i = 0; i < 10; i++) {
                        if (this.hasLabel(argument + i)) {
                            labelExists = true;
                            break;
                        }
                    }
                    if (!labelExists) issuesFound.add(new ScriptIssue(lineIndex, 0, 2));
                }
                
                break;

            case "stringautojump":
                if (argument.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 4, 9));
                break;

            case "nowplaying":
                if (argument.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 5));
                break;

            case "quietcreep":
                if (!argument.isEmpty()) issuesFound.add(new ScriptIssue(lineIndex, 1, 0));
                break;
            case "claimfold":
                if (!argument.isEmpty()) issuesFound.add(new ScriptIssue(lineIndex, 1, 1));
                break;

            default:
                if (!isValidCharacter(prefix)) errorsFound.add(new ScriptError(lineIndex, 0, 0));

                String[] modSplit = argument.split(" /// ");
                if (modSplit.length > 2) {
                    errorsFound.add(new ScriptError(lineIndex, 6, 0));
                } else if (modSplit.length == 2) {
                    String modifiers = modSplit[1];
                    String[] mods = modifiers.split(" ");
                    String[] checkIDs;
                    Voice speaker = Voice.getVoice(prefix);
                    Voice currentVoice;
                    
                    ArrayList<String> invalidMods = new ArrayList<>();
                    boolean checkVoicePresent = false;
                    ArrayList<String> invalidPosArgs = new ArrayList<>();
                    ArrayList<Voice> posVoiceChecks = new ArrayList<>();
                    boolean checkNoVoicePresent = false;
                    ArrayList<String> invalidNegArgs = new ArrayList<>();
                    ArrayList<Voice> negVoiceChecks = new ArrayList<>();
                    boolean interruptPresent = false;

                    if (mods.length == 0) {
                        errorsFound.add(new ScriptError(lineIndex, 6, 1));
                    } else {
                        for (String m : mods) {
                            if (m.startsWith("interrupt")) {
                                if (!m.equals("interrupt")) {
                                    errorsFound.add(new ScriptError(lineIndex, 6, 10));
                                }

                                if (interruptPresent && !extraInfo.contains("interrupt")) extraInfo.add("interrupt");
                                interruptPresent = true;
                            } else if (m.startsWith("checkvoice")) {
                                checkIDs = m.split("-");

                                if (checkVoicePresent && !extraInfo.contains("checkvoice")) extraInfo.add("checkvoice");
                                checkVoicePresent = true;
                                
                                if (checkIDs.length > 1) {
                                    for (int i = 1; i < checkIDs.length; i++) {
                                        currentVoice = Voice.getVoice(checkIDs[i]);
                                        if (currentVoice == null) {
                                            invalidPosArgs.add(checkIDs[i]);
                                        } else {
                                            posVoiceChecks.add(currentVoice);
                                        }
                                    }
                                } else {
                                    if (m.equals("checkvoice")) {
                                        if (speaker == null) {
                                            errorsFound.add(new ScriptError(lineIndex, 6, 4));
                                        } else {
                                            posVoiceChecks.add(speaker);
                                        }
                                    } else {
                                        errorsFound.add(new ScriptError(lineIndex, 6, 3));
                                    }
                                }
                            } else if (m.startsWith("checknovoice")) {
                                checkIDs = m.split("-");

                                if (checkNoVoicePresent && !extraInfo.contains("checknovoice")) extraInfo.add("checknovoice");
                                checkNoVoicePresent = true;
                                
                                if (checkIDs.length > 1) {
                                    for (int i = 1; i < checkIDs.length; i++) {
                                        currentVoice = Voice.getVoice(checkIDs[i]);
                                        if (currentVoice == null) {
                                            invalidNegArgs.add(checkIDs[i]);
                                        } else {
                                            if (currentVoice == speaker) errorsFound.add(new ScriptError(lineIndex, 6, 7));
                                            negVoiceChecks.add(currentVoice);
                                        }
                                    }
                                } else {
                                    errorsFound.add(new ScriptError(lineIndex, 6, 6));
                                }
                            } else {
                                invalidMods.add(m);
                            }
                        }
                    }

                    if (!extraInfo.isEmpty()) issuesFound.add(new ScriptIssue(lineIndex, 2, 0, extraInfo));
                    if (!invalidMods.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 6, 2, invalidMods));
                    if (!invalidPosArgs.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 6, 5, invalidPosArgs));
                    if (!invalidNegArgs.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 6, 8, invalidNegArgs));

                    ArrayList<String> duplicatePosVoices = new ArrayList<>();
                    ArrayList<String> duplicateNegVoices = new ArrayList<>();
                    ArrayList<Voice> checkedVoices = new ArrayList<>();
                    for (Voice v : posVoiceChecks) {
                        if (checkedVoices.contains(v)) {
                            duplicatePosVoices.add(v.getDialogueTag());
                        } else {
                            checkedVoices.add(v);
                        }
                    }
                    
                    ArrayList<String> impossibleChecks = new ArrayList<>();
                    checkedVoices.clear();
                    for (Voice v : negVoiceChecks) {
                        if (checkedVoices.contains(v)) {
                            duplicateNegVoices.add(v.getDialogueTag());
                        } else {
                            if (posVoiceChecks.contains(v)) impossibleChecks.add(v.getDialogueTag());
                            checkedVoices.add(v);
                        }
                    }

                    if (!duplicatePosVoices.isEmpty()) issuesFound.add(new ScriptIssue(lineIndex, 2, 1, duplicatePosVoices));
                    if (!duplicateNegVoices.isEmpty()) issuesFound.add(new ScriptIssue(lineIndex, 2, 1, duplicateNegVoices));
                    if (!impossibleChecks.isEmpty()) errorsFound.add(new ScriptError(lineIndex, 6, 9, impossibleChecks));
                }
        }
    }

    /**
     * Prints out a report of all errors and potential issues in the file
     */
    public void printReport() {
        System.out.println();
        IOHandler.wrapPrintln("--- SCAN RESULTS: " + source.getName() + " ---");
        System.out.println();

        if (errorsFound.isEmpty()) {
            if (issuesFound.isEmpty()) {
                IOHandler.wrapPrintln("No errors or potential issues found! " + source.getName() + " is perfecttly functional!");
                System.out.println();
                return;
            } else {
                IOHandler.wrapPrintln("No errors found!");
            }
        } else {
            System.out.println("- " + errorsFound.size() + " ERRORS FOUND -");
            for (ScriptError error : errorsFound) {
                IOHandler.wrapPrintln(error.toString());
            }
        }

        System.out.println();
        if (issuesFound.isEmpty()) {
            IOHandler.wrapPrintln("No potential issues found!");
        } else {
            IOHandler.wrapPrintln("- " + issuesFound.size() + " POTENTIAL ISSUES FOUND -");
            for (ScriptIssue issue : issuesFound) {
                IOHandler.wrapPrintln(issue.toString());
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        IOHandler parser = new IOHandler(manager);
        ScriptScanner scanner = new ScriptScanner(manager, parser, "Routes/JOINT/Grey/GreyShared");

        scanner.printReport();
    }

    // note to self: there is EITHER something weird and fucked up going on with the check for numswitchjump's labels being valid OR the way numswitchjump actually stores them, somehow

}

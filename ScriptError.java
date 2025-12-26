import java.util.ArrayList;

public class ScriptError extends ScriptNote {
    
    /* --- TYPE KEY ---
        - 0 = invalid line
        - 1 = invalid linebreak (non-int argument)
        - 2 = invalid jumpto
            - 0 = line index too large
            - 1 = given label does not exist
        - 3 = invalid firstswitch / bladeswitch
            - 0 = firstswitch (no prefix given)
            - 1 = firstswitch (one or both labels do not exist, check extra)
            - 2 = bladeswitch (no prefix given)
            - 3 = bladeswitch (one or both labels do not exist, check extra)
        - 4 = invalid condition switch jump
            - 0 = switchjump (no labels given)
            - 1 = switchjump (one or both labels do not exist, check extra)
            - 2 = numswitchjump (no labels given)
            - 3 = numswitchjump (one or more labels do not exist, check extra)
            - 4 = stringswitchjump (no arguments given)
            - 5 = stringswitchjump (one argument given)
            - 6 = stringswitchjump (odd number of arguments)
            - 7 = stringswitchjump (one or more labels do not exist, check extra)
            - 8 = numautojump (no prefix given)
            - 9 = stringautojump (no suffix given)
        - 5 = invalid nowplaying (no argument given)
        - 6 = invalid dialogue line modifiers
            - 0 = multiple modifier dividers (///)
            - 1 = modifier divider but no modifiers
            - 2 = completely invalid modifier
            - 3 = invalid syntax for checkvoice
            - 4 = checkvoice used without argument for truth / princess
            - 5 = one or more invalid arguments for checkvoice, check extra
            - 6 = invalid syntax for checknovoice
            - 7 = checknovoice used with id for speaker (impossible line)
            - 8 = one or more invalid arguments for checknovoice, check extra
            - 9 = same argument used for both checkvoice and checknovoice (impossible line)
            - 10 = interrupt attempted with argument
    */

    // --- CONSTRUCTORS ---

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     * @param subtype the subtype of this error
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, int subtype, String[] extraInfo) {
        super(lineIndex, type, subtype, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, int subtype, ArrayList<String> extraInfo) {
        super(lineIndex, type, subtype, extraInfo.toArray(new String[0]));
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     * @param subtype the subtype of this error
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, int subtype, String extraInfo) {
        super(lineIndex, type, subtype, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     * @param subtype the subtype of this error
     */
    public ScriptError(int lineIndex, int type, int subtype) {
        super(lineIndex, type, subtype);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, String[] extraInfo) {
        super(lineIndex, type, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, ArrayList<String> extraInfo) {
        super(lineIndex, type, extraInfo.toArray(new String[0]));
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptError(int lineIndex, int type, String extraInfo) {
        super(lineIndex, type, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the error
     * @param type the type of this error
     */
    public ScriptError(int lineIndex, int type) {
        super(lineIndex, type);
    }

    // --- MISC ---

    /**
     * Returns a String representation of this ScriptError
     */
    @Override
    public String toString() {
        String s = super.toString();

        switch (this.type) {
            case 1:
                s += "Invalid linebreak (non-integer argument in place of number of lines)";
                break;
            
            case 2:
                s += "Invalid jumpto (";
                switch (this.subtype) {
                    case 0:
                        s += "given index is larger than script's size)";
                        break;
                        
                    case 1:
                        s += "given label " + this.extraInfo[0] + " does not exist in script)";
                        break;
                }
                break;
            
            case 3:
                s += "Invalid ";
                switch (this.subtype) {
                    case 0:
                        s += "firstswitch (no prefix given)";
                        break;
                        
                    case 1:
                        s += "firstswitch (label(s) " + this.extraList() + " do not exist in script)";
                        break;
                        
                    case 2:
                        s += "bladeswitch (no prefix given)";
                        break;
                        
                    case 3:
                        s += "bladeswitch (label(s) " + this.extraList() + " do not exist in script)";
                        break;
                }
                break;
            
            case 4:
                s += "Invalid ";
                switch (this.subtype) {
                    case 0:
                        s += "switchjump (no labels given)";
                        break;
                        
                    case 1:
                        s += "switchjump (label(s) " + this.extraList() + " do not exist in script)";
                        break;
                        
                    case 2:
                        s += "numswitchjump (no labels given)";
                        break;
                        
                    case 3:
                        s += "numswitchjump (label(s) " + this.extraList() + " do not exist in script)";
                        break;
                        
                    case 4:
                        s += "stringswitchjump (no arguments given)";
                        break;
                        
                    case 5:
                        s += "stringswitchjump (only one argument given)";
                        break;
                        
                    case 6:
                        s += "stringswitchjump (odd number of arguments)";
                        break;
                        
                    case 7:
                        s += "stringswitchjump (label(s) " + this.extraList() + " do not exist in script)";
                        break;

                    case 8:
                        s += "numautojump (no prefix given)";
                        break;

                    case 9:
                        s += "stringautojump (no suffix given)";
                        break;
                }
                break;
            
            case 5:
                s += "Invalid nowplaying (no argument given)";
                break;
            
            case 6:
                s += "Invalid dialogue line modifier (";
                switch (this.subtype) {
                    case 0:
                        s += "multiple modifier dividers)";
                        break;

                    case 1:
                        s += "modifier divider present without modifiers)";
                        break;

                    case 2:
                        s += "completely invalid modifier)";
                        break;

                    case 3:
                        s += "checkvoice with invalid syntax)";
                        break;
                        
                    case 4:
                        s += "checkvoice used without argument for non-Voice speaker)";
                        break;
                        
                    case 5:
                        s += "invalid argument(s) " + this.extraList() + " for checkvoice)";
                        break;

                    case 6:
                        s += "checkvoice with invalid syntax)";
                        break;
                        
                    case 7:
                        s += "checknovoice used with same id as speaker " + this.extraInfo[0] + " -- IMPOSSIBLE LINE)";
                        break;
                        
                    case 8:
                        s += "invalid argument(s) " + this.extraList() + " for checknovoice)";
                        break;
                        
                    case 9:
                        s += "same argument(s) " + this.extraList() + " used for both checkvoice and checknovoice -- IMPOSSIBLE LINE)";
                        break;
                        
                    case 10:
                        s += "interrupt used with argument)";
                        break;
                }
                break;

            default: s += "Invalid line (invalid command)";
        }
        
        return s;
    }

}

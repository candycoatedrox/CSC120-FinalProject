import java.util.ArrayList;

public class ScriptIssue extends ScriptNote {
    
    /* --- TYPE KEY ---
        - 0 = incomplete condition switch jump
            - 0 = switchjump (only one label given)
            - 1 = switchjump (more than 2 labels given)
            - 2 = numautojump (no labels exist for numbers up through 10)
        - 1 = argument given where none needed
            - 0 = quietcreep
            - 1 = claimfold
        - 2 = duplicate dialogue line modifier
            - 0 = duplicate overall modifier, check extraInfo
            - 1 = duplicate modifier argument, check extraInfo
    */

    // --- CONSTRUCTORS ---

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, int subtype, String[] extraInfo) {
        super(lineIndex, type, subtype, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, int subtype, ArrayList<String> extraInfo) {
        super(lineIndex, type, subtype, extraInfo.toArray(new String[0]));
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, int subtype, String extraInfo) {
        super(lineIndex, type, subtype, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     */
    public ScriptIssue(int lineIndex, int type, int subtype) {
        super(lineIndex, type, subtype);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, String[] extraInfo) {
        super(lineIndex, type, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param subtype the subtype of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, ArrayList<String> extraInfo) {
        super(lineIndex, type, extraInfo.toArray(new String[0]));
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     * @param extraInfo any extra information to print in the scan report
     */
    public ScriptIssue(int lineIndex, int type, String extraInfo) {
        super(lineIndex, type, extraInfo);
    }

    /**
     * Constructor
     * @param lineIndex the index of the line with the potential issue
     * @param type the type of this potential issue
     */
    public ScriptIssue(int lineIndex, int type) {
        super(lineIndex, type);
    }

    // --- MISC ---

    /**
     * Returns a String representation of this ScriptIssue
     */
    @Override
    public String toString() {
        String s = super.toString();

        switch (this.type) {
            case 0:
                s += "Incomplete ";
                switch (this.subtype) {
                    case 0:
                        s += "switchjump (only true label given)";
                        break;
                        
                    case 1:
                        s += "switchjump (more than 2 labels given)";
                        break;
                        
                    case 2:
                        s += "numautojump (no labels exist for numbers 0-9)";
                        break;
                }
                break;
            
            case 1:
                s += "Unnecessary argument given for ";
                switch (this.subtype) {
                    case 0:
                        s += "quietcreep";
                        break;
                        
                    case 1:
                        s += "claimfold";
                        break;
                }
                break;
            
            case 2:
                s += "Duplicate dialogue line modifier (";
                switch (this.subtype) {
                    case 0:
                        s += "duplicate modifier(s) " + this.extraList() + ")";
                        break;
                        
                    case 1:
                        s += "duplicate modifier argument(s) " + this.extraList() + ")";
                        break;
                }
                break;
        }

        return s;
    }
    
}

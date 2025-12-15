public class InverseCondition extends AbstractCondition {

    private final Condition inverse;
        
    // --- CONSTRUCTOR ---

    /**
     * Constructor
     * @param inverse the Condition to define the inverse of
     */
    public InverseCondition(Condition inverse) {
        this.inverse = inverse;
    }

    // --- CHECKS ---

    /**
     * Accessor for value
     * @return the boolean value of this condition
     */
    @Override
    public boolean check() {
        return !this.inverse.check();
    }
    
}

public class Condition extends  AbstractCondition {
    
    private boolean value;
    
    // --- CONSTRUCTOR ---

    /**
     * Constructor
     * @param value the starting value of the condition
     */
    public Condition(boolean value) {
        this.value = value;
    }

    // --- ACCESSORS & MANIPULATORS ---

    /**
     * Accessor for value
     * @return the boolean value of this condition
     */
    @Override
    public boolean check() {
        return this.value;
    }

    /**
     * Manipulator for value
     * @param newValue the new boolean value of this condition
     */
    public void set(boolean newValue) {
        this.value = newValue;
    }

    /**
     * Returns the inverse of this condition
     * @return the inverse of this condition
     */
    public InverseCondition getInverse() {
        return new InverseCondition(this);
    }

}

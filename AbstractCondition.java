public abstract class AbstractCondition {
    
    public abstract boolean check();

    /**
     * Returns the inverse of this AbstractCondition
     * @return the inverse of this AbstractCondition
     */
    public InverseCondition getInverse() {
        return new InverseCondition(this);
    }

    /**
     * Returns a String representation of this AbstractCondition
     */
    @Override
    public String toString() {
        return Boolean.toString(this.check());
    }

}

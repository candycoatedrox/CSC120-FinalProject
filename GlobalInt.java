public class GlobalInt {
    
    private int value;

    // --- CONSTRUCTORS ---

    /**
     * Constructor
     * @param value the starting value of the GlobalInt
     */
    public GlobalInt(int value) {
        this.value = value;
    }

    /**
     * Empty constructor (0)
     */
    public GlobalInt() {
        this(0);
    }

    // --- ACCESSORS & MANIPULATORS ---

    /**
     * Accessor for value
     * @return the integer value of this GlobalInt
     */
    public int check() {
        return this.value;
    }

    /**
     * Manipulator for value
     * @param newValue the new integer value of this GlobalInt
     */
    public void set(int newValue) {
        this.value = newValue;
    }

    // --- BASIC MATH ---

    /**
     * Checks whether the value of this GlobalInt is equal to a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(int n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(double n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given float
     * @param n the float to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(float n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given byte
     * @param n the byte to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(byte n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given long
     * @param n the long to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(long n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given short
     * @param n the short to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(short n) {
        return this.value == n;
    }

    /**
     * Checks whether the value of this GlobalInt is equal to a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is equal to n; false otherwise
     */
    public boolean equals(Number n) {
        return this.value == n.longValue();
    }

    /**
     * Checks whether the value of this GlobalInt is greater than a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is greater than n; false otherwise
     */
    public boolean greaterThan(int n) {
        return this.value > n;
    }

    /**
     * Checks whether the value of this GlobalInt is greater than or equal to a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is greater than or equal to n; false otherwise
     */
    public boolean greaterOrEquals(int n) {
        return this.value >= n;
    }

    /**
     * Checks whether the value of this GlobalInt is less than a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is less than n; false otherwise
     */
    public boolean lessThan(int n) {
        return this.value < n;
    }

    /**
     * Checks whether the value of this GlobalInt is less than or equal to a given int
     * @param n the int to check against
     * @return true if the value of this GlobalInt is less than or equal to n; false otherwise
     */
    public boolean lessOrEquals(int n) {
        return this.value <= n;
    }

    /**
     * Add an int to this GlobalInt
     * @param n the integer to add to this GlobalInt
     */
    public void add(int n) {
        this.value += n;
    }

    /**
     * Increment this GlobalInt by 1
     */
    public void increment() {
        this.value += 1;
    }

    /**
     * Subtract an int from this GlobalInt
     * @param n the integer to subtract from this GlobalInt
     */
    public void subtract(int n) {
        this.value -= n;
    }

    /**
     * Decrement this GlobalInt by 1
     */
    public void decrement() {
        this.value -= 1;
    }

    /**
     * Multiply this GlobalInt by an int
     * @param n the integer to multiply by this GlobalInt
     */
    public void multiply(int n) {
        this.value *= n;
    }

    /**
     * Divide this GlobalInt by an int
     * @param n the integer to divide this GlobalInt by
     */
    public void divideBy(int n) {
        this.value /= n;
    }

    // --- MISC ---

    /**
     * Returns a String representation of this AbstractCondition
     */
    @Override
    public String toString() {
        return Integer.toString(this.check());
    }

}

package utils; /**
 * Name : Chagit Stupel
 * ID : 209089960
 * Course group : 04
 */

/**
 * utils.Counter class.
 */
public class Counter {
    private int sum;

    /**
     * @param sum1 a number
     *             a constructor
     */
    public Counter(int sum1) {
        this.sum = sum1;
    }

    /**
     * @param number a number
     *               add number to current count.
     */
    public void increase(int number) {
        this.sum = this.sum + number;
    }

    /**
     * @param number a number
     *               subtract number from current count.
     */
    public void decrease(int number) {
        this.sum = this.sum - number;
    }

    /**
     * @return the counter value
     */
    public int getValue() {
        return this.sum;
    }

    /**
     * @param sum1 the new sum of the blocks
     */
    public void setSum(int sum1) {
        this.sum = sum1;
    }
}
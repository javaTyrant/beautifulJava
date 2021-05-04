package dayPlan;

/**
 * @author lumac
 * @since 2021/3/13
 */
public class MyHashSet {
    boolean[] array;

    /**
     * Initialize your data structure here.
     */
    public MyHashSet() {
        array = new boolean[100001];
    }

    public void add(int key) {
        array[key] = true;
    }

    public void remove(int key) {
        array[key] = false;
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        return array[key];
    }
}

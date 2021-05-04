package dayPlan;

import java.util.PriorityQueue;

/**
 * @author lumac
 * @since 2021/3/4
 */
public class Demo {
    public static void main(String[] args) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(3);
        minQueue.offer(3);
        minQueue.offer(2);
        minQueue.offer(1);
        System.out.println(minQueue.peek());
    }
}

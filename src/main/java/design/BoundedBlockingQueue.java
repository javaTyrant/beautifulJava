package design;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lumac
 * @since 2020/7/28
 */
public class BoundedBlockingQueue {
    private int[] items;

    private int putIndex;
    private int takeIndex;
    private int count;

    private ReentrantLock lock;
    private Condition notFull;
    private Condition notEmpty;

    public BoundedBlockingQueue(int capacity) {
        this.items = new int[capacity];

        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
    }

    public void enqueue(int element) throws InterruptedException {
        final ReentrantLock lock = this.lock;

        lock.lock();
        try {
            while (this.count == this.items.length) {
                this.notFull.await();
            }

            this.items[this.putIndex] = element;
            if (++this.putIndex == this.items.length) {
                this.putIndex = 0;
            }

            this.count++;
            this.notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        ReentrantLock lock = this.lock;

        lock.lock();
        try {
            while (this.count == 0) {
                this.notEmpty.await();
            }

            int val = this.items[this.takeIndex];
            if (++this.takeIndex == this.items.length) {
                this.takeIndex = 0;
            }

            this.count--;
            this.notFull.signal();
            return val;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        ReentrantLock lock = this.lock;

        lock.lock();
        try {
            return this.count;
        } finally {
            lock.unlock();
        }
    }
}

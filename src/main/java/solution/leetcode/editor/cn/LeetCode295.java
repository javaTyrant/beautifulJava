package solution.leetcode.editor.cn;

import java.util.PriorityQueue;

public class LeetCode295 {
    //leetcode submit region begin(Prohibit modification and deletion)
    //数据流的中位数
    static class MedianFinder {
        public static void main(String[] args) {
            MedianFinder finder = new MedianFinder();
            finder.addNum(2);
            finder.addNum(3);
            finder.addNum(1);
            finder.addNum(4);
            System.out.println(finder.findMedian());
        }

        /**
         * 当前大顶堆和小顶堆的元素个数之和
         */
        private int count;
        //max
        private PriorityQueue<Integer> maxheap;
        //min
        private PriorityQueue<Integer> minheap;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            count = 0;
            maxheap = new PriorityQueue<>((x, y) -> y - x);
            minheap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            count += 1;
            maxheap.offer(num);
            minheap.add(maxheap.poll());
            // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
            if ((count & 1) != 0) {
                maxheap.add(minheap.poll());
            }
        }

        public double findMedian() {
            if ((count & 1) == 0) {
                // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
                return (double) (maxheap.peek() + minheap.peek()) / 2;
            } else {
                // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
                return (double) maxheap.peek();
            }
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}

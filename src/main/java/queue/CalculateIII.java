package queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author lumac
 * @since 2020-05-25
 */
class Test {

    public static synchronized void writer() {
        System.out.println("执行writer方法");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void read() {
        System.out.println("执行read方法:");
    }
}

public class CalculateIII {

    public static void main(String[] args) {
        CalculateIII calculate = new CalculateIII();
        System.out.println(calculate.calculate("(1+200)/3"));
        for (int i = 0; i < 80; i++) {
            Test test = new Test();
            new Thread(() -> Test.writer()).start();

            new Thread(() -> Test.read()).start();
        }

    }


    public int calculate(String s) {
        //
        Queue<Character> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            q.offer(c);
        }
        q.offer(' ');
        return helper(q);
    }

    public int helper(Queue<Character> q) {
        int prev = 0, num = 0, sum = 0;
        char prevOp = '+';
        while (!q.isEmpty()) {
            //Retrieves and removes the head of this queue
            char c = q.poll();
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                num = helper(q);
            } else {
                switch (prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }
                if (c == ')') break;
                prevOp = c;
                num = 0;
            }
        }
        return prev + sum;
    }
}

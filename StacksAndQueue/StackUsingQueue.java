import java.util.*;
import java.util.LinkedList;

public class StackUsingQueue {
    
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    static int curr_size;

    public StackUsingQueue () {
        curr_size = 0;
    }

    public void push (int element) {
        q2.add(element);
        while( ! q1.isEmpty()) {
            q2.add(q1.poll());
        }
        Queue<Integer> q = q1;
        q1 = q2;
        q2 = q;
    }

    public int pop () {
        return q1.poll();
    }

    public int peek() {
        return q1.peek();
    }
}
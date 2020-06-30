import java.util.EmptyStackException;
import java.util.Stack;

public class QueueUsingStack {
    static Stack<Integer> s1 = new Stack<>();
    static Stack<Integer> s2 = new Stack<>();

    public void enQueue(int element) {
        s1.push(element);
    }

    public int deQueue() {
        if (s1.isEmpty()) {
            throw new EmptyStackException();
        }
        while( ! s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int first = s2.pop();
        while ( ! s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return first;
    }
} 
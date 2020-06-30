import java.util.EmptyStackException;

public class StackUsingArray {
    
    private int arr[];
    private int size;
    private int index = 0;

    public void Stack(final int size) {
        this.size = size;
        this.arr = new int[size];
    }

    public void push(final int element) {
        if(index == size) {
            throw new StackOverflowError("Stack is full");
        }
        arr[index] = element; index++;
    }

    public int pop() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[--index];
    }

    public int size() {
        return index;
    }

    public boolean isFull() {
        if (index == size) return true;
        return false;
    }

    public boolean isEmpty() {
        if (index == 0) return true;
        return false;
    }
}
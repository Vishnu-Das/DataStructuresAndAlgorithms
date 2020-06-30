public class QueueUsingArray {
    private int capacity;
    private int[] arr;
    private int size;
    private int front, rear;

    public QueueUsingArray(int capacity) {
        this.capacity = capacity;
        front = this.size = 0;
        rear = capacity-1;
        arr = new int[this.capacity];
    }

    public boolean isFull () {
        return size == capacity;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }

    public void enQueue (int element) {
        if (isFull()) return;
        rear = (rear + 1) % capacity;
        arr[rear] = element;
        size++;
    }

    public int deQueue () {
        if (isEmpty()) return Integer.MIN_VALUE;
        int first = arr[front];
        front = (front + 1) % capacity; size--;
        return first;
    }

    public int front () {
        if (isEmpty()) return Integer.MIN_VALUE;
        return arr[front];
    }

    public int rear () {
        if (isEmpty()) return Integer.MIN_VALUE;
        return arr[rear];
    }

}
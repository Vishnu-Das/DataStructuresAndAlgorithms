public class LinkedList {
    Node head;

    static class Node {
        int data;
        Node next = null;

        Node(int d) 
        { 
            data = d; 
            next = null; 
        } 
    }

    public static Node reverse(Node head) {
        Node prev = null;
        Node next = null;
        Node current = head;
        while(current != null) {
            next =current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList(); 
        list.head = new Node(85); 
        list.head.next = new Node(15); 
        list.head.next.next = new Node(4); 
        list.head.next.next.next = new Node(20);
    }
}
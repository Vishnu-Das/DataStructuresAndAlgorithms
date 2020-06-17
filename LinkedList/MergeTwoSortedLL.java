public class MergeTwoSortedLL {
    static ListNode prev = null;
    static ListNode head = null;
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1); 
        list1.next = new ListNode(3); 
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(2); 
        list2.next = new ListNode(5); 
        list2.next.next = new ListNode(6);

        mergeSortedLL(list1, list2);
        
    }

    public static ListNode mergeSortedLL(ListNode h1, ListNode h2) {
        ListNode i = h1, j = h2; head = null; prev = null;
        while(i != null && j != null) {
            if(i.val <= j.val) {
                add(i);
                i= i.next;
            } else {
                add(j);
                j= j.next;
            }
        }

        while(i != null) {
            add(i); i = i.next;
        }
        while(j != null) {
            add(j); j = j.next;
        }

        return head;
    }

    public static void add (ListNode node) {
        if (head == null) {
            head = node; prev = node;
        } else {
            prev.next = node;
            prev = node;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {this.val = val;}
    ListNode(int val, ListNode next) {this.val = val; this.next = next;}
}
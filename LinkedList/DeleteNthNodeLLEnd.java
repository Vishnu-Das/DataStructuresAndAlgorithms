public class DeleteNthNodeLLEnd {
    public static void main(String[] args) {
        LiNode list1 = new LiNode(1); 
        list1.next = new LiNode(3); 
        list1.next.next = new LiNode(4);
        list1.next = new LiNode(5); 
        list1.next.next = new LiNode(6);
        LiNode head = removeNthFromEnd(list1, 3);
        LiNode curr = head;
        while(curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    public static LiNode removeNthFromEnd(LiNode head, int n) {
        LiNode first = head, second = head;
        for(int i=0; i<n; i++) {
                if(second.next == null) {
                    if (i==n-1){
                    head = head.next;
                    return head;
                }
            }
            second = second.next;
        }
        
        while(second.next != null) {
            first = first.next;
            second = second.next;
        }
        
        first.next = first.next.next;
        return head;
    }
}
class LiNode {
    int val;
    LiNode next;
    LiNode(int val) {this.val = val;}
    LiNode(int val, LiNode next) {this.val = val; this.next = next;}
}
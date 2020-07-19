import java.util.Stack;

public class InorderTraverse {
    public static void main(String[] args) {  
        
        Node<Integer> five = new Node<>(5);
        Node<Integer> six = new Node<>(6);
        Node<Integer> seven = new Node<>(7);
        Node<Integer> eight = new Node<>(8);
        Node<Integer> four = new Node<>(4, eight, null);
        Node<Integer> three = new Node<>(3, six, seven);
        Node<Integer> two = new Node<>(2, four, five);
        Node<Integer> one = new Node<>(1, two, three);

        inorderTraverseRecursive(one);
        System.out.println();
        inorderTraverseIterative(one);      
    }

    public static void inorderTraverseRecursive(Node<Integer> root) {
        if(root == null) return;
        inorderTraverseRecursive(root.left);
        System.out.print(root.value + " ");
        inorderTraverseRecursive(root.right);
    }

    public static void inorderTraverseIterative(Node<Integer> root) {
        final Stack<Node<Integer>> stk = new Stack<>();
        Node<Integer> current = root;
        while( ! stk.isEmpty() || current != null) {
            if (current == null) {
                current = stk.pop();
                System.out.print(current.value + " ");
                current = current.right;
            } else {
                stk.push(current);
                current = current.left;
            }  
        }
    }
}
import java.util.Stack;

public class PostOrderTraverse {
    public static void main(String[] args) {
        Node<Integer> five = new Node<>(5);
        Node<Integer> six = new Node<>(6);
        Node<Integer> seven = new Node<>(7);
        Node<Integer> eight = new Node<>(8);
        Node<Integer> four = new Node<>(4, eight, null);
        Node<Integer> three = new Node<>(3, six, seven);
        Node<Integer> two = new Node<>(2, four, five);
        Node<Integer> one = new Node<>(1, two, three);

        postorderTraverseRecursive(one);
        System.out.println();
        postorderTraverseIterative(one);
    }

    public static void postorderTraverseRecursive(Node<Integer> root) {
        if(root == null) return;
        postorderTraverseRecursive(root.left);
        postorderTraverseRecursive(root.right);
        System.out.print(root.value + " ");
    }

    public static void postorderTraverseIterative(Node<Integer> root) {
        final Stack<Node<Integer>> stk1 = new Stack<>();
        final Stack<Node<Integer>> stk2 = new Stack<>();
        stk1.push(root);
        while( ! stk1.isEmpty()) {
            Node<Integer> curr = stk1.pop();
            stk2.push(curr);
            if(curr.left != null) stk1.push(curr.left);
            if(curr.right != null) stk1.push(curr.right);
        }
        while( ! stk2.isEmpty()) {
            System.out.print(stk2.pop().value + " ");
        }
    }


}
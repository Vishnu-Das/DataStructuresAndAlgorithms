import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class LevelOrderTraversalBT {
    public static void main(String[] args) {
        final Node<Integer> five = new Node<>(5);
        final Node<Integer> six = new Node<>(6);
        final Node<Integer> seven = new Node<>(7);
        final Node<Integer> eight = new Node<>(8);
        final Node<Integer> nine = new Node<>(9);
        final Node<Integer> four = new Node<>(4, eight, nine);
        final Node<Integer> three = new Node<>(3, six, seven);
        final Node<Integer> two = new Node<>(2, four, five);
        final Node<Integer> one = new Node<>(1, two, three);

        levelOrderTraverse(one);
        System.out.println();
        levelOrderTraverseSpiral(one);
    }

    public static void levelOrderTraverseSpiral(Node<Integer> root) {
        Stack<Node<Integer>> stk1 = new Stack<>();
        Stack<Node<Integer>> stk2 = new Stack<>();
        boolean toggle = true;
        stk1.add(root); //stk2.add(root); toggle = false; /* spiral order control */
        while( ! stk1.isEmpty() ||  ! stk2.isEmpty()) {
            if(toggle) {
                while( ! stk1.isEmpty()) {
                    Node<Integer> current = stk1.pop();
                    System.out.print(current.value + " ");
                    if (current.left != null) stk2.add(current.left);
                    if (current.right != null) stk2.add(current.right);
                    toggle = false;
                }
            } else {
                while( ! stk2.isEmpty()) {
                    Node<Integer> current = stk2.pop();
                    System.out.print(current.value + " ");
                    if (current.right != null) stk1.add(current.right);
                    if (current.left != null) stk1.add(current.left);
                    toggle = true;
                }
            }
        }
    }

    public static void levelOrderTraverse(Node<Integer> root) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while( ! queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            System.out.print(current.value + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}
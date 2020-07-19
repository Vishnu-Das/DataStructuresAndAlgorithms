import java.util.Stack;

public class PreOrderTraverse {
    public static void main(String[] args) {
        Node<Integer> five = new Node<>(5);
        Node<Integer> six = new Node<>(6);
        Node<Integer> seven = new Node<>(7);
        Node<Integer> eight = new Node<>(8);
        Node<Integer> four = new Node<>(4, eight, null);
        Node<Integer> three = new Node<>(3, six, seven);
        Node<Integer> two = new Node<>(2, four, five);
        Node<Integer> one = new Node<>(1, two, three);

        preorderTraverseRecursive(one);
        System.out.println();
        preorderTraverseIterative(one);
    }

	public static void preorderTraverseRecursive(Node<Integer> root) {
        if(root == null) return;
        System.out.print(root.value + " ");
        preorderTraverseRecursive(root.left);
        preorderTraverseRecursive(root.right);
    }
    
    public static void preorderTraverseIterative(Node<Integer> root) {
        final Stack<Node<Integer>> stk = new Stack<>();
        Node<Integer> current = root;
        while(! stk.isEmpty() || current != null) {
            if (current != null) {
                System.out.print(current.value + " ");
                stk.push(current);
                current = current.left;
            } else {
                current = stk.pop().right;
            }
        }
    }
}
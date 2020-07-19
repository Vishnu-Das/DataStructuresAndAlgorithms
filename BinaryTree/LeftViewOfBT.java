public class LeftViewOfBT {

    static int maxLevel = 0;

    public static void main(String[] args) {
        Node<Integer> six = new Node<>(6);
        Node<Integer> eight = new Node<>(8);
        Node<Integer> seven = new Node<>(7, eight, null);
        Node<Integer> three = new Node<>(3, six, seven);
        Node<Integer> two = new Node<>(2);
        Node<Integer> one = new Node<>(1, two, three);

        leftView(one, 1);
    }

    public static void leftView(Node<Integer> root, int level) {
        if (root == null) return;
        if (maxLevel < level) {
            System.out.print(root.value + " ");
            maxLevel = level;
        }
        leftView(root.left, level+1);
        leftView(root.right, level+1);
    }
}
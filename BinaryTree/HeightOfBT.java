public class HeightOfBT {
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

        System.out.println(getHeightOfBT(one));
    }

    public static int getHeightOfBT(Node<Integer> root) {
        return getHeightUtil(root, 0);
    }

    private static int getHeightUtil(Node<Integer> node, int i) {
        if(node == null) return 0;
        return 1 + Math.max(getHeightUtil(node.left, i), getHeightUtil(node.right, i));
    }
}
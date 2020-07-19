import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.LinkedList;


public class TopViewOfBT {
    public static void main(final String[] args) {
        final Node<Integer> five = new Node<>(5);
        final Node<Integer> six = new Node<>(6);
        final Node<Integer> seven = new Node<>(7);
        final Node<Integer> eight = new Node<>(8);
        final Node<Integer> four = new Node<>(4, eight, null);
        final Node<Integer> three = new Node<>(3, six, seven);
        final Node<Integer> two = new Node<>(2, four, five);
        final Node<Integer> one = new Node<>(1, two, three);

        getTopView(one); // 8 4 2 1 3 7
    }

    private static void getTopView(final Node<Integer> root) {
        final Queue<Node<Integer>> queue = new LinkedList<>();
        final Map<Integer, Integer> map = new TreeMap<>();
        root.hd = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            if( ! map.containsKey(current.hd)) {
                map.put(current.hd, current.value);
            }
            if (current.left != null) {
                current.left.hd = current.hd - 1;
                queue.add(current.left);
            }
            if (current.right != null) {
                current.right.hd = current.hd + 1;
                queue.add(current.right);
            }
        }
        map.values().forEach(value -> System.out.print(value + " "));
    }
}
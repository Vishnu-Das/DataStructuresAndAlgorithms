import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomViewOfBT {
    public static void main(String[] args) {
        Node<Integer> five = new Node<>(5);
        Node<Integer> six = new Node<>(6);
        Node<Integer> seven = new Node<>(7);
        Node<Integer> eight = new Node<>(8);
        Node<Integer> four = new Node<>(4, eight, null);
        Node<Integer> three = new Node<>(3, six, seven);
        Node<Integer> two = new Node<>(2, four, five);
        Node<Integer> one = new Node<>(1, two, three);

        getBottomView(one); // 8 4 2 6 3 7
    }

    public static void getBottomView(Node<Integer> root) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        root.hd = 0;
        queue.add(root);
        while ( ! queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            map.put(current.hd, current.value);
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
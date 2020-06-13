import java.util.ArrayList;
import java.util.List;

class PascalsTriangle {
    public static void main(String[] args) {
        List<List<Integer>> pascalTriangle = generate(5);
        pascalTriangle.forEach(
            seq -> {
                seq.forEach(
                    elem -> System.out.print(elem + " ")
                );
                System.out.println();
            }
        );
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalTriangle = new ArrayList<>();
        List<Integer> firstList = new ArrayList<>();
        if (numRows == 0) {
            return pascalTriangle;
        }
        firstList.add(1);
        pascalTriangle.add(firstList);
        int count = 1;
        while (count < numRows) {
            List<Integer> nextList = createNextList(firstList);
            pascalTriangle.add(nextList);
            firstList = nextList;
            count++;
        }
        return pascalTriangle;
    }
    
    private static List<Integer> createNextList(List<Integer> pseq) {
        List<Integer> nseq = new ArrayList<Integer>();
        for (int i = 0; i<=pseq.size(); i++) {
            if(i == 0) {
                nseq.add(pseq.get(0));
            } else if(i == pseq.size()) {
                nseq.add(pseq.get(pseq.size()-1));
            } else {
                nseq.add(pseq.get(i-1) + pseq.get(i));
            }
        }
        return nseq;
    }
}
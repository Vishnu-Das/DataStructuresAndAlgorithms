import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    public static void main(String[] args) 
    { 
        int[] wt = {10, 40, 20, 30}; 
        int[] val = {60, 40, 100, 120}; 
        int capacity = 50; 
  
        double maxValue = getMaxValue(wt, val, capacity); 
        System.out.println("Maximum value we can obtain = " +  
                            maxValue); 
  
    }

    private static double getMaxValue(int[] wt, int[] val, int capacity) {
        ItemValue[] ratio = new ItemValue[wt.length];
        for (int i = 0; i < wt.length; i++) {
            ratio[i] = new ItemValue(wt[i], val[i], i);
        }
        Arrays.sort(ratio, Comparator.comparingDouble( a -> a.cost));
        double maxValue = 0;
        for(ItemValue itv : ratio) {
            if (capacity - itv.wt >= 0) 
            {  
                capacity = (int) (capacity - itv.wt);
                maxValue += itv.wt; 
  
            } 
            else
            { 
                double fraction = ((double)capacity/(double)itv.wt); 
                maxValue += (itv.val*fraction); 
                capacity = (int)(capacity - (itv.wt*fraction)); 
                break; 
            }
        }
        return maxValue;
    }

    static class ItemValue  
    { 
        Double cost; 
        double wt, val, ind; 
        public ItemValue(int wt, int val, int ind) 
        { 
            this.wt = wt; 
            this.val = val; 
            this.ind = ind; 
            cost = new Double(val/wt ); 
        } 
    }
}
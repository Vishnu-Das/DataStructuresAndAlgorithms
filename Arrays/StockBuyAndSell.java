import java.util.ArrayList;
import java.util.List;

public class StockBuyAndSell { // atmost one time buy and sell -> 0 or 1.
    // This can be also solved with Kadane's Algo by taking differences between each indices.

    //[7,1,5,3,6,4]
    public static void main(String[] args) {
        int[] costs = {2,1,2,1,0,1,2};
        System.out.println(maxProfit(costs));
    }

    private static int maxProfit(int[] prices) {
        if (prices.length == 0) {
			 return 0 ;
		 }		
		 int max = 0 ;
		 int sofarMin = prices[0] ;
	     for (int i = 0 ; i < prices.length ; ++i) {
	    	 if (prices[i] > sofarMin) {
	    		 max = Math.max(max, prices[i] - sofarMin) ;
	    	 } else{
	    		sofarMin = prices[i];  
	    	 }
	     }	     
	    return  max ;
    }
}
// maximize profit
// no limit on transactions.
//[3,3,5,0,0,3,1,4]  /** This question get pretty dope when we are given with the atmost trnasaction count (k) for max profit */
public class StockBuySellMedium {
    public static void main(String[] args) {
        int[] stocks = {3,4,4,8,4,7};
        int max_profit = maximumProfit(stocks);
        System.out.println(max_profit);
    }

	private static int maximumProfit(int[] stocks) {
        boolean bought = false;
        int i =0, profit = 0;
        while(i < stocks.length-1) {
            if (stocks[i] < stocks[i+1] && !bought) {
                profit -= stocks[i];
                bought = true;
            } else
            if (stocks[i] > stocks[i+1] && bought) {
                profit += stocks[i];
                bought = false;
            }
            i++;
        }

        if(bought && i == stocks.length-1) {
            profit += stocks[i];
            bought = false;
        }

		return profit;
	}
}
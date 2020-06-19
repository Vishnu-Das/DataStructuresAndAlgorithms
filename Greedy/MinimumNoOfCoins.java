import java.util.Arrays;

public class MinimumNoOfCoins {
    public static void main(String[] args) {
        int coins[] = {25, 10, 5}, Amount = 470;
        int minimumCoins = minimumCoins(coins, Amount);
        System.out.println(minimumCoins);
    }

    public static int minimumCoins(int[] coins, int Amount) {
        Arrays.sort(coins);
        int coinCount = 0;
        int i = coins.length-1;
        while(Amount > 0) {
            if (Amount >= coins[i]) {
                coinCount += Amount/coins[i];
                Amount = Amount%coins[i];
            }
            i--;
        }
            return coinCount;
    }
}
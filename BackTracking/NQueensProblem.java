public class NQueensProblem {
    public static void main(String[] args) {
        
        boolean possible = placeNQueens();
        System.out.println(possible);
    }

    public static boolean placeNQueens() {
        int board[][] = { { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 },
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 }, 
                          { 0, 0, 0, 0, 0, 0, 0, 0 } };
        boolean possible = solveNQUtil(board,0);
        for(int i=0; i<board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        return possible;
    }

    public static boolean solveNQUtil(int[][] board, int col) { 

        if(col >= board.length) return true;

        for(int i = 0; i<board.length; i++) {
            if (noAttacksOn(board, i, col)) {
                board[i][col] = 1;
                if (solveNQUtil(board, col + 1) == true) {
                    return true;
                }
                board[i][col] = 0;
            } 
        }
        return false;
    }

    public static boolean noAttacksOn(int[][] board, int row, int col) {
        // Row check
        for(int i = 0; i<board.length; i++) {
            if(board[row][i] == 1) {
                return false;
            }
        }
        // Upper diagonal check
        for(int i = row, j = col; i>=0 && j>=0; i--,j--) {
            if(board[i][j] == 1) return false;
        }

        // Lower diagonal check
        for(int i = row, j = col; i<board.length && j>=0; i++,j--) {
            if(board[i][j] == 1) return false;
        }

        return true;
    }
    
    
}
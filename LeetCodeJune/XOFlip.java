/** NOT WORKING PERFECTLY */
class XOFlip { 
    public static void main(final String[] args) {
       //final char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','X','X'},{'X','O','O','X'}};
       final char[][] board = {{'O','X','X','O','X'},{'X','O','O','X','O'},{'X','O','X','O','X'},{'O','X','O','O','O'},{'X','X','O','X','O'}};
       for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solve(board);
       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[0].length; j++) {
               System.out.print(board[i][j] + " ");
           }
           System.out.println();
       } 
    }

    public static void solve(final char[][] board) {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !isBorder(i, j, board.length, board[0].length)
                        && !isConnectedToBorderO(i, j, board,-1,-1)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static boolean isBorder(final int i, final int j, final int rowSize, final int colSize) {
        if (i == 0 || j == 0 || i == rowSize - 1 || j == colSize - 1) {
            return true;
        }
        return false;
    }

    public static boolean isConnectedToBorderO(final int i, final int j, final char[][] board, int previ, int prevj) {
        if(board[i+1][j] == 'O' && i+1 != previ) {
            if (isBorder(i+1,j,board.length,board[0].length)) return true;
            return isConnectedToBorderO(i+1,j,board,i,j);
        }
        if(board[i][j+1] == 'O' && j+1 != prevj) {
            if (isBorder(i,j+1,board.length,board[0].length)) return true;
            return isConnectedToBorderO(i,j+1,board,i,j);
        }
        if(board[i][j-1] == 'O' && j-1 != prevj) {
            if (isBorder(i,j-1,board.length,board[0].length)) return true;
            return isConnectedToBorderO(i,j-1,board,i,j);
        }
        if(board[i-1][j] == 'O' && i-1 != previ) {
            if (isBorder(i-1,j,board.length,board[0].length)) return true;
            return isConnectedToBorderO(i-1,j,board,i,j);
        }
        return false;
    }
}
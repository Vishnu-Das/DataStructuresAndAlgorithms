
public class RottenOranges {
        static boolean rotted = true;
        static int rotVal = 2;
        static int minutes = 0;

        public static void main(String[] args) {
            int[][] grid = {{2,1,1},
                            {1,1,0},
                            {0,1,1}};
            int time = orangesRotting(grid);
            System.out.println(time);
        }

        public static int orangesRotting(int[][] grid) {        
            while(rotted && hasFreshOrange(grid)) { 
                rotted = false;
                for(int i = 0; i < grid.length; i++) {
                    for(int j = 0; j < grid[0].length; j++) {
                        if (grid[i][j] == rotVal) {
                            rotOranges(i, j+1, rotVal, grid);
                            rotOranges(i, j-1, rotVal, grid);
                            rotOranges(i+1, j, rotVal, grid);
                            rotOranges(i-1, j, rotVal, grid);
                        }
                    }
                }
                if (rotted) {
                    rotVal++; minutes++;
                }
            }     
            if (! rotted) {
                return -1;
            }
            return minutes;
        }
        
        private static boolean hasFreshOrange(int[][] grid) {
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) return true;  
                }
            }
            return false;
        }
        
        private static void rotOranges(int i, int j, int rotVal, int[][] grid) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
            if (grid[i][j] == 1) {
                grid[i][j] = rotVal + 1;
                rotted = true;
            }
        }
}
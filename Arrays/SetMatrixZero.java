public class SetMatrixZero {
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};

        setMatrixToZero(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void setMatrixToZero(int[][] matrix) {
        boolean rowzero = false;
        boolean colzero =false;
        int r = matrix.length;
        int c = matrix[0].length;
        /** check first row contains 0 */
        for(int i=0; i< r; i++) {
            if(matrix[i][0] == 0) {
                colzero = true;
            }
        }
        /** check first column contains 0 */
        for(int i=0; i< c; i++) {
            if(matrix[0][i] == 0) {
                rowzero = true;
            }
        }
        /** check for zero elements and use first row and column as flags */
        for(int i = 1; i< r; i++) {
            for(int j =1; j<c; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        /** Using first row and column mark elements as zero */
        for(int i = 1; i< r; i++) {
            for(int j =1; j<c; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        /** check and convert first row*/
        if(rowzero == true) {
            for(int i=0; i<c ; i++) {
                matrix[0][i] = 0;
            }
        }
        /** check and convert first column*/
        if(colzero == true) {
            for(int i=0; i<r ; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
public class Matrix {
    
    public static int[][] Fill(int a, int size_x, int size_y) {
        int[][] Matrix = new int[size_x][size_y];
        for(int i=0; i< size_x; i++) {
            for(int j = 0; j < size_y; j++) {
                Matrix[i][j] = a;
            }
        }
        return Matrix;
    }
}

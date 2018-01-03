/**
 * Created by JDidat on 2/13/2016.
 */
public class Matrix {
    public boolean isSymmetric(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] == matrix[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i != j && matrix[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isIdentity(int[][] matrix) {
        boolean m = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == j && matrix[i][j] == 1 || i != j && matrix[i][j] == 0) {
                    m = true;
                }
                else {
                    m = false;
                }
            }
        }
        return m;
    }
    public boolean isUpperTriangular(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTridiagonal(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cell = matrix[i][j];
                if (i == j || i - 1 == j || i + 1 == j) {
                    if (cell == 0) {
                        return false;
                    }
                }
                else {
                    if (cell != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

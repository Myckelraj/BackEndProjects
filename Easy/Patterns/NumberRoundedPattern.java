package Zoho.Patterns;

public class NumberRoundedPattern {
    public static void main(String[] args) {
        int num = 4;
        int size = 2 * num - 1;
        int[][] arr = new int[size][size];
        int N = 1, top = 0, left = 0, bottom = size - 1, right = size - 1;
        while (top <= bottom && left <= right) {
            // top to left
            for (int i = top; i <= right; i++) {
                arr[top][i] = N++;
            }
            top++;
            // right to bottom
            for (int i = top; i <= bottom; i++) {
                arr[i][right] = N++;
            }
            right--;
            // bottom to left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    arr[bottom][i] = N++;
                }
            }
            bottom--;
            // botttom to top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    arr[i][left] = N++;
                }
            }
            left++;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%-5d", arr[i][j]);
            }
            System.out.print("\n");
        }
    }

}

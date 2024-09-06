package Zoho.Patterns;

/*
3 3 3 3 3 
3 2 2 2 3 
3 2 1 2 3 
3 2 2 2 3 
3 3 3 3 3 
 */
public class RoundedPattern1 {
    public static void main(String[] args) {

        int num = 3;
        int size = 2 * num - 1;
        int front = 0;
        int last = size - 1;
        int[][] arr = new int[size][size];

        while (num-- != 0) { // Change the loop condition
            for (int i = front; i <= last; i++) {
                for (int j = front; j <= last; j++) {
                    if (i == front || i == last || j == front || j == last) {
                        arr[i][j] = num + 1;
                    }
                }
            }
            front++;
            last--;
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

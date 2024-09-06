package Zoho.Arrays;
public class RightRotateArray {
  public static void main(String[] args) {
    int[] arr = { 3, 4, 5, 6, 7, 8, 9 };
    int RotateTimes = 3;
    System.out.println("Given array is: ");
    for (int i = 0; i < arr.length - 1; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();

    for (int i = 0; i < RotateTimes; i++) {
      int j, right = arr[arr.length - 1];
      for (j = arr.length-1; j > 0; j--) {
        arr[j] = arr[j-1];
      }
      arr[j] = right;
    }

    for (int i = 0; i < arr.length - 1; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}

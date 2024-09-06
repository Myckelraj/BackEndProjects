package Zoho.Arrays;
public class LeftRotateArray {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    int num = arr.length - 1;
    int rotateTimes = 3;
    System.out.println("Before ");
    for (int i = 0; i < num; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
    System.out.println("After ");
    for (int i = 0; i < rotateTimes; i++) {
      int left = arr[0], j;
      for (j = 0; j < num; j++) {
        arr[j] = arr[j + 1];
      }
      arr[j] = left;
    }

    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}

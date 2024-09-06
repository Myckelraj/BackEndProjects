package Zoho.Arrays;
public class ReverseArray {
  public static void main(String[] args) {
    int[] arr = { 34, 5, 4, 3, 2, 1 };
    int start = 0, end = arr.length - 1;
    while (start < end) {
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
    for (int val : arr) {
      System.out.print(val + " ");
    }
  }
}

package Zoho.Arrays;

import java.util.Arrays;

public class CompareArray {
  public static void main(String[] args) {
    int[] arr1 = { 2, 3, 4, 5, 3, 5 };
    int[] arr2 = { 2, 3, 4, 5, 3, 5 };
    Object[] arr3 = { arr1 };
    Object[] arr4 = { arr2 };
    if (Arrays.deepEquals(arr3, arr4)) {
      System.out.println("Equal");
    } else {
      System.out.println("Not Equal");
    }
  }
}

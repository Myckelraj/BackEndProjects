package Zoho.Arrays;
public class DuplicateArray {
  public static void main(String[] args) {
    int arr[] = { 34, 23, 34, 44, 23, 44, 55, 66, 55 };

    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }

    // Print the sorted array
    System.out.print("Sorted array: ");
    for (int k = 0; k < arr.length; k++) {
      System.out.print(arr[k] + " ");
    }
    System.out.println();

    // Find and print duplicates
    System.out.print("Unique: ");
    boolean foundDuplicate = false;
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] == arr[i + 1]) {
        System.out.print(arr[i] + " ");
        foundDuplicate = true;
        while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
          i++;
        }
      }
    }
    if (!foundDuplicate) {
      System.out.print("No duplicates found");
    }
  }
}

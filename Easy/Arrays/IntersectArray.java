package Zoho.Arrays;
import java.util.*;
public class IntersectArray {
  public static void main(String[] args) {
    int[] nums1 = { 1, 2, 3, 4, 1, 5 };
    int[] nums2 = { 2, 2, 4, 5, 3 };
    Set<Integer> set1 = new HashSet<>();
    Set<Integer> resultSet = new HashSet<>();

    // Add elements of nums1 to set1
    for (int num : nums1) {
      set1.add(num);
    }

    // Check for common elements in nums2
    for (int num : nums2) {
      if (set1.contains(num)) {
        resultSet.add(num);
      }
    }

    // Convert resultSet to array
    int[] result = new int[resultSet.size()];
    int index = 0;
    for (int num : resultSet) {
      result[index++] = num;
    }

    // Print result array
    for (int res : result) {
      System.out.print(res + " ");
    }
  }
}

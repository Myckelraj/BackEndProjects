package Zoho.Arrays;
import java.util.*;
public class OddEvenPosition {
    public static void main(String[] args) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        int[] arr = { 12, 54, 34, 44, 67, 33 };

        // Separate the array values into odd and even index lists
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0) {
                odd.add(arr[i]);
            } else {
                even.add(arr[i]);
            }
        }

        // Sort the lists
        Collections.sort(odd); // Ascending order for odd indices
        Collections.sort(even, Collections.reverseOrder()); // Descending order for even indices

        // Prepare the output array
        int[] output = new int[arr.length];
        int oddIndex = 0;
        int evenIndex = 0;

        // Populate the output array
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0) {
                output[i] = odd.get(oddIndex++);
            } else {
                output[i] = even.get(evenIndex++);
            }
        }

        // Print the output array
        System.out.println(Arrays.toString(output));
    }
}

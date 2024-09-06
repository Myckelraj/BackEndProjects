package Zoho.Arrays;
import java.util.ArrayList;
import java.util.List;

public class OddEvenNumber {
  public static void main(String[] args) {
    int num = 50;
    List<Integer> odd = new ArrayList<>();
    List<Integer> even = new ArrayList<>();
    for (int i = 1; i <= num; i++) {
      if (i % 2 != 0) {
        odd.add(i);
      } else {
        even.add(i);
      }
    }

    System.out.println(odd);
    System.out.println(even);
  }
}

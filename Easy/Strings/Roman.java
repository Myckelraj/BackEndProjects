package Zoho.Strings;
import java.util.HashMap;

public class Roman {
  public static void main(String[] args) {

    String st = "IV";
    HashMap<Character, Integer> val = new HashMap<>();

    val.put('I', 1);
    val.put('V', 5);
    val.put('X', 10);
    val.put('L', 50);
    val.put('C', 100);
    val.put('D', 500);
    val.put('M', 1000);

    int result = 0;
    int Prev = 0;

    for (int i = st.length() - 1; i >= 0; i--) {
      int curr = val.get(st.charAt(i));
      if (curr < Prev) {
        result = result - curr;
      } else {
        result = result + curr;
      }
      Prev = curr;
    }
    System.out.println(result);
  }
}

package Zoho.Strings;
import java.util.Objects;

public class DuplicateString {
  @SuppressWarnings("unlikely-arg-type")
  public static void main(String[] args) {
    String str = "Dig dung Dig";
    str = str.toLowerCase();
    String[] st = str.split(" ");
    for (int i = 0; i < st.length; i++) {
      int count = 1;
      for (int j = i + 1; j < st.length; j++) {
        if (st[i].equals(st[j]) && !st[j].equals(" ")) {
          count++;
          st[j] = "0";
        }
      }

      if (!Objects.equals(st[i], '0') && count > 1) {
        System.out.print(st[i]);
      }

    }
  }
}

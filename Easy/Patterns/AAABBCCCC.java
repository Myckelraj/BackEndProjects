package Zoho.Patterns;

public class AAABBCCCC {
  public static void main(String[] args) {
    String str = "aaabbcccc";
    StringBuilder builder = new StringBuilder();
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == str.charAt(i - 1)) {
        count++;
      } else {
        builder.append(str.charAt(i - 1)).append(count);
        count = 1;
      }
    }
    builder.append(str.charAt(str.length() - 1)).append(count);
    System.out.println(builder);
  }
}

package Zoho.Patterns;

public class A4B6C8 {
  public static void main(String[] args) {
    String str = "a4b5c6";
    StringBuilder builder = new StringBuilder();
    int index = 0;
    while (index < str.length()) {
      char letter = str.charAt(index++);
      int repeater = 0;
      while (index < str.length() && Character.isDigit(str.charAt(index))) {
        repeater = repeater * 10 + str.charAt(index) - '0';
      }
      for (int i = 0; i < repeater; i++) {
        builder.append(letter);
      }
    }
    builder.toString();
    System.out.println(builder);
  }

}

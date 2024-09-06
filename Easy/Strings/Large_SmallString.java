package Zoho.Strings;
public class Large_SmallString {
  public static void main(String[] args) {
    String str = "Practices makes a man perfect";
    StringBuilder builder = new StringBuilder();
    String[] words = new String[100];
    str = str + " ";
    int len = 0;
    String small, large;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) != ' ') {
        builder.append(str.charAt(i));
      } else {
        words[len++] = builder.toString();
        builder = new StringBuilder();
      }
    }
    small = large = words[0];
    for (int i = 0; i < len; i++) {
      if (small.length() > words[i].length()) {
        small = words[i];
      }
      if (large.length() < words[i].length()) {
        large = words[i];
      }
    }
    System.out.println("Smallest word: " + small);
    System.out.println("Largest word: " + large);

  }
}

package Zoho.Strings;
public class Frequency {
  public static void main(String[] args) {
    String str = "Picture perfectest";
    str = str.toLowerCase();
    char[] ch = str.toCharArray();

    for (int i = 0; i < ch.length; i++) {
      int count = 1;
      for (int j = i + 1; j < ch.length; j++) {
        if (ch[i] == ch[j]) {
          count++;
          ch[j] = '0';
        }
      }
      if (ch[i] != '0' && count > 1) {
        System.out.println(ch[i] + " " + count);
      }
    }
  }
}

package Zoho.Strings;
public class ReverseStringByRecursion {
  public static void main(String[] args) {
    String str="Hello my dear son";
    String rev=reverseRecursion(str);
    System.out.println(rev);
  }

  private static String reverseRecursion(String st)
  {
         if(st.isEmpty()) return st;
         return reverseRecursion(st.substring(1))+st.charAt(0);
  }
}

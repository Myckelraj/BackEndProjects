package Zoho.Strings;
public class StringPalindrome {
  public static void main(String[] args) {
    boolean flag=true;
    String str="M0M";
    str=str.toLowerCase();
    for(int i=0;i<str.length()/2;i++){
      if(str.charAt(i)!=str.charAt(str.length()-1-i))
      {
        flag=false;
        break;
      }
    }
 if(flag)
 {
  System.out.println("Palindrome");
 }else{
  System.out.println("Not Palindrome");
 }

  }
}

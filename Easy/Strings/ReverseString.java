package Zoho.Strings;

public class ReverseString {
  public static void main(String[] args) {
    String str="Myckelraj";

    /*
    Method 1
    char[] ch=str.toCharArray();
    for(int i=ch.length-1;i>=0;i--)
    {
        System.out.print(ch[i]);
    }
   */
    /* 
    Method 2
    StringBuilder builder=new StringBuilder();
    builder.append(str);
    builder.reverse();
    System.out.println(builder.toString());
     */
    
     char ch;
     String sh="";
     for(int i=0;i<str.length();i++)
     {
      ch=str.charAt(i);
      sh=ch+sh;
     }
System.out.println(sh);
  }
}

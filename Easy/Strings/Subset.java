package Zoho.Strings;
public class Subset {
  public static void main(String[] args) {
    String str= "FUN";
    int len =str.length(),temp=0;
    String[] st= new String[len*(len+1)/2];

    for(int i=0;i<len;i++)
    {
      for(int j=i;j<len;j++)
      {
          st[temp++]=str.substring(i, j+1);
      }
    }
  
    for (String s : st) {
      System.out.println(s);
    }

  }
}

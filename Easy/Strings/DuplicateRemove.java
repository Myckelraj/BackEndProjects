package Zoho.Strings;
//import java.util.HashSet;

public class DuplicateRemove {
  public static void main(String[] args) {
    
    String st="Funny";

    /* 
   StringBuilder builder=new StringBuilder();
   HashSet<Character> seen=new HashSet<>();
   
   for (char c:st.toCharArray()) {
    if(!seen.contains(c))
    {
      builder.append(c);
      seen.add(c);
    }
   }
   System.out.println(builder.toString());
*/

String result="";
for(int i=0;i<st.length();i++)
{
  char currentCharacter=st.charAt(i);
 if(result.indexOf(currentCharacter)==-1)
 {
  result+=currentCharacter;
 }
}
System.out.println(result);
  }
}

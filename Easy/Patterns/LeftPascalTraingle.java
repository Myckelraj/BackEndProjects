package Zoho.Patterns;
/*
      *
     **
    ***
   ****
  *****
 ******
*******
 ******
  *****
   ****
    ***
     **
      *
 */
public class LeftPascalTraingle {
    public static void main(String[] args) {
        int rows=7;
        for (int i=1;i<=rows;i++)
        {
            for (int space=1;space<=(rows-i);space++)
            {
                System.out.print(" ");
            }
            for (int j=1;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i=rows-1;i>=0;i--)
        {
            for (int space=rows;space>i;space--)
            {
                System.out.print(" ");
            }
            for (int j=1;j<=i;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

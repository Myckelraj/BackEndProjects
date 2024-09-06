package Zoho.Patterns;
/*
     0
    101
   21012
  3210123
 432101234
54321012345
 */
public class NumberPattern1
{
public static void main(String[] args)
{
    int times=5;
    for(int i=0;i<times;i++)
    {
        for(int j=0;j<=times-(i+1);j++)
        {
            System.out.print(" ");
        }
        for(int k=i;k>=0;k--)
        {
            System.out.print(k);
        }
        for(int k=1;k<=i;k++)
        {
            System.out.print(k);
        }
        System.out.println();

    }
} 

}

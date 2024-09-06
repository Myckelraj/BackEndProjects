package Zoho.Patterns;
/*
E D C B A 
D C B A 
C B A 
B A
A
 */
public class ALP6 
{
    public static void main(String[] args)
    {
       int num=5;
       int letter=64;
     for(int i=num;i>=1;i--)
     {
        for(int j=i;j>=1;j--)
        {
            System.out.print((char)(letter+j)+" ");
        }
        System.out.println();
     }
    }
}

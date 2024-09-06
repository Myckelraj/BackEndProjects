package Zoho.Patterns;

/*
A B C D E 
B C D E
C D E
D 
E
 */
/**
 * 
 */
public class ALP3
{
    public static void main(String[] args)
    {
        int num=5;
        int letter=64;
        for(int i=1;i<=num;i++)
        {
            for(int j=i;j<=num;j++)
            {
                System.out.print((char)(letter+j)+" ");
            }
            System.out.println();
        }     
    }
    
}

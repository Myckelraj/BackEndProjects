package Zoho.Patterns;
/*
E 
E D
E D C
E D C B
E D C B A
 */
public class ALP4
{
    public static void main(String[] args) {
        int num=5;
        int letter=64;
        for(int i=num;i>=1;i--)
        {
            for(int j=num;j>=i;j--)
            {
                System.out.print((char)(letter+j)+" ");
            }
            System.out.println();
        }

    }
    
}

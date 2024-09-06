package Zoho.Patterns;
/*
A 
B A
C B A
D C B A
E D C B A
 */
public class ALP1 
{
    public static void main(String[] args) {
        
     int num=5;
     int letter=64;
     for(int i=1;i<=num;i++)
     {
        for(int j=i;j>=1;j--)
        {
            System.out.print((char)(letter+j)+" ");
        }
        System.out.println();
     }

    }
    
}

package Zoho.Patterns;

/*
E D C B A 
E D C B
E D C
E D
E
 */
public class ALP5 {
    public static void main(String[] args) {
        
        int num=5;
        int letter=64;
        for(int i=1;i<=num;i++)
        {
            for(int j=num;j>=i;j--)
            {
                System.out.print((char)(letter+j)+" ");
            }
            System.out.println();
        }
    }
    
}

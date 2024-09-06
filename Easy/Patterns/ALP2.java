package Zoho.Patterns;
/*
A B C D E 
A B C D
A B C
A B
A
 */
public class ALP2 {
    public static void main(String[] args) {
        int num=5;
        int letter=64;
        for(int i=num;i>=1;i--){
            for(int j=1;j<=i;j++)
            {
              System.out.print((char)(letter+j)+" ");
            }
            System.out.println();
        }
    }
    
}

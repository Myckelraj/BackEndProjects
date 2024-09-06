package Zoho.Patterns;
/*
5 
5 4
5 4 3
5 4 3 2
5 4 3 2 1 
 */
public class NLP3 {
    public static void main(String[] args) {
        int num=5;
        for(int i=num;i>=1;i--)
        {
            for(int j=num;j>=i;j--)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}

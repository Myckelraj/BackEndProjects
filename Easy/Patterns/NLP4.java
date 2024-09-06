package Zoho.Patterns;

/*
5 4 3 2 1 
5 4 3 2
5 4 3
5 4
5 
*/
public class NLP4
{
    public static void main(String[] args) {
        int num=5;
        for(int i=1;i<=num;i++)
        {
            for(int j=num;j>=i;j--)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    
}

package Zoho.Patterns;
/*
1
2 1
3 2 1
4 3 2 1
5 4 3 2 1 
 */
public class NLP1
{
    public static void main(String[] args) {
        int num=5;
        for(int i=1;i<=num;i++)
        {
            for(int j=i;j>=1;j--)
            {
                System.out.print(j+" ");
            }
            System.out.println();
    }
}
}

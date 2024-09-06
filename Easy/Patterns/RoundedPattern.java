package Zoho.Patterns;

/*
4 4 4 4 4 4 4 
4 3 3 3 3 3 4 
4 3 2 2 2 3 4 
4 3 2 1 2 3 4 
4 3 2 2 2 3 4 
4 3 3 3 3 3 4 
4 4 4 4 4 4 4  
 */
public class RoundedPattern 
{
    public static void main(String[] args)
    {
      int num=4;
      int n=2*num-1;

      for(int i=1;i<=n;i++)
      {
        for(int j=1;j<=n;j++)
        {
            System.out.print(Math.max(Math.abs(i-num),Math.abs(j-num))+1+" ");
        }
        System.out.println();
      }
        
    }
}

package Zoho.Arrays;
class PrimeNumber {
    public static void main(String[] args) {
        for(int i=1;i<100;i++)
    {
        if(isPrime(i))
         System.out.print(i+" ");
    }
    }

    private static boolean isPrime(int n)
{
    boolean flag=true;
 int j;
    if(n==1 || n==0)
    {
        flag=false;
    }
    for(j=2;j<=n/2;j++)
    {
        if(n%j==0)
        {
            flag=false;
            break;
        }
    }
   return flag;
}

}

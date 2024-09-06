package Zoho.Arrays;

public class SortedArray {
  public static void main(String[] args) {
    int[] arr= { 12,11,9,45,6,34};
    int n=arr.length;
    sort(arr,n);
    for(int ne:arr)
    {
      System.out.print(ne+" ");
    }
  }
private static void sort(int[] arr,int n)
{
    for(int i=0;i<n;i++)
    {
      for(int j=i+1;j<n;j++)
      {
        if(arr[i]>arr[j])
        {
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
      }
    }
}
  
   
}

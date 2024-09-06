package Zoho.Arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReflectionArrays {
  public static void main(String[] args) 
  {
     int arr=7;

     int[] intArray=(int[])Array.newInstance(int.class, arr);

    Array.setInt(intArray, 0, 100);
    Array.setInt(intArray, 1, 200);
    Array.setInt(intArray, 2, 300);

    System.out.println(Arrays.toString(intArray));

  }
}

package Zoho.Arrays;
public class LeapYearArray {
  public static void main(String[] args) {
    int year = 2009;
    boolean result = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? true : false;
    if (result) {
      System.out.println("Leap Year");
    } else {
      System.out.println("Not Leap Year");
    }
  }
}

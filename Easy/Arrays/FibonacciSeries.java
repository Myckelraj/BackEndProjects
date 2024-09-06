package Zoho.Arrays;
public class FibonacciSeries {
  static int n1 = 0, n2 = 1, times = 20;

  public static void main(String[] args) {

    /*
     * int num1 = 0;
     * int num2 = 1;
     * int num3;
     * System.out.print(num1 + " " + num2 + " ");
     * for (int i = 1; i < 10; i++) {
     * num3 = num1 + num2;
     * System.out.print(num3 + " ");
     * num1 = num2;
     * num2 = num3;
     * }
     */

    System.out.print(n1 + " " + n2 + " ");
    fibo(times);
  }

  private static void fibo(int times) {
    if (times > 0) {
      int n3 = n1 + n2;
      n1 = n2;
      n2 = n3;
      System.out.print(n3 + " ");
      fibo(times - 1);
    }
  }

}

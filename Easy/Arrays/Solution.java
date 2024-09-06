package Zoho.Arrays;
class Solution {
  public static void main(String[] args) {
    int[] testValues = { 4, 6, 8, 15 };
    for (int N : testValues) {
      System.out.println("For N = " + N + ": " + calculateProduct(N));
    }
  }

  public static int calculateProduct(int N) {
    int sumComponent = 1;

    // Find the component to break N into
    for (int i = 2; i <= N; i++) {
      if (N % i == 0) {
        sumComponent = i;
        break;
      }
    }

    int quotient = N / sumComponent;
    int product = 1;

    for (int i = 0; i < quotient; i++) {
      product *= sumComponent;
    }

    return product;
  }
}

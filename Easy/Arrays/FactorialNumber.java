package Zoho.Arrays;
public class FactorialNumber {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            factorial(i);
        }
    }

    private static void factorial(int i) {
        int fact = 1;
        for (int j = 1; j <= i; j++) {
            fact = fact * j;
        }

        System.out.print(fact + " ");
    }

    /*
     * Recursion
     * private static long factorial(int num)
     * {
     * if(num==0)
     * {
     * return 1;
     * }
     * else{
     * return num * factorial((num-1));
     * }
     * }
     */

}

package Zoho.Arrays;
public class squareRoot {
    public static void main(String[] args) {
        int number = 64;
        double guess = number / 2;
        double ep = 1e-15;
        while (Math.abs(guess * guess - number) > ep) {
            guess = (guess + number / guess) / 2.0;
        }
        System.out.println(guess);

    }
}
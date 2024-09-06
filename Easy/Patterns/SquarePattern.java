package Zoho.Patterns;

public class SquarePattern {
    public static void main(String[] args) {
        int num = 5;
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num; j++) {
                System.out.print("*" + " ");
            }
            System.out.println();
        }
    }

}

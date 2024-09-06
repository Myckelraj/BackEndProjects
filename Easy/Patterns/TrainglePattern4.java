package Zoho.Patterns;

public class TrainglePattern4 {
    public static void main(String[] args) {
        int num=7;
        for (int i=num;i>=0;i--){
            for (int j=i;j>=1;j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

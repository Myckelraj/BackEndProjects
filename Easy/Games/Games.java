import java.util.Random;
import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the choice below");
        System.out.println("1.Rock");
        System.out.println("2.Paper");
        System.out.println("3.Scissors");
        System.out.println("4.Exit");
        System.out.println("Choose anyone from these options above: ");
        int choice = sc.nextInt();
        if (choice == 4) {
            System.out.println("Thanks for playing");
        }

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid Entered");
        }
        int computerChoice = random.nextInt(3) + 1;
        System.out.println("Computer Choice " + getChoice(computerChoice));
        System.out.println("Your choice " + getChoice(choice));

        if (choice == computerChoice) {
            System.out.println("Tie Game");
        } else if ((choice == 1 && computerChoice == 3) || (choice == 2 && computerChoice == 1)
                || (choice == 3 && computerChoice == 2)) {
            System.out.println("YOU Win");
        } else {
            System.out.println("Computer Wins");
        }
        sc.close();
    }

    private static String getChoice(int choice) {
        switch (choice) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";

            default:
                return "Invalid";
        }
    }
}
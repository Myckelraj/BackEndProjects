public class Competition {
    public static void main(String[] args) {
                System.out.println("Welcome To Game World:");
        
         int rounds[][] = {
                    {90, 82, 23, 33, 43},
                    {10, 42, 31, 21, 61},
                    {50, 33, 92, 54, 63}
                };
                
         for (int i = 0; i < rounds.length; i++) {
                    int winner = findWinner(rounds[i]);
                    System.out.println("Round " + (i + 1) + " Winner is: " + "player " + winner+ " Score= " + rounds[i][winner-1]);
                }
            }
        
            static int findWinner(int arr[]) {
                int max = arr[0];
                int maxIndex=0;
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] > max) {
                        max = arr[i];
                        maxIndex=i;
                    }
                }
        
                return maxIndex+1;
            }
}
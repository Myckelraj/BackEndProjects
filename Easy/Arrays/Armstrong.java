package Zoho.Arrays;
//a number that is equal to the sum of cubes of its digits.
// 1*1*1 + 5*5*5 + 3*3*3 = 1 + 125  + 27 = 153
class Armstrong {
  public static void main(String[] args) {
    int num = 1000;
    for (int i = 1; i <= num; i++) {
      if (isArmstrong(i)) {
        System.out.print(i + " ");
      }
    }
  }

  private static boolean isArmstrong(int num) {
    int sum = 0, temp = num, rem;
    while (num != 0) {
      rem = num % 10;
      sum= (int) (sum+ Math.pow(rem,3)); 
      num = num / 10;
    }
    return sum == temp;
  }
}



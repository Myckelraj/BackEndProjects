package Zoho.Strings;
class NumbertoWord {

  public static void main(String[] args) {
    int num = 1335;
    String str;
    if (num % 100 < 20) {
      str = oneDigit[num % 100];
      num = num / 100;
    } else {
      str = oneDigit[num % 10];
      num = num / 10;
      str = twoDigit[num % 10] + str;
      num = num / 10;
    }
    if (num == 0) {
      System.out.print(str.trim());
    } else {
      System.out.print(oneDigit[num] + " Hundred " + str.trim());
    }
  }

  private static final String[] twoDigit = {
      "", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty",
      " Seventy", " Eighty", " Ninety"
  };

  private static final String[] oneDigit = {
      "", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine",
      " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen",
      " Seventeen", " Eighteen", " Nineteen"
  };

}

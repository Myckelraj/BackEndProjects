interface Rbi
{
  public abstract void fixedDeposit();
}

interface Sbi
{
  public abstract void recurredDeposit();
  public abstract void fixedDeposit();
}

class PallvanBank implements Rbi,Sbi {

  @Override
  public void recurredDeposit() {
   System.out.println("It provides RD Interest rate 4%");
  }

  @Override
  public void fixedDeposit() {
   System.out.println("It provides FD Interest rate 5%");
  } 
}


public class Interface {
  public static void main(String[] args) {
    Sbi sb=new PallvanBank();
    sb.fixedDeposit();
    sb.recurredDeposit();
  }
}
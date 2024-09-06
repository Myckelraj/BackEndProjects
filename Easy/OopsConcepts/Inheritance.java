public class Inheritance {
  public static void main(String[] args) {
    Animals al=new Tiger();
    al.sound();
  }
}

class Animals
{
  public void sound(){
    System.out.println("Sounds are created from animals");
  }
}

class Tiger extends Animals
{
  public void sound()
  {
    System.out.println("Hukum ");
  }
}

class Cat extends Animals
{
  public void sound()
  {
    System.out.println("Meow Meow");
  }
}

public class Abstraction {
  public static void main(String[] args) {

    Rectangle rt=new Rectangle(12, 13);
      double rectangleArea = rt.area();
      System.out.println("Area of rectangle: " + rectangleArea);

      Circle cl=new Circle(8);
      double circleArea=cl.area();
      System.out.println("Area of a Circle: "+circleArea);
  }
  
  }

abstract class Shape
{
    public  abstract double area();
}

//Rectangle method 
class Rectangle extends Shape{
  public double length;
  public double breadth;

  public Rectangle(double length, double breadth)
  {
    this.length=length;
    this.breadth=breadth;
  }

  public  double area(){
      return length*breadth;
  }
}


//Circle method
class Circle extends Shape{
 public double radius;

 public Circle(double radius)
 {
   this.radius=radius;
 }

 public double area()
 {
  return Math.PI*radius*radius;
 }
}


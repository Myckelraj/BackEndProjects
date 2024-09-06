import java.util.*;

public class Encapsulation {
    public static void main(String[] args) {
    
    List<College> arrayList = new ArrayList<>();
    College cl = new College();
    cl.setName("Myckelraj");
    cl.setId(99051577);
    cl.setDepartment("Electrical and Electronics Engineering");
    arrayList.add(cl);
    
    for (College cls : arrayList) { // Iterating over the College ArrayList
      System.out.println(cls.getId());
      System.out.println(cls.getName());
      System.out.println(cls.getDepartment());
  }
    }

}


//Getter and Setter Method 
class College
{
  private String name ;
  private int id;
  private String department;

  public String getName()
  {
    return name;
  }

   public void setName(String name)
   {
       this.name=name;
   }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }   
}
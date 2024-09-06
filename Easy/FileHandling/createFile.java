import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class createFile {
    public static void main(String[] args) {
/*try{
            byte[] br={97,77,87,99};
            OutputStream fo= new FileOutputStream("text2.txt");
for (int i=0;i<br.length;i++) {
    fo.write(br[i]);
}
fo.close();

 InputStream io= new FileInputStream("tes2.txt");
 int sie=io.available();

 for(int i=0;i<sie;i++)
 {
      System.out.println((char)io.read()+"");
 }
 io.close();
}
catch(Exception e)
{
    System.out.println("Excption");
}*/
  try {
          File file = new File("text3.txt");
          
          //Create the file
          if (file.createNewFile()) { 
             System.out.println("File is created!");
          } else {
             System.out.println("File already exists.");
          } 
          
          // Write Content
          FileWriter writer = new FileWriter(file);
          writer.write("Test data");
          writer.close();
          
          
          // read content
          FileReader reader = new FileReader(file);
          
          int c;
          while ((c = reader.read()) != -1) {
        	  char ch = (char) c;
              System.out.print(ch);
          }
          reader.close();
      } catch (IOException e) {
         System.out.print("Exception");
      }	
    }
}
import java.io.File;
import java.util.*;

public class ReadFile {
    public static void main(String[] args) {

        try {
            File f= new File("FileHandling/text1.txt");
            Scanner sc = new Scanner(f); 
            while(sc.hasNextLine())
            {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (Exception e) {
          System.out.println(e);
        }
    }
}

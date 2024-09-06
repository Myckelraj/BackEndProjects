import java.io.File;
import java.io.FileWriter;

public class WriteFile {
    public static void main(String[] args) {
        try{
            File f= new File("FileHandling/text1.txt");
            FileWriter fw= new FileWriter(f);
            fw.write("I am graduated from GCE,Salem");
            fw.close();

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

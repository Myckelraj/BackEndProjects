import java.io.File;

public class deleteFile {
    public static void main(String[] args) {
        File file= new File("text3.txt");
        if(file.exists())
        {
            file.delete();
        }
    }
}

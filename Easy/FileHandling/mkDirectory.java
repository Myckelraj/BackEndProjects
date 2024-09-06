import java.io.File;

public class mkDirectory {
    public static void main(String[] args) {
        
        String dir="M:/JavaProgramming/FileHandling";
        File directory= new File(dir);

        directory.mkdirs();
        File file= new File("M:/JavaProgramming/FileHandling");
        System.out.println(file.exists());
    }
}

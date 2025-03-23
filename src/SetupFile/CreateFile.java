
package setupFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.io.File;

// nơi tạo file
public class CreateFile {
    public static void main(String[] args) {
        try {
            File myFile = new File("carts.txt");
            if (myFile.createNewFile()) {
                System.out.println("File with " + myFile.getName());
            } else {
                System.out.println("File already exist");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
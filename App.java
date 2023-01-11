import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class App {
    /**
     * @throws Exception
     * @param args
     */
    public static void main(String[] args) throws Exception {
        
        Path p = Paths.get("c://test//t1.txt");

        System.out.println("Root: " + p.getRoot().toString());
        System.out.println(p.getFileName().toString());
        System.out.println(p.getParent().toString());
        System.out.println(p.getNameCount());

        URI u = URI.create("https://www.google.com.ua/images/srpr/logo11w.png");
        Path ps = Paths.get("c://test//www123.png");
        // Path file = Files.createFile(Paths.get("c://test//www1.png"));

        System.out.println("Copying https://www.google.com.ua/images/srpr/logo11w.png");
        try (InputStream is = u.toURL().openStream();) {
            Files.copy(is, ps, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copying done.");
            System.out.println("App.main()");
        }

        Path zipFile = Paths.get("c://test//zippp.zip");
        Path fileToArc = Paths.get("c://test//t3.txt");

        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFile.toFile(), false));) {
            zip.putNextEntry(new ZipEntry("file_in_arc.txt"));
            Files.copy(fileToArc, zip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String jarCanonicalPath
                = Main.class.getResource("").getPath() + "casebook.jar";

        new JarUnzipUtil().unzipJar(jarCanonicalPath);
    }
}

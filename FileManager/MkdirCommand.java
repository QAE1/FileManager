import java.io.File;
import java.nio.file.Path;

public class MkdirCommand implements InterfaceCommand{
    @Override
    public Path execute(String[] path, Path currentPath) {
        try {
            Path filepath = currentPath.resolve(path[1]);//Заменил на path.resolve
            File directory = new File(filepath.toUri());
            if (directory.exists()) {
                System.out.println("Директория уже создана");
            } else {
                directory.mkdirs();
                System.out.println("Директория " + path[1] + " успешно создана");
            }
        } catch (SecurityException exception) {
            System.out.println(exception.getMessage()); // обработать корректно, не просто сообщение
        }
        return currentPath;
    }
}

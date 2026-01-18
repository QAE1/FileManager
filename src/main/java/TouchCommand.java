import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TouchCommand implements InterfaceCommand {
    @Override
    public Path execute(String[] path, Path currentPath) {
        try {
            Path path2 = Paths.get(currentPath.resolve(path[1]).toUri());
            if (path2.toFile().exists()) {
                System.out.println("Файл уже существует");
            } else {
                Files.createFile(path2);
                System.out.println("Файл " + path2.getFileName() + " успешно создан");
            }
        } catch (SecurityException | IOException exception) {
            System.out.println(exception.getMessage());
        }
        return currentPath;
    }
}

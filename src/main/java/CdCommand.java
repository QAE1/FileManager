import java.io.File;
import java.nio.file.Path;

public class CdCommand implements InterfaceCommand {

    @Override
    public Path execute(String[] path, Path currentPath) {

        Path cdPath = Path.of(path[1]);
        if (!cdPath.isAbsolute()) {
            cdPath = currentPath.resolve(cdPath).normalize();
        }
        File dir = new File(cdPath.toUri()); // добавить проверку наличия path[1]; использовать Path API ДОБАВИЛ ВЫШЕ
        if (dir.exists() && dir.isDirectory()) { // добавить проверку dir.isDirectory() ГОТОВО
            currentPath = Path.of(cdPath.toUri()); // использовать Path.resolve(...) вместо строки КАК СЕЙЧАС ПОДОЙДЕТ? Сделал через патчи
            System.out.println(currentPath);
        } else {
            System.out.println("Путь не существует");
        }
        return currentPath;
    }
}

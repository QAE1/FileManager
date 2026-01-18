import java.io.File;
import java.nio.file.Path;

public class MkdirCommand implements InterfaceCommand {
    @Override
    public Path execute(String[] path, Path currentPath) {
        try {
            Path filepath = currentPath.resolve(path[1]);
            File directory = new File(filepath.toUri());
            if (directory.exists()) {
                System.out.println("Директория уже создана");
            } else {
                directory.mkdirs();
                System.out.println("Директория " + path[1] + " успешно создана");
            }
        } catch (SecurityException exception) {
            System.err.println("Ошибка безопасности: Нет прав для создания директории.");
            System.err.println("Подробности: " + exception.getMessage());
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка при создании директории: " + e.getClass().getSimpleName());
            System.err.println("Сообщение: " + e.getMessage());
        }
        return currentPath;
    }
}

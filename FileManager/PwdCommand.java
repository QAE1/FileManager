import java.nio.file.Path;
import java.nio.file.Paths;

public class PwdCommand implements InterfaceCommand{
    @Override
    public Path execute(String[] args, Path currentPath) {
        System.out.println("Рабочая директория - " + Paths.get("").toAbsolutePath());
        return null;
    }
}

import java.nio.file.Path;

public interface InterfaceCommand {
    Path execute(String[] args, Path currentPath);
}

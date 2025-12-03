import java.nio.file.Path;

public class ExitCommand implements InterfaceCommand{
    @Override
    public Path execute(String[] args, Path currentPath) {
        System.exit(0);
        return null;
    }
}

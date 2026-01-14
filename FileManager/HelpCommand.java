import java.nio.file.Path;

public class HelpCommand implements InterfaceCommand {
    @Override
    public Path execute(String[] args, Path currentPath) {
        System.out.println(ConsoleController.LISTOFCOOMANDS);
        return currentPath;
    }
}

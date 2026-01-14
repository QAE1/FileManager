import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private final Map<String, InterfaceCommand> commands;

    public CommandHandler() {
        this.commands = new HashMap<>();
        initializeCommands();
    }

    private void initializeCommands() {
        commands.put("ls", new LsCommand());
        commands.put("cd", new CdCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("touch", new TouchCommand());
        commands.put("pwd", new PwdCommand());
        commands.put("getsize", new GetSizeCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
    }


    public Path executeCommand(String commandName, Path currentPath, String[] path) {
        String commandNameToLowerCase = commandName.toLowerCase();
        InterfaceCommand command = commands.get(commandNameToLowerCase);
        Path resultPath = null;
        if (command != null) {
            boolean requiresPath = commandNameToLowerCase.equals("cd") ||
                    commandNameToLowerCase.equals("mkdir") ||
                    commandNameToLowerCase.equals("touch") ||
                    commandNameToLowerCase.equals("getsize");

            if (requiresPath) {
                if (path.length <= 1 || path[1] == null || path[1].trim().isEmpty()) {
                    System.out.println("Введено неверное значение пути для команды: " + commandNameToLowerCase);
                    return null; // или другое значение по умолчанию
                }
            }
            resultPath = command.execute(path, currentPath);
        } else {
            System.out.println("Неизвестная команда: " + commandNameToLowerCase);
        }

        return resultPath;
    }
}

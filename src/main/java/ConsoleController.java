import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleController {

    public static final String LISTOFCOOMANDS =
            "Вас приветствует файловый менеджер 'Doggy'\n" +
                    "Доступный список команд:\n" +
                    "ls - Показать содержимое текущей директории (файлы и папки).  ls\n" +
                    "cd <path> - Сменить текущую директорию.  cd .., cd /home/user/docs, cd project\n" +
                    "pwd - Показать текущий рабочий путь.  pwd\n" +
                    "mkdir <name> - Создать новую папку (директорию).  mkdir NewFolder\n" +
                    "touch <name> - Создать новый пустой файл.  touch newfile.txt\n" +
                    "help - Показать список доступных команд. \n" +
                    "exit - Завершить работу программы. \n";

    void start() {
        System.out.println(LISTOFCOOMANDS);
        Path currentPath = null;
        CommandHandler commandHandler = new CommandHandler();
        try (Scanner scanner = new Scanner(System.in)) { // корректно
            while (true) {
                String input = scanner.nextLine();  // добавить валидацию пустой строки
                if (input.trim().isEmpty()) {
                    System.out.println("Отправлена пустая строка. Повторите попытку"); //UPD: Исправил
                    continue;
                }

                String[] path = input.split(" ");  // проверить наличие аргументов при командах cd/mkdir/touch
                String commandName = path[0];
                currentPath = commandHandler.executeCommand(commandName, currentPath, path);
            }
        }
    }
}

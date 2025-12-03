import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    void start() {
        String listOfCommands = "Вас приветсвует файловый менеджер 'Doggy'\n" +
                "Доступный список команд:\n" +
                "ls - Показать содержимое текущей директории (файлы и папки).  ls\n" +
                "cd <path> - Сменить текущую директорию.  cd .., cd /home/user/docs, cd project\n" +
                "pwd - Показать текущий рабочий путь.  pwd\n" +
                "mkdir <name> - Создать новую папку (директорию).  mkdir NewFolder\n" +
                "touch <name> - Создать новый пустой файл.  touch newfile.txt\n" +
                "help - Показать список доступных команд.  help\n" +
                "exit - Завершить работу программы.  exit\n";

        System.out.println(listOfCommands);
        Path currentPath = null;
        try (Scanner scanner = new Scanner(System.in)) { // корректно
            while (true) {
                String input = scanner.nextLine();  // добавить валидацию пустой строки
                if (input.trim().isEmpty()) {
                    System.out.println("Отправлена пустая строка. Повторите попытку"); //UPD: Исправил
                    continue;
                }

                String[] path = input.split(" ");  // проверить наличие аргументов при командах cd/mkdir/touch
                String commandName = path[0];
                CommandHandler commandHandler = new CommandHandler();
                currentPath= commandHandler.executeCommand(commandName, currentPath, path);
            }
        }
    }
}

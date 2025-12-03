import java.nio.file.Path;

public class HelpCommand implements InterfaceCommand{
    @Override
    public Path execute(String[] args, Path currentPath) {
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
        return null;
    }
}

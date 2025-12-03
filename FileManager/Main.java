import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

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
        //String globalPath = "";  // заменить на Path currentPath и работать через Path API

        try (Scanner scanner = new Scanner(System.in)) { // корректно
            while (true) {
                String input = scanner.nextLine();  // добавить валидацию пустой строки
                if(input.trim().isEmpty()){
                    System.out.println("Отправлена пустая строка. Повторите попытку"); //UPD: Исправил
                    continue;
                }


                String[] path = input.split(" ");  // проверить наличие аргументов при командах cd/mkdir/touch

                switch (path[0]) {  // вынести обработку команд в отдельные классы (Command pattern)
                    case "ls":
                        //System.out.println(currentPath);
                        if (currentPath==null) { // заменить на .isEmpty() или .equals("")ТЕПЕРЬ сравниваю с null
                            Path currentWorkingDirectory= Paths.get("").toAbsolutePath(); // использовать Path currentPath ГОТОВО
                            currentPath = Path.of(currentWorkingDirectory.toUri());
                            System.out.println(currentWorkingDirectory);
                            printListOfFiles(String.valueOf(currentWorkingDirectory));
                        } else {
                            printListOfFiles(String.valueOf(currentPath));
                        }
                        break;

                    case "cd":
                        if (path.length > 1 && path[1] != null && !path[1].trim().isEmpty()) {
                            Path cdPath = Path.of(path[1]);
                            File dir = new File(cdPath.toUri()); // добавить проверку наличия path[1]; использовать Path API ДОБАВИЛ ВЫШЕ
                            if (dir.exists()&&dir.isDirectory()) { // добавить проверку dir.isDirectory() ГОТОВО
                                currentPath = Path.of(cdPath.toUri()); // использовать Path.resolve(...) вместо строки КАК СЕЙЧАС ПОДОЙДЕТ? Сделал через патчи
                                System.out.println(currentPath);
                            } else {
                                System.out.println("Путь не существует");
                            }
                        } else {
                            System.out.println("Введено не верное значение пути");
                            continue;
                        }
                        break;

                    case "pwd":
                        System.out.println("Рабочая директория - " + Paths.get("").toAbsolutePath()); // использовать currentPath вместо системного ГОТОВО
                        break;

                    case "mkdir":
                        try {
                            Path filepath = currentPath.resolve(path[1]);//Заменил на path.resolve
                            File directory = new File(filepath.toUri());
                            if (directory.exists()) {
                                System.out.println("Директория уже создана");
                            } else {
                                directory.mkdirs();
                                System.out.println("Директория " + path[1] + " успешно создана");
                            }
                        } catch (SecurityException exception) {
                            System.out.println(exception.getMessage()); // обработать корректно, не просто сообщение
                        }
                        break; // Вынес brake после кейса


                    case "touch":
                        try {
                            Path path2 = Paths.get(currentPath.resolve(path[1]).toUri()); //Переписал через Path.resolve()
                            if (path2.toFile().exists()) {
                                System.out.println("Файл уже существует");
                            } else {
                                Files.createFile(path2);
                                System.out.println("Файл " + path2.getFileName() + " успешно создан");
                            }
                        } catch (SecurityException exception) {
                            System.out.println(exception.getMessage()); // обработать корректно
                        }
                        break;

                    case "getSize":
                        List<Path> fileList= Files.walk(Path.of(path[1])).toList();
                        double result = 0.0;
                        for (Path ph : fileList) {
                            result += Files.size(ph);
                        }
                        if(result<1048576 ){
                            System.out.println(result/1024.0+" кб");
                        } else if (result<1073741824) {
                            System.out.println(Math.round(result/(1024.0*1024.0))+" мб");
                        }
                        else {
                            System.out.println(Math.round(result/(1024.0*1024.0*1024.0))+" гб");
                        }
                        break;


                    case "help":
                        System.out.println(listOfCommands);
                        break;

                    case "exit":
                        System.exit(0);

                    default:
                        System.out.println("Введена неверная команда"); // лучше уточнить
                }
            }
        }
    }

    private static void printListOfFiles(String s) {
        Path ph = Path.of(s);
        if (Files.isDirectory(ph)){
            try (Stream<Path> stream = Files.list(ph)) {
                List<Path> str= stream.toList();
                for(Path st:str) {
                    if (Files.isDirectory(st)) {
                        System.out.println(st.getFileName() + " Folder");
                    } else {
                        System.out.println(st.getFileName() + " File");
                    }
                }
            }
            catch (Exception e){

            }
        }
    }



    /*private static boolean validateCommand_cd_mkdir_touch(String [] arr){
        boolean flag = false;
        if(arr[0]=="ls"||arr[0]=="pwd"&&arr.length==1){
            flag= true;
        }
        return flag;
    }*/ // сравнение строк через == некорректно; нужно дописать проверку аргументов
}

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagerApp {
    public static void main(String[] args) throws IOException {

        String listOfCommands="Вас приветсвует файловый менеджер 'Doggy'\n" +
                "Доступный список команд:\n"+
                "ls - Показать содержимое текущей директории (файлы и папки).  ls\n" +
                "cd <path> - Сменить текущую директорию.  cd .., cd /home/user/docs, cd project\n" +
                "pwd - Показать текущий рабочий путь.  pwd\n" +
                "mkdir <name> - Создать новую папку (директорию).  mkdir NewFolder\n" +
                "touch <name> - Создать новый пустой файл.  touch newfile.txt\n" +
                "help - Показать список доступных команд.  help\n" +
                "exit - Завершить работу программы.  exit\n";


        System.out.println(listOfCommands);

        String globalPath = "";

        try(Scanner scanner = new Scanner(System.in))
        {
            while (true){
                String input = scanner.nextLine();

                String [] path= input.split(" ");

                //validateCommand_cd_mkdir_touch(path);

                switch (path[0]){
                    case "ls":
                        System.out.println(globalPath);
                        if(globalPath==""){
                            String pwdPath=System.getProperty("user.dir");
                            globalPath=pwdPath;
                            System.out.println(pwdPath);
                            printListOfFiles(pwdPath);
                        }
                        else {
                            printListOfFiles(globalPath);
                        }
                        break;

                    case "cd":
                        File dir = new File(path[1]);
                        if(dir.exists()){
                            globalPath=path[1];
                            System.out.println(globalPath);
                        }
                        else{
                            System.out.println("Путь не существует");
                        }
                        break;

                    case "pwd":
                        System.out.println("Рабочая директория - " + System.getProperty("user.dir"));
                        break;

                    case "mkdir":
                        try{
                            String filepath=globalPath+"\\"+path[1];
                            File directory = new File(filepath);
                            if(directory.exists()){
                                System.out.println("Директория уже создана");
                            }
                            else {
                                directory.mkdirs();
                                System.out.println("Директория "+path[1]+" успешно создана");
                                break;
                            }
                        }
                        catch (SecurityException exception){
                            System.out.println(exception.getMessage());
                        }


                    case "touch":
                        try {
                            Path path2 = Paths.get(globalPath+"\\"+path[1]+".txt");
                            if(path2.toFile().exists()){
                                System.out.println("Файл уже существует");
                            }
                            else {
                                Files.createFile(path2);
                                System.out.println("Файл " + path2.getFileName()+" успешно создан");
                            }
                        }catch (SecurityException exception ){
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case "getSize":
                        List<File> fileList = new ArrayList<>();
                        getSize(path[1], fileList);
                        Double result = 0.0;
                        for(File fl:fileList){

                             result+= fl.length() / (1024.0 * 1024.0);
                        }
                        System.out.println("Размер папки " + Math.round(result)+" мб");
                        break;
                    case "help":
                        System.out.println(listOfCommands); break;

                    case "exit": System.exit(0);
                    default:
                        System.out.println("Введена не верная команда");
                }
        }
    }

}

    private static void printListOfFiles(String s) {


        File dir = new File(s);
        if(dir.isDirectory())
        {
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){

                if(item.isDirectory()){
                    System.out.println(item.getName()+" Folder");
                }
                else{
                    System.out.println(item.getName()+" File");
                }
            }
        }

    }

    private static void getSize(String s, List<File> fileList) {

        File dir = new File(s);
        if(dir.isDirectory())
        {
            // получаем все вложенные объекты в каталоге
            for(File item : dir.listFiles()){
                if(item.isDirectory()){
                    getSize(item.getAbsolutePath(), fileList);
                }
                else{
                    fileList.add(item);
                }
            }
        }



        /*for (File fl : fileList) {
            double sizeMB = fl.length() / (1024.0 * 1024.0);
            System.out.printf("%s: %.2f MB%n", fl.getName(), sizeMB);
        }*/
    }

    /*private static boolean validateCommand_cd_mkdir_touch(String [] arr){
        boolean flag = false;
        if(arr[0]=="ls"||arr[0]=="pwd"&&arr.length==1){
            flag= true;
        }
        return flag;
    }*/
}



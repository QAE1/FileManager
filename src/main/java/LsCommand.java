import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LsCommand implements InterfaceCommand {

    @Override
    public Path execute(String[] path, Path currentPath) {
        if (currentPath == null) {
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            currentPath = Path.of(currentWorkingDirectory.toUri());
            System.out.println(currentWorkingDirectory);
            printListOfFiles(String.valueOf(currentWorkingDirectory));
        } else {
            printListOfFiles(String.valueOf(currentPath));
        }
        return currentPath;
    }

    private static void printListOfFiles(String s) {
        Path ph = Path.of(s);
        if (Files.isDirectory(ph)) {
            try (Stream<Path> stream = Files.list(ph)) {
                List<Path> str = stream.toList();
                for (Path st : str) {
                    if (Files.isDirectory(st)) {
                        System.out.println(st.getFileName() + " Folder");
                    } else {
                        System.out.println(st.getFileName() + " File");
                    }
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка при выводе списка файлов");
                e.printStackTrace();//Как разберусь с логером, планирую переписать
            }
        }
    }
}

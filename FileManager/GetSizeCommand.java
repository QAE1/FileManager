import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class GetSizeCommand implements InterfaceCommand {
    @Override
    public Path execute(String[] path, Path currentPath) {
        try {
            try (Stream<Path> lifOfFiles = Files.walk(Path.of(path[1]))
                    .filter(path2 -> Files.isRegularFile(path2))) {

                List<Path> fileList = lifOfFiles.toList();
                double result = 0.0;
                for (Path ph : fileList) {
                    result += Files.size(ph);
                }
                if (result < 1048576) {
                    System.out.println(result / 1024.0 + " кб");
                } else if (result < 1073741824) {
                    System.out.println(Math.round(result / (1024.0 * 1024.0)) + " мб");
                } else {
                    System.out.println(Math.round(result / (1024.0 * 1024.0 * 1024.0)) + " гб");
                }
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return currentPath;
    }
}

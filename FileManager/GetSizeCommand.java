import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GetSizeCommand implements InterfaceCommand{
    @Override
    public Path execute(String[] path, Path currentPath) {
        try{
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
        } catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
        return null;
    }
}

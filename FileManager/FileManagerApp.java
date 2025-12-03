import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileManagerApp {
    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController();
        consoleController.start();
    }
}
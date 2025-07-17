import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int validFileCount = 0;

        while (true) {
            System.out.print("Введите путь к файлу: ");
            String path = scanner.nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!fileExists) {
                System.out.println("Файл не существует. Повторите попытку.");
                continue;
            }

            if (isDirectory) {
                System.out.println("Указанный путь ведет к папке, а не к файлу. Повторите попытку.");
                continue;
            }

            validFileCount++;
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + validFileCount);
        }
    }
}
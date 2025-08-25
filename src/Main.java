import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int validFileCount = 0;

        while (true) {
            System.out.print("Введите путь к файлу (или 'exit' для выхода): ");
            String path = scanner.nextLine();

            if (path.equalsIgnoreCase("exit")) {
                System.out.println("Программа завершена. Всего верных файлов: " + validFileCount);
                break;
            }

            File file = new File(path);

            if (!file.exists()) {
                System.out.println("Файл не существует. Повторите попытку.");
                continue;
            }

            if (file.isDirectory()) {
                System.out.println("Указанный путь ведет к папке, а не к файлу. Повторите попытку.");
                continue;
            }

            validFileCount++;
            System.out.println("Путь указан верно. Это файл номер " + validFileCount);

            // чтение файла
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);

                int lineCount = 0;
                int maxLength = Integer.MIN_VALUE;
                int minLength = Integer.MAX_VALUE;

                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();

                    if (length > 1024) {
                        throw new RuntimeException("Ошибка: строка длиннее 1024 символов!");
                    }

                    lineCount++;
                    if (length > maxLength) maxLength = length;
                    if (length < minLength) minLength = length;
                }

                reader.close();

                System.out.println("Количество строк в файле: " + lineCount);
                if (lineCount > 0) {
                    System.out.println("Длина самой длинной строки: " + maxLength);
                    System.out.println("Длина самой короткой строки: " + minLength);
                }

            } catch (Exception ex) {
                System.out.println("Произошла ошибка при чтении файла:");
                ex.printStackTrace();
            }
        }

        scanner.close();
    }
}


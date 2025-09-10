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
                int googlebotCount = 0;
                int yandexbotCount = 0;

                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();

                    // проверка на слишком длинную строку
                    if (length > 1024) {
                        throw new RuntimeException("Ошибка: строка длиннее 1024 символов!");
                    }

                    lineCount++;

                    // разбор строки: User-Agent содержится в последней паре кавычек
                    String[] quoteParts = line.split("\"");
                    if (quoteParts.length >= 6) { // 6-й элемент = User-Agent
                        String userAgent = quoteParts[5];

                        // извлекаем часть в первых скобках
                        int start = userAgent.indexOf("(");
                        int end = userAgent.indexOf(")");
                        if (start != -1 && end != -1 && end > start) {
                            String firstBrackets = userAgent.substring(start + 1, end);

                            // делим по ';'
                            String[] parts = firstBrackets.split(";");
                            if (parts.length >= 2) {
                                String fragment = parts[1].trim(); // берём второй фрагмент и убираем пробелы

                                // отделяем до слэша
                                int slashIndex = fragment.indexOf("/");
                                if (slashIndex != -1) {
                                    fragment = fragment.substring(0, slashIndex);
                                }

                                // проверяем ботов
                                if (fragment.equals("Googlebot")) {
                                    googlebotCount++;
                                } else if (fragment.equals("YandexBot")) {
                                    yandexbotCount++;
                                }
                            }
                        }
                    }
                }

                reader.close();

                // выводим результаты
                System.out.println("Количество строк в файле: " + lineCount);
                if (lineCount > 0) {
                    double googleShare = (googlebotCount * 100.0) / lineCount;
                    double yandexShare = (yandexbotCount * 100.0) / lineCount;

                    System.out.printf("Запросы Googlebot: %d (%.2f%%)\n", googlebotCount, googleShare);
                    System.out.printf("Запросы YandexBot: %d (%.2f%%)\n", yandexbotCount, yandexShare);
                }

            } catch (Exception ex) {
                System.out.println("Произошла ошибка при чтении файла:");
                ex.printStackTrace();
            }
        }

        scanner.close();
    }
}

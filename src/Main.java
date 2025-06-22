import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст и нажмите <Enter>:");
        String text = scanner.nextLine();
        int length = text.length();
        System.out.println("Длина текста: " + length);
        scanner.close();
    }
}
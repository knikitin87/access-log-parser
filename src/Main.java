import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
       int firstNumber =  new Scanner(System.in) .nextInt();

        System.out.println("Введите второе число:");
        int secondNumber =  new Scanner(System.in) .nextInt();

        int x = firstNumber + secondNumber;
        int y = firstNumber - secondNumber;
        int z = firstNumber * secondNumber;
        double q = (double) firstNumber / secondNumber;

        System.out.println("Сумма: " + x);
        System.out.println("Разность: " + y);
        System.out.println("Произведение: " + z);
        System.out.println("Частное: " + q);


    }
}

//public static void main(String[] args) {
   // Scanner scanner = new Scanner(System.in);
    //System.out.println("Введите текст и нажмите <Enter>:");
    //String text = scanner.nextLine();
    //int length = text.length();
    //System.out.println("Длина текста: " + length);
    //scanner.close();//
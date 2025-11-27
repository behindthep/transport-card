package github.behindthep;

public class InputBalanceValidator {
    public static int validateBalance(Scanner input) {
        System.out.println("Enter balance: ");
        int balance = -1;
        while (balance < 0) {
            if (input.hasNextInt()) {
                balance = input.nextInt(); // nextInt() игнорирует пробелы
                if (balance < 0) {
                    System.out.println("Balance should be positive.");
                }
            } else {
                System.out.println("Balance should be a number.");
                input.next(); // съедаем некорректный ввод
            }
        }
        return balance;
    }
}

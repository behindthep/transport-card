package github.behindthep;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// TODO: Как правильно писать консольную утилиту

public class TransportCardBalanceCalculator {
    private static final byte TRIP_COST_PER_DAY = 34 * 2;

    private static String calculateCardBalance(int balance) {
        int availableTripDays = balance / TRIP_COST_PER_DAY;

        LocalDate dateOfReplenishment = LocalDate.now();

        while (availableTripDays > 0) {
            dateOfReplenishment = dateOfReplenishment.plusDays(1);
            DayOfWeek dayOfWeek = dateOfReplenishment.getDayOfWeek();

            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                availableTripDays--;
            }
        }

        return "Top up card before\n" + dateOfReplenishment.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    private static int getCardBalance(Scanner input) {
        int balance = -1;

        while (balance < 0) {
            if (input.hasNextInt()) {
                balance = input.nextInt();
                if (balance < 0) {
                    System.out.println("Balance cannot be negative.");
                }
            } else {
                System.out.println("Invalid input.");
                input.next();
            }
        }

        return balance;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter card balance:");
        System.out.println(calculateCardBalance(getCardBalance(input)));
        input.close();
    }
}

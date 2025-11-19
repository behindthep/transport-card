package github.behindthep;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import github.behindthep.InputBalanceValidator;

// TODO: Как правильно писать консольную утилиту
public class AvailableTripDaysCalculator {
    private static final byte TRIP_COST_PER_DAY = 34 * 2;

    private static String calculateAvailableTripDays(int balance) {
        LocalDate dateOfReplenishment = LocalDate.now();
        int availableTripDays = balance / TRIP_COST_PER_DAY;
        while (availableTripDays > 0) {
            dateOfReplenishment = dateOfReplenishment.plusDays(1);
            DayOfWeek dayOfWeek = dateOfReplenishment.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                availableTripDays--;
            }
        }
        return "Top up card before: " + dateOfReplenishment.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(calculateAvailableTripDays(InputBalanceValidator.validateBalance(input)));
        input.close();
    }
}

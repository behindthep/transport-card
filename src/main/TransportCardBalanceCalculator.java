package src.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TransportCardBalanceCalculator {
    private static final byte TRIP_COST_PER_DAY = 34 * 2;

    private static String calculateCardBalance(int balance, boolean workingSaturday) {
        int workingDaysPerWeek = workingSaturday ? 6 : 5;

        int availableTripDays = balance / TRIP_COST_PER_DAY;

        LocalDate dateOfReplenishment = LocalDate.now();

        while (availableTripDays > 0) {
            dateOfReplenishment = dateOfReplenishment.plusDays(1);
            DayOfWeek dayOfWeek = dateOfReplenishment.getDayOfWeek();

            boolean isWorkingDay;
            if (workingDaysPerWeek == 6) {
                isWorkingDay = (dayOfWeek != DayOfWeek.SUNDAY);
            } else {
                isWorkingDay = (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY);
            }

            if (isWorkingDay) {
                availableTripDays--;
            }
        }

        String formattedDate =
                dateOfReplenishment.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));

        return "Top up card before\n" + formattedDate;
    }

    private static int getInputBalance(Scanner input) {
        int balance = -1;
        while (balance < 0) {
            System.out.println("Enter card balance:");
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

    private static boolean isWorkOnSaturday(Scanner input) {
        Boolean worksOnSaturday = null;
        while (worksOnSaturday == null) {
            System.out.println("Do you work on Saturday? ('1' for yes):");
            if (input.hasNextInt()) {
                int val = input.nextInt();
                if (val == 1) {
                    worksOnSaturday = true;
                } else {
                    worksOnSaturday = false;
                }
            } else {
                System.out.println("Invalid input.");
                input.next();
            }
        }
        return worksOnSaturday;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int balance = getInputBalance(input);
        boolean workingSaturday = isWorkOnSaturday(input);

        System.out.println(calculateCardBalance(balance, workingSaturday));

        input.close();
    }
}

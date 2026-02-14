package io.github.behindthep;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Only local format for dates
public class TripDaysCalculator {
  private static final int TRIP_COST_PER_DAY = 34 * 2;

  private static LocalDate calculateDateOfReplenishment(int balance) {
    LocalDate dateOfReplenishment = LocalDate.now();
    var availableTripDays = balance / TRIP_COST_PER_DAY;

    while (availableTripDays > 0) {
      dateOfReplenishment = dateOfReplenishment.plusDays(1);
      DayOfWeek dayOfWeek = dateOfReplenishment.getDayOfWeek();

      if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
        availableTripDays--;
      }
    }

    return dateOfReplenishment;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter your card balance: ");
    int balance = 0;

    while (balance <= 0) {
      if (input.hasNextInt()) {
        balance = input.nextInt(); // nextInt() игнорирует пробелы

        if (balance <= 0) {
          System.out.println("Enter a positive number.");
        }
      } else {
        System.out.println("Balance can only be a number.");
        input.next();
        /*
        console.nextLine(); // Поглощаем оставшийся символ новой строки, оставшийся в буфере после вызова console.nextInt().
        console.nextInt() читает целое число из входных данных, но не считывает символ новой строки, который вводится после числа нажатием Enter. символ остаётся во входном буфере.
        След вызов console.nextLine() считывает весь оставшийся в буфере текст до символа новой строки, в данном случае — только сам символ новой строки. 
        очистить буфер, иначе след вызов console.nextLine() прочитает пустую строку (оставшийся после числа символ новой строки), и пользовательский ввод не корректным.
        */
      }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
    LocalDate dateOfReplenishment = calculateDateOfReplenishment(balance);
    String formattedDate = dateOfReplenishment.format(formatter);
    System.out.println("Top up your card before: " + formattedDate);

    input.close();
  }
}

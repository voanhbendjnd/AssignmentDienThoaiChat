/**
 *
 * @author Vo Anh Ben - CE190709
 */
package utils.error;

import java.util.Scanner;

public class Validation {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    private static final Scanner sc = new Scanner(System.in);

    public static String getNonEmptyString(String message, String errorMessage) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (!input.isEmpty())
                return input;
            System.out.println(errorMessage);
        }
    }

    public static Long getPositiveLong(String message, String errorMessage) {
        while (true) {
            try {
                System.out.print(message);
                long value = Long.parseLong(sc.nextLine().trim());
                if (value >= 0)
                    return value;
                System.out.println(errorMessage);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input! Please enter a valid number." + RESET);
            }
        }
    }
}

package utils;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

// class này để mở đầu và kết thúc cho giới thiệu dự án
public class AnimationOpeningAndEnding {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void MovieOpening() throws InterruptedException {

        String[] endingText = {
                BOLD + BLUE + " Hi Welcome to Project DienThoaiChat..." + RESET,
                BOLD + CYAN + " Less but quality" + RESET,
                BOLD + YELLOW + " Loading data please wait a moment" + RESET,

                BOLD + " Ready..." + RESET

        };

        for (String line : endingText) {
            System.out.println(line);
            Thread.sleep(1000);
        }
    }

    public static void MovieEnding() throws InterruptedException {

        String[] endingText = {
                BOLD + BLUE + " Project desinged by group 3" + RESET,
                BOLD + YELLOW + " Inspired by the website thoigioididong.com" + RESET,
                BOLD + GREEN + " Thank you everyone for listening to the presentation." + RESET,
                BOLD + CYAN + " Hello and see you again!" + RESET,
                BOLD + " Goodbye..." + RESET
        };

        for (String line : endingText) {
            System.out.println(line);
            Thread.sleep(1000);
        }

        System.out.println(BOLD + "\nThe End.");
    }

}

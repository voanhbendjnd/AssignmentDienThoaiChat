package display;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.entity.User;
import handle.HandleUser;
import setupFile.AllFile;
import utils.constant.RoleEnum;
import utils.error.ValidationUser;

public class RegisterForm {

    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public void mainMethod() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        MenuSystem menu = new MenuSystem();
        HandleUser reader = new HandleUser();
        List<User> accountList = reader.read(AllFile.fileAccountTxt);
        List<User> auth = new ArrayList<>();
        Long code = 0L;

        while (check) {
            System.out.println(CYAN + "════════════════════════════════════════" + RESET);
            System.out.println(CYAN + "       " + BOLD + "            WELCOME TO GROUP3 MOBILE" + RESET + CYAN);
            System.out.println(CYAN + "════════════════════════════════════════" + RESET);
            System.out.print(BOLD + GREEN + " >>>>> Login or Sign up (1/2): " + RESET);

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(RED + " Invalid input! Please enter 1 or 2." + RESET);
                sc.nextLine();
                continue;
            }

            if (choice == 1) {
                // Handle login
                System.out.print(BOLD + YELLOW + " <> Please enter username(email): " + RESET);
                String user = sc.nextLine();
                System.out.print(BOLD + BLUE + " <> Please enter password: " + RESET);
                String password = sc.nextLine();
                boolean checkLogin = false;
                Long userId = null;
                for (User acc : accountList) {
                    if (acc.getEmail().equals(user) && acc.getPassword().equals(password)) {

                        checkLogin = true;
                        userId = acc.getId();
                        System.out.println(GREEN + " Login success!" + RESET);
                        System.out.println(BLUE + "--------------------------------" + RESET);
                        if (acc.getRole().toString().equals("ADMIN")) {
                            menu.getMenu(userId);
                        } else if (acc.getRole().toString().equals("USER")) {
                            menu.getMenuUser(userId);
                        }
                        break;
                    }
                }

                for (User acc : auth) {
                    if (acc.getEmail().equals(user) && acc.getPassword().equals(password)) {
                        checkLogin = true;
                        userId = acc.getId();
                        System.out.println(GREEN + " Login success!" + RESET);
                        System.out.println(BLUE + "--------------------------------" + RESET);
                        if (acc.getRole().toString().equals("ADMIN")) {
                            menu.getMenu(acc.getId());
                        } else if (acc.getRole().toString().equals("USER")) {
                            menu.getMenuUser(acc.getId());
                        }
                        break;
                    }
                }
                for (User x : auth) {
                    if (user.equals(x.getEmail()) && password.equals(x.getPassword())) {
                        checkLogin = true;
                    }
                }

                if (!checkLogin) {
                    System.out.println(RED + "------------------------------------------------" + RESET);
                    System.out.println(RED + " Username or password incorrect, please try again." + RESET);
                }

            } else if (choice == 2) {
                System.out.print(BOLD + YELLOW + " <> Please enter gmail for new account: " + RESET);
                String email = sc.nextLine();
                System.out.print(BOLD + GREEN + " <> Please enter full name for new account: " + RESET);
                String user = sc.nextLine();
                System.out.print(BOLD + BLUE + " <> Please enter password for new account: " + RESET);
                String password = sc.nextLine();
                if (new ValidationUser().validUser(user, email, password).equals("validation")) {
                    // boolean uniqueEmail = true;
                    if (accountList.isEmpty()) {
                        code = 0L;
                    } else {
                        for (User x : accountList) {
                            if (x.getId() >= code) {
                                code = x.getId();
                            }
                        }
                    }
                    HandleUser ar = new HandleUser();
                    RoleEnum role = RoleEnum.USER;
                    auth.add(new User(code + 1, user, password, email, role));
                    ar.addNew(AllFile.fileAccountTxt, new User(code + 1, user, password, email, role));
                    // auth.add(new Accounts(code + 1, user, password, email, role));
                    System.out.println(BOLD + GREEN + " >>>  Account created successfully! <<<" + RESET);
                    System.out.println(BLUE + "-----------------------------" + RESET);

                } else {
                    System.out.println(RED + new ValidationUser().validUser(user, email, password) + RESET);
                    System.out.println(RED + "---------------------------------" + RESET);
                }

            } else {
                System.out.println(BOLD + RED + " Invalid choice, please enter 1 for Login or 2 for Sign up." + RESET);
            }
        }
        sc.close();
    }
}

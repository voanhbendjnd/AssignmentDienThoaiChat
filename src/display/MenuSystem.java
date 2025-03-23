
package display;

import java.util.Scanner;

import utils.AnimationOpeningAndEnding;
import utils.function.AddProduct;
import utils.function.CartProduct;
import utils.function.DeleteProduct;
import utils.function.FindProduct;
import utils.function.OpenCart;
import utils.function.ViewOrder;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class MenuSystem {
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm
    private static Scanner sc = new Scanner(System.in);

    public void getMenuUser(Long id) {
        new FindProduct().findAll();

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║    " + BOLD + "WELCOME TO DienThoaiChat" + RESET + CYAN
                    + "                                  ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════╣" + RESET);
            System.out
                    .println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products                                  ║");
            System.out
                    .println(CYAN + "║ " + GREEN + "2." + RESET + " Find All Products                              ║");
            System.out
                    .println(CYAN + "║ " + GREEN + "3." + RESET + " Add To Cart                                    ║");
            System.out
                    .println(CYAN + "║ " + GREEN + "4." + RESET + " View Order                                     ║");
            System.out
                    .println(CYAN + "║ " + GREEN + "5." + RESET + " View Cart                                      ║");
            System.out
                    .println(CYAN + "║ " + GREEN + "6." + RESET + " Logout                                         ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                                           ║");
            System.out.println(CYAN + "╚════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(BOLD + GREEN + " Form Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BOLD + BLUE + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 3) {
                    System.out.println(BOLD + CYAN + " Processing your add to cart..." + RESET);
                    new CartProduct().cartProduct(id);
                } else if (q == 4) {
                    System.out.println(BOLD + YELLOW + " Viewing your orders..." + RESET);
                    new ViewOrder().viewOrderFromUser(id);
                } else if (q == 5) {
                    System.out.println(BOLD + YELLOW + " Viewing your carts..." + RESET);
                    new OpenCart().openCart(id);
                } else if (q == 6) {
                    System.out.println(BOLD + RED + " Logging out..." + RESET);
                    break;
                } else if (q == 0) {
                    System.out.println(BOLD + RED + " Exiting..." + RESET);
                    AnimationOpeningAndEnding.MovieEnding();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option (0 - 5)!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }

    }

    public void getMenu(Long id) {
        new FindProduct().findAll();

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════════╗" + RESET);
            System.out.println(
                    CYAN + "║   " + BOLD + "    WELCOME TO DienThoaiChat (ADMIN) " + RESET + CYAN
                            + "                      ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products                               ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Add Products                                ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Delete Products                             ║");
            System.out.println(CYAN + "║ " + GREEN + "4." + RESET + " Fetch All Products                          ║");
            System.out.println(CYAN + "║ " + GREEN + "5." + RESET + " View Order Customer                         ║");
            System.out.println(CYAN + "║ " + RED + "6." + RESET + " Logout                                      ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                                        ║");
            System.out.println(CYAN + "╚════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(BOLD + GREEN + " Form Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BOLD + BLUE + " Form Adding a new product..." + RESET);
                    new AddProduct().addNewProduct();
                } else if (q == 3) {
                    System.out.println(BOLD + RED + " Form Deleting a product..." + RESET);
                    // new DeleteProduct().deleteProduct();
                    new DeleteProduct().delete();
                } else if (q == 4) {
                    System.out.println(BOLD + CYAN + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 5) {
                    System.out.println(BOLD + YELLOW + " Viewing all customer orders..." + RESET);
                    new ViewOrder().viewOrderFromAdmin();
                } else if (q == 6) {
                    System.out.println(BOLD + RED + " Logging out..." + RESET);
                    break;
                } else if (q == 0) {
                    System.out.println(BOLD + RED + " Exiting..." + RESET);
                    AnimationOpeningAndEnding.MovieEnding();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option (0 - 6)!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }
    }

    public void getMenuNotLogin() {
        new FindProduct().findAll();

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║  " + BOLD + "WELCOME TO DienThoaiChat" + RESET + CYAN
                    + "                             ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products                           ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Fetch All Products                      ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Login or Sign Up                        ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                                    ║");
            System.out.println(CYAN + "╚════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(BOLD + GREEN + " Form Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BOLD + BLUE + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 3) {
                    System.out.println(BOLD + CYAN + " Redirecting to login..." + RESET);
                    RegisterForm app = new RegisterForm();
                    app.mainMethod();
                } else if (q == 0) {
                    System.out.println(BOLD + RED + " Exiting..." + RESET);
                    AnimationOpeningAndEnding.MovieEnding();

                    break;
                } else {
                    System.out.println(BOLD + RED + " Please enter a valid option!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(BOLD + RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }

    }

}

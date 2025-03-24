package utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.entity.Product;
import handle.HandleProduct;
import setupFile.AllFile;
import utils.constant.FactoryEnum;
import utils.constant.TargetEnum;
import utils.error.Validation;

/**
 * @author Duong Thien Nhan - CE190741
 */

public class AddProduct {
    private static Scanner sc = new Scanner(System.in);
    // Mã ANSI để đổi màu
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void addNewProduct() throws InterruptedException {
        HandleProduct reader = new HandleProduct();
        List<Product> proListed = new ArrayList<>();
        System.out.println(BOLD + BLUE + "═════════════════════════" + RESET);
        System.out.println(BOLD + BLUE + "      ADD NEW PRODUCT     " + RESET);
        System.out.println(BOLD + BLUE + "═════════════════════════" + RESET);

        String name = Validation.getNonEmptyString(BOLD + BLUE + " Name: " + RESET,
                RED + " Product name cannot be empty !!! Please try again." + RESET);
        FactoryEnum factory = FactoryEnum.APPLE;
        System.out.println(BOLD + YELLOW + " Factory " + RESET);
        System.out.println(BLUE + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(BLUE + "│ 1. Apple    │ 2. Samsung   │ 3. Xiaomi     │" + RESET);
        System.out.println(BLUE + "│ 4. Asus     │ 5. Oppo      │ 6. Vivo       │" + RESET);
        System.out.println(BLUE + "│ 7. Honor    │ 8. Nokia     │ 9. Realme     │" + RESET);
        System.out.println(BLUE + "└────────────────────────────────────────────┘" + RESET);
        int uu = -1;
        while (true) {
            try {
                System.out.print(BOLD + BLUE + " Number for factory: " + RESET);
                uu = sc.nextInt(); // Nhận số nguyên từ người dùng

                if (uu >= 1 && uu <= 9) {
                    break; // Thoát vòng lặp nếu nhập đúng
                } else {
                    System.out.println(BOLD + RED + "Invalid factory. Please enter again (1-9): " + RESET);
                }
            } catch (Exception e) {
                System.out.println(BOLD + RED + "Invalid input! Please enter a number (1-9): " + RESET);
                sc.nextLine(); // Xóa bộ đệm nhập để tránh lặp vô hạn
            }
        }
        // Xử lý giá trị hợp lệ
        switch (uu) {
            case 1:
                factory = FactoryEnum.APPLE;
                break;
            case 2:
                factory = FactoryEnum.SAMSUNG;
                break;
            case 3:
                factory = FactoryEnum.XIAOMI;

                break;
            case 4:
                factory = FactoryEnum.ASUS;

                break;
            case 5:
                factory = FactoryEnum.OPPO;

                break;
            case 6:
                factory = FactoryEnum.VIVO;
                break;
            case 7:
                factory = FactoryEnum.HONOR;
                break;
            case 8:
                factory = FactoryEnum.NOKIA;

                break;
            case 9:
                factory = FactoryEnum.REALME;

                break;
        }

        System.out.println(BOLD + YELLOW + " Target: " + RESET);
        System.out.println(GREEN + " ┌────────────────────────────────────────────┐" + RESET);
        System.out.println(GREEN + " │ 1. Gaming        │ 4. Camera, Video        │" + RESET);
        System.out.println(GREEN + " │ 2. Thin & Light  │ 5. Battery              │" + RESET);
        System.out.println(GREEN + " │ 3. Work, Study   │                         │" + RESET);
        System.out.println(GREEN + " └────────────────────────────────────────────┘" + RESET);

        int tt = -1; // Giá trị mặc định ngoài phạm vi hợp lệ
        TargetEnum target = TargetEnum.GAMING;

        while (true) {
            try {
                System.out.print(BOLD + BLUE + " Number for target: " + RESET);
                tt = sc.nextInt(); // Nhận số nguyên từ người dùng

                if (tt >= 1 && tt <= 5) {
                    break; // Thoát vòng lặp nếu nhập đúng
                } else {
                    System.out.println(BOLD + RED + " ERROR: " + RESET + BOLD
                            + "Invalid target. Please enter again (1-5): " + RESET);
                }
            } catch (Exception e) {
                System.out.println(BOLD + RED + "Invalid input! Please enter a number (1-5): " + RESET);
                sc.nextLine(); // Xóa bộ đệm nhập để tránh lặp vô hạn
            }
        }

        // Xử lý giá trị hợp lệ
        switch (tt) {
            case 1:
                target = TargetEnum.GAMING;
                break;
            case 2:
                target = TargetEnum.THIN_LIGHT;
                break;
            case 3:
                target = TargetEnum.WORK_STUDY;
                break;
            case 4:
                target = TargetEnum.CAMERA_VIDEO;
                break;
            case 5:
                target = TargetEnum.BATTERY;
                break;
        }

        Long price = Validation.getPositiveLong(BOLD + BLUE + " Price: " + RESET,
                RED + " Price must be greater than 0! Please enter again." + RESET);
        String ds = Validation.getNonEmptyString(BOLD + BLUE + " Description: " + RESET,
                RED + " Product description cannot be empty !!! Please try again." + RESET);

        boolean check = false;
        Long maxCode = -1L;
        List<Product> data = reader.read(AllFile.fileProductTxt);

        for (Product x : data) {
            if (maxCode < x.getCode()) {
                maxCode = x.getCode();
            }
        }

        for (Product x : data) {
            // trùng mô tả, tên và brand thì tính sản phẩm đó là 1
            if (name.equalsIgnoreCase(x.getName()) && factory.equals(x.getFactory())
                    && ds.equalsIgnoreCase(x.getDescription()) && x.getPrice().equals(price)) {
                System.out.print(BOLD + BLUE + " One or Many stock? (1/2): " + RESET);
                int z = sc.nextInt();
                if (z == 1) {
                    System.out.println(BOLD + GREEN + " Current stock: " + x.getStock() + RESET);
                    System.out.print(BOLD + BLUE + " Enter stock quantity to add: " + RESET);
                    Long stockAdd = sc.nextLong();
                    if (stockAdd > 0) {
                        x.setStock(x.getStock() + stockAdd);
                    } else {
                        System.out.println(BOLD + RED + " ERROR: "
                                + RESET + BOLD + "Please enter positive number!"
                                + RESET);
                    }

                    sc.nextLine();
                } else {
                    System.out.println(BOLD + GREEN + " Current stock: " + x.getStock() + RESET);
                    System.out.print(BOLD + BLUE + " Enter new stock quantity: " + RESET);
                    Long stockChange = sc.nextLong();
                    if (stockChange > 0) {
                        x.setStock(stockChange);
                    } else {
                        System.out.println(BOLD + RED + " ERROR: "
                                + RESET + BOLD + "Please enter positive number!"
                                + RESET);
                    }

                    sc.nextLine();
                }
                reader.writeFile(AllFile.fileAccountTxt, data);
                proListed.add(x);
                check = true;

                System.out.println(BOLD + GREEN + " Product updated successfully!" + RESET);
            }
        }

        if (!check) {

            Long stock = null;
            while (true) {
                try {
                    sc.nextLine();
                    System.out.print(BOLD + BLUE + " Stock: " + RESET);
                    stock = Long.parseLong(sc.nextLine().trim());
                    if (stock < 0) {
                        System.out.println(BOLD + RED + " Stock must be greater than 0! Please enter again." + RESET);
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(BOLD + RED + " Invalid input! Price must be a number greater than 0!!" + RESET);
                }
            }

            Product pro = new Product(maxCode + 1, name, factory, target, price, ds, stock);
            data.add(pro);
            reader.writeFile(AllFile.fileProductTxt, data);
            proListed.add(pro);

            System.out.println(GREEN + " Product added successfully!" + RESET);
        }

        while (true) {
            System.out.println(BOLD + CYAN + "═════════════════════════════════════" + RESET);
            System.out.println(BOLD + CYAN + " Do you want to continue or go back? " + RESET);
            System.out.println(BOLD + CYAN + " 1. Add another product" + RESET);
            System.out.println(BOLD + CYAN + " 2. Return to main menu" + RESET);
            System.out.println(BOLD + CYAN + "═════════════════════════════════════" + RESET);
            System.out.print(BOLD + YELLOW + " Your choice: " + RESET);
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    addNewProduct();
                    break;
                } else if (choice == 2) {
                    System.out.print(BOLD + GREEN + "The program is returning to menu" + RESET);
                    for (int i = 0; i < 3; i++) {
                        System.out.print(RED + "." + RESET);
                        Thread.sleep(700);
                    }
                    System.out.println();
                    // n = 0; // exit while loop
                    break;
                } else {
                    System.out.println(RED + "Invalid choice! Please enter 1 or 2." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input!! Please enter !!!!" + RESET);
            }
        }

        System.out.println(BOLD + CYAN + "══════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "     PRODUCTS ADDED/UPDATED   " + RESET);
        System.out.println(BOLD + CYAN + "══════════════════════════════" + RESET);
        Product.printTable(proListed);
    }
}
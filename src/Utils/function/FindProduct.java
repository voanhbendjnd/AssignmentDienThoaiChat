package utils.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import domain.entity.Product;
import handle.HandleProduct;
import setupFile.AllFile;

import java.util.Arrays;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class FindProduct {

    private static Scanner sc = new Scanner(System.in);

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void findAll() {
        HandleProduct reader = new HandleProduct();

        // Hiển thị tiêu đề với màu sắc nổi bật
        System.out.println(BOLD + CYAN + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "                           PRODUCT LIST               " + RESET);
        System.out.println(BOLD + CYAN + "════════════════════════════════════════" + RESET);

        List<Product> proList = reader.read(AllFile.fileProductTxt);

        // Hiển thị danh sách sản phẩm với màu xanh
        System.out.println(GREEN);
        Product.printTable(proList);
        System.out.println(RESET);
    }

    public void findProducts() {
        System.out.println(BOLD + CYAN + "══════════════════════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "                       SELECT THE PRODUCT TO SEARCH         " + RESET);
        System.out.println(BOLD + CYAN + "══════════════════════════════════════════════" + RESET);

        System.out.println(BOLD + YELLOW + " Factory " + RESET);
        System.out.println(BLUE + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(BLUE + "│ 1. Apple    │ 2. Samsung   │ 3. Xiaomi     │" + RESET);
        System.out.println(BLUE + "│ 4. Asus     │ 5. Oppo      │ 6. Vivo       │" + RESET);
        System.out.println(BLUE + "│ 7. Honor    │ 8. Nokia     │ 9. Realme     │" + RESET);
        System.out.println(BLUE + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Target " + RESET);
        System.out.println(GREEN + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(GREEN + "│ 10. Gaming        │ 11. Camera, Video      │" + RESET);
        System.out.println(GREEN + "│ 12. Thin & Light  │ 13. Battery            │" + RESET);
        System.out.println(GREEN + "│ 14. Work, Study   │                        │" + RESET);
        System.out.println(GREEN + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Price Range " + RESET);
        System.out.println(RED + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(RED + "│ 15. Under 2M       │ 16. 2M - 4M           │" + RESET);
        System.out.println(RED + "│ 17. 4M - 7M        │ 18. 7M - 13M          │" + RESET);
        System.out.println(RED + "│ 19. 13M - 20M      │ 20. Over 20M          │" + RESET);
        System.out.println(RED + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Sort by Price " + RESET);
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│ 21. Ascending Order   │ 22. Descending Order │");
        System.out.println("│                       │                      │");
        System.out.println("└──────────────────────────────────────────────┘");

        System.out.print(BOLD + CYAN + " Enter values here: " + RESET);
        List<Product> proList = new HandleProduct().read(AllFile.fileProductTxt);
        List<Product> proFind = new ArrayList<>();

        String option = sc.nextLine();
        boolean checkEmpty = false;
        // check lựa chọn rỗng
        if (option.isEmpty()) {
            proFind.addAll(proList);
            checkEmpty = true;

        }

        boolean checkSort = true;
        boolean checkSortDes = false;
        boolean checkSortAs = false;
        boolean checkPrice = false;
        String[] ss = option.split("\\s+");
        Long max_value = 200000000L;
        Long min_value = 1L;
        // thêm từ khóa tìm kiếm từ các lựa chọnchọn
        List<String> op = new ArrayList<>();
        for (String x : ss) {
            switch (x) {
                case "1":
                    op.add("apple");
                    checkSort = false;
                    break;
                case "2":
                    op.add("samsung");
                    checkSort = false;
                    break;
                case "3":
                    op.add("xiaomi");
                    checkSort = false;
                    break;
                case "4":
                    op.add("asus");
                    checkSort = false;
                    break;
                case "5":
                    op.add("oppo");
                    checkSort = false;
                    break;
                case "6":
                    op.add("vivo");
                    checkSort = false;
                    break;
                case "7":
                    op.add("honor");
                    checkSort = false;
                    break;
                case "8":
                    op.add("nokia");
                    checkSort = false;
                    break;
                case "9":
                    op.add("realme");
                    checkSort = false;
                    break;
                case "10":
                    op.add("GAMING");
                    checkSort = false;
                    break;
                case "11":
                    op.add("CAMERA_VIDEO");
                    checkSort = false;
                    break;
                case "12":
                    op.add("THIN_LIGHT");
                    checkSort = false;
                    break;
                case "13":
                    op.add("BATTERY");
                    checkSort = false;
                    break;
                case "14":
                    op.add("WORK_STUDY");
                    checkSort = false;
                    break;
                case "15":
                    max_value = 2000000L;
                    min_value = 1L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "16":
                    max_value = 4000000L;
                    min_value = 2000000L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "17":
                    max_value = 7000000L;
                    min_value = 4000000L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "18":
                    max_value = 13000000L;
                    min_value = 7000000L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "19":
                    max_value = 20000000L;
                    min_value = 13000000L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "20":
                    max_value = 200000000L;
                    min_value = 20000000L;
                    checkPrice = true;
                    checkSort = false;
                    break;
                case "21":
                    checkSortAs = true;
                    break;
                case "22":
                    checkSortDes = true;
                    break;
                default:
                    System.out.println(BOLD + RED + " ERROR: " + RESET
                            + BOLD + "Please enter number from 1 to 22!");
                    break;
            }
        }

        // check có nhập hiệu
        boolean checkFactory = op.stream().anyMatch(x -> Arrays
                .asList("apple", "samsung", "xiaomi", "asus", "oppo", "vivo", "honor", "nokia", "realme").contains(x));
        // check có nhập tính năngnăng
        boolean checkTarget = op.stream().anyMatch(
                x -> Arrays.asList("GAMING", "CAMERA_VIDEO", "THIN_LIGHT", "BATTERY", "WORK_STUDY").contains(x));
        // không nhập giá
        if (!checkPrice) {
            // có hãng không có tính năng
            if (checkFactory && !checkTarget) {
                for (Product x : proList) {
                    if (op.contains(x.getFactory().name().toLowerCase())) {
                        proFind.add(x);
                    }
                }
            }
            // có tính năng và không hãnghãng
            if (checkTarget && !checkFactory) {
                for (Product x : proList) {
                    if (op.contains(x.getTarget().name())) {
                        proFind.add(x);
                    }
                }
            }
            // có cả 22
            if (checkFactory && checkTarget) {
                for (Product x : proList) {
                    if (op.contains(x.getFactory().name().toLowerCase())
                            && op.contains(x.getTarget().name())) {
                        proFind.add(x);
                    }
                }
            }
            // tìm theo giá
        } else {
            if (checkFactory && checkTarget) {
                for (Product x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        for (String y : op) {
                            if (x.getTarget().name().equals(y)) {
                                for (String z : op) {
                                    if (x.getFactory().name().equalsIgnoreCase(z)) {
                                        if (!proFind.contains(x)) {
                                            proFind.add(x);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (checkFactory && !checkTarget) {
                for (Product x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        if (op.contains(x.getFactory().name().toLowerCase())) {
                            proFind.add(x);
                        }
                    }
                }
            } else if (!checkFactory && checkTarget) {
                for (Product x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        if (op.contains(x.getTarget().name())) {
                            proFind.add(x);
                        }
                    }
                }
            } else {
                for (Product x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        proFind.add(x);
                    }
                }
            }

        }

        // tìm theo giá tăng dần
        if (checkSortAs && !checkSortDes) {
            if (checkSort) {
                proFind.addAll(proList);
            }

            Collections.sort(proFind, new Comparator<Product>() {

                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getPrice() > o2.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            });
            // tìm theo giá giảm dần

        } else if (checkSortDes && !checkSortAs) {
            if (checkSort) {
                proFind.addAll(proList);
            }
            // proFind.addAll(proList);
            Collections.sort(proFind, new Comparator<Product>() {

                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getPrice() < o2.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            });

        }
        boolean checkS = true;
        // lỗi nhập cả 2 loại sort
        if (checkSortAs && checkSortDes) {
            proFind.addAll(proList);
            Product.printTable(proFind);
            System.out.println(BOLD + RED + " ERROR: " + RESET
                    + "Cannot select both ascending and descending sorting at the same time.\n");
            checkS = false;
        }
        if (checkFactory || checkPrice || checkTarget || checkSortAs || checkSortDes || checkEmpty) {
            if (proFind.isEmpty()) {
                // không có sản phẩm nào phù hợp
                System.out.println(BOLD + GREEN + " There are no suitable products!" + RESET);
            } else if (checkS) {
                Product.printTable(proFind);
                if (checkEmpty) {
                    // bạn đang tìm kiếm không theo tiêu chí nào
                    System.out.println(BOLD + CYAN + " You are searching without any criteria." + RESET);
                }
            }

        }

    }
}

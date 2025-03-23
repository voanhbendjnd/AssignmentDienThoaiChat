package utils.function;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Product;
import handle.HandleProduct;
import setupFile.AllFile;

/**
 *
 * @author Nguyễn Hữu Lập - CE190492
 */

public class DeleteProduct {
    // Mã màu ANSI dùng để hiển thị màu sắc trong console
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";

    private Scanner sc = new Scanner(System.in); // Đối tượng Scanner để nhập dữ liệu từ bàn phím

    public void deleteProduct() {

        // Đọc danh sách sản phẩm từ tệp
        List<Product> proList = new HandleProduct().read(AllFile.fileProductTxt);

        while (true) {
            String input;
            do {
                System.out.print(BOLD + CYAN + " Please enter id product you want to delete: " + RESET);
                input = sc.nextLine().trim(); // Nhập ID sản phẩm và loại bỏ khoảng trắng thừa

                // Kiểm tra nếu người dùng để trống hoặc nhập không phải số
                if (input.isEmpty()) {
                    System.out.println(RED + " Error: Product ID cannot be empty!" + RESET);
                } else if (!input.matches("\\d+")) {
                    System.out.println(RED + " Error: Please enter a valid number!" + RESET);
                    input = "";
                }
            } while (input.isEmpty()); // Lặp lại nếu nhập không hợp lệ

            Long id = Long.parseLong(input); // Chuyển ID sản phẩm sang kiểu Long
            boolean found = false; // Biến kiểm tra xem sản phẩm có tồn tại không

            // Duyệt danh sách sản phẩm để tìm sản phẩm có ID cần xóa
            for (Product x : proList) {
                if (x.getCode().equals(id)) {
                    new HandleProduct().deleteIt(AllFile.fileProductTxt, Optional.of(id)); // Gọi hàm xóa sản phẩm
                    System.out.println(GREEN + BOLD + " Delete product success!" + RESET);
                    found = true;
                    break; // Thoát vòng lặp sau khi xóa thành công
                }
            }

            // Nếu sản phẩm được tìm thấy và xóa thành công, kết thúc vòng lặp
            if (found) {
                break;
            } else {
                System.out.println(RED + " Product does not exist in cart!" + RESET);

                // Yêu cầu người dùng chọn tiếp tục hay thoát
                String choice;
                while (true) {
                    System.out.print(BOLD + GREEN + "Would you like to try again? (Y/N): " + RESET);
                    choice = sc.nextLine().trim().toUpperCase(); // Chuyển đổi về chữ hoa để so sánh

                    // Chỉ chấp nhận "Y" hoặc "N", nếu không, yêu cầu nhập lại
                    if (choice.equals("Y") || choice.equals("N")) {
                        break;
                    } else {
                        System.out.println(BOLD + RED + " Error! Please select only (Y or N)" + RESET);
                    }
                }

                // Nếu người dùng chọn "N", thoát khỏi vòng lặp chính
                if (choice.equals("N")) {
                    break;
                }
            }
        }
    }

    public void delete() {
        HandleProduct handle = new HandleProduct();
        List<Product> proList = handle.read(AllFile.fileProductTxt);
        if (proList.isEmpty() || proList.size() == 0) {
            System.out.println(BOLD + CYAN + " List product empty! " + RESET);

        } else {
            System.out.print(BOLD + CYAN + " Please enter id product you want to delete: " + RESET);
            String id = sc.nextLine();
            boolean check = false;
            try {
                Long productId = Long.parseLong(id);
                for (Product x : proList) {
                    if (x.getCode().equals(productId)) {
                        check = true;
                        break;
                    }
                }
                if (check) {
                    proList.removeIf(x -> x.getCode().equals(Long.parseLong(id)));
                    handle.writeFile(AllFile.fileProductTxt, proList);
                    System.out.print(BOLD + GREEN + " Delete product with " + productId + " successful!" + RESET);

                } else {
                    System.out.println(RED + " Product with " + productId + " not found!" + RESET);
                }

            } catch (Exception ex) {
                System.out.println(RED + BOLD + " Error: Please enter only number!" + RESET);
            }
        }

    }
}

package utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.OrderUserImpl;
import domain.entity.OrderImpl;
import domain.entity.Product;
import handle.HandleOrder;
import handle.HandleOrderUser;
import handle.HandleProduct;
import setupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class ViewOrder {

    // hàm này để thay đổi trạng thái của đơn hàng
    public static void changeStatus(List<OrderImpl> orderList, List<OrderUserImpl> orderUserList, String id,
            int status) {
        HandleProduct handleProduct = new HandleProduct();
        List<Product> proList = handleProduct.read(AllFile.fileProductTxt);
        HandleOrderUser handleUser = new HandleOrderUser();
        HandleOrder handle = new HandleOrder();
        boolean check = false;
        Long orderId = null;
        Long stId = null;
        // Long stUserId = null;
        Long stProductId = null;
        // String stName = "";
        // String stAddress = "";
        // String stPhone = "";
        // Long stPrice = null;

        for (OrderImpl x : orderList) {
            if (x.getId().equals(Long.parseLong(id))) {
                check = true;
                stId = x.getId();
                // stUserId = x.getUser_id();
                stProductId = x.getProduct_id();
                // stName = x.getName();
                // stAddress = x.getAddress();
                // stPhone = x.getPhone();
                // stPrice = x.getPrice();
                orderId = x.getOrder_id();
                break;
            }
        }
        Long stockOrder = null;
        for (OrderUserImpl x : orderUserList) {
            if (x.getId().equals(Long.parseLong(id))) {
                stockOrder = x.getQty();
                break;
            }
        }
        if (check) {
            if (status == 3) {
                for (OrderUserImpl x : orderUserList) {
                    if (x.getId().equals(orderId)) {
                        x.setStatus(status);
                    }
                }
                handleUser.writeFile(AllFile.fileOrderUserTxt, orderUserList);
            } else if (status == 1) {
                for (OrderUserImpl x : orderUserList) {
                    if (x.getId().equals(orderId)) {
                        x.setStatus(status);
                    }
                }
                Long stockData = null;
                for (Product x : proList) {
                    if (x.getCode().equals(stProductId)) {
                        // System.out.println(x.getCode());
                        // kho co 200 cai
                        stockData = x.getStock();
                        break;
                    }
                }
                Long finalStock = stockData - stockOrder;
                Long iddd = stProductId;
                // System.out.println(finalStock);
                if (finalStock <= 0) {
                    proList.removeIf(x -> x.getCode().equals(iddd));
                    // handleProduct.delete(AllFile.fileProductTxt, Optional.of(stProductId));
                    handleProduct.writeFile(AllFile.fileProductTxt, proList);
                } else {
                    for (Product x : proList) {
                        if (x.getCode().equals(stProductId)) {
                            x.setStock(finalStock);
                        }
                    }
                    handleProduct.writeFile(AllFile.fileProductTxt, proList);
                }

                handleUser.writeFile(AllFile.fileOrderUserTxt, orderUserList);
                // handle.deleteIt(AllFile.fileOrderTxt, Optional.of(stId));
                handle.delete(AllFile.fileOrderTxt, Optional.of(stId));

            } else {
                for (OrderUserImpl x : orderUserList) {
                    if (x.getId().equals(orderId)) {
                        x.setStatus(status);
                    }
                }
                handleUser.writeFile(AllFile.fileOrderUserTxt, orderUserList);
                // handle.deleteIt(AllFile.fileOrderTxt, Optional.of(stId));
                handle.delete(AllFile.fileOrderTxt, Optional.of(stId));
            }
            System.out.println(BOLD + GREEN + " Order successful..." + RESET);

        } else {
            System.out.println(BOLD + RED + " Order id with " + id + " is incorrect, please re-enter" + RESET);
        }
    }

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm
    private static Scanner sc = new Scanner(System.in);

    public void viewOrderFromAdmin() {
        System.out.println(BOLD + GREEN + "---List order from user---" + RESET);
        List<OrderImpl> orderList = new HandleOrder().read(AllFile.fileOrderTxt);
        OrderImpl.printTableOrderForAdmin(orderList);
        // order by user
        List<OrderUserImpl> orderUserList = new HandleOrderUser().read(AllFile.fileOrderUserTxt);
        while (true) {
            System.out.print(BOLD + CYAN + " <> Order confirmation with (y/n): " + RESET);
            String c = sc.nextLine();
            if (c.equals("y")) {
                System.out.print(BOLD + YELLOW + " Please enter the order id you want to confirm: " + RESET);
                String id = sc.nextLine();
                System.out.println(GREEN + "┌───────────────────┐" + RESET);
                System.out.println(GREEN + "│ 1. Confirmed      │" + RESET);
                System.out.println(GREEN + "│ 2. Not Confirmed  │" + RESET);
                System.out.println(GREEN + "│ 3. Undetermined   │" + RESET);
                System.out.println(GREEN + "└───────────────────┘" + RESET);
                System.out.print(BOLD + BLUE + " Please enter status for this order: " + RESET);
                String c2 = sc.nextLine();
                if (c2.equals("1")) {
                    changeStatus(orderList, orderUserList, id, 1);

                } else if (c2.equals("2")) {
                    changeStatus(orderList, orderUserList, id, 2);
                } else {
                    changeStatus(orderList, orderUserList, id, 3);
                }

            } else {
                break;
            }
        }
        System.out.println(BOLD + RED + " Exiting view order user..." + RESET);

    }

    public void viewOrderFromUser(Long id) {
        System.out.println(BOLD + GREEN + "---List order---" + RESET);
        List<OrderUserImpl> orderList = new HandleOrderUser().read(AllFile.fileOrderUserTxt);
        List<OrderUserImpl> orderFind = new ArrayList<>();
        for (OrderUserImpl x : orderList) {
            if (x.getUserId().equals(id)) {
                orderFind.add(x);
            }
        }
        OrderUserImpl.printTableOrderForUser(orderFind);
    }
}

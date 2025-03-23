package utils.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Cart;
import domain.entity.OrderUserImpl;
import domain.entity.Product;
import domain.entity.OrderImpl;
import handle.HandleCart;
import handle.HandleOrder;
import handle.HandleOrderUser;
import handle.HandleProduct;
import setupFile.AllFile;
import utils.FormatData;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class OpenCart {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    private static Scanner sc = new Scanner(System.in);

    // cho người dùng nhập thông tin
    public static void inforAndHandleOrder(Long userId, Long productId, String name, Long price, Long qty) {
        List<OrderUserImpl> newOrder = new ArrayList<>();
        HandleOrder handleOrder = new HandleOrder();
        HandleOrderUser handleOrderUser = new HandleOrderUser();
        System.out.println(BOLD + GREEN + " Please enter order details:" + RESET);
        String nameUser;

        while (true) {
            System.out.print(" Name: ");
            nameUser = sc.nextLine();
            if (nameUser.trim().isEmpty() || nameUser.equals("")) {
                System.out.println(BOLD + RED + " Name must be not empty!" + RESET);

                continue;
            } else {
                break;
            }
        }
        String address;
        while (true) {
            System.out.print(" Address: ");
            address = sc.nextLine();
            if (address.trim().isEmpty() || address.equals("")) {
                System.out.println(BOLD + RED + " Address must be not empty!" + RESET);

                continue;
            } else {
                break;
            }
        }
        String phone;
        while (true) {
            System.out.print(" Phone Number: ");
            phone = sc.nextLine();
            if (phone.trim().isEmpty() || phone.equals("")) {
                System.out.println(BOLD + RED + " Phone number must be not empty!" + RESET);
                continue;
            } else {
                break;
            }
        }

        List<OrderImpl> orderList = handleOrder.read(AllFile.fileOrderTxt);
        Long orderId = orderList == null || orderList.isEmpty() ? 1L // nếu là lần đầu thêm vô cart cho id = 1
                : orderList.get(orderList.size() - 1).getId() + 1; // lấy id của cart cuói cùng + 1

        List<OrderUserImpl> orderUserList = handleOrderUser.read(AllFile.fileOrderUserTxt);
        Long orderUserId = orderUserList == null || orderUserList.isEmpty() ? 1L
                : orderUserList.get(orderUserList.size() - 1).getId() + 1;
        orderList.add(new OrderImpl(
                orderId,
                userId,
                productId,
                nameUser,
                address,
                phone,
                price,
                orderUserId));
        handleOrder.writeFile(AllFile.fileOrderTxt, orderList);
        orderUserList.add(new OrderUserImpl(
                orderUserId,
                name,
                nameUser,
                address,
                phone,
                userId,
                price,
                0,
                qty,
                productId));
        orderUserList.sort(Comparator.comparingLong(OrderUserImpl::getId));
        handleOrderUser.writeFile(AllFile.fileOrderUserTxt, orderUserList);
        System.out.println(BOLD + YELLOW + " Order placed successfully!" + RESET);
        System.out.println(BOLD + " Your Order Details:" + RESET);
        newOrder.add(new OrderUserImpl(
                orderUserId,
                name,
                nameUser,
                address,
                phone,
                userId,
                price,
                0,
                qty,
                productId));
        OrderUserImpl.printTableOrderForUser(newOrder);
    }

    @SuppressWarnings("static-access")
    public void openCart(Long userId) {
        // cart
        HandleCart handleCart = new HandleCart();
        List<Cart> list = handleCart.read(AllFile.fileCartTxt);
        List<Cart> cartList = new ArrayList<>();
        // product
        HandleProduct handleProduct = new HandleProduct();
        List<Product> proList = handleProduct.read(AllFile.fileProductTxt);
        boolean checkCurrentStock = false;
        for (Cart x : list) {
            if (x.getUserId().equals(userId)) {
                cartList.add(x);
            }
        }
        new Cart().printTable(cartList);
        if (!cartList.isEmpty()) {
            System.out.print(BOLD + GREEN + " Buy product or exit cart(y/n): " + RESET);
            char q = sc.nextLine().charAt(0);
            if (q == 'y' || q == 'Y') {
                try {
                    Long cartIdCurrent;
                    while (true) {
                        System.out.print(BOLD + BLUE + " Id of product: " + RESET);
                        boolean checkI = false;
                        cartIdCurrent = Long.parseLong(sc.nextLine());
                        for (Cart x : cartList) {
                            if (x.getId().equals(cartIdCurrent)) {
                                checkI = true;
                                break;
                            }
                        }
                        if (checkI) {
                            break;
                        }
                        System.out.println(BOLD + RED + " Id product not exists cart..." + RESET);
                    }
                    String name = "";
                    Long price = null;
                    Long qty = null;
                    Long productId = null;
                    Long userID = null;
                    for (Cart x : cartList) {
                        if (x.getId().equals(cartIdCurrent)) {
                            name += x.getName();
                            price = x.getPrice();
                            qty = x.getQty();
                            productId = x.getProductId();
                            userID = x.getUserId();
                        }
                    }
                    System.out.println(BOLD + CYAN + " Current stock product: " + RESET + BOLD + qty);
                    System.out.print(BOLD + YELLOW + " Keep the current quantity?(y/n): " + RESET);
                    char qq = sc.nextLine().charAt(0);
                    if (qq == 'y') {
                        System.out.println(BLUE + " Product selected (ID = " + cartIdCurrent + ")" + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.println(BOLD + " Name: " + name + RESET);
                        System.out.println(BOLD + " Price: " + new FormatData().formatPrice(price) + RESET);
                        System.out.println(BOLD + " Quantity: " + qty + RESET);
                        System.out.println(BOLD + " Total: " + new FormatData().formatPrice(price * qty) + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.print(BOLD + YELLOW + "Buy(y/n)?: " + RESET);
                        char qqq = sc.nextLine().charAt(0);
                        if (qqq == 'y') {
                            for (Product x : proList) {
                                if (x.getCode().equals(productId)) {
                                    if (x.getStock() >= qty) {
                                        checkCurrentStock = true;
                                        break;
                                    }
                                }
                            }
                            if (checkCurrentStock) {
                                handleCart.delete(AllFile.fileCartTxt, Optional.of(cartIdCurrent));
                                inforAndHandleOrder(userId, productId, name, price * qty, qty);
                            } else {
                                System.out.println(BOLD + RED
                                        + " The quantity of this product has been changed,\n please add the product back to your cart."
                                        + RESET);
                                // xoa trong gio cua user nay
                                handleCart.delete(AllFile.fileCartTxt, Optional.of(cartIdCurrent));

                            }

                        }
                    } else {
                        checkCurrentStock = false;
                        System.out.print(BOLD + GREEN + " Please enter quantity: " + RESET);
                        Long quantity = Long.parseLong(sc.nextLine());
                        System.out.println(BLUE + " Product selected (ID = " + cartIdCurrent + ")" + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.println(BOLD + " Name: " + name + RESET);
                        System.out.println(BOLD + " Price: " + new FormatData().formatPrice(price) + RESET);
                        System.out.println(BOLD + " Quantity: " + quantity + RESET);
                        System.out.println(BOLD + " Total: " + new FormatData().formatPrice(price * quantity) + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.print(BOLD + YELLOW + " Buy(y/n)?: " + RESET);
                        char qqq = sc.nextLine().charAt(0);
                        if (qqq == 'y') {
                            for (Product x : proList) {
                                if (x.getCode().equals(productId)) {
                                    if (x.getStock() >= quantity) {
                                        checkCurrentStock = true;
                                        break;
                                    }
                                }
                            }
                            if (checkCurrentStock) {
                                if (qty - quantity <= 0) {
                                    handleCart.delete(AllFile.fileCartTxt, Optional.of(cartIdCurrent));
                                    inforAndHandleOrder(userId, productId, name, price * quantity, quantity);

                                } else {
                                    // chỉ cần giảm số lượng
                                    // xóa và thêm dữ liệu mới từ dữ liệu cũ cập nhật qty
                                    for (Cart x : list) {
                                        if (x.getId().equals(cartIdCurrent)) {
                                            x.setQty(qty - quantity);
                                            x.setTotal((qty - quantity) * price);
                                        }
                                    }
                                    handleCart.writeFile(AllFile.fileCartTxt, list);
                                    inforAndHandleOrder(userId, productId, name, price * quantity, quantity);

                                }
                            } else {
                                System.out.println(BOLD + RED
                                        + " The quantity of this product has been changed,\n please add the product back to your cart."
                                        + RESET);
                                handleCart.delete(AllFile.fileCartTxt, Optional.of(cartIdCurrent));

                            }

                        }
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println(BOLD + BLUE + " Exiting view your cart..." + RESET);
            }

        }
    }
}

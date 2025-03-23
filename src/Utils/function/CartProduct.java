/**
 *
 * @author Vo Anh Ben - CE190709
 */
package utils.function;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Cart;
import domain.entity.Product;
import handle.HandleCart;
import handle.HandleProduct;
import setupFile.AllFile;
import utils.FormatData;

public class CartProduct {
    public static Long currentSt;
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    private static Scanner sc = new Scanner(System.in);

    public void cartProduct(Long userId) {
        Long cartId;
        boolean checkProduct = false;
        String name = "", brand = "", desc = "";
        Long price = null, code = null, stock = null;
        HandleProduct handleProduct = new HandleProduct();
        HandleCart handleCart = new HandleCart();

        System.out.println(CYAN + "══════════════════════════════════════" + RESET);
        System.out.print(BOLD + GREEN + "Please enter ID of the product you want to add to cart: " + RESET);

        Long idProduct;
        try {
            idProduct = sc.nextLong();
            sc.nextLine(); // Đọc ký tự xuống dòng còn lại
        } catch (Exception e) {
            System.out.println(RED + "Invalid product ID! Please enter a number." + RESET);
            sc.nextLine(); // Clear buffer
            return;
        }

        List<Product> proList = handleProduct.read(AllFile.fileProductTxt);
        for (Product x : proList) {
            if (x.getCode().equals(idProduct)) {
                brand = x.getBrand();
                name = x.getName();
                desc = x.getDescription();
                price = x.getPrice();
                stock = x.getStock();
                code = x.getCode();
                checkProduct = true;
                break;
            }
        }

        if (!checkProduct) {
            System.out.println(RED + "Product not found or out of stock!" + RESET);
            return;
        }

        System.out.println(BOLD + CYAN + " Current stock product: " + RESET + stock);
        System.out.print(BOLD + BLUE + " Please enter quantity product for add to cart: " + RESET);

        Long st;
        try {
            st = sc.nextLong();
            sc.nextLine(); // Đọc ký tự xuống dòng còn lại
        } catch (Exception e) {
            System.out.println(RED + "Invalid quantity! Please enter a number." + RESET);
            sc.nextLine(); // Clear buffer
            return;
        }

        if (st <= 0 || st > stock) {
            System.out.println(
                    RED + " The quantity of the product cannot be greater or less than the available stock." + RESET);
            return;
        }

        System.out.println(BLUE + " Product selected (ID = " + idProduct + ")" + RESET);
        System.out.println(YELLOW + "---------------------------------------" + RESET);
        System.out.println(BOLD + " Name: " + name + RESET);
        System.out.println(BOLD + " Brand: " + brand + RESET);
        System.out.println(BOLD + " Description: " + desc + RESET);
        System.out.println(BOLD + " Quantity: " + st + RESET);
        System.out.println(BOLD + " Price: " + new FormatData().formatPrice(price * st) + RESET);
        System.out.println(YELLOW + "---------------------------------------" + RESET);
        System.out.print(BOLD + GREEN + " <> Add to Cart (y/n): " + RESET);

        char question = sc.nextLine().charAt(0);
        if (question != 'y' && question != 'Y') {
            return;
        }

        try {
            List<Cart> cartList = handleCart.read(AllFile.fileCartTxt);
            Long newQty = st;

            for (Cart cart : cartList) {
                if (cart.getProductId().equals(idProduct)) {
                    newQty += cart.getQty();
                    handleCart.delete(AllFile.fileCartTxt, Optional.of(cart.getId()));
                    break;
                }
            }

            cartId = cartList.isEmpty() ? 1L : cartList.get(cartList.size() - 1).getId() + 1;
            Cart newCart = new Cart(cartId, userId, name, price, newQty, price * newQty, idProduct);
            handleCart.addNew(AllFile.fileCartTxt, newCart);

            // Cập nhật lại stock
            Long currentStock = stock - st;
            currentSt = currentStock;
            if (currentStock == 0) {

                handleCart.delete(AllFile.fileProductTxt, Optional.of(code));
//                new HandleProduct().deleteProduct(AllFile.fileProductTxt, code); // Xóa sản phẩm khi hết hàng
            } else {
//                for (Product x : proList) {
//                    if (x.getCode().equals(code)) {
//                        x.setStock(currentStock);
//                        break;
//                    }
//                }
//                new HandleProduct().writeFile(AllFile.fileProductTxt, proList);
            }

            System.out.println(BOLD + BLUE + " Order placed successfully!" + RESET);
        } catch (Exception e) {
            System.out.println(RED + " An error occurred while processing your order: " + e.getMessage() + RESET);
        }
    }
}


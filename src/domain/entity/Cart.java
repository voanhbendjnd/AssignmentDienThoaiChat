package domain.entity;

import java.util.List;

import utils.FormatData;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class Cart {
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm
    private Long id;
    private Long userId;
    private Long price;
    private Long qty;
    private String name;
    private Long productId;
    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Cart() {
    }

    public Cart(Long id, Long userId, String name, Long price, Long qty, Long totalProducts, Long productId) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.qty = qty;
        this.name = name;
        this.productId = productId;
        this.total = totalProducts;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotalProducts() {
        return this.qty * this.price;
    }

    public String toStringFormatted() {
        return this.id + "?" + this.userId + "?" + this.name + "?" + this.price + "?" + this.qty + "?"
                + this.getTotal() + "?"
                + this.productId;
    }

    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-26s%s | %s%-26s%s | %s%-26d%s | %s%-26s%s |",
                BOLD, YELLOW, this.id, RESET,
                GREEN, this.name, RESET,
                RED, new FormatData().formatPrice(this.price), RESET,
                BLUE, this.qty, RESET,
                YELLOW, new FormatData().formatPrice(this.getTotal()), RESET);
    }

    public static void printTable(List<Cart> ordersList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+----------------------------+----------------------------+----------------------------+----------------------------+\n");
        sb.append(
                "| ID    | Name                       | Price                      | Quantity                   | Total                      |\n");
        sb.append(
                "+-------+----------------------------+----------------------------+----------------------------+----------------------------+\n");
        for (Cart order : ordersList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN + "+-------+----------------------------+----------------------------+----------------------------+----------------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }

}

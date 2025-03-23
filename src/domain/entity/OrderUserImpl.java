package domain.entity;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

import domain.Order;
import utils.FormatData;

public class OrderUserImpl extends Order {
    private String nameProduct;
    private Long qty;
    private Long userId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public Long getQty() {
        return qty;
    }

    public OrderUserImpl(Long id, String nameProduct, String userName, String address, String phone, Long userId,
            Long price, int status, Long qty, Long product_id) {
        super(id, userName, address, phone, price, product_id);
        this.nameProduct = nameProduct;
        this.userId = userId;
        this.status = status;
        this.qty = qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public String getNameProduct() {
        return nameProduct;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String toStringFormatted() {
        return super.getId() + "?" + this.nameProduct + "?" + super.getName() + "?" + super.getAddress() + "?"
                + super.getPhone() + "?" + this.userId + "?" + super.getPrice() + "?" + this.status + "?" + this.qty
                + "?" + super.getProduct_id();
    }

    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-30s%s | %s%-30s%s | %s%-30s%s | %s%-60s%s | %s%-20s%s | %s%-20s%s |",
                BOLD, YELLOW, super.getId(), RESET,
                RED, this.nameProduct, RESET,
                BOLD, new FormatData().formatPrice(super.getPrice()), RESET,
                YELLOW, super.getName(), RESET,
                GREEN, super.getAddress(), RESET,
                BLUE, super.getPhone(), RESET,
                this.status == 1 ? GREEN : this.status == 2 ? RED : YELLOW,
                this.status == 1 ? "Confirmed" : this.status == 2 ? "Not Confirmed" : "Undetermined", RESET);
    }

    public static void printTableOrderForUser(List<OrderUserImpl> ordersList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n");
        sb.append(
                "| ID    | Product                        | Price                          | Name                           | Address                                                      | Phone                | Status               |\n");
        sb.append(
                "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n");
        for (OrderUserImpl order : ordersList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN +
                            "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }

}

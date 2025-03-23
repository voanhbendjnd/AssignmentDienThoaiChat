
/**
 *
 * @author Vo Anh Ben - CE190709
 */
package domain.entity;

import java.util.List;
import utils.FormatData;
import utils.constant.FactoryEnum;
import utils.constant.TargetEnum;

public class Product {
    private Long id;
    private Long code;
    private String name;
    // private String brand;
    private FactoryEnum factory;
    private Long price;
    private String description;
    private Long stock;
    private TargetEnum target;

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public Product() {
    }

    public Product(Long code, String name, FactoryEnum factory, TargetEnum target, Long price, String description,
            Long stock) {
        this.name = name;
        this.code = code;
        this.factory = factory;
        this.price = price;
        this.target = target;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public TargetEnum getTarget() {
        return target;
    }

    public void setTarget(TargetEnum target) {
        this.target = target;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public FactoryEnum getFactory() {
        return factory;
    }

    public void setFactory(FactoryEnum factory) {
        this.factory = factory;
    }

    public String toStringFormatted() {
        return this.code + "?" + this.name + "?" + this.factory + "?" + this.target + "?" + this.price + "?"
                + this.description + "?"
                + this.stock;
    }

    @Override
    public String toString() {
        return String.format("| %s%-5d%s | %s%-26s%s | %s%-16s%s | %s%-23s%s | %s%-15s%s | %s%-80s%s | %s%-5d%s |",
                BOLD, this.code, RESET,
                GREEN, this.name, RESET,
                BLUE, this.factory, RESET,
                CYAN, this.target, RESET,
                RED, new FormatData().formatPrice(this.price), RESET,
                YELLOW, this.description, RESET,
                GREEN, this.stock, RESET);
    }

    public static void printTable(List<Product> productsList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n");
        sb.append(
                "| Code  | Name                       | Brand            | Target                  | Price           | Description                                                                      | Stock |\n");
        sb.append(
                "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n");
        sb.append(RESET);
        for (Product product : productsList) {
            sb.append(product.toString()).append("\n");
            // sb.append(
            // CYAN +
            // "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n"
            // + RESET);
        }
        sb.append(
                CYAN +
                        "+-------+----------------------------+------------------+-------------------------+-----------------+----------------------------------------------------------------------------------+-------+\n"
                        + RESET);
        System.out.println(sb.toString());
    }
}

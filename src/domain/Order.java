/**
 *
 * @author Vo Anh Ben - CE190709
 */
package domain;

public class Order {
    private Long id;
    private Long price;
    private String name;
    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    private String address;
    private String phone;
    private Long product_id;

    public Order(Long id, String name, String address, String phone, Long price, Long product_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.product_id = product_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}

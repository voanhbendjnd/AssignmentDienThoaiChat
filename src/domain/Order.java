/**
 *
 * @author Vo Anh Ben - CE190709
 */
package domain;

public class Order {
    private Long id;
    private Long price;
    private String name;
    private String address;
    private String phone;

    public Order(Long id, String name, String address, String phone, Long price) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.price = price;
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

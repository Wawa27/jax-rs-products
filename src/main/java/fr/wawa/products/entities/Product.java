package fr.wawa.products.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "id")
    private Long id;
    private int quantity;
    private String name;
    private BigDecimal price;
    private String detail;
    private String info;
    private String image;

    public Product() {

    }

    public Product(Long id, int quantity, String name, BigDecimal price, String detail, String info, String image) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.info = info;
        this.image = image;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = 64)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 64)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 64)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && id.equals(product.id) && name.equals(product.name) && price.equals(product.price) && detail.equals(product.detail) && info.equals(product.info) && image.equals(product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, name, price, detail, info, image);
    }
}

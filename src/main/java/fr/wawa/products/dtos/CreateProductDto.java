package fr.wawa.products.dtos;

import fr.wawa.products.entities.Product;

import java.math.BigDecimal;

public class CreateProductDto {
    private String name;
    private String detail;
    private BigDecimal price;
    private int quantity;
    private String info;
    private String image;

    public CreateProductDto() {

    }

    public CreateProductDto(String name, String detail, BigDecimal price, int quantity, String info, String image) {
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.image = image;
    }

    public Product toProduct() {
        return new Product(null, quantity, name, price, detail, info, image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

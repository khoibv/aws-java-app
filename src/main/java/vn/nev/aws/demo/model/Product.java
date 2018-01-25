package vn.nev.aws.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(length = 100, nullable = false)
  private String category;

  @Column(length = 255, nullable = false)
  private String productName;

  @Column(nullable = false)
  private double price;

  @Column(length = 255)
  private String image;

  public Product() {
  }

  public Product(int id, String category, String productName, double price) {
    this();
    this.setId(id);
    this.setCategory(category);
    this.setProductName(productName);
    this.setPrice(price);
  }

  @Override
  public String toString() {
    return String.format("{productName=%s, category=%s, price=%s, image=%s}", this.getProductName(),
        this.getCategory(), this.getPrice(), this.getImage());
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}

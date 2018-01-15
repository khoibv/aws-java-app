package vn.nev.aws.demo.model;


public class Product {

  private int id;
  private String category;
  private String productName;
  private double price;

  public Product() {}

  public Product(int id, String category, String productName, double price) {
    this();
    this.setId(id);
    this.setCategory(category);
    this.setProductName(productName);
    this.setPrice(price);
  }

  @Override
  public String toString() {
    return String.format("{productName=%s, category=%s, price=%s}", this.getProductName(),
        this.getCategory(), this.getPrice());
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
}

package vn.nev.aws.demo.form;

public class SearchForm {


  private String productName;
  private String category;

  @Override
  public String toString() {
    return "SearchForm{" +
        "productName='" + productName + '\'' +
        ", category='" + category + '\'' +
        '}';
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}

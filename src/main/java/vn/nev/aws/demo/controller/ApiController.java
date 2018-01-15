package vn.nev.aws.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.nev.aws.demo.model.Product;

@RestController
@RequestMapping("api")
public class ApiController {

  private static final List<Product> products = Arrays.asList(
      new Product(1, "fruit", "Apple", 10f),
      new Product(2, "vegetable", "Pumpkin", 20f),
      new Product(3, "fruit", "Watermelon", 30f),
      new Product(4, "vegetable", "Tomato", 40f),
      new Product(5, "fruit", "Strawberry", 50f),
      new Product(6, "vegetable", "Cucumber", 60f),
      new Product(7, "fruit", "Grape", 70f),
      new Product(8, "vegetable", "Mushroom", 80f),
      new Product(9, "fruit", "Banana", 90f)
  );


  /**
   *
   * @param category
   * @return
   */
  @GetMapping("products")
  @ResponseBody
  public List<Product> listProducts(String category) {

    // Filter by category
    return products
        .stream()
        .filter(product -> Objects.equals(product.getCategory(), category))
        .collect(Collectors.toList());
  }


}

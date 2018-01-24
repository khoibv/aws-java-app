package vn.nev.aws.demo.service;

import java.util.List;
import vn.nev.aws.demo.form.SearchForm;
import vn.nev.aws.demo.model.Product;

public interface ProductService {

  List<Product> list();

  Product findById(int id);

  List<Product> search(SearchForm searchForm);

  List<Product> findByCategory(String category);

  Product create(Product product);

  Product update(Product product);

  void delete(int productId);
}

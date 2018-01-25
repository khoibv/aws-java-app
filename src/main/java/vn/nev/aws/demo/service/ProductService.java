package vn.nev.aws.demo.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import vn.nev.aws.demo.form.SearchForm;
import vn.nev.aws.demo.model.Product;

public interface ProductService {

  List<Product> list();

  Product findById(int id);

  List<Product> search(String searchForm);

  List<Product> findByCategory(String category);

  Product create(Product product, MultipartFile uploadFile) throws IOException;

  Product update(Product product, MultipartFile uploadFile) throws IOException;

  void delete(int productId);
}

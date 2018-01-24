package vn.nev.aws.demo.service;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.nev.aws.demo.dao.ProductRepository;
import vn.nev.aws.demo.form.SearchForm;
import vn.nev.aws.demo.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;


  @Override
  public List<Product> list() {
    List<Product> products = new ArrayList<>();

    productRepository.findAll().forEach(products::add);
    // Order by category
//    products.sort(Comparator.comparing(Product::getCategory));

    return products;
  }

  @Override
  public Product findById(int id) {
    return productRepository.findById(id).orElse(null);
  }

  @Override
  public List<Product> search(SearchForm searchForm) {
    return productRepository
        .searchByNameAndCategory(searchForm.getProductName(), searchForm.getCategory());
  }

  @Override
  public List<Product> findByCategory(String category) {
    return productRepository.getByCategory(category);
  }

  @Override
  public Product create(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(int productId) {
    productRepository.deleteById(productId);
  }
}

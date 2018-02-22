package vn.nev.aws.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.nev.aws.demo.dao.ProductRepository;
import vn.nev.aws.demo.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private AwsStorageService storageService;

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
  public List<Product> search(String searchForm) {
    return productRepository
        .searchByNameAndCategory(searchForm);
  }

  @Override
  public List<Product> findByCategory(String category) {
    return productRepository.getByCategory(category);
  }

  @Override
  @Transactional
  public Product create(Product product, MultipartFile uploadFile) throws IOException {
    if (uploadFile != null && uploadFile.getInputStream() != null) {
      storageService.save(uploadFile.getInputStream(), uploadFile.getOriginalFilename());
      product.setImage(uploadFile.getOriginalFilename());
    }

    return productRepository.save(product);
  }

  @Override
  @Transactional
  public Product update(Product product, MultipartFile uploadFile) throws IOException {
    if (uploadFile != null && uploadFile.getInputStream() != null) {
      storageService.save(uploadFile.getInputStream(), uploadFile.getOriginalFilename());
      product.setImage(uploadFile.getOriginalFilename());
    }

    return productRepository.save(product);
  }

  @Override
  public void delete(int productId) {
    productRepository.deleteById(productId);
  }
}

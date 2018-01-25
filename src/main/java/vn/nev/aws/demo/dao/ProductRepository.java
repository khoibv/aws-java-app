package vn.nev.aws.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.nev.aws.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

  List<Product> getByCategory(String category);

  @Query("select p from Product p where (?1 = '' or p.productName like %?1% or p.category like %?1%)")
  List<Product> searchByNameAndCategory(String searchTerm);

}

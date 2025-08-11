package org.example.productcatalogservice_june2025_morning.repos;

import org.example.productcatalogservice_june2025_morning.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
   Optional<Product> findById(Long id);

   Page<Product> findByNameEquals(String query, Pageable pageable);

   Product save(Product product);

   List<Product> findAll();

   List<Product> findProductByPriceBetween(Double low,Double high);

   List<Product> findAllByOrderByPriceDesc();

   @Query("SELECT p.description from Product p where p.id=:id")
   String getMeDescriptionForProductId(Long id);
}


//void fn(double value,String name) {
//}
//
//fn(1,"anurag");
//fn(name:"anurag",value:1);


//Pageable p = new PageRequest();
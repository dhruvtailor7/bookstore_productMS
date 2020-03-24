package com.coviam.bookstore.productMS.repository;

import com.coviam.bookstore.productMS.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product,String> {

    List<Product> findByProductIdIn(List<String> productIds);


}

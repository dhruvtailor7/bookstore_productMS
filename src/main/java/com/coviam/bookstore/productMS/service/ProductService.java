package com.coviam.bookstore.productMS.service;

import com.coviam.bookstore.productMS.dto.ProductDetailsDTO;
import com.coviam.bookstore.productMS.dto.RemoveProductDTO;
import com.coviam.bookstore.productMS.entity.Product;

import java.util.ArrayList;
import java.util.List;


public interface ProductService {
    String addProduct(ProductDetailsDTO productDetailsDTO);

    public String deleteProductById(RemoveProductDTO removeProductDTO);

    Iterable<Product> updateProduct(Product product);

    ProductDetailsDTO getProductById(String id);

    List<String> getGenre();

    ArrayList<Product> getProductByGenre(String genre);
    ArrayList<Product> getTopProducts();

    String getProductURLByProductId(String productId);

    String getProductNameByProductId(String productId);

    String addRating(String rating, String productId);
}

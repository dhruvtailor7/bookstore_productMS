package com.coviam.bookstore.productMS.controller;

import com.coviam.bookstore.productMS.dto.*;
import com.coviam.bookstore.productMS.entity.Product;
import com.coviam.bookstore.productMS.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class ProductController {



    @Autowired
    private ProductService productService;


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "kafkaAdd";

    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDetailsDTO productDetailsDTO) throws JsonProcessingException {
        String response=productService.addProduct(productDetailsDTO);
        SearchDTO searchDTO=new SearchDTO();
        BeanUtils.copyProperties(productDetailsDTO,searchDTO);
        searchDTO.setProductId(response);
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(searchDTO));
        return "{\"response\":\""+response+"\"}";
    }

    @GetMapping("/getTopProducts")
    List<ProductDTO3> getTopProducts(){
        List<ProductDTO3> listOfProductDTO = new ArrayList<ProductDTO3>();
        List<Product> productList=productService.getTopProducts();

        for(Product product:productList){
            ProductDTO3 productDTO3=new ProductDTO3();
            BeanUtils.copyProperties(product,productDTO3);
            listOfProductDTO.add(productDTO3);
        }
        return listOfProductDTO;

    }


    @DeleteMapping(value = "/removeProduct")
    String deleteProductById(@RequestBody RemoveProductDTO removeProductDTO){

        return productService.deleteProductById(removeProductDTO);

    }

    @GetMapping("/getProductURLByProductId/{id}")
    String getProductURLByProductId(@PathVariable("id") String productId){
        return productService.getProductURLByProductId(productId);
    }
    @GetMapping("/getProductNameByProductId/{id}")
    String getProductNameByProductId(@PathVariable("id") String productId){
        return productService.getProductNameByProductId(productId);
    }

    @GetMapping("/getProductByProductId/{id}")
    ProductDetailsDTO getProductById(@PathVariable("id") String id){
        System.out.println("called---__>"+id);
        return productService.getProductById(id);
//        System.out.println("Product :"+product);
//        ProductDTO productDTO =new ProductDTO();
//        BeanUtils.copyProperties(product, productDTO);
//        return productDTO;
    }

    @GetMapping("/getGenreList")
    List<String> getGenre(){
        return productService.getGenre();
    }

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre){

        List<Product> productList=productService.getProductByGenre(genre);
        List<ProductDTO3> dto3List=new ArrayList<ProductDTO3>();
        for(Product product:productList){
            ProductDTO3 productDTO3=new ProductDTO3();
            BeanUtils.copyProperties(product,productDTO3);
            dto3List.add(productDTO3);
        }
        return dto3List;
    }

    @PostMapping("/addProductRating")
    String addProductRating(@RequestBody ProductRatingDTO productRatingDTO){
        return productService.addRating(productRatingDTO.getRating(),productRatingDTO.getProductId());
    }

}

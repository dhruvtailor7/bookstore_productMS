package com.coviam.bookstore.productMS.service.impl;

import com.coviam.bookstore.productMS.client.MerchantClient;
import com.coviam.bookstore.productMS.dto.ProductDetailsDTO;
import com.coviam.bookstore.productMS.dto.ProductMerchantDTO;
import com.coviam.bookstore.productMS.dto.RemoveProductDTO;
import com.coviam.bookstore.productMS.entity.Product;
import com.coviam.bookstore.productMS.repository.ProductRepository;
import com.coviam.bookstore.productMS.service.ProductService;
import org.apache.kafka.common.security.auth.Login;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private static DecimalFormat df=new DecimalFormat("0.00");
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    MerchantClient merchantClient;

    @Override
    public String addProduct(ProductDetailsDTO productDetailsDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDetailsDTO,product);
        product.setRating("0");
        product.setCount("0");
        List<Product> productArrayList=(ArrayList<Product>) productRepository.findAll();
        productArrayList = productArrayList.stream().filter(product1 -> (product1.getIsbn().trim()).equals(productDetailsDTO.getIsbn().trim())).collect(Collectors.toList());
        String product_id="";
        if(productArrayList.size()!=0){
            Product product1=productArrayList.get(0);
            productDetailsDTO.setProductId(product1.getProductId());
            float oldPrice=Float.parseFloat(product1.getPrice());
            float newPrice=Float.parseFloat(productDetailsDTO.getPrice());
            if(oldPrice > newPrice)
                productDetailsDTO.setPrice(String.valueOf(newPrice));

            product.setProductId(product1.getProductId());
            product_id = productRepository.save(product).getProductId();
        }
        else {
            product_id = productRepository.save(product).getProductId();
        }
            ProductMerchantDTO productMerchantDTO = new ProductMerchantDTO();
            System.out.println("----_>ProductDetails" + productDetailsDTO);
            productMerchantDTO.setMerchantId(productDetailsDTO.getMerchantId());
            productMerchantDTO.setProductId(product_id);
            productMerchantDTO.setPrice(productDetailsDTO.getPrice());
            productMerchantDTO.setQuantity(productDetailsDTO.getQuantity());
            System.out.println(productMerchantDTO);
            merchantClient.addProductMerchant(productMerchantDTO);
            return product_id;
    }


    ////////TODO     Add comparator
    @Override
    public ArrayList<Product> getTopProducts() {
        ArrayList<Product> productList = (ArrayList<Product>) productRepository.findAll();
        productList=(ArrayList<Product>)productList.stream().sorted().limit(20).collect(Collectors.toList());
        //Page<Login> page= loginRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return productList;
    }

    @Override
    public String deleteProductById(RemoveProductDTO removeProductDTO) {
        productRepository.deleteById(removeProductDTO.getProductId());
        merchantClient.removeProduct(removeProductDTO);
        return "{\"response\":\"Success\"}";


    }

    @Override
    public Iterable<Product> updateProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        System.out.println("------_>"+id);
        Optional<Product> o= productRepository.findById(id);
        System.out.println(o);
        Product product= o.get();
        ProductDetailsDTO productDetailsDTO=new ProductDetailsDTO();
        BeanUtils.copyProperties(product,productDetailsDTO);
        ProductMerchantDTO productMerchantDTO=merchantClient.getDefaultMerchantByProductId(id);
        BeanUtils.copyProperties(productMerchantDTO,productDetailsDTO);
        return productDetailsDTO;
    }

    @Override
    public  ArrayList<Product> getProductByGenre(String genre)
    {


        List<Product> productList=(ArrayList<Product>)productRepository.findAll();
        return (ArrayList<Product>) productList.stream().filter(product -> product.getGenre().equals(genre)).collect(Collectors.toList());


    }

    @Override
    public String getProductNameByProductId(String productId) {
        return productRepository.findById(productId).get().getProductName();
    }

    @Override
    public String getProductURLByProductId(String productId) {
        return productRepository.findById(productId).get().getUrl();

    }

    @Override
    public List<String> getGenre() {

        List<Product> listOfProduct = (ArrayList<Product>)productRepository.findAll();
        return listOfProduct.stream().map(e -> e.getGenre()).distinct().collect(Collectors.toList());
    }


    @Override
    public String addRating(String rating, String productId) {

        System.out.println(productId);
        Product product=productRepository.findById(productId).get();
        float prevRating=Float.parseFloat(product.getRating());
        float givenRating = Float.parseFloat(rating);
        int count = Integer.parseInt(product.getCount());
        float newRating;
        if(count == 0) {
            newRating = givenRating;
            count++;
        }
        else {
            newRating = (prevRating*count) + givenRating;
            newRating = newRating/(count+1);
            count++;
        }
        product.setCount(String.valueOf(count));
        product.setRating(String.valueOf(df.format(newRating)));
        System.out.println("adding rating");
        return productRepository.save(product).getRating();
    }
}

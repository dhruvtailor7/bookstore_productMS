package com.coviam.bookstore.productMS.client;

import com.coviam.bookstore.productMS.dto.ProductMerchantDTO;
import com.coviam.bookstore.productMS.dto.RemoveProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("merchant")
public interface MerchantClient {


    @PostMapping("/addProductMerchant")
    String addProductMerchant(@RequestBody ProductMerchantDTO productMerchantDTO);

    @DeleteMapping("/removeProduct")
    String removeProduct(@RequestBody RemoveProductDTO removeProductDTO);


    @GetMapping("/getProductMerchantByMerchantId/{id}")
    List<ProductMerchantDTO> getProductMerchantByMerchantId(@PathVariable("id") String merchantId);



    @GetMapping("/getDefaultMerchantByProductId/{id}")
    ProductMerchantDTO getDefaultMerchantByProductId(@PathVariable("id") String productId);
}

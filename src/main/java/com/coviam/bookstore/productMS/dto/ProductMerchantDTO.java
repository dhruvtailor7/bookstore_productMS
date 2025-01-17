package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductMerchantDTO {
    private String productId;
    private  String merchantId;
    private  String quantity;
    private  String price;
}

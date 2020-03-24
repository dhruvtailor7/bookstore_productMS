package com.coviam.bookstore.productMS.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Setter
@Getter
@ToString
public class ProductDetailsDTO {
    private String merchantId;
    private String productId;
    private String productName;
    private String genre;
    private HashMap<String,String> attributes;
    private String quantity;
    private String price;
    private String url;
    private String author;  ///////TODO add in UI
    private  String description;
    private String isbn;
    private String rating;
    private String count;






}

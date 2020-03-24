package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ProductDTO2 {

    private String productId;
    private String productName;
    private String genre;
    private String rating;
    private  HashMap<String,String> attributes;
    private  String description;
    private String author;
    private  String url;
    private  String isbn;
    private  String price;
    private String quantity;
}

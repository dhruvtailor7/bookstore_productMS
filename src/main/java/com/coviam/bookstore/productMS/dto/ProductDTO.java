package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.util.HashMap;

@Getter
@Setter
public class ProductDTO {
    private String productId;
    private String productName;
    private String genre;
    private String rating;
    private HashMap<String,String> attributes;
    private  String description;
    private  String author;
    private  String url;
    private  String isbn;
    private String price;




}

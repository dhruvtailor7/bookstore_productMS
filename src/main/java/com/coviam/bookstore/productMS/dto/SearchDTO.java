package com.coviam.bookstore.productMS.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class SearchDTO {
    private String productId;
    private String productName;
    private String genre;
    private String rating;
    private String description;
    private String author;
    private String url;
    private String isbn;
    private String price;
}




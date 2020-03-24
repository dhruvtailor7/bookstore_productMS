package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRatingDTO {
    String productId;
    String rating;
}

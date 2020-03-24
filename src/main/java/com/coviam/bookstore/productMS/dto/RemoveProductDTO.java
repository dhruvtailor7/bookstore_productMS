package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveProductDTO {
    private String productId;
    private String merchantId;
}

package com.coviam.bookstore.productMS.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MerchantDTO {
        private String merchantId;
        private String merchantName;
        private String merchantEmail;
        private String merchantPhone;
        private String pincode;
        private String merchantAddress;
}

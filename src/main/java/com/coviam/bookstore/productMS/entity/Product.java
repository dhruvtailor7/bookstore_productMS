package com.coviam.bookstore.productMS.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

@Entity
@Table(name="PRODUCT")
@Getter
@Setter
public class Product implements Comparable<Product>{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String productId;
    String productName;
    String genre;
    String rating;
    HashMap<String,String> attributes;
    String description;
    String author;
    String url;
    String isbn;
    String price;
    String count;


    @Override
    public int compareTo(Product o) {
        //System.out.println(this.rating);
        float thisRating = Float.parseFloat(this.rating);
        float otherRating = Float.parseFloat(o.getRating());
        if(thisRating > otherRating)
            return -1;
        else if(thisRating < otherRating)
            return 1;
        else
            return 0;

    }
}

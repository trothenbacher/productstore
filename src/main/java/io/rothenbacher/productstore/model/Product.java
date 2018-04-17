package io.rothenbacher.productstore.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */

@Document(indexName = "store", type="products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public Product() { }

    public Product(final String id, final String name, final String description, final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "'id='" + id + ", " +
                "'name='" + name + ", " +
                "'description='" + description + ", " +
                "'price='" + price +
                "}";
    }

}

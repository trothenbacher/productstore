package io.rothenbacher.productstore.service;

import io.rothenbacher.productstore.model.Product;

import java.util.List;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */
public interface ProductService {
    Product save(Product product);
    void delete(Product product);
    Iterable<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByNameFuzzy(String name);
}

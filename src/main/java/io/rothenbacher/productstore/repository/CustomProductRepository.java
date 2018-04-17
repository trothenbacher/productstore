package io.rothenbacher.productstore.repository;

import io.rothenbacher.productstore.model.Product;

import java.util.List;

/**
 * Created by Thomas Rothenbacher on 17.04.18.
 */
public interface CustomProductRepository {
    List<Product> findByNameFuzzy(String name);
}

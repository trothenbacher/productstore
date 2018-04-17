package io.rothenbacher.productstore.repository;

import io.rothenbacher.productstore.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */
public interface ProductRepository extends ElasticsearchRepository<Product, String>, CustomProductRepository {
    List<Product> findByName(String name);
}


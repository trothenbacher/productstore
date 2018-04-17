package io.rothenbacher.productstore.service.impl;

import io.rothenbacher.productstore.model.Product;
import io.rothenbacher.productstore.repository.ProductRepository;
import io.rothenbacher.productstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(final Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(final Product product) {
        productRepository.delete(product);
    }


    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(final String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByNameFuzzy(final String name) {
        return productRepository.findByNameFuzzy(name);
    }
}

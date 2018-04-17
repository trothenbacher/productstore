package io.rothenbacher.productstore;

import io.rothenbacher.productstore.model.Product;
import io.rothenbacher.productstore.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Thomas Rothenbacher on 16.04.18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Product.class);
        esTemplate.createIndex(Product.class);
        esTemplate.putMapping(Product.class);
        esTemplate.refresh(Product.class);
    }

    @Test
    public void testSave() {
        final Product product = new Product("1","TestPhone1", " 512 GB - Grey", new BigDecimal(1024.95));
        final Product testProduct = productService.save(product);

        assertNotNull(testProduct.getId());
        assertEquals(testProduct.getName(), product.getName());
        assertEquals(testProduct.getDescription(), product.getDescription());
        assertEquals(testProduct.getPrice(), product.getPrice());
    }

    @Test
    public void testFindByName() {
        final Product product = new Product("1","TestPhone1", " 512 GB - Grey", new BigDecimal(1024.95));
        productService.save(product);

        final List<Product> products = productService.findByName(product.getName());
        assertThat(products.size(), is(1));
    }

    @Test
    public void testFindByNameFuzzy() {
        final String productName = "TestPhoneFuzzy";
        final String productNameFuzzy = "TestPhoneFuzyz";
        final Product product1 = new Product("1",productName, " 512 GB - Grey", new BigDecimal(1024.95));
        final Product product2 = new Product("2",productName, " 1024 GB - Black", new BigDecimal(999.99));
        final Product product3 = new Product("3","SomethingElse", " 64 GB - Grey", new BigDecimal(729.95));

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);

        final List<Product> products = productService.findByNameFuzzy(productNameFuzzy);
        assertThat(products.size(), is(2));
    }

    @Test
    public void testDelete() {
        final Product product = new Product("1","TestPhone1", " 512 GB - Grey", new BigDecimal(1024.95));

        productService.save(product);
        productService.delete(product);

        final List<Product> testProduct = productService.findByName(product.getName());
        assertTrue(testProduct.isEmpty());
    }
}

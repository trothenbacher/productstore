package io.rothenbacher.productstore;

import io.rothenbacher.productstore.model.Product;
import io.rothenbacher.productstore.service.ProductService;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private ProductService productService;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //printElasticSearchInfo();

        productService.save(new Product("1", "HUAWEI P20 Pro", " 128 GB - Schwarz", new BigDecimal(799.99)));
        productService.save(new Product("2", "Apple iPhone X", " 256 GB - Space Grey", new BigDecimal(999.99)));
        productService.save(new Product("3", "Apple iPhone 8", " 64 GB - Space Grau", new BigDecimal(614.99)));
        productService.save(new Product("4", "Apple iPhone 7", " 32 GB - Schwarz", new BigDecimal(499.99)));
        productService.save(new Product("5", "Nokia 5", " 16 GB - Mattschwarz", new BigDecimal(139.99)));

        List<Product> products = productService.findByNameFuzzy("Aplpe");
        products.forEach(x -> System.out.println(x));
    }

    /**
     * debug method - prints elasticsearch infos
     */
    private void printElasticSearchInfo() {
        final Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        System.out.println("### ElasticSearchInfos ###");
        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("###########################");
    }
}

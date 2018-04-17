package io.rothenbacher.productstore.repository.impl;

import io.rothenbacher.productstore.model.Product;
import io.rothenbacher.productstore.repository.CustomProductRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * Created by Thomas Rothenbacher on 17.04.18.
 */
public class CustomProductRepositoryImpl implements CustomProductRepository {

    @Autowired
    private ElasticsearchOperations es;

    @Override
    public List<Product> findByNameFuzzy(final String name) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name)
                        .operator(AND)
                        .fuzziness(Fuzziness.ONE))
                .build();
         return es.queryForList(searchQuery,Product.class);
    }

}

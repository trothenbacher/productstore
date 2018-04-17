package io.rothenbacher.productstore;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

/**
 * Created by Thomas Rothenbacher on 15.04.18.
 */

@Configuration
@EnableElasticsearchRepositories(basePackages = "io.rothenbacher.productstore.repository")
public class ElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Bean
    public Client client() throws Exception {
        final Settings esSettings = Settings.builder()
                .put("cluster.name", esClusterName)
                //.put("xpack.security.user", "elastic:changme")
                .build();

        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));


        //TODO impl RestHighLevelClient instead of TransportClient with elasticsearch version 6.x
        //return new RestHighLevelClient(RestClient.builder(esHost, esPort, "http"));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}

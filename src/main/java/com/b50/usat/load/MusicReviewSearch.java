package com.b50.usat.load;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.LinkedHashSet;
import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.filteredQuery;
import static org.elasticsearch.index.query.QueryBuilders.wildcardQuery;

/**
 *
 */
public class MusicReviewSearch {

    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        LinkedHashSet<String> servers = new LinkedHashSet<String>();
        servers.add("http://localhost:9200");
        clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);

        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig);
        JestClient client = factory.getObject();

        //QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("brief", "eurythmics");
        //QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("brief", "jazz");
        //QueryBuilder queryBuilder = QueryBuilders.termQuery("brief", "jazz");

        //QueryBuilder queryBuilder = filteredQuery(termQuery("brief", "jazz"), rangeFilter("rating").from(3.5).to(4.0));
        QueryBuilder queryBuilder = filteredQuery(wildcardQuery("brief", "buddy*"), rangeFilter("rating").from(3.5).to(4.0));
        Search search = new Search(queryBuilder);

        search.addIndex("music_reviews");
        search.addType("review");

        JestResult result = client.execute(search);

        List<MusicReview> reviewList = result.getSourceAsObjectList(MusicReview.class);
        for (MusicReview review : reviewList) {
            System.out.println("search result is " + review);
        }
    }
}

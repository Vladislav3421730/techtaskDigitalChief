package com.example.backend.services;

import com.example.backend.exceptions.SearchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class SearchDataService {

    private final RestHighLevelClient restHighLevelClient;
    private final static String INDEX_NAME="products_sku";


    public List<Map<String,Object>> findAll() {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.termQuery("active", true));
        return executeSearch(queryBuilder);
    }


    public List<Map<String,Object>> findByNameOrDescription(String search)  {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("name", search))
                .should(QueryBuilders.matchPhraseQuery("description", search))
                .filter(QueryBuilders.termQuery("active", true))
                .minimumShouldMatch(1);
        return executeSearch(queryBuilder);
    }



    private List<Map<String,Object>> executeSearch(QueryBuilder qb)  {

        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(qb);
        searchSourceBuilder.sort("createdAt",SortOrder.DESC);
        searchSourceBuilder.size(searchRequest.getBatchedReduceSize());

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchStatusException | IOException e) {
            log.error("Произошла ошибка при поиске ",e);
            throw new SearchDataException("Произошла ошибка при поиске products "+e.getMessage());
        }
        log.info("Поиск успешно выполнен");
        List<Map<String, Object>> sourceList=new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            sourceList.add(hit.getSourceAsMap());
        }
        return sourceList;
    }


}

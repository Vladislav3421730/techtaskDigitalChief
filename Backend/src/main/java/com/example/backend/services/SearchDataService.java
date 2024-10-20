package com.example.backend.services;

import com.example.backend.exceptions.SearchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class SearchDataService {

    private final RestHighLevelClient restHighLevelClient;
    private final CreateIndexService createIndexService;
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
                .should(QueryBuilders.nestedQuery("skuList",
                        QueryBuilders.boolQuery()
                                .should(QueryBuilders.matchQuery("skuList.color",search))
                        , ScoreMode.None))
                .filter(QueryBuilders.termQuery("active", true))
                .minimumShouldMatch(1);
        return executeSearch(queryBuilder);
    }



    private List<Map<String,Object>> executeSearch(QueryBuilder qb)  {
        createIndexService.createIndex();//Если индекс создан эту строку можно убрать

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
        return Arrays.stream(searchResponse.getHits().getHits())
                .map(hit -> {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    sourceAsMap.put("unique_id", hit.getId());
                    return sourceAsMap;
                })
                .collect(Collectors.toList());

    }


}

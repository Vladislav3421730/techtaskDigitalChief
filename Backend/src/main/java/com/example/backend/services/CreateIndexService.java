package com.example.backend.services;

import com.example.backend.exceptions.CreateIndexException;
import com.example.backend.config.CreateBuilder;
import com.example.backend.exceptions.SearchDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreateIndexService {

    private final RestHighLevelClient restHighLevelClient;
    private final LoadDataService loadDataService;

    private final static String INDEX_NAME="products_sku";

    public void createIndex() {

        GetIndexRequest request = new GetIndexRequest(INDEX_NAME);
        try {
            if (!restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT)) {
                CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX_NAME);
                createIndexRequest.settings(Settings.builder()
                        .put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", 1)
                );
                createIndexRequest.mapping(CreateBuilder.getInstance().buildMapping());
                CreateIndexResponse createIndexResponse = restHighLevelClient.indices()
                        .create(createIndexRequest, RequestOptions.DEFAULT);

                if (createIndexResponse.isAcknowledged()){
                        log.info("Индекс {} был успешно создан",createIndexResponse.index());
                        loadDataService.loadDataFromDbToIndex();
                }
                else throw new CreateIndexException("Ошибка создания индекса");
            }
        } catch (ElasticsearchStatusException | IOException e) {
            log.error("Произошла ошибка при создании индекса ",e);
            throw new SearchDataException("Произошла ошибка при поиске products "+e.getMessage());
        }

    }

}

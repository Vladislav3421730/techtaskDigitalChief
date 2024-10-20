package com.example.backend.services;


import com.example.backend.exceptions.SearchDataException;
import com.example.backend.model.Product;
import com.example.backend.model.Sku;
import com.example.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadDataService {

    private final ProductRepository productRepository;
    private final RestHighLevelClient restHighLevelClient;
    private final static String INDEX_NAME="products_sku";


    public void loadDataFromDbToIndex() {
        try {
            BulkRequest bulkRequest = new BulkRequest();

            for (Product product : productRepository.findAll()) {

                XContentBuilder builder = XContentFactory.jsonBuilder();

                builder.startObject();
                {
                    builder.field("id", product.getId());
                    builder.field("name", product.getName());
                    builder.field("description", product.getDescription());
                    builder.field("active", product.isActive());
                    builder.timeField("createdAt", product.getCreatedAt());
                    builder.startArray("skuList");
                    for (Sku sku : product.getSkuList()) {
                        builder.startObject();
                        {
                            builder.field("color", sku.getColor());
                            builder.field("size", sku.getSize());
                            builder.field("cost", sku.getCost());
                        }
                        builder.endObject();
                    }
                    builder.endArray();
                }
                builder.endObject();

                IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                        .id(UUID.randomUUID().toString())
                        .source(builder);

                bulkRequest.add(indexRequest);
            }
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            restHighLevelClient.indices().refresh(new RefreshRequest(INDEX_NAME), RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    if (bulkItemResponse.isFailed()) {
                        BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                        System.out.println("Ошибка для документа с id: " + failure.getId() + " - " + failure.getMessage());
                    }
                }
                log.error("Произошла ошибка при загрузке данных index: {}",bulkResponse.buildFailureMessage());
                throw new SearchDataException("Произошла ошибка при загрузке данных");
            }
            log.info("Данные были успешно загружены в elasticsearch");

        }catch (ElasticsearchStatusException | IOException e) {
            log.error("Произошла ошибка при поиске элементов",e);
            throw new SearchDataException("Произошла ошибка при поиске products "+e.getMessage());
        }

    }
}

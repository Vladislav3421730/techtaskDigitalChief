package com.example.backend.services;

import com.example.backend.exceptions.LoadDataFromDbToIndexException;
import com.example.backend.model.Product;
import com.example.backend.model.Sku;
import com.example.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
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
public class LoadDataService {

    private final ProductRepository productRepository;
    private final RestHighLevelClient restHighLevelClient;
    private final static String INDEX_NAME="products_sku";


    public void loadDataFromDbToIndex() throws IOException {
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

        if (bulkResponse.hasFailures()) {
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                if (bulkItemResponse.isFailed()) {
                    BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                    System.out.println("Ошибка для документа с id: " + failure.getId() + " - " + failure.getMessage());
                }
            }
            throw new LoadDataFromDbToIndexException("Произошла ошибка при загрузке данных");
        }
    }
}

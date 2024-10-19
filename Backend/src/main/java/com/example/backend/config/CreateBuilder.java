package com.example.backend.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateBuilder {

    private static final CreateBuilder INSTANCE=new CreateBuilder();

    public static CreateBuilder getInstance(){
        return INSTANCE;
    }

    public XContentBuilder buildMapping() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("id").field("type", "long").endObject();
                builder.startObject("name").field("type", "text").endObject();
                builder.startObject("description").field("type", "text").endObject();
                builder.startObject("active").field("type", "boolean").endObject();
                builder.startObject("createdAt").field("type", "date").endObject();
                builder.startObject("skuList");
                {
                    builder.field("type", "nested");
                    builder.startObject("properties");
                    {
                        builder.startObject("id").field("type", "long").endObject();
                        builder.startObject("color").field("type", "text").endObject();
                        builder.startObject("size").field("type", "text").endObject();
                        builder.startObject("cost").field("type", "double").endObject();
                    }
                    builder.endObject();
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        return builder;
    }


}

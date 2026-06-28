package com.luvletter.search.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class ElasticSearchDocument {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.139.128:9200")
        ));
    }

    void testConnect() {
        System.out.println(client);
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
    @Test
    void testExistsIndex() throws IOException {
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("artists");
        // 2.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        // 3.输出
        System.err.println(exists ? "索引库已经存在！" : "索引库不存在！");
    }

    @Test
    void testDeleteIndex() throws IOException {
        // 1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("albums");
        // 2.发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testCreateIndexAlbums() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("albums");
        // 2.准备请求参数
        request.source(MAPPING_TEMPLATE_Albums, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    static final String MAPPING_TEMPLATE_Albums = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"title\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"artistId\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"coverUrl\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"releaseDate\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"description\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"language\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"genre\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "       \"company\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"createdAt\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";


    @Test
    void testCreateIndexArtists() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("artists");
        // 2.准备请求参数
        request.source(MAPPING_TEMPLATE_Artists, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    static final String MAPPING_TEMPLATE_Artists = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"englishName\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"description\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"avatarUrl\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"backgroundUrl\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"country\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"birthDate\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"createdAt\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @Test
    void testCreateIndexSongs() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("songs");
        // 2.准备请求参数
        request.source(MAPPING_TEMPLATE_Songs, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    static final String MAPPING_TEMPLATE_Songs = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"englishName\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"description\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"avatarUrl\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"backgroundUrl\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"country\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"birthDate\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"createdAt\":{\n" +
            "        \"type\":\"date\",\n" +
            "        \"index\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

}

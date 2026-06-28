package com.luvletter.search.controller;



import com.alibaba.fastjson.JSON;
import com.luvletter.model.po.Albums;
import com.luvletter.model.service.IAlbumsService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.apache.ibatis.annotations.Delete;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 歌手信息表 前端控制器
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@RestController
@RequestMapping("/albums")
@MapperScan("com.luvletter.model.mapper")
@SpringBootTest(properties = "spring.profiles.active=local")
public class AlbumsController {

    private static final Logger log = LoggerFactory.getLogger(ArtistsController.class);

    private RestHighLevelClient client;

    @PostConstruct
    void init() {
        this.client = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.139.128:9200"))
        );
    }

    @PreDestroy
    void close() throws IOException {
        if (this.client != null) {
            this.client.close();
        }
    }

    @Autowired
    private IAlbumsService AlbumsService;
    @GetMapping("/search")
    public void ElasticSearchDocumentDatum() throws IOException {
        // 直接初始化客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(HttpHost.create("http://192.168.139.128:9200"))
        );

        try {
            List<Albums> albumsList = AlbumsService.list();
            log.info("albumsList: {}", albumsList);

            BulkRequest bulkRequest = new BulkRequest();

            for (Albums albums : albumsList) {
                IndexRequest request = new IndexRequest("  ")
                        .id(albums.getId().toString())
                        .source(JSON.toJSONString(albums), XContentType.JSON);
                bulkRequest.add(request);
            }

            client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } finally {
            // 确保资源被正确释放
            if (client != null) {
                client.close();
            }
        }
    }
    @DeleteMapping("/deleteAll")
    public void deleteAllAlbums() throws IOException {
        // 删除 albums 索引中所有文档
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest("albums");
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
        client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
    }
}

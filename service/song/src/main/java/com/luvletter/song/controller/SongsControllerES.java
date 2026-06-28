package com.luvletter.song.controller;


import com.alibaba.fastjson.JSON;
import com.luvletter.api.controller.ArtistsController;
import com.luvletter.model.song.po.Songs;
import com.luvletter.song.service.ISongsService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 歌曲信息表 前端控制器
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@RestController
@RequestMapping("/songs")
@MapperScan("com.luvletter.model.mapper")
@SpringBootTest(properties = "spring.profiles.active=local")
public class SongsControllerES {

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
    private ISongsService songsService;
    @GetMapping("/search")
    public void ElasticSearchDocumentDatum() throws IOException {
        List<Songs> songsList = songsService.list();
        log.info("songsList: {}", songsList);

        BulkRequest bulkRequest = new BulkRequest();

        for (Songs song : songsList) {
            IndexRequest request = new IndexRequest("songs")
                    .id(song.getId().toString())
                    .source(JSON.toJSONString(song), XContentType.JSON);
            bulkRequest.add(request);
        }

        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllAlbums() throws IOException {

        // 删除 albums 索引中所有文档
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest("songs");
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());

        client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);

    }

}

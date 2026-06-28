package com.luvletter.song.controller;


import cn.hutool.json.JSONUtil;
import com.luvletter.model.common.dtos.ResponseResult;
import com.luvletter.model.song.po.Songs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 歌曲信息表 前端控制器
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("")
public class SongsController {

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


    @ApiOperation("关键字搜索歌曲")
    @PostMapping("/searchKeyword")
    public ResponseResult searchKeyword(@RequestParam(required = false) String keyword) throws IOException{
        // 直接初始化客户端
        SearchRequest request = new SearchRequest("songs");
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.must(QueryBuilders.matchQuery("title", keyword));
        request.source().query(bool);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        List<Songs> songs=new ArrayList<>();
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("索引 " + songs.getLast() + " 共搜索到" + total + "条数据");
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String source = hit.getSourceAsString();
            Songs result = JSONUtil.toBean(source, Songs.class);
            System.out.println(result);
            if(result!=null){
                songs.add(result);
            }
        }
        return ResponseResult.okResult(songs);
    }
}

package com.luvletter.search.controller;


import cn.hutool.json.JSONUtil;
import com.luvletter.model.common.dtos.ResponseResult;
import com.luvletter.model.po.Albums;
import com.luvletter.model.po.Artists;
import com.luvletter.model.song.po.Songs;
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
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("")
@MapperScan("com.luvletter.model.mapper")
public class SearchController {

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



    @GetMapping("/searchKeyword")
    public ResponseResult searchAlbums(@RequestParam(required = false) String keyword) throws  IOException{
        // 分别查询不同类型的索引
        List<Songs> songs=searchAndHandle("songs", "title", keyword, Songs.class);
        List<Albums> albums = searchAndHandle("albums","title", keyword, Albums.class);
        List<Artists> artists=searchAndHandle("artists", "name", keyword, Artists.class);
        return ResponseResult.okResult(List.of(songs,artists,albums));
    }
    private <T> List<T> searchAndHandle(String indexName,String fieldName, String keyword, Class<T> clazz) throws IOException {
        // 直接初始化客户端
        SearchRequest request = new SearchRequest(indexName);
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        bool.must(QueryBuilders.matchQuery(fieldName, keyword));
        request.source().query(bool);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return handleTypedResponse(response, clazz);
    }

    private <T> List<T> handleTypedResponse(SearchResponse response, Class<T> clazz) throws IOException {
        // 直接初始化客户端
        List<T> list= new ArrayList<>();
        SearchHits searchHits = response.getHits();
        long total = searchHits.getTotalHits().value;
        System.out.println("索引 " + clazz.getSimpleName() + " 共搜索到" + total + "条数据");

        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String source = hit.getSourceAsString();
            T result = JSONUtil.toBean(source, clazz);
            System.out.println(result);
            if(result!=null){
                list.add(result);
            }
        }
        return list;
    }
}

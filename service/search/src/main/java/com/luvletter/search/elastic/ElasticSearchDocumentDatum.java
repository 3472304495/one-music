package com.luvletter.search.elastic;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.luvletter.model.po.Albums;
import com.luvletter.model.po.Artists;
import com.luvletter.model.po.DOC.ArtistsDOC;
import com.luvletter.model.service.IAlbumsService;
import com.luvletter.model.service.IArtistsService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
@SpringBootTest(properties = "spring.profiles.active=local")
public  class ElasticSearchDocumentDatum {
    private static final Logger log = LoggerFactory.getLogger(ElasticSearchDocumentDatum.class);
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

    /** @Test
    void testIndexDoc() throws IOException {
    //准备文档数据
    Artists artists = searchService.getById(31L);
    log.info("artists: {}", artists);
    ArtistsDOC artistsDOC = new ArtistsDOC(); // 先创建目标对象
    BeanUtils.copyProperties(artists, artistsDOC); // 拷贝属性
    log.info("artistsDOC: {}", artistsDOC);

    //1。准备Request
    IndexRequest request = new IndexRequest("asongwrittenforyou").id(artistsDOC.getId().toString());
    //2。准备请求参数
    request.source(JSON.toJSONString(artistsDOC), XContentType.JSON);
    //3.发送请求
    client.index(request, RequestOptions.DEFAULT);
    }
     **/

    @Test
    void testGetDocumentById() throws IOException {
        // 1.准备Request对象
        GetRequest request = new GetRequest("asongwrittenforyou").id("29");
        // 2.发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.获取响应结果中的source
        String json = response.getSourceAsString();
        ArtistsDOC artistsDOC = JSONUtil.toBean(json, ArtistsDOC.class);
        System.out.println("artistsDOC= " + artistsDOC);
    }

    @Test
    void testDeleteDocumentById() throws IOException {
        // 1.准备Request对象
        DeleteRequest request = new DeleteRequest("asongwrittenforyou").id("29");
        // 2.发送请求
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Test
    void testUpdateDocument() throws IOException {
        // 1.准备Request
        UpdateRequest request = new UpdateRequest("asongwrittenforyou", "29");
        // 2.准备请求参数
        request.doc(
                "avatarUrl", "https://img2.baidu.com/it/u=3422252222,2832289388&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
                "commentCount", 1
        );
        // 3.发送请求
        client.update(request, RequestOptions.DEFAULT);
    }


    /* @Test
     void testIndexDocAlbums() throws IOException {
     //准备文档数据
     List<Albums> albumsList = iAlbumsService.list();
     log.info("albums: {}", albumsList);

     // 批量导入到ES
     BulkRequest bulkRequest = new BulkRequest();

     for (Albums album : albumsList) {
     // 转换为文档对象
     Albums albumsDOC = new Albums();
     BeanUtils.copyProperties(album, albumsDOC);

     // 添加到批量请求中
     IndexRequest request = new IndexRequest("albums")
     .id(albumsDOC.getId().toString())
     .source(JSON.toJSONString(albumsDOC), XContentType.JSON);
     bulkRequest.add(request);
     }

     // 发送批量请求
     client.bulk(bulkRequest, RequestOptions.DEFAULT);
     }

*/


    @Autowired
    private IAlbumsService iAlbumsService;
    @Test
    void testIndexDocAlbums() throws IOException {
        //准备文档数据
        List<Albums> albumsList = iAlbumsService.list();
        log.info("albums: {}", albumsList);

        // 批量导入到ES
        BulkRequest bulkRequest = new BulkRequest();

        for (Albums album : albumsList) {
            // 转换为文档对象
            Albums albumsDOC = new Albums();
            BeanUtils.copyProperties(album, albumsDOC);

            // 添加到批量请求中
            IndexRequest request = new IndexRequest("albums")
                    .id(albumsDOC.getId().toString())
                    .source(JSON.toJSONString(albumsDOC), XContentType.JSON);
            bulkRequest.add(request);
        }

        // 发送批量请求
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }



    @Autowired
    private IArtistsService iArtistsService;
    @Test
    void testIndexDocArtists() throws IOException {
        //准备文档数据
        List<Artists> artistsList = iArtistsService.list();
        log.info("artists: {}", artistsList);

        // 批量导入到ES
        BulkRequest bulkRequest = new BulkRequest();

        for (Artists artist : artistsList) {

            // 添加到批量请求中
            IndexRequest request = new IndexRequest("artists")
                    .id(artist.getId().toString())
                    .source(JSON.toJSONString(artist), XContentType.JSON);
            bulkRequest.add(request);
        }

        // 发送批量请求
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }



}

package com.luvletter.search.client;

import com.luvletter.model.song.po.Songs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "song")
public interface Client {
    @GetMapping("/search")
    List<Songs> ElasticSearchDocumentDatum();
}

package com.luvletter.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.mapper.ArtistsMapper;
import com.luvletter.model.po.Artists;


import com.luvletter.model.service.IArtistsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌手信息表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class ArtistsServiceImpl extends ServiceImpl<ArtistsMapper, Artists> implements IArtistsService {

}

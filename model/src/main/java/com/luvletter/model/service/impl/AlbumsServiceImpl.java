package com.luvletter.model.service.impl;


import com.luvletter.model.mapper.AlbumsMapper;
import com.luvletter.model.po.Albums;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.service.IAlbumsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专辑信息表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class AlbumsServiceImpl extends ServiceImpl<AlbumsMapper, Albums> implements IAlbumsService {

}

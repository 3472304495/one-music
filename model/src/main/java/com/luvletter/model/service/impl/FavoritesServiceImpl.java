package com.luvletter.model.service.impl;

import com.luvletter.model.mapper.FavoritesMapper;
import com.luvletter.model.po.Favorites;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.service.IFavoritesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {

}

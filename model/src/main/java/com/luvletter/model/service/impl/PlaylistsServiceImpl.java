package com.luvletter.model.service.impl;

import com.luvletter.model.mapper.PlaylistsMapper;
import com.luvletter.model.po.Playlists;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.service.IPlaylistsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 播放列表表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class PlaylistsServiceImpl extends ServiceImpl<PlaylistsMapper, Playlists> implements IPlaylistsService {

}

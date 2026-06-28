package com.luvletter.model.service.impl;

import com.luvletter.model.mapper.PlaylistSongsMapper;
import com.luvletter.model.po.PlaylistSongs;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.service.IPlaylistSongsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 播放列表歌曲关联表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class PlaylistSongsServiceImpl extends ServiceImpl<PlaylistSongsMapper, PlaylistSongs> implements IPlaylistSongsService {

}

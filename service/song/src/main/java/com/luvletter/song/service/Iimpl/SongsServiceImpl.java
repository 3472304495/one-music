package com.luvletter.song.service.Iimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.song.po.Songs;
import com.luvletter.song.mapper.SongsMapper;
import com.luvletter.song.service.ISongsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲信息表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class SongsServiceImpl extends ServiceImpl<SongsMapper, Songs> implements ISongsService {

}

package com.luvletter.model.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.mapper.PlayHistoryMapper;
import com.luvletter.model.po.PlayHistory;
import com.luvletter.model.service.IPlayHistoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户播放历史表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-10-11
 */
@Service
public class PlayHistoryServiceImpl extends ServiceImpl<PlayHistoryMapper, PlayHistory> implements IPlayHistoryService {

}

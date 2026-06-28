package com.luvletter.model.service.impl;



import com.luvletter.model.mapper.CommentsMapper;
import com.luvletter.model.po.Comments;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luvletter.model.service.ICommentsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲评论表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}

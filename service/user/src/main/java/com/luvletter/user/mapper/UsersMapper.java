package com.luvletter.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luvletter.model.user.pojos.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}

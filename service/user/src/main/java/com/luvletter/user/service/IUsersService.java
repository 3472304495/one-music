package com.luvletter.user.service;

import com.luvletter.model.common.dtos.ResponseResult;
import com.luvletter.model.user.pojos.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luvletter.model.user.dto.LoginDTO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
public interface IUsersService extends IService<Users> {

    ResponseResult<Users> login(LoginDTO loginDTO);
}

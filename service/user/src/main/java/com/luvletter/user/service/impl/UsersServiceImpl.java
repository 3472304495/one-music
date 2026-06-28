package com.luvletter.user.service.impl;

import com.luvletter.common.util.AppJwtUtil;
import com.luvletter.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luvletter.model.common.dtos.ResponseResult;
import com.luvletter.model.user.dto.LoginDTO;
import com.luvletter.user.mapper.UsersMapper;
import com.luvletter.model.user.pojos.Users;
import com.luvletter.user.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Service
@Transactional
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public ResponseResult login(LoginDTO loginDTO) {
        if(loginDTO.getUsername().length()<6){
            return ResponseResult.errorResult(3,"用户名长度不能小于6位");
        }
        if(loginDTO.getPassword().length()<6){
            return ResponseResult.errorResult(2,"密码长度不能小于6位");
        }
        //1.正常登录 用户名和密码
        if(StringUtils.isNotBlank(loginDTO.getUsername())&& StringUtils.isNotBlank(loginDTO.getPassword())){
            //1.1根据用户名查询用户信息
            Users dbUser = getOne(Wrappers.<Users>lambdaQuery().eq(Users::getUsername, loginDTO.getUsername()));
            if(dbUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户数据不存在");
            }
            //1.2比对密码
            if(!loginDTO.getPassword().equals(dbUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            log.info("能进行到这:{ }"+dbUser);
            //1.3返回登录数据 jwt
            String token = AppJwtUtil.getToken(dbUser.getId().longValue());
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            dbUser.setPassword("");
            map.put("user",dbUser);
            return ResponseResult.okResult(map);
        }else{
            //2.游客登陆
            Map<String,Object> map = new HashMap<>();
            map.put("token",AppJwtUtil.getToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}

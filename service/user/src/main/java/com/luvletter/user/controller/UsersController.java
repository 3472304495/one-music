package com.luvletter.user.controller;


import com.luvletter.model.common.dtos.ResponseResult;
import com.luvletter.model.user.pojos.Users;
import com.luvletter.model.user.dto.LoginDTO;
import com.luvletter.user.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author xiaohuai
 * @since 2025-09-23
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("")
public class UsersController {

    @Autowired
    IUsersService usersService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseResult<Users> Login (@RequestBody LoginDTO loginDTO){
        return usersService.login(loginDTO);
    }
}

package com.luvletter.gateway.config;

import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 白名单配置
 * 配置不需要登录即可访问的接口路径
 */
@Configuration
public class WhitelistConfig {

    /**
     * 白名单路径列表
     * 支持通配符，如 /search/* 表示所有以 /search/ 开头的路径
     */
    private static final List<String> WHITELIST_PATHS = Arrays.asList(
        "/user/login",
        "/user/register",
        "/search/searchKeyword",
        "/search/*"
    );

    /**
     * 判断路径是否在白名单中
     * @param path 请求路径
     * @return true-在白名单中，false-不在白名单中
     */
    public boolean isWhitelisted(String path) {
        if (path == null) {
            return false;
        }

        for (String whitelistPath : WHITELIST_PATHS) {
            if (whitelistPath.endsWith("/*")) {
                // 处理通配符匹配
                String prefix = whitelistPath.substring(0, whitelistPath.length() - 1);
                if (path.startsWith(prefix)) {
                    return true;
                }
            } else {
                // 精确匹配
                if (path.equals(whitelistPath)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 添加白名单路径
     * @param path 要添加的路径
     */
    public void addWhitelistPath(String path) {
        WHITELIST_PATHS.add(path);
    }

    /**
     * 获取白名单路径列表
     * @return 白名单路径列表
     */
    public List<String> getWhitelistPaths() {
        return WHITELIST_PATHS;
    }
}

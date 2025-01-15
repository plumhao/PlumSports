package com.lzh.sports.tools;

import com.lzh.sports.tools.dto.CurrentUserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 解析当前token的用户信息 存到线程中
 */
public class CurrentUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // jwt不建议作为请求参数，而应该在请求的请求头中
        // 获取请求头中的令牌（token）
        String token = request.getHeader("Authorization");
        if(token!=null) {
            CurrentUserDto currentUserDto = Extension.GetCurrentUserByToken(token);
            BaseContext.setCurrentUserDto(currentUserDto);
        }
         return true;


    }
}

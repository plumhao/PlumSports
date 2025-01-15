package com.lzh.sports.tools;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lzh.sports.SysConst;
import com.lzh.sports.tools.dto.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT自定义切面处理
 */
public class JWTInterceptor implements HandlerInterceptor {


    /**
     * 具体的处理逻辑
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        ResponseData<Object> res = new ResponseData<>();

        // jwt不建议作为请求参数，而应该在请求的请求头中
        // 获取请求头中的令牌（token）
        String token = request.getHeader("Authorization");
        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            // 放行请求
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();

            map.put("msg", "无效签名！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期！");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不一致！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效！");
        }
        map.put("state", false);        //设置状态
        // 将map转为json，@ResponseBody使用了jackson依赖，所以可以直接使用
        res.setCode(SysConst.STATUS_401);
        res.setMsg(map.get("msg").toString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        String json = mapper.writeValueAsString(res);
        // 设置相应类型为json，字符集为utf-8
        response.setContentType("application/json;charset=UTF-8");
        // 返回json
        response.getWriter().print(json);
        return false;
    }
}

package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import uitl.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过拦截器");
        //无论如何都放行，具体能不能操作还在具体操作中去判定
        //拦截器只是负责吧请求头包含的token令牌进行一个解析验证
        String header = request.getHeader("Authorization");
        if(header!=null&&!header.equals("")){
            //如果有包含头信息，就对其进行解析
            if(header.startsWith("Bearer ")){
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (handler!=null&&!handler.equals("")&&roles.equals("admin")){
                        request.setAttribute("claims_admin",claims);
                    }else if (handler!=null&&!handler.equals("")&&roles.equals("user")){
                        request.setAttribute("claims_user",claims);
                    }

                }catch (Exception e){
                    throw new RuntimeException("令牌错误");
                }
            }
        }
        return true;
    }
}

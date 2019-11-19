package com.jihu.shiro.demo.filter;

import com.jihu.shiro.demo.shiro.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object mappedValue){
        try {
            executeLogin(request,response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean executeLogin(ServletRequest request,ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");

        JwtToken jwtToken = new JwtToken(token);
        //提交给realm进行登录，如果错误会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        //如果没有抛出异常则代表登录成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return  false;
        }
        return super.preHandle(request, response);
    }
}

package com.lhl.test.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class AccessFilter extends ZuulFilter {

    /**
     * 类型有pre、route、post、error，对应Spring AOP里的前加强、前后加强、后加强、异常处理
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，多个过滤器同时存在时根据这个order来决定先后顺序，越小优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *  过滤器是否被执行，只有true时才会执行run()里的代码
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getRequestURI().contains("/inner")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        if (token == null || "".equals(token.trim())) {
            requestContext.setResponseStatusCode(403);
            requestContext.setSendZuulResponse(false);

            return null;
        }
        return null;
    }
}

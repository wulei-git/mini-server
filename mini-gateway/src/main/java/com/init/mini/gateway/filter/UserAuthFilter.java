package com.init.mini.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.init.mini.common.base.ResponseVo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.init.mini.common.constant.ErrorCodeConstant.ERROR;

@Component
public class UserAuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 可以在请求被路由之前调用
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
        return 0;
    }

    /**
     * 是否执行该过滤器
     *
     * @return true，说明需要过滤
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String requestURL = httpServletRequest.getRequestURL().toString();
        // todo
//        return (!requestURL.contains("/baseURL") || requestURL.contains("/login")) ? false : true;
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        Map<String, String> headerMap = requestContext.getZuulRequestHeaders();
        String userCode = headerMap.get("user-code");
        requestContext.getRequest().getHeader("user-code");
        // 1.log当前请求 URL，用户基本信息 TODO
        // 2.redis 获取用户登录信息,if null return null;
        System.out.println("用户信息");
        String userInfo = "123";
        if (StringUtils.isBlank(userInfo)) {
            access(requestContext, "未登录");
            return null;
        }
//        JSONArray jsonArray = JSON.parseArray(userInfo);
        // 3.遍历权限，判定权限
        return null;
    }

    private void access(RequestContext requestContext, String refuseMessage) {
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(200);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setResponseCode(ERROR);

        responseVo.setResponseMessage(refuseMessage);
        requestContext.setResponseBody(JSON.toJSONString(responseVo));
    }
}

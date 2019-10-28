package com.xinlang.zly_xyx.cat_gateway_zuul.filter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.exception.ZuulException;
import com.xinlang.zly_xyx.cat_gateway_zuul.feign.BackendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


/**
 * 黑名单拦截
 */
@Component
public class BlackIPAccessFilter extends ZuulFilter {

    private Set<String> blackIPs = new HashSet<>();
    @Autowired
    private BackendClient backendClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Value("${cron.black-ip}")
    private String corn;

    @Override
    public boolean shouldFilter() {

        if (blackIPs.isEmpty()){
            return false;
        }
      RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String ip = getIpAddress(request);
        return  blackIPs.contains(ip);//判断ip是否在黑名单列表里
    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        requestContext.setResponseBody("this is b1ack ip");
        requestContext.setSendZuulResponse(false);
        return null;
    }


    /**
     *  定时同步黑名单
     */
    @Scheduled(cron = "${cron.black-ip}")
    public void syncBlackIPList(){
        try{
            Set<String> list = backendClient.findAllBlackIPs(Collections.emptyMap());
            blackIPs = list;
        }catch (Exception e){
        }
    }
}

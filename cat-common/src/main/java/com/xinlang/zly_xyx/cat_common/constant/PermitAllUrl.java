package com.xinlang.zly_xyx.cat_common.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The url to which you need to open permissions
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
public class PermitAllUrl {

    /**
     * The url that the monitor center and swagger need to access
     */
    private static final String[] ENDPOINTS = {"/actuator/health", "/actuator/env", "/actuator/metrics/**", "/actuator/trace", "/actuator/dump",
            "/actuator/jolokia", "/actuator/info", "/actuator/logfile", "/actuator/refresh", "/actuator/flyway", "/actuator/liquibase",
            "/actuator/heapdump", "/actuator/loggers", "/actuator/auditevents", "/actuator/env/PID", "/actuator/jolokia/**",
            "/v2/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**"
    };

    /**
     * the url to which you need to open permissions
     * @param urls custom urls
     * @return custom urls and a collection of urls that the monitor center needs to access
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }

}

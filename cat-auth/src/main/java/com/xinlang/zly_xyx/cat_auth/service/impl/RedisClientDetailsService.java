package com.xinlang.zly_xyx.cat_auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Slf4j
@Service
public class RedisClientDetailsService extends JdbcClientDetailsService {

    /**
     * 缓存客户端的redis key,hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "client_details";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisClientDetailsService(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;
        //从redis中获取
        String value = (String)stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
        if(StringUtils.isBlank(value)){
            clientDetails = cacheAndGetClient(clientId);
        }else {
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }
        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
        removeCache(clientId);
    }

    /**
     * 缓存Client并返回
     * @param clientId
     * @return
     */
    private ClientDetails cacheAndGetClient(String clientId){
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        if(clientDetails != null){
            stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(clientId, JSONObject.toJSONString(clientDetails));
        }
        log.info("获取redisClient并加入缓存:{},{}",clientId,clientDetails);
        return clientDetails;
    }

    /**
     * 删除缓存中的Client
     * @param clientId
     */
    private void removeCache(String clientId){
        stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).delete(clientId);
    }

    /**
     * 将oauth_client_details放入缓存
     */
    public void loadAllClientToCache(){
        if(stringRedisTemplate.hasKey(CACHE_CLIENT_KEY) == Boolean.TRUE){
            return;
        }
        log.info("将oauth_client_details放入缓存redis");
        List<ClientDetails> list = super.listClientDetails();
        if(CollectionUtils.isEmpty(list)){
            log.error("oauth_client_details表为空");
            return;
        }
        list.parallelStream().forEach(client->{
            stringRedisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(client.getClientId(),JSONObject.toJSONString(client));
        });
    }
}

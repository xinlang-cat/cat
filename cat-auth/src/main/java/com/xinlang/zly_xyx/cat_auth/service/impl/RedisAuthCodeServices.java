package com.xinlang.zly_xyx.cat_auth.service.impl;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis存储授权码
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Service
public class RedisAuthCodeServices extends RandomValueAuthorizationCodeServices {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] token = jointCodeKey(code).getBytes();
                redisConnection.set(token, SerializationUtils.serialize(oAuth2Authentication), Expiration.from(10, TimeUnit.MINUTES), RedisStringCommands.SetOption.UPSERT);
                return 1L;
            }
        });
    }

    @Override
    protected OAuth2Authentication remove(final  String code) {
        OAuth2Authentication oAuth2Authentication = redisTemplate.execute(new RedisCallback<OAuth2Authentication>() {
            @Override
            public OAuth2Authentication doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] key = jointCodeKey(code).getBytes();
                byte[] value = redisConnection.get(key);
                if(value != null){
                    redisConnection.del(key);
                    return SerializationUtils.deserialize(value);
                }
                return null;
            }
        });
        return oAuth2Authentication;
    }


    private String jointCodeKey(String code){
        return "oauth2:codes:"+code;
    }
}

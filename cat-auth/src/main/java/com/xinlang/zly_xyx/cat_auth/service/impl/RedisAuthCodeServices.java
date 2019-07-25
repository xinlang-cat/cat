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


    /**
     * 存储code到redis，并设置过期时间，10分钟<br>
     * value为OAuth2Authentication序列化后的字节<br>
     * 因为OAuth2Authentication没有无参构造函数<br>
     * redisTemplate.opsForValue().set(key, value, timeout, unit);
     * 这种方式直接存储的话，redisTemplate.opsForValue().get(key)的时候有些问题，
     * 所以这里采用最底层的方式存储，get的时候也用最底层的方式获取
     */
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

package com.xinlang.zly_xyx.cat_auth.controller;

import com.xinlang.zly_xyx.auth.SystemClientInfo;
import com.xinlang.zly_xyx.cat_auth.service.impl.RedisClientDetailsService;
import com.xinlang.zly_xyx.common.Page;
import com.xinlang.zly_xyx.log.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/25
 */
@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 添加
     * @param baseClientDetails
     */
    @LogAnnotation(module = "保存client")
    @PreAuthorize("hasAuthority('client:save')")
    @PostMapping
    public void save(@RequestBody BaseClientDetails baseClientDetails){
        ClientDetails clientDetails = getAndCheckClient(baseClientDetails.getClientId(),false);
        if(clientDetails !=null){
            throw new IllegalArgumentException(baseClientDetails.getClientId()+"已存在");
        }
        baseClientDetails.setClientSecret(passwordEncoder.encode(baseClientDetails.getClientSecret()));
        redisClientDetailsService.addClientDetails(baseClientDetails);
        log.info("保存client信息:{}",baseClientDetails);
    }

    /**
     * 修改
     * @param baseClientDetails
     */
    @LogAnnotation(module = "修改client")
    @PreAuthorize("hasAuthority('client:update')")
    @PutMapping
    public void update(@RequestBody BaseClientDetails baseClientDetails){
        getAndCheckClient(baseClientDetails.getClientId(),true);
        redisClientDetailsService.updateClientDetails(baseClientDetails);
        log.info("修改client信息:{}",baseClientDetails);
    }

    /**
     * 修改密码
     * @param clientId
     * @param secret
     */
    @LogAnnotation(module = "修改密码")
    @PreAuthorize("hasAuthority('client:update')")
    @PutMapping(value = "/{clientId}",params = {"secret"})
    public void updateSecret(@PathVariable String clientId,String secret){
        getAndCheckClient(clientId,true);
        checkSystemClient(clientId);
        secret = passwordEncoder.encode(secret);
        redisClientDetailsService.updateClientSecret(clientId,secret);
        log.info("修改client密码:{},{}",clientId,secret);
    }

    /**
     * 查询所有Client
     * @return
     */
    @PreAuthorize("hasAuthority('client:query')")
    @GetMapping
    public Page<ClientDetails> findClients(){
        List<ClientDetails> list = redisClientDetailsService.listClientDetails();
        list.parallelStream().forEach(c->isSystemClient(c));
        return new Page<>(list.size(),list);
    }

    /**
     * 根据ID查询
     * @param clientId
     * @return
     */
    @PreAuthorize("hasAuthority('client:query')")
    @GetMapping("/{clientId}")
    public ClientDetails findById(@PathVariable String clientId){
        return getAndCheckClient(clientId,true);
    }

    /**
     * 根据ID删除
     * @param clientId
     */
    @LogAnnotation(module = "删除client")
    @PreAuthorize("hasAuthority('client:del')")
    @DeleteMapping("/{clientId}")
    public void delete(@PathVariable String clientId){
        getAndCheckClient(clientId,true);
        checkSystemClient(clientId);
        redisClientDetailsService.removeClientDetails(clientId);
        log.info("删除Client:{}",clientId);
    }

    /**
     * 根据clientId获取client信息
     * @param clientId
     * @param check
     * @return
     */
    private ClientDetails getAndCheckClient(String clientId,boolean check){
        ClientDetails clientDetails = null;
        try {
            clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
            isSystemClient(clientDetails);
        }catch (NoSuchClientException e){
            if(check){
                throw new IllegalArgumentException(clientId+"不存在");
            }
        }
        return clientDetails;
    }

    /**
     * 校验是否是系统内部的Client
     * @param clientDetails
     * @return
     */
    private boolean isSystemClient(ClientDetails clientDetails){
        BaseClientDetails baseClientDetails = (BaseClientDetails)clientDetails;
        Map<String,Object> map = baseClientDetails.getAdditionalInformation();
        if(map == null){
            map = new HashMap<>();
            baseClientDetails.setAdditionalInformation(map);
        }
        boolean isSystem = SystemClientInfo.CLIENT_ID.equalsIgnoreCase(baseClientDetails.getClientId());
        baseClientDetails.addAdditionalInformation("isSystem",isSystem);
        return isSystem;
    }

    private void checkSystemClient(String clientId){
        if(SystemClientInfo.CLIENT_ID.equals(clientId)){
            throw new IllegalArgumentException("不能操作系统数据");
        }
    }
}

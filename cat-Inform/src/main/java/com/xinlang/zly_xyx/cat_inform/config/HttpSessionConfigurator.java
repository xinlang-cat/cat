package com.xinlang.zly_xyx.cat_inform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * NAME   :  WebChat/com.amayadream.webchat.websocket
 * Author :  Amayadream
 * Date   :  2016.01.12 17:10
 * TODO   :
 */
@Configuration
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator  {
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response){
        config.getUserProperties().put("userId",request.getParameterMap().get("userId").get(0));
        super.modifyHandshake(config, request, response);
    }

}

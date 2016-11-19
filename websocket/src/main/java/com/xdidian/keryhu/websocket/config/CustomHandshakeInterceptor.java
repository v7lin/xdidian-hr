package com.xdidian.keryhu.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor.HTTP_SESSION_ID_ATTR_NAME;

/**
 * Created by hushuming on 2016/11/16.
 */
@Slf4j
public class CustomHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler webSocketHandler,
                                   Map<String, Object> map) throws Exception {

        log.info("222");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest =
                    (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest()
                    .getSession(false);
            if (session != null) {
                // 使用userName区分WebSocketHandler，以便定向发送消息
               log.info(String.valueOf(session));
            }
        }

        if (request.getURI().getQuery().split("access_token=").length == 2) {
            String token = "Bearer " + request.getURI().getQuery()
                    .split("access_token=")[0];
            map.put("SPRING.SESSION.ID", token);
            map.put(HTTP_SESSION_ID_ATTR_NAME, token);
            log.info(String.valueOf(map));
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest,
                               ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {

    }
}

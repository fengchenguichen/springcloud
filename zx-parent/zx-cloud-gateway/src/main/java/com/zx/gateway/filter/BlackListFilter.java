package com.zx.gateway.filter;

import org.springframework.boot.actuate.logging.LogFileWebEndpoint;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;



@Component
public class BlackListFilter implements GlobalFilter,Ordered {

    // 自定义黑名单ip
    private static List<String> blackList = new ArrayList<>();
    static {
        blackList.add("127.0.0.1");
    }

    /**
     * 过滤ip黑白名单
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        // 获取请求ip
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String clientIp = request.getRemoteAddress().getHostString();
        // 判断是否为黑名单ip
        if (blackList.contains(clientIp)){
            // 拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            String data = "request be denied";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(wrap));

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

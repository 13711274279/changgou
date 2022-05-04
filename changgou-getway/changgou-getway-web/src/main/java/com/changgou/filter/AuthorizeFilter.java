package com.changgou.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if (request.getURI().getPath().startsWith("api/user/login") ){
            return chain.filter(exchange);
        }
        String token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        if (StringUtils.isEmpty(token)){
            token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        }
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(cookie !=null){
                token = cookie.getValue();//令牌的数据
            }
        }
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
//        try {
//            JwtUtil.parseJWT(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        }
        // 将网关收到的cookie中的token数据 传递给下一个微服务
        request.mutate().header(AUTHORIZE_TOKEN,"bearer "+token);

        return chain.filter(exchange);
    }


    @Override
    // 数值越低 过滤器的执行的优先级越高
    public int getOrder() {
        return 0;
    }
}

package com.kyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
@EnableZuulProxy
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class zuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(zuulApplication.class);
    }
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        //cookie名字
        defaultCookieSerializer.setCookieName("sessionId");
        //域
        defaultCookieSerializer.setDomainName("localhost:8080");
        //存储路径设为根路径，同一域名下多个项目共享该cookie
        defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;
    }


}

package io.github.fanlizhichzu.common.config;

import io.github.fanlizhichzu.common.interceptor.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Auther: fanlz
 * @Date: 2021/12/8 9:06
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // SpringMVC 需要手动添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ResponseResultInterceptor responseResultInterceptor = new ResponseResultInterceptor();
        registry.addInterceptor(responseResultInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

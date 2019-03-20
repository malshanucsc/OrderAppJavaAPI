package com.sysco.order.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan("com.sysco.order")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("http://localhost:3000")
                .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("X-Auth-Token", "Content-Type")
                .exposedHeaders("custom-header1", "custom-header2")
                .allowCredentials(false)
                .maxAge(4800);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}



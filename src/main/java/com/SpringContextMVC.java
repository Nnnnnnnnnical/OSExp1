package com;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringContextMVC extends WebMvcConfigurerAdapter implements EnvironmentAware {


    private Environment env;

    /**
     * 运行跨域（只用于开发环境）
     */
    @Override
    //@Profile("dev")
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Cache-Control", "Content-Language", "Content-Type",
                        "Expires", "Last-Modified", "Pragma")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}

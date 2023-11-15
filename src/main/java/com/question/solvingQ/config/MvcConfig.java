package com.question.solvingQ.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
//    @Autowired
//    ApplicationContext applicationContext;
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(thymeleafViewResolver());
//    }
//
//    @Bean
//    public SpringResourceTemplateResolver templateResolver(){
//        SpringResourceTemplateResolver templateResolver =
//                new SpringResourceTemplateResolver();
//        templateResolver.setApplicationContext(applicationContext);
//        templateResolver.setCharacterEncoding("UTF-8");
//        templateResolver.setPrefix("/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//
//    @Bean
//    SpringTemplateEngine templateEngine(){
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setEnableSpringELCompiler(true);
//        templateEngine.addDialect(new Java8TimeDialect());
//        return templateEngine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver(){
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setContentType("text/html");
//        resolver.setCharacterEncoding("UTF-8");
//        resolver.setTemplateEngine(templateEngine());
//        return resolver;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/SmartEditor/**")
                .addResourceLocations("classpath:/SmartEditor/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
        registry.addResourceHandler("/static/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCachePeriod(60 * 60 * 24 * 365);
    }
}

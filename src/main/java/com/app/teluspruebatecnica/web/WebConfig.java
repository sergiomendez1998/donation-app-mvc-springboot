package com.app.teluspruebatecnica.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){

        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;

    }

    @Override
    public void addInterceptors(InterceptorRegistry register){
        register.addInterceptor(localeChangeInterceptor());
    }


    //if the user is login , he can access the home page if is not, he will be redirected to the login page
    @Override
    public void addViewControllers(ViewControllerRegistry register){
        register.addViewController("/").setViewName("index");
        register.addViewController("/login");
        register.addViewController("/errores/403").setViewName("/errores/403");
    }

}

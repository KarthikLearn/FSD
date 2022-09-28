package com.stackroute.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stackroute.zuulservice.filter.JwtFilter;



@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}
	
	
	  @Bean public FilterRegistrationBean jwtFilter() { FilterRegistrationBean
	  filterRegistrationBean = new FilterRegistrationBean();
	  filterRegistrationBean.setFilter(new JwtFilter());
	  filterRegistrationBean.addUrlPatterns(
	  "/favouriteservice/api/v1/favouriteservice/*",
	  "/recommendservice/api/v1/recommendservice/*");
	  
	  return filterRegistrationBean;
	  
	  }


}

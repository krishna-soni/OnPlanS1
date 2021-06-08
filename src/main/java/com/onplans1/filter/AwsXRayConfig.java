package com.onplans1.filter;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class AwsXRayConfig {
  @Bean
  public Filter TracingFilter() {
    return new AWSXRayServletFilter("onplan");
  }
}

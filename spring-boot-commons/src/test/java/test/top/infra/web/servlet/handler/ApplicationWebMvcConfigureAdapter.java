package test.top.infra.web.servlet.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import top.infra.web.servlet.handler.OrderedHandlerInterceptor;
import top.infra.web.servlet.handler.OrderedHandlerInterceptorWebMvcConfigureAdapter;

@Configuration
public class ApplicationWebMvcConfigureAdapter extends OrderedHandlerInterceptorWebMvcConfigureAdapter {

  @Override
  public List<OrderedHandlerInterceptor<?>> addOrderedInterceptors() {
    final List<OrderedHandlerInterceptor<?>> interceptors = new ArrayList<>();
    interceptors.add(new OrderedHandlerInterceptor<>(testInterceptor(), new String[]{"/**"}));
    return interceptors;
  }

  @Bean
  public TestInterceptor testInterceptor() {
    return new TestInterceptor();
  }
}

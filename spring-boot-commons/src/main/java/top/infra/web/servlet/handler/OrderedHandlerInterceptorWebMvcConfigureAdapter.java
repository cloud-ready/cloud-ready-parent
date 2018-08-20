package top.infra.web.servlet.handler;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Comparator;
import java.util.List;

/**
 * Created by zhuowan on 2018/5/15 15:28.
 * Description:
 */
public abstract class OrderedHandlerInterceptorWebMvcConfigureAdapter extends WebMvcConfigurerAdapter {

  private static Comparator<OrderedHandlerInterceptor<?>> comparator() {
    return Comparator.comparingInt(i -> i.getInterceptor().getOrder());
  }

  public abstract List<OrderedHandlerInterceptor<?>> addOrderedInterceptors();

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    final List<OrderedHandlerInterceptor<?>> interceptors = this.addOrderedInterceptors();
    interceptors
        .stream()
        .sorted(comparator())
        .forEach(i -> registry.addInterceptor(i.getInterceptor()).addPathPatterns(i.getPatterns()));
  }
}

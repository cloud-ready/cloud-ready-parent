package top.infra.web.servlet.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;


/**
 * OrderedHandlerInterceptor and patterns.
 *
 * @param <T> Interceptor type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedHandlerInterceptor<T extends OrderedHandlerInterceptorAdapter> implements Serializable {

  /**
   * ordered interceptor
   */
  private T interceptor;

  /**
   * Add URL patterns to which the registered interceptor should apply to.
   */
  private String[] patterns;
}

package top.infra.web.servlet.handler;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Ordered HandlerInterceptorAdapter.
 * {@link Ordered}
 * {@link HandlerInterceptorAdapter}
 */
public abstract class OrderedHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements Ordered {

}

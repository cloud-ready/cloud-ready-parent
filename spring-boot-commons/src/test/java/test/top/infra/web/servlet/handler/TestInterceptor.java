package test.top.infra.web.servlet.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.Ordered;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.infra.web.servlet.handler.OrderedHandlerInterceptorAdapter;

/**
 * common log interceptor
 */
@Slf4j
public class TestInterceptor extends OrderedHandlerInterceptorAdapter {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public boolean preHandle(final HttpServletRequest req, final HttpServletResponse resp, final Object o) throws Exception {
        log.info("preHandle {}", this.count.incrementAndGet());
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest req, final HttpServletResponse resp, final Object o, final Exception ex) throws Exception {
        log.info("afterCompletion {}", this.count.incrementAndGet());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    public int getCount() {
        return this.count.get();
    }
}

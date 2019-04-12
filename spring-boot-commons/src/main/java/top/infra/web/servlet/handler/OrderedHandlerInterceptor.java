package top.infra.web.servlet.handler;


import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * OrderedHandlerInterceptor and patterns.
 *
 * @param <T> Interceptor type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressFBWarnings({"EI_EXPOSE_REP"})
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

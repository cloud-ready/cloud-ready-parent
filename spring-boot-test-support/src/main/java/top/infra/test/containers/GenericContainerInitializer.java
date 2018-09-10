package top.infra.test.containers;

import com.google.common.collect.Maps;

import lombok.SneakyThrows;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

public class GenericContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Map<GenericContainer, BiFunction<GenericContainer, ConfigurableApplicationContext, Void>> //
        containers = Maps.newLinkedHashMap();

    public static void onInitialize(
        final GenericContainer container, //
        final BiFunction<GenericContainer, ConfigurableApplicationContext, Void> callback //
    ) {
        containers.put(container, callback);
    }

    @SneakyThrows
    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        for (final Entry<GenericContainer, BiFunction<GenericContainer, ConfigurableApplicationContext, Void>> entry :
            containers.entrySet()) {
            final GenericContainer container = entry.getKey();
            if (container.getContainerId() != null) {
                final BiFunction<GenericContainer, ConfigurableApplicationContext, Void> callback = entry.getValue();
                callback.apply(container, applicationContext);
            }
        }
    }
}

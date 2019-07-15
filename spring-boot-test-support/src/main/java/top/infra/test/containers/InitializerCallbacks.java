package top.infra.test.containers;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

import java.util.function.BiFunction;

@NoArgsConstructor(access = PRIVATE)
public final class InitializerCallbacks {

    public static final BiFunction<GenericContainer, ConfigurableApplicationContext, Void> SPRING_DATA_REDIS = (container, applicationContext) -> {
        final String containerIpAddress = container.getContainerIpAddress();
        final int mappedPort = container.getMappedPort(6379);

        // spring-boot 1.5.x
        // org.springframework.boot.test.util.EnvironmentTestUtils.addEnvironment("testcontainers", applicationContext.getEnvironment(), //
        //     "spring.redis.host=" + containerIpAddress, //
        //     "spring.redis.port=" + mappedPort
        // );

        // spring-boot 2.x
        TestPropertyValues values = TestPropertyValues.of(
           "spring.redis.host=" + containerIpAddress,
           "spring.redis.port=" + mappedPort
        );
        values.applyTo(applicationContext);

        return null;
    };
}

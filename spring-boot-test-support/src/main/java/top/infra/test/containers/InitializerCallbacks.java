package top.infra.test.containers;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;

import java.util.function.BiFunction;

@NoArgsConstructor(access = PRIVATE)
public final class InitializerCallbacks {

  public static final BiFunction<GenericContainer, ConfigurableApplicationContext, Void> SPRING_DATA_REDIS = (container, applicationContext) -> {
    final String containerIpAddress = container.getContainerIpAddress();
    final int mappedPort = container.getMappedPort(6379);

    // spring-boot 1.5.x
    EnvironmentTestUtils.addEnvironment("testcontainers", applicationContext.getEnvironment(), //
        "spring.redis.host=" + containerIpAddress, //
        "spring.redis.port=" + mappedPort
    );
    // spring-boot 2.0.x
    //TestPropertyValues values = TestPropertyValues.of(
    //    "spring.redis.host=" + redis.getContainerIpAddress(),
    //    "spring.redis.port=" + redis.getMappedPort(6379)
    //);
    //values.applyTo(configurableApplicationContext);

    return null;
  };
}

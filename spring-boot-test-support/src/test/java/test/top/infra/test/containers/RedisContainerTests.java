package test.top.infra.test.containers;

import lombok.extern.slf4j.Slf4j;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;

import javax.annotation.Resource;

import top.infra.test.containers.GenericContainerInitializer;
import top.infra.test.containers.InitializerCallbacks;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest(classes = RedisContainerTests.TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = GenericContainerInitializer.class)
public class RedisContainerTests {

  @SpringBootApplication
  public static class TestApplication {

    public static void main(final String... args) {
      SpringApplication.run(TestApplication.class, args);
    }
  }

  @ClassRule
  public static final GenericContainer redis = new GenericContainer("redis:3.0.2")
      .withExposedPorts(6379);

  static {
    GenericContainerInitializer.onInitialize(redis, InitializerCallbacks.SPRING_DATA_REDIS);
  }

  // inject the actual template
  @Autowired
  private RedisTemplate<String, String> template;

  // inject the template as ListOperations
  // can also inject as Value, Set, ZSet, and HashOperations
  @Resource(name = "redisTemplate")
  private ListOperations<String, String> listOps;

  @Test
  public void testRedisson() {
    final String key = "key";
    final String value = "value";

    listOps.leftPush(key, value);
    // or use template directly
    this.template.boundListOps(key).leftPush(value);
  }
}

package test.top.infra.web.servlet.handler;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GreetingController controller;

    @Autowired
    private TestInterceptor testInterceptor;

    @Test
    public void contexLoads() throws Exception {
        log.info("contexLoads");
        assertThat(this.controller).isNotNull();
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + this.port + "/",
            String.class)).contains("Hello World");

        final long thresholdMillis = TimeUnit.SECONDS.toMillis(5);
        TestUtils.waitForCondition(() -> testInterceptor.getCount() >= 2, thresholdMillis);
        assertThat(this.testInterceptor.getCount()).isEqualTo(2);
    }
}

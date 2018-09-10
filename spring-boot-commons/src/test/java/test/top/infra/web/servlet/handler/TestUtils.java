package test.top.infra.web.servlet.handler;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BooleanSupplier;

@NoArgsConstructor(access = PRIVATE)
@Slf4j
public final class TestUtils {

    @SneakyThrows
    public static void waitForCondition(final BooleanSupplier condition, final long thresholdMillis) {
        final long start = System.currentTimeMillis();
        while (!condition.getAsBoolean() && System.currentTimeMillis() - start < thresholdMillis) {
            log.info("wait for condition");
            Thread.sleep(thresholdMillis / 5);
        }
    }
}

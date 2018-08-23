package top.infra.test.contract;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class ContractRule implements TestRule {

  @Override
  public Statement apply(final Statement base, final Description description) {
    return new Statement() {

      @Override
      public void evaluate() throws Throwable {

        List<Throwable> errors = new ArrayList<>();

        try {
          starting(description);
          base.evaluate();
          succeeded(description);
        } catch (Throwable e) {
          errors.add(e);
          failed(e, description);
        } finally {
          finished(description);
        }

        MultipleFailureException.assertEmpty(errors);
      }
    };
  }

  private static final String EUREKA_CLIENT_ENABLED = "eureka.client.enabled";
  private static final String SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED = "spring.cloud.config.discovery.enabled";
  private static final String SPRING_CLOUD_CONFIG_FAILFAST = "spring.cloud.config.failFast";

  private String eurekaClientEnabled = null;
  private String springCloudConfigDiscoveryEnabled = null;
  private String springCloudConfigFailFast = null;

  protected void starting(Description description) {
    this.backupProperties();

    this.setProperties();
  }

  protected void succeeded(Description description) {
  }

  protected void failed(Throwable e, Description description) {
  }

  protected void finished(Description description) {
    this.restoreProperties();
  }

  private void backupProperties() {
    this.eurekaClientEnabled = System.getProperty(EUREKA_CLIENT_ENABLED);
    this.springCloudConfigDiscoveryEnabled = System.getProperty(SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED);
    this.springCloudConfigFailFast = System.getProperty(SPRING_CLOUD_CONFIG_FAILFAST);
  }

  private void restoreProperties() {
    if (this.eurekaClientEnabled != null) {
      System.setProperty(EUREKA_CLIENT_ENABLED, this.eurekaClientEnabled);
    } else {
      System.getProperties().remove(EUREKA_CLIENT_ENABLED);
    }

    if (this.springCloudConfigDiscoveryEnabled != null) {
      System.setProperty(SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED, this.springCloudConfigDiscoveryEnabled);
    } else {
      System.getProperties().remove(SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED);
    }

    if (this.springCloudConfigFailFast != null) {
      System.setProperty(SPRING_CLOUD_CONFIG_FAILFAST, this.springCloudConfigFailFast);
    } else {
      System.getProperties().remove(SPRING_CLOUD_CONFIG_FAILFAST);
    }
  }

  private void setProperties() {
    System.setProperty(EUREKA_CLIENT_ENABLED, "false");
    System.setProperty(SPRING_CLOUD_CONFIG_DISCOVERY_ENABLED, "false");
    System.setProperty(SPRING_CLOUD_CONFIG_FAILFAST, "false");
  }
}

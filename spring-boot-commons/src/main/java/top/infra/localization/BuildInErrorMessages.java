package top.infra.localization;

import top.infra.core.ErrorMessage;

/**
 * Created by zhuowan on 2018/3/24 17:12.
 * Description:
 */
@LocalizedMessageBundle(MessageLocale.DEFAULT)
public enum BuildInErrorMessages implements ErrorMessage {

  AUTHENTICATION_FAILED("Authentication failed"), //
  AUTHORIZATION_FAILED("Authorization failed"), //
  ILLEGAL_REQUEST("Parameter error, please check your request whether has illegal parameters"), //
  INVALID_TOKEN("Invalid or expired token"), //
  OK("OK"), //
  RATE_LIMITATION_REACHED("Reached the request rate limitation, please retry later"), //
  SERVER_ERROR("Server error");

  private String text;

  BuildInErrorMessages(final String text) {
    this.text = text;
  }

  @Override
  public String getText() {
    return this.text;
  }

  @Override
  public String getName() {
    return this.name();
  }
}

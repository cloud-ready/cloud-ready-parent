package top.infra.localization;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

import org.springframework.http.HttpStatus;

import top.infra.core.ErrorCode;
import top.infra.core.ErrorMessage;

/**
 * Created by zhuowan on 2016/11/20 22:56.
 * Description:
 */
public enum BuildInErrorCodes implements ErrorCode {

  AUTHORIZATION_FAILED(BuildInErrorMessages.AUTHORIZATION_FAILED, FORBIDDEN.value()), //
  ILLEGAL_REQUEST(BuildInErrorMessages.ILLEGAL_REQUEST, BAD_REQUEST.value()), //
  OK(BuildInErrorMessages.OK, HttpStatus.OK.value()), //
  RATE_LIMITATION_REACHED(BuildInErrorMessages.RATE_LIMITATION_REACHED, TOO_MANY_REQUESTS.value()), //
  SERVER_ERROR(BuildInErrorMessages.SERVER_ERROR, INTERNAL_SERVER_ERROR.value());

  private final ErrorMessage message;
  private final int value;

  BuildInErrorCodes(final ErrorMessage message, final int value) {
    this.message = message;
    this.value = value;
  }

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public ErrorMessage getMessage() {
    return this.message;
  }
}

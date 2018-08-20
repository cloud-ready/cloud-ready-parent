package top.infra.core;

import top.infra.localization.LocalizedMessages;

/**
 * Created by zhuowan on 2016/12/29 10:22.
 * Description: Web Project APIs Response bean should implements this interface
 */
public interface ErrorCode {

  String getName();

  ErrorMessage getMessage();

  default String getLocalizedMessage() {
    return LocalizedMessages.getMessageByLocale(this.getMessage(), null);
  }

  int getValue();
}

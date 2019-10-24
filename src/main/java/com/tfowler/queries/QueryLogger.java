package com.tfowler.queries;

import java.util.Collections;
import java.util.List;

import com.tfowler.utils.BeanTable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryLogger {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueryLogger.class);

  private QueryLogger() {} // prevent instantiation

  public static <T> void log(List<T> beans, String message, Throwable throwable) {
    if (message == null) {
      message = StringUtils.EMPTY;
    }
    if (throwable == null) {
      LOGGER.info(String.format("%s%s", message, BeanTable.asciiRender(beans)));
    } else {
      LOGGER.info(String.format("%s%s", message, BeanTable.asciiRender(beans)), throwable);
    }
  }

  public static <T> void log(T bean) {
    QueryLogger.log(Collections.singletonList(bean), null, null);
  }

  public static <T> void log(T bean, String message) {
    QueryLogger.log(Collections.singletonList(bean), message, null);
  }

  public static <T> void log(T bean, String message, Throwable throwable) {
    QueryLogger.log(Collections.singletonList(bean), message, throwable);
  }

  public static <T> void log(List<T> beans) {
    QueryLogger.log(beans, null, null);
  }

  public static <T> void log(List<T> beans, String message) {
    QueryLogger.log(beans, message, null);
  }
}

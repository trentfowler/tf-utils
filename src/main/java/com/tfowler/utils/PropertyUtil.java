package com.tfowler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.*;

public class PropertyUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);

  private PropertyUtil() {} // prevent instantiation

  public static Properties fromFile(final File file) {
    Properties properties = null;

    try (InputStream fis = new FileInputStream(file)) {
      properties = new Properties();
      properties.load(fis);

    } catch (IOException e) {
      LOGGER.error("IOException: Failed loading properties from file.", e);
    }
    return properties;
  }

  public static Properties fromFileName(final String fileName) {
    return fromFile(new File(fileName));
  }

}

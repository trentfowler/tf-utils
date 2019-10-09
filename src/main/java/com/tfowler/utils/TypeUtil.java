package com.tfowler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(TypeUtil.class);

  /**
   * Checks whether a type which is given as the sole parameter is a primitive data type or not. For
   * this to be true, it must be one of types: {@code int}, {@code long}, {@code double},
   * {@code float}, {@code boolean}, {@code byte}, {@code char}, or {@code short}.
   *
   * @param type The class that you want to evaluate to determine whether it is a primitive data
   *        type or not.
   * @return {@code true} if the given parameter represents a primitive data type, otherwise
   *         {@code false}.
   */
  public static boolean isPrimitive(Class<?> type) {
    return type == int.class || type == long.class || type == double.class || type == float.class
        || type == boolean.class || type == byte.class || type == char.class || type == short.class;
  }

  /**
   * Takes a primitive data type as the sole parameter and returns its object (boxed) equivalent.
   *
   * @param type The primitive type that you want to get the object (boxed) equivalent of.
   * @return The object type that matches the primitive type given.
   */
  public static Class<?> boxPrimitive(Class<?> type) {
    if (type == int.class) {
      return Integer.class;
    }
    if (type == long.class) {
      return Long.class;
    }
    if (type == double.class) {
      return Double.class;
    }
    if (type == float.class) {
      return Float.class;
    }
    if (type == boolean.class) {
      return Boolean.class;
    }
    if (type == byte.class) {
      return Byte.class;
    }
    if (type == char.class) {
      return Character.class;
    }
    if (type == short.class) {
      return Short.class;
    } else {
      final String err = String.format("Class '%s' is not a primitive.", type.getName());
      LOGGER.error(err);
      throw new IllegalArgumentException(err);
    }
  }
}

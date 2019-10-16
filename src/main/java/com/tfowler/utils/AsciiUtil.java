package com.tfowler.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.vandermeer.asciitable.AsciiTable;

public class AsciiUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsciiUtil.class);

  public static <T> AsciiTable tableOf(List<T> rows) {
    // return an empty table
    if (rows == null || rows.isEmpty()) {
      return new AsciiTable();
    }
    final AsciiTable table = new AsciiTable();
    final List<String> headerCols = new ArrayList<>();
    for (Field field : rows.get(0).getClass().getDeclaredFields()) {
      field.setAccessible(true);
      headerCols.add(field.getName());
    }
    table.addRule();
    table.addRow(headerCols);
    for (T row : rows) {
      final List<String> cols = new ArrayList<>();
      for (Field field : row.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (TypeUtil.isPrimitive(type)) {
          type = TypeUtil.boxPrimitive(type);
        }
        try {
          final Object obj = type.newInstance();
          final String getterMethodName = String.format("get%s%s",
              field.getName().substring(0, 1).toUpperCase(), field.getName().substring(1));
          final Method method = row.getClass().getMethod(getterMethodName);
          final String val = method.invoke(row).toString();
          cols.add(val);
        } catch (Exception e) {
          LOGGER.warn(
              "{}: Could not make ASCII representation of field \"{}\" with type \"{}\" in class \"{}\".",
              e.getCause(), field.getName(), field.getType().getName(), row.getClass().getName());
          cols.add("Err");
        }
      }
      table.addRule();
      table.addRow(cols);
    }
    table.addRule();
    return table;
  }
}

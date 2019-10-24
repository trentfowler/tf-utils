package com.tfowler.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWord;
import de.vandermeer.asciitable.CWC_LongestWordMin;
import de.vandermeer.asciithemes.u8.U8_Grids;

/**
 * @see <a href="https://github.com/vdmeer/asciitable"></a>
 * @see <a href="http://www.vandermeer.de/projects/skb/java/asciitable/"></a>
 */
public class BeanTable {

  private static final Logger LOGGER = LoggerFactory.getLogger(BeanTable.class);

  // Defines the minimum number of characters each column in the rendered ASCII table can have. The
  // column width will only be set to this value and padded with blank spaces at the end of the
  // column when the longest word in column has fewer characters than this minimum value.
  private static final int MIN_CHARS_PER_COLUMN = 8;

  private BeanTable() {} // prevent instantiation

  public static <T> AsciiTable asciiTable(T bean) {
    return BeanTable.asciiTable(Collections.singletonList(bean));
  }

  public static <T> String asciiRender(T bean) {
    return String.format("\n%s", BeanTable.asciiTable(Collections.singletonList(bean)).render());
  }

  public static <T> String asciiRender(List<T> beans) {
    return String.format("\n%s", BeanTable.asciiTable(beans).render());
  }

  public static <T> AsciiTable asciiTable(List<T> beans) {
    final AsciiTable table = new AsciiTable();
    table.addRule();
    if (beans == null || beans.isEmpty()) {
      table.addRow(String.format("Table: %s", (beans == null) ? "null" : "empty"));
      // set a one-character padding between the text and the outside border when returning a
      // single-cell ascii table to make it easier to see in prints
      table.setPadding(1);

      table.getRenderer().setCWC(new CWC_LongestWord());
      table.getContext().setGrid(U8_Grids.borderDoubleLight());

      table.addRule();
      return table;
    }
    final Field[] fields = beans.get(0).getClass().getDeclaredFields();
    table.addRow(Stream.of(fields).map(Field::getName).map(BeanTable::getNamePretty)
        .collect(Collectors.toList()));
    table.addRule();
    for (T row : beans) {
      final List<String> cols = new ArrayList<>();
      for (Field field : fields) {
        field.setAccessible(true);
        try {
          final String getterMethodName = String.format("get%s%s",
              field.getName().substring(0, 1).toUpperCase(), field.getName().substring(1));
          final Method method = row.getClass().getMethod(getterMethodName);
          final String val = String.valueOf(method.invoke(row));
          cols.add(val);
        } catch (Exception e) {
          LOGGER.warn(
              "Could not get ASCII representation of field \"{}\" with type \"{}\" in class \"{}\". ({}). Is \"{}\" a well-formed bean?",
              field.getName(), field.getType().getSimpleName(), row.getClass().getName(),
              e.getCause(), row.getClass().getSimpleName());
          cols.add("Err");
        }
      }
      table.addRow(cols);
      table.addRule();
    }
    // take the longest word in each column and set the column width to it, or set the width to the
    // minimum if the longest word has fewer chars than than the minimum
    table.getRenderer().setCWC(new CWC_LongestWordMin(MIN_CHARS_PER_COLUMN));

    table.getContext().setGrid(U8_Grids.borderDoubleLight());
    return table;
  }

  private static String getNamePretty(final String name) {
    if (name == null) {
      return "null";
    }
    if (name.isEmpty()) {
      return StringUtils.EMPTY;
    }
    // always capitalize the first character
    if (name.length() == 1) {
      return name.toUpperCase();
    }
    final StringBuilder stringBuilder = new StringBuilder(name.length());
    stringBuilder.append(name.substring(0, 1).toUpperCase());
    // add a space before any uppercase character that is not the first character and that is
    // preceded by either a lowercase character or a digit
    for (int i = 1; i < name.length(); i++) {
      if (Character.isUpperCase(name.charAt(i))) {
        if (Character.isLowerCase(name.charAt(i - 1)) || Character.isDigit(name.charAt(i - 1))) {
          stringBuilder.append(StringUtils.SPACE);
        }
      } else if (Character.isDigit(name.charAt(i))) {
        if (Character.isLowerCase(name.charAt(i - 1)) && !Character.isDigit(name.charAt(i - 1))) {
          stringBuilder.append(StringUtils.SPACE);
        }
      }
      stringBuilder.append(name.charAt(i));
    }
    return stringBuilder.toString();
  }
}

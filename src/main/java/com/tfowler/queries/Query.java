package com.tfowler.queries;

import com.tfowler.datasource.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Query {

  private static final Logger LOGGER = LoggerFactory.getLogger(Query.class);

  private static final Query QUERY = new Query(); // create instance for singleton class

  private DataSource dataSource;

  private Query() {} // prevent instantiation

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
  private static boolean isPrimitive(Class<?> type) {
    return type == int.class || type == long.class || type == double.class || type == float.class
        || type == boolean.class || type == byte.class || type == char.class || type == short.class;
  }

  /**
   * Takes a primitive data type as the sole parameter and returns its object (boxed) equivalent
   * type. Note that {@link Field#set(Object, Object)} expects both arguments to be objects, so this
   * method enables us to get the object equivalent to a primitive type, which we can use to cast a
   * primitive value to an object by calling {@code boxed.cast(value)} (where {@code boxed} is the
   * type returned by this method.
   *
   * @param type The primitive type that you want to get the object (boxed) equivalent of.
   * @return The object type that matches the primitive type given.
   */
  private static Class<?> boxPrimitiveType(Class<?> type) {
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

  /**
   * Creates a {@link Query} object for use with the given {@link DataSource}.
   *
   * @param dataSource The {@link DataSource} to use for this query. Must not be null.
   * @return The {@link Query} object for use with the given {@link DataSource}.
   */
  public static Query with(final DataSource dataSource) {
    QUERY.dataSource = dataSource;

    return QUERY;
  }

  /**
   * Helper function that builds an object of the specified type from a {@link ResultSet}.
   * 
   * @param obj An object whose definition contains one field annotated with {@link Column} for each
   *        column in the {@link ResultSet}. The field names in the class definition must match the
   *        column names in the {@link ResultSet}.
   * @param rs A {@link ResultSet} with data to be mapped to an object.
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   * @throws SQLException
   */
  private static void loadResultSetIntoObject(ResultSet rs, Object obj)
      throws IllegalArgumentException, IllegalAccessException, SQLException {
    Class<?> clazz = obj.getClass();
    for (Field field : clazz.getDeclaredFields()) {
      if (field.isAnnotationPresent(Column.class)) {
        field.setAccessible(true);
        Object val = rs.getObject(field.getName());
        Class<?> type = field.getType();
        if (isPrimitive(type)) {
          Class<?> boxed = boxPrimitiveType(type);
          val = boxed.cast(val);
        }
        field.set(obj, val);
      }
    }
  }

  /**
   * Performs a database query and returns the results as a list of elements with the specified data
   * type.
   * 
   * @param type
   * @param query
   * @param <T>
   * @return
   * @throws SQLException
   */
  public <T> List<T> fetchList(Class<T> type, String query) throws SQLException {

    if (dataSource == null) {
      throw new SQLException("Unable to fetch records: DataSource must not be null.");
    }

    final List<T> results = new ArrayList<>();

    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        T t = type.newInstance();

        loadResultSetIntoObject(rs, t);

        results.add(t);
      }
    } catch (InstantiationException | IllegalAccessException wrappedExc) {
      Throwable exc = wrappedExc.getCause();
      LOGGER.error("Failed create an instance of type \"{}\". Err: {}", type.getName(), exc);
    }

    return results;
  }
}

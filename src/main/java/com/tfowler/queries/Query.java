package com.tfowler.queries;

import com.tfowler.datasource.DataSource;
import com.tfowler.utils.TypeUtil;
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

  private static final Query QUERY = new Query(); // instance for singleton class

  private DataSource dataSource;

  private Query() {} // prevent instantiation

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
        if (TypeUtil.isPrimitive(type)) {
          Class<?> boxed = TypeUtil.boxPrimitive(type);
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
      LOGGER.error("Failed createWithProperties an instance of type \"{}\". Err: {}", type.getName(), exc);
    }

    return results;
  }
}

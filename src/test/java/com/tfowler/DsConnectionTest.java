package com.tfowler;

import com.tfowler.constants.ReturnCode;
import com.tfowler.datasource.DataSource;
import com.tfowler.constants.JdbcDriver;
import com.tfowler.datasource.DsConfig;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * Validates that the required DataSource properties have been set in the .properties file and that
 * they are valid, then creates a connection to the test database and executes a test query.
 */
public class DsConnectionTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DsConnectionTest.class);

  @Test
  public void dsConnectionTest() {

    final String FILENAME = "application";

    final File f = new File(String.format("%s.properties", FILENAME));

    Properties p = null;
    try (final InputStream fis = new FileInputStream(f)) {

      p = new Properties();
      p.load(fis);

    } catch (FileNotFoundException fe) {
      LOGGER.error("File named \"{}.properties\" was not found in the expected location.", FILENAME,
          fe);
      System.exit(ReturnCode.FAILURE);
    } catch (IOException ioe) {
      LOGGER.error(
          "\"{}.properties\" was found but there was an error loading key/value pairs into memory.",
          FILENAME, ioe);
      System.exit(ReturnCode.FAILURE);
    }
    Assert.assertNotNull(
        "Something went wrong while trying to load application properties from file.", p);

    String jdbcUrl = p.getProperty("database.jdbcUrl");
    Assert.assertNotNull("\"jdbcUrl\" property not found in application properties file (null).",
        jdbcUrl);
    Assert.assertFalse(
        "The \"jdbcUrl\" key was found in the application properties file but the value is blank/invalid.",
        jdbcUrl.isEmpty());

    String username = p.getProperty("database.username");
    Assert.assertNotNull("\"username\" property not found in application properties file (null).",
        username);
    Assert.assertFalse(
        "The \"username\" key was found in the application properties file but the value is blank/invalid.",
        username.isEmpty());

    String password = p.getProperty("database.password");
    Assert.assertNotNull("\"password\" property not found in application properties file (null).",
        password);
    Assert.assertFalse(
        "The \"password\" key was found in the application properties file but the value is blank/invalid.",
        password.isEmpty());

    String driverClassName = p.getProperty("database.driverClassName");
    Assert.assertNotNull(
        "\"driverClass\" property not found in application properties file (null).",
        driverClassName);
    Assert.assertFalse(
        "The \"driverClass\" key was found in the application properties file but the value is blank/invalid.",
        driverClassName.isEmpty());

    try {
      Class.forName(driverClassName);

    } catch (ClassNotFoundException ce) {
      LOGGER.error(
          "ClassNotFoundException: Missing dependency or invalid sql driver class name provided: {}",
          driverClassName, ce);
      System.exit(ReturnCode.FAILURE);
    }

    DataSource ds = null;
    if (JdbcDriver.MSSQL.getClassName() == driverClassName) {
      ds = new DataSource(DsConfig.builder().jdbcUrl(jdbcUrl).driverClass(JdbcDriver.MSSQL)
          .username(username).password(password).build());
    }
    // TODO ...

    final String QUERY =
        "SELECT TOP 1000 DepartmentID, Name, GroupName, ModifiedDate FROM AdventureWorks.HumanResources.Department";

    try (Connection con = ds.getConnection(); Statement stmt = con.createStatement()) {

      ResultSet rs = stmt.executeQuery(QUERY);
      ResultSetMetaData rsmd = rs.getMetaData();

      int cols = rsmd.getColumnCount();
      while (rs.next()) {
        for (int i = 1; i <= cols; i++) {
          String colName = rsmd.getColumnName(i);
          String colVal = rs.getString(i);

          LOGGER.info("{} {}", colName, colVal);
        }
      }
    } catch (SQLException se) {
      LOGGER.error("Failed to execute test query: \"{}\"", QUERY, se);
      System.exit(ReturnCode.FAILURE);
    }
  }
}

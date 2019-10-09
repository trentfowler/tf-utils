package com.tfowler.datasource;

import com.tfowler.constants.JdbcDriver;
import com.tfowler.utils.PropertyUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A utility wrapper around {@link HikariDataSource} made for use with {@link DsConfig} which allows
 * the SQL connection properties to be specified more easily by chaining the properties using the
 * builder pattern. Also sets the connection properties that we use every time (cachePrepStmts,
 * prepStmtCacheSize, prepStmtCacheSqlLimit) to suitable defaults so that they do not have to be
 * explicitly defined for each new DataSource. Create an instance by calling "new" and passing a
 * {@link DsConfig} object as the sole parameter. After instantiating the DataSource, obtain a
 * {@link Connection} to the database for executing SQL statements by calling
 * {@link #getConnection()}.
 * 
 * @see DsConfig
 */
public class DataSource {

  private final HikariDataSource dataSource;

  public DataSource(final DsConfig config) {
    final HikariConfig hkConfig = new HikariConfig();

    if (config.getJdbcUrl() == null) {
      throw new IllegalArgumentException("The \"jdbcUrl\" property must not be null.");
    }
    if (config.getJdbcUrl().isEmpty()) {
      throw new IllegalArgumentException("The \"jdbcUrl\" property must not be empty or blank.");
    }
    hkConfig.setJdbcUrl(config.getJdbcUrl());

    if (config.getDriverClass() == null) {
      throw new IllegalArgumentException("The \"driverClass\" property must not be null.");
    }
    hkConfig.setDriverClassName(config.getDriverClass().getClassName());

    if (config.getUsername() == null) {
      throw new IllegalArgumentException("The \"username\" property must not be null.");
    }
    if (config.getUsername().isEmpty()) {
      throw new IllegalArgumentException("The \"username\" property must not be empty or blank.");
    }
    hkConfig.setUsername(config.getUsername());

    // If the password property is unspecified, we'll assume it is blank and set it to the empty
    // string.
    if (config.getPassword() == null) {
      config.setPassword("");
    }
    hkConfig.setPassword(config.getPassword());

    if (config.getConnectionTimeout() == null) {
      config.setConnectionTimeout(10000L);
    } else if (config.getConnectionTimeout() < 250L) {
      throw new IllegalArgumentException(
          "The \"connectionTimeout\" property must be at least 250 (milliseconds).");
    }
    hkConfig.setConnectionTimeout(config.getConnectionTimeout());

    if (config.getIdleTimeout() == null) {
      config.setIdleTimeout(600000L);
    } else if (config.getIdleTimeout() != 0L && config.getIdleTimeout() < 10000L) {
      throw new IllegalArgumentException(
          "The \"idleTimeout\" property must be at least 10,000 (milliseconds).");
    }
    hkConfig.setIdleTimeout(config.getIdleTimeout());

    if (config.getIsAutoCommit() == null) {
      config.setIsAutoCommit(true);
    }
    hkConfig.setAutoCommit(config.getIsAutoCommit());

    if (config.getMaxLifetime() == null) {
      config.setMaxLifetime(1800000L);
    } else if (config.getMaxLifetime() < 0L) {
      throw new IllegalArgumentException("The \"maximumLifetime\" property must not be negative.");
    }
    hkConfig.setMaxLifetime(config.getMaxLifetime());

    if (config.getConnectionTestQuery() != null) {
      hkConfig.setConnectionTestQuery(config.getConnectionTestQuery());
    }

    if (config.getConnectionInitSql() != null) {
      hkConfig.setConnectionInitSql(config.getConnectionInitSql());
    }

    if (config.getValidationTimeout() == null) {
      config.setValidationTimeout(5000L);
    }
    if (config.getValidationTimeout() > config.getConnectionTimeout()) {
      throw new IllegalArgumentException(
          "The \"validationTimeout\" property must be less than the \"connectionTimeout\" property.");
    }
    hkConfig.setValidationTimeout(config.getValidationTimeout());

    if (config.getMaximumPoolSize() == null) {
      config.setMaximumPoolSize(10);
    } else if (config.getMaximumPoolSize() < 1) {
      throw new IllegalArgumentException(
          "The \"maximumPoolSize\" property must be greater than 0.");
    }
    hkConfig.setMaximumPoolSize(config.getMaximumPoolSize());

    if (config.getPoolName() != null) {
      hkConfig.setPoolName(config.getPoolName());
    }

    if (config.getIsAllowPoolSuspension() == null) {
      config.setIsAllowPoolSuspension(false);
    }
    hkConfig.setAllowPoolSuspension(config.getIsAllowPoolSuspension());

    if (config.getIsReadOnly() == null) {
      config.setIsReadOnly(false);
    }
    hkConfig.setReadOnly(config.getIsReadOnly());

    if (config.getTransactionIsolation() != null) {
      hkConfig.setTransactionIsolation(config.getTransactionIsolation());
    }

    if (config.getLeakDetectionThreshold() == null) {
      config.setLeakDetectionThreshold(0L);
    } else if (config.getLeakDetectionThreshold() != 0L
        && config.getLeakDetectionThreshold() < 2000L) {
      throw new IllegalArgumentException(
          "The \"leakDetection\" property must either be 0 (disabled) or a value greater than 2000 (milliseconds).");
    }
    hkConfig.setLeakDetectionThreshold(config.getLeakDetectionThreshold());

    hkConfig.addDataSourceProperty("cachePrepStmts", "true");
    hkConfig.addDataSourceProperty("prepStmtCacheSize", "250");
    hkConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    dataSource = new HikariDataSource(hkConfig);
  }

  public static DataSource createWithProperties(final Properties properties) {

    final String jdbcUrl = properties.getProperty("database.jdbcUrl");
    final String username = properties.getProperty("database.username");
    final String password = properties.getProperty("database.password");
    // ...

    final DsConfig config = DsConfig.builder().jdbcUrl(jdbcUrl).driverClass(JdbcDriver.MSSQL)
        .username(username).password(password).build();

    return new DataSource(config);
  }

  /**
   * Retrieves a {@link Connection} to the specified database.
   *
   * @return The specified {@link Connection}.
   * @throws SQLException If the connection is closed or shut down.
   */
  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}

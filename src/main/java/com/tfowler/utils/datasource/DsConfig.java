package com.tfowler.utils.datasource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * An alternative to the {@link com.zaxxer.hikari.HikariConfig} class that provides a sometimes more
 * convenient way to set the useful {@link java.sql.Connection} properties using the builder
 * pattern. The variable names are consistent with HikariCP but some of the default values have been
 * changed.
 */
@Builder
@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "database")
public final class DsConfig {

  /**
   * This property sets the name of the class that provides the JDBC DataSource. This is the newer,
   * more preferred, alternative to a DriverManager-based configuration and this property can be set
   * instead of setting the jdbcUrl and driverClassName properties. The default is none.
   * 
   * @see <a href="https://github.com/brettwooldridge/HikariCP#popular-datasource-class-names"</a>
   */
  private String dataSourceClassName;

  /**
   * This property directs HikariCP to use a DriverManager-based configuration. This style is older
   * and less preferred to a DataSource-based configuration, but it is sometimes necessary when
   * dealing with certain JDBC driver classes. When setting the jdbcUrl, you may also need to set
   * the driverClassName property, but you should try without it first. The default is none.
   */
  private String jdbcUrl;

  /**
   * This property sets the driver class name for use in a DriverManager-based configuration where
   * the jdbcUrl is specified. Note that the jdbcUrl is usually all that is required and HikariCP
   * will try to resolve the driver solely using the jdbcUrl property, but occasionally this
   * property must also be set when dealing with older drivers. You should try without setting this
   * property first. The default is none.
   */
  private String driverClassName;

  /**
   * This property sets the default username for use by the underlying driver when it calls
   * {@link javax.sql.DataSource#getConnection()}. The default is none.
   */
  private String username;

  /**
   * This property sets the default password for use by the underlying driver when it calls
   * {@link javax.sql.DataSource#getConnection()}. The default is none.
   */
  private String password;

  /**
   * This property sets the maximum number of milliseconds to wait for a connection to be
   * established from the connection pool. The lowest value is 250 (milliseconds). The default value
   * is 10,000 (milliseconds) or 10 seconds. If the timeout period is exceeded, a
   * {@link java.sql.SQLException} will be thrown.
   */
  private Long connectionTimeout;

  /**
   * This property sets the amount of time a {@link java.sql.Connection} is allowed to sit idle in
   * the pool. This setting only applies when minimumIdle < maximumPoolSize. Setting this property
   * to 0 means that the connection is never removed from the pool. Otherwise, the minimum value
   * allowed is 10,000 milliseconds (10 seconds) and the default value is 600,000 milliseconds (10
   * minutes).
   */
  private Long idleTimeout;

  /**
   * This property sets the maximum size that the pool is allowed to reach for both idle and in-use
   * connections. When the pool reaches this size and no idle connections are available for use,
   * calls to {@link DataSource#getConnection()} will block for connectionTimeout milliseconds
   * before timing out. The default is 10.
   */
  private Integer maximumPoolSize;

  /**
   * This property sets the minimum number of idle connections that HikariCP tries to maintain in
   * the connection pool. When the number of idle connections falls below this number and total
   * connections is less than maximumPoolSize, HikariCP will try to add additional connections. It
   * is generally recommended to not use this property. The default is the same as maximumPoolSize.
   */
  private Long minimumIdle;

  /**
   * This property sets the default auto-commit behavior of connections returned from the connection
   * pool. The default is true.
   */
  private Boolean isAutoCommit;

  /**
   * This property sets the maximum lifetime of a connection in the connection pool, in
   * milliseconds. Note that an in-use connection will never be closed. A value of 0 indicates no
   * maximum lifetime, default is 1,800,000 milliseconds (30 minutes).
   */
  private Long maxLifetime;

  /**
   * This property sets the query that will be executed just before a connection is given to you
   * from the connection pool to validate that the connection is still valid. This is for use by
   * legacy drivers that do not support {@link java.sql.Connection#isValid(int)}. It is recommended
   * that this property is not set. The default is none.
   */
  private String connectionTestQuery;

  /**
   * This property sets the query that will be executed after a new connection is created and before
   * adding it to the connection pool. If the query is invalid or throws an exception then it will
   * be treated as a connection failure. The default is none.
   */
  private String connectionInitSql;

  /**
   * This property sets the maximum amount of time a connection will be tested to check that it is
   * alive, in milliseconds. Must be less than connectionTimeout. The minimum value allowed is 250
   * milliseconds, default is 5000 milliseconds (5 seconds).
   */
  private Long validationTimeout;

  /**
   * This property sets the connection pool name as it appears in logging. The default is
   * auto-generated.
   */
  private String poolName;

  /**
   * This property sets whether the pool can be suspended and resumed using JMX for failover
   * automation scenarios. The default is false.
   */
  private Boolean isAllowPoolSuspension;

  /**
   * This property controls whether connections obtained from the pool are in read-only mode by
   * default. Some databases do not support the concept of read-only mode while others provide query
   * optimizations when the connection is set to read-only. The default is false.
   */
  private Boolean isReadOnly;

  /**
   * This property sets the default transaction isolation level for connections returned from the
   * connection pool. The value is a constant from the {@link java.sql.Connection} class such as
   * {@link java.sql.Connection#TRANSACTION_READ_COMMITTED}, etc. The default is determined by the
   * JDBC driver that is used.
   */
  private String transactionIsolation;

  /**
   * This property sets the amount of time in milliseconds that a connection can be out of the
   * connection pool before a message is logged to indicate a possible connection leak. A value of 0
   * disables leak detection. Otherwise, the minimum value allowed is 2000 milliseconds (2 seconds).
   * The default is 0.
   */
  private Long leakDetectionThreshold;

}

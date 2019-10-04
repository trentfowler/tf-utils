package com.tfowler.utils.constants;

/**
 * Provides a list of popular JDBC DataSources and their corresponding class names. This is the
 * newer, more preferred, alternative to a DriverManager-based configuration. When using this method
 * there is no need to set the {@code jdbcUrl} or {@code driverClassName} properties. Instead, set
 * the {@code dataSourceClassName} property.
 * 
 * @see <a href="https://github.com/brettwooldridge/HikariCP#popular-datasource-class-names"></a>
 */
public enum JdbcDataSource {
  /**
   *
   */
  DERBY("org.apache.derby.jdbc.ClientDataSource"),

  /**
   *
   */
  FIREBIRD("org.firebirdsql.ds.FBSimpleDataSource"),

  /**
   *
   */
  H2("org.h2.jdbcx.JdbcDataSource"),

  /**
   *
   */
  HSQLDB("org.hsqldb.jdbc.JDBCDataSource"),

  /**
   *
   */
  IBM_DB2("com.ibm.db2.jcc.DB2SimpleDataSource"),

  /**
   *
   */
  IBM_INFORMIX("com.informix.jdbcx.IfxDataSource"),

  /**
   *
   */
  MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDataSource"),

  /**
   *
   */
  MYSQL("com.mysql.jdbc.jdbc2.optional.MysqlDataSource"),

  /**
   *
   */
  MARIADB("org.mariadb.jdbc.MariaDbDataSource"),

  /**
   *
   */
  ORACLE("oracle.jdbc.pool.OracleDataSource"),

  /**
   *
   */
  ORIENTDB("com.orientechnologies.orient.jdbc.OrientDataSource"),

  /**
   *
   */
  PGJDBC_NG("com.impossibl.postgres.jdbc.PGDataSource"),

  /**
   *
   */
  POSTGRESQL("org.postgresql.ds.PGSimpleDataSource"),

  /**
   *
   */
  SAP("com.sap.dbtech.jdbc.DriverSapDB"),

  /**
   *
   */
  SQLITE("org.sqlite.SQLiteDataSource"),

  /**
   *
   */
  SYBASE("com.sybase.jdbc4.jdbc.SybDataSource");

  private final String name;

  JdbcDataSource(final String name) {
    this.name = name;
  }

  /**
   *
   * @return String containing the full class name.
   */
  public String getName() {
    return name;
  }
}

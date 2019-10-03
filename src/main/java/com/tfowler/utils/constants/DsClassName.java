package com.tfowler.utils.constants;

/**
 * Provides a list of popular JDBC DataSource classes.
 *
 * @see <a href="https://github.com/brettwooldridge/HikariCP#popular-datasource-class-names"></a>
 */
public enum DsClassName {
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

  private final String value;

  DsClassName(final String value) {
    this.value = value;
  }

  /**
   *
   * @return
   */
  public String getName() {
    return value;
  }

}

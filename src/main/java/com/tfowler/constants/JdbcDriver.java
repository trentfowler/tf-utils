package com.tfowler.constants;

/**
 * Provides a list of popular JDBC drivers and their corresponding class names. For use in a
 * DriverManager-based configuration to set the {@code driverClass} property to desired
 * database.
 *
 * @see <a href="http://www.sql-workbench.net/manual/jdbc-setup.html"></a>
 */
public enum JdbcDriver {

  /**
   * @see <a href="http://jdbc.postgresql.org"></a>
   */
  POSTGRESQL("org.postgresql.Driver"),

  /**
   * @see <a href="http://www.firebirdsql.org/"></a>
   */
  FIREBIRD("org.firebirdsql.jdbc.FBDriver"),

  /**
   * @see <a href="http://www.h2database.com"></a>
   */
  H2("org.h2.Driver"),

  /**
   * @see <a href="http://hsqldb.sourceforge.net"></a>
   */
  HSQLDB("org.hsqldb.jdbcDriver"),

  /**
   * Apache Derby
   * 
   * @see <a href="http://db.apache.org/derby/"></a>
   */
  DERBY(""),

  /**
   * IBM DB2
   *
   * @see <a href="http://www-01.ibm.com/software/data/db2/linux-unix-windows/download.html"></a>
   */
  DB2("com.ibm.db2.jcc.DB2Driver"),

  /**
   * IBM DB2 for iSeries
   *
   * @see <a href="http://jt400.sourceforge.net/"></a>
   */
  AS400("com.ibm.as400.access.AS400JDBCDriver"),

  /**
   * @see <a href="https://github.com/Microsoft/mssql-jdbc"></a>
   */
  MSSQL("com.microsoft.sqlserver.jdbc.SQLServerDriver"),

  /**
   * @see <a href="http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html"></a>
   */
  ORACLE("oracle.jdbc.OracleDriver"),

  /**
   * @see <a href="https://downloads.mariadb.org/connector-java"></a>
   */
  MARIADB("org.mariadb.jdbc.Driver"),

  /**
   * @see <a href="http://www.mysql.com/downloads/connector/j"></a>
   */
  MYSQL("com.mysql.jdbc.Driver");

  private final String className;

  JdbcDriver(final String className) {
    this.className = className;
  }

  /**
   * @return String containing the full class name.
   */
  public String getClassName() {
    return this.className;
  }
}

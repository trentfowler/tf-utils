package com.tfowler.models;

import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tfowler.datasource.DataSource;
import com.tfowler.models.adventureworks.humanresources.Department;
import com.tfowler.queries.Query;
import com.tfowler.queries.QueryLogger;
import com.tfowler.utils.PropertyUtil;

public class HumanResourcesQueryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(HumanResourcesQueryTest.class);

  @Test
  public void testDepartment() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM HumanResources.Department";

    List<Department> departments = Query.with(ds).fetchList(Department.class, SQL);

    QueryLogger.log(departments);
  }
}

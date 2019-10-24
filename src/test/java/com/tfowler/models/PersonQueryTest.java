package com.tfowler.models;

import java.sql.SQLException;
import java.util.List;

import com.tfowler.queries.QueryLogger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tfowler.datasource.DataSource;
import com.tfowler.models.adventureworks.person.*;
import com.tfowler.queries.Query;
import com.tfowler.utils.PropertyUtil;

public class PersonQueryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonQueryTest.class);

  @Test
  public void testAddress() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT * FROM Person.Person";

    List<Person> people = Query.with(ds).fetchList(Person.class, SQL);

    QueryLogger.log(people);
  }

  @Test
  public void testAddressType() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.AddressType";

    List<AddressType> at = Query.with(ds).fetchList(AddressType.class, SQL);

    QueryLogger.log(at);
  }

  @Test
  public void testBusinessEntity() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntity";

    List<BusinessEntity> businessEntities = Query.with(ds).fetchList(BusinessEntity.class, SQL);

    QueryLogger.log(businessEntities);
  }

  @Test
  public void testBusinessEntityAddress() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntityAddress";

    List<BusinessEntityAddress> addresses =
        Query.with(ds).fetchList(BusinessEntityAddress.class, SQL);

    QueryLogger.log(addresses);
  }

  @Test
  public void testBusinessEntityContact() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntityContact";

    List<BusinessEntityContact> contacts =
        Query.with(ds).fetchList(BusinessEntityContact.class, SQL);

    QueryLogger.log(contacts);
  }

  @Test
  public void testContactType() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.ContactType";

    List<ContactType> cts = Query.with(ds).fetchList(ContactType.class, SQL);

    QueryLogger.log(cts);
  }

  @Test
  public void testCountryRegion() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.CountryRegion";

    List<CountryRegion> regions = Query.with(ds).fetchList(CountryRegion.class, SQL);

    QueryLogger.log(regions);
  }

  @Test
  public void testEmailAddress() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.EmailAddress";

    List<EmailAddress> emails = Query.with(ds).fetchList(EmailAddress.class, SQL);

    QueryLogger.log(emails);
  }

  @Test
  public void testPassword() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.Password";

    List<Password> passwords = Query.with(ds).fetchList(Password.class, SQL);

    QueryLogger.log(passwords);
  }

  @Test
  public void testPersonPhone() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.PersonPhone";

    List<PersonPhone> phones = Query.with(ds).fetchList(PersonPhone.class, SQL);

    QueryLogger.log(phones);
  }

  @Test
  public void testPhoneNumberType() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.PhoneNumberType";

    List<PhoneNumberType> types = Query.with(ds).fetchList(PhoneNumberType.class, SQL);

    QueryLogger.log(types);
  }

  @Test
  public void testStateProvince() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.StateProvince";

    List<StateProvince> provinces = Query.with(ds).fetchList(StateProvince.class, SQL);

    QueryLogger.log(provinces);
  }
}

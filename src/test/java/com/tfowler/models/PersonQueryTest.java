package com.tfowler.models;

import java.sql.SQLException;
import java.util.List;
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
  public void addressTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.Person";

    final List<Person> people = Query.with(ds).fetchList(Person.class, SQL);

    people.forEach(person -> LOGGER.info("{}", person));
  }

  @Test
  public void addressTypeTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.AddressType";

    List<AddressType> at = Query.with(ds).fetchList(AddressType.class, SQL);

    at.forEach(t -> LOGGER.info("{}", t));
  }

  @Test
  public void businessEntityTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntity";

    List<BusinessEntity> businessEntities = Query.with(ds).fetchList(BusinessEntity.class, SQL);

    businessEntities.forEach(entity -> LOGGER.info("{}", entity));
  }

  @Test
  public void businessEntityAddressTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntityAddress";

    List<BusinessEntityAddress> addresses =
        Query.with(ds).fetchList(BusinessEntityAddress.class, SQL);

    addresses.forEach(address -> LOGGER.info("{}", address));
  }

  @Test
  public void businessEntityContactTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.BusinessEntityContact";

    List<BusinessEntityContact> contacts =
        Query.with(ds).fetchList(BusinessEntityContact.class, SQL);

    contacts.forEach(contact -> LOGGER.info("{}", contact));
  }

  @Test
  public void contactTypeTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.ContactType";

    List<ContactType> cts = Query.with(ds).fetchList(ContactType.class, SQL);

    cts.forEach(ct -> LOGGER.info("{}", ct));
  }

  @Test
  public void countryRegionTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.CountryRegion";

    List<CountryRegion> regions = Query.with(ds).fetchList(CountryRegion.class, SQL);

    regions.forEach(region -> LOGGER.info("{}", region));
  }

  @Test
  public void emailAddressTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.EmailAddress";

    List<EmailAddress> emails = Query.with(ds).fetchList(EmailAddress.class, SQL);

    emails.forEach(email -> LOGGER.info("{}", email));
  }

  @Test
  public void passwordTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.Password";

    List<Password> passwords = Query.with(ds).fetchList(Password.class, SQL);

    passwords.forEach(password -> LOGGER.info("{}", password));
  }

  @Test
  public void personPhoneTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.PersonPhone";

    List<PersonPhone> phones = Query.with(ds).fetchList(PersonPhone.class, SQL);

    phones.forEach(phone -> LOGGER.info("{}", phone));
  }

  @Test
  public void phoneNumberTypeTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.PhoneNumberType";

    List<PhoneNumberType> types = Query.with(ds).fetchList(PhoneNumberType.class, SQL);

    types.forEach(type -> LOGGER.info("{}", type));
  }

  @Test
  public void stateProvinceTest() throws SQLException {
    final DataSource ds =
        DataSource.createWithProperties(PropertyUtil.fromFileName("application.properties"));

    final String SQL = "SELECT TOP 1 * FROM Person.StateProvince";

    List<StateProvince> provinces = Query.with(ds).fetchList(StateProvince.class, SQL);

    provinces.forEach(province -> LOGGER.info("{}", province));
  }
}

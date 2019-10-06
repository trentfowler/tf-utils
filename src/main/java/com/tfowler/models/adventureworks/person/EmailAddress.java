package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class EmailAddress {

  @Column
  private Integer businessEntityId;

  @Column
  private Integer emailAddressId;

  @Column
  private String emailAddress;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class BusinessEntityAddress {

  @Column
  private Integer businessEntityId;

  @Column
  private Integer addressId;

  @Column
  private Integer addressTypeId;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

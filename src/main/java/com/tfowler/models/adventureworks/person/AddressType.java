package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class AddressType {

  @Column
  private Integer addressTypeId;

  @Column
  private String name;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

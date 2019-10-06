package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class BusinessEntity {

  @Column
  private Integer businessEntityId;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

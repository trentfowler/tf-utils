package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class BusinessEntityContact {

  @Column
  private Integer businessEntityId;

  @Column
  private Integer personId;

  @Column
  private Integer contactTypeId;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

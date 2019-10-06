package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class PersonPhone {

  @Column
  private Integer businessEntityId;

  @Column
  private String phoneNumber;

  @Column
  private Integer phoneNumberTypeId;

  @Column
  private java.sql.Timestamp modifiedDate;
}

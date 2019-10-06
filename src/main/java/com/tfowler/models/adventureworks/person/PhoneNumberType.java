package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class PhoneNumberType {

  @Column
  private Integer phoneNumberTypeId;

  @Column
  private String name;

  @Column
  private java.sql.Timestamp modifiedDate;
}

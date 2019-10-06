package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class ContactType {

  @Column
  private Integer contactTypeId;

  @Column
  private String name;

  @Column
  private java.sql.Timestamp modifiedDate;
}

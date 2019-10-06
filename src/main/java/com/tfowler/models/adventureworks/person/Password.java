package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class Password {

  @Column
  private Integer businessEntityId;

  @Column
  private String passwordHash;

  @Column
  private String passwordSalt;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

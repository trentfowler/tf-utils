package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class Person {

  @Column
  private Integer businessEntityId;

  @Column
  private String personType;

  @Column
  private Boolean nameStyle;

  @Column
  private String title;

  @Column
  private String firstName;

  @Column
  private String middleName;

  @Column
  private String lastName;

  @Column
  private String suffix;

  @Column
  private Integer emailPromotion;

  @Column
  private String additionalContactInfo;

  @Column
  private String demographics;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

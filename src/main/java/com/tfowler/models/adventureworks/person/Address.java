package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class Address {

  @Column
  private Integer addressId;

  @Column
  private String addressLine1;

  @Column
  private String addressLine2;

  @Column
  private String city;

  @Column
  private Integer stateProvinceId;

  @Column
  private String postalCode;

  @Column
  private String spatialLocation;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

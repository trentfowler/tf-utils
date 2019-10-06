package com.tfowler.models.adventureworks.person;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class StateProvince {

  @Column
  private Integer stateProvinceId;

  @Column
  private String stateProvinceCode;

  @Column
  private String countryRegionCode;

  @Column
  private Boolean isOnlyStateProvinceFlag;

  @Column
  private String name;

  @Column
  private Integer territoryId;

  @Column
  private String rowguid;

  @Column
  private java.sql.Timestamp modifiedDate;
}

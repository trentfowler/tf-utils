package com.tfowler.models.adventureworks.humanresources;

import com.tfowler.queries.Column;
import lombok.Data;

@Data
public class Department {

    @Column
    private short departmentId;

    @Column
    private String name;

    @Column
    private String groupName;

    @Column
    private java.sql.Timestamp modifiedDate;
}

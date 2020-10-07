package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.Policy;

public class EmployeePolicy extends Policy {

    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}

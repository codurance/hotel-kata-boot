package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.Policy;

public class CompanyPolicy extends Policy {

  private Integer companyId;

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
}

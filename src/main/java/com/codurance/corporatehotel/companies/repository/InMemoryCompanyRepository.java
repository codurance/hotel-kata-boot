package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Company;
import java.util.HashMap;
import java.util.Map;

public class InMemoryCompanyRepository implements CompanyRepository {

  private final Map<Integer, Company> companies = new HashMap<>();

  public void persist(Company company) {
    this.companies.put(company.getId(), company);
  }

  public Company findById(Integer companyId) {
    return this.companies.get(companyId);
  }
}

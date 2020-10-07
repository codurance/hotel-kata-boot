package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Company;

public interface CompanyRepository {

  void persist(Company company);

  Company findById(Integer companyId);
}

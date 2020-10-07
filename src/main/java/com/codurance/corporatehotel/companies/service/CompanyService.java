package com.codurance.corporatehotel.companies.service;

public interface CompanyService {

    void addEmployee(Integer companyId, Integer employeeId);

    void deleteEmployee(Integer employeeId);
}

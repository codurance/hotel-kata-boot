package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Employee;

public interface EmployeeRepository {

  void persist(Employee employee);

  Employee findById(Integer employeeId);

  void delete(Integer employeeId);
}

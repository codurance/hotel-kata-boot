package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private Map<Integer, Employee> employees = new HashMap<>();

    public void persist(Employee employee) {
        this.employees.put(employee.getId(), employee);
    }

    public Employee findById(Integer employeeId) {
        return this.employees.get(employeeId);
    }

    public void delete(Integer employeeId) {
        this.employees.remove(employeeId);
    }
}

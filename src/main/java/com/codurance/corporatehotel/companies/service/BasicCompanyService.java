package com.codurance.corporatehotel.companies.service;

import com.codurance.corporatehotel.companies.model.Company;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.repository.PolicyRepository;

public class BasicCompanyService implements CompanyService {

    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;
    private PolicyRepository policyRepository ;

    public BasicCompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository, PolicyRepository policyRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.policyRepository = policyRepository;
    }

    public void addEmployee(Integer companyId, Integer employeeId) {
        Company company = this.companyRepository.findById(companyId);

        if (company == null){
            company = new Company(companyId);
            this.companyRepository.persist(company);
        }

        Employee employee = this.employeeRepository.findById(employeeId);

        if (employee == null) {
            employee = new Employee(employeeId);
            employee.setCompanyId(companyId);
            this.employeeRepository.persist(employee);
        }
    }

    public void deleteEmployee(Integer employeeId) {
        if (this.employeeRepository.findById(employeeId) != null){
            this.employeeRepository.delete(employeeId);
            this.policyRepository.deleteEmployee(employeeId);
        }
    }
}

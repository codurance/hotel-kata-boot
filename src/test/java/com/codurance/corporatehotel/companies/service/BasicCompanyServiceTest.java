package com.codurance.corporatehotel.companies.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codurance.corporatehotel.companies.model.Company;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.repository.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BasicCompanyServiceTest {

  @InjectMocks
  private BasicCompanyService companyService;

  @Mock
  private CompanyRepository companyRepository;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private PolicyRepository policyRepository;

  private final Integer companyId = 1;
  private final Integer employeeId = 1;

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  public void shouldAddCompany_whenAddingAnEmployee() throws Exception {
    // given
    // when
    companyService.addEmployee(companyId, employeeId);
    // then
    verify(companyRepository).persist(any(Company.class));
  }

  @Test
  public void shouldAddEmployeeWithoutAddingCompany() throws Exception {
    // given
    given(companyRepository.findById(companyId)).willReturn(new Company(companyId));

    // when
    companyService.addEmployee(companyId, employeeId);

    // then
    verify(companyRepository, times(0)).persist(any(Company.class));
    verify(employeeRepository).persist(any(Employee.class));
  }

  @Test
  public void shouldAddEmployee() throws Exception {
    // given
    // when
    companyService.addEmployee(companyId, employeeId);
    // then
    verify(employeeRepository).persist(any(Employee.class));
  }

  @Test
  public void shouldNotAddEmployee_givenAlreadyExistsInTheSameCompany() throws Exception {
    // given
    given(companyRepository.findById(companyId)).willReturn(new Company(companyId));
    given(employeeRepository.findById(employeeId)).willReturn(new Employee(employeeId));

    // when
    companyService.addEmployee(companyId, employeeId);

    // then
    verify(employeeRepository, times(0)).persist(any(Employee.class));
  }

  @Test
  public void shouldDeleteEmployee() throws Exception {
    // given
    given(employeeRepository.findById(employeeId)).willReturn(new Employee(employeeId));

    // when
    companyService.deleteEmployee(employeeId);

    // then
    verify(policyRepository).deleteEmployee(employeeId);
    verify(employeeRepository).delete(employeeId);
  }

  @Test
  public void shouldNotDeleteEmployee() throws Exception {
    // when
    companyService.deleteEmployee(employeeId);

    // then
    verify(employeeRepository, times(0)).delete(employeeId);
  }
}
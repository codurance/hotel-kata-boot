package com.codurance.corporatehotel.policies.service;

import com.codurance.corporatehotel.common.model.Policy;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.model.CompanyPolicy;
import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import com.codurance.corporatehotel.policies.repository.PolicyRepository;
import java.util.List;

public class BasicPolicyService implements PolicyService {

  private final PolicyRepository policyRepository;
  private final EmployeeRepository employeeRepository;

  public BasicPolicyService(PolicyRepository policyRepository,
      EmployeeRepository employeeRepository) {
    this.policyRepository = policyRepository;
    this.employeeRepository = employeeRepository;
  }

  public void setEmployeePolicy(Integer employeeId, List<RoomTypes> roomTypes) {
    roomTypes.forEach(roomType -> {

      EmployeePolicy employeePolicy = this.policyRepository.findForEmployee(employeeId);

      if (employeePolicy != null) {
        this.policyRepository.updateEmployeePolicy(employeeId, roomType);
      } else {
        this.policyRepository.persistEmployeePolicy(employeeId, roomType);
      }

    });
  }

  public void setCompanyPolicy(Integer companyId, List<RoomTypes> roomTypes) {
    roomTypes.forEach(roomType -> {

      CompanyPolicy companyPolicy = this.policyRepository.findForCompany(companyId);

      if (companyPolicy != null) {
        this.policyRepository.updateCompanyPolicy(companyId, roomType);
      } else {
        this.policyRepository.persistCompanyPolicy(companyId, roomType);
      }
    });
  }

  public boolean isBookingAllowed(Integer employeeId, RoomTypes roomType) {
    EmployeePolicy employeePolicy = this.policyRepository.findForEmployee(employeeId);

    if (employeePolicy != null) {
      return this.isPolicySufficient(employeePolicy, roomType);
    } else {
      CompanyPolicy companyPolicy = this.findCompanyPolicy(employeeId);
      if (companyPolicy != null) {
        return this.isPolicySufficient(companyPolicy, roomType);
      }
    }

    return true;
  }

  private boolean isPolicySufficient(Policy policy, RoomTypes roomType) {
    return policy != null && policy.getRoomTypes().contains(roomType);
  }

  private CompanyPolicy findCompanyPolicy(Integer employeeId) {
    Employee employee = this.employeeRepository.findById(employeeId);

    if (employee != null) {
      return this.policyRepository.findForCompany(employee.getCompanyId());
    }

    return null;
  }
}

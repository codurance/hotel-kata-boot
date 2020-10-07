package com.codurance.corporatehotel.policies.repository;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.policies.model.CompanyPolicy;
import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPolicyRepository implements PolicyRepository {

  private final Map<Integer, EmployeePolicy> employeePolicies = new HashMap<>();
  private final Map<Integer, CompanyPolicy> companyPolicies = new HashMap<>();

  public void persistEmployeePolicy(Integer employeeId, RoomTypes roomType) {
    EmployeePolicy employeePolicy = new EmployeePolicy();
    employeePolicy.setEmployeeId(employeeId);
    employeePolicy.addRoomType(roomType);

    this.employeePolicies.put(employeeId, employeePolicy);
  }

  public void persistCompanyPolicy(Integer companyId, RoomTypes roomType) {
    CompanyPolicy companyPolicy = new CompanyPolicy();
    companyPolicy.setCompanyId(companyId);
    companyPolicy.addRoomType(roomType);

    this.companyPolicies.put(companyId, companyPolicy);
  }

  public EmployeePolicy findForEmployee(Integer employeeId) {
    return this.employeePolicies.get(employeeId);
  }

  public void updateEmployeePolicy(Integer employeeId, RoomTypes roomType) {
    EmployeePolicy employeePolicy = this.employeePolicies.get(employeeId);
    employeePolicy.addRoomType(roomType);
  }

  public CompanyPolicy findForCompany(Integer companyId) {
    return this.companyPolicies.get(companyId);
  }

  public void updateCompanyPolicy(Integer companyId, RoomTypes roomType) {
    CompanyPolicy companyPolicy = this.findForCompany(companyId);
    companyPolicy.addRoomType(roomType);
  }

  public void deleteEmployee(Integer employeeId) {
    this.employeePolicies.remove(employeeId);
  }
}

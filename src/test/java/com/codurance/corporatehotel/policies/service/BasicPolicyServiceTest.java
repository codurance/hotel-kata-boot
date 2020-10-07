package com.codurance.corporatehotel.policies.service;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.companies.model.Company;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.model.CompanyPolicy;
import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import com.codurance.corporatehotel.policies.repository.PolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BasicPolicyServiceTest {

    @InjectMocks
    private BasicPolicyService policyService;

    @Mock
    private PolicyRepository policyRepositoryStub;

    @Mock
    private EmployeeRepository employeeRepositoryStub;

    private Integer employeeId;
    private Integer companyId;
    private List<RoomTypes> roomTypes;

    @BeforeEach
    private void init(){
        MockitoAnnotations.initMocks(this);
        employeeId = 10;
        companyId  =101;
        roomTypes = new ArrayList<>();
    }

    @Test
    public void shouldCreateEmployeePolicyForSingleRoomType() throws Exception{
        // given
        roomTypes.add(RoomTypes.STANDARD);

        // when
        policyService.setEmployeePolicy(employeeId, roomTypes);

        // then
        verify(policyRepositoryStub).persistEmployeePolicy(employeeId, RoomTypes.STANDARD);
    }

    @Test
    public void shouldCreateEmployeePoliciesForManyRoomTypes() throws Exception{
        // given
        roomTypes.add(RoomTypes.STANDARD);
        roomTypes.add(RoomTypes.MASTER_SUITE);

        // when
        policyService.setEmployeePolicy(employeeId, roomTypes);

        // then
        verify(policyRepositoryStub).persistEmployeePolicy(employeeId, RoomTypes.STANDARD);
        verify(policyRepositoryStub).persistEmployeePolicy(employeeId, RoomTypes.MASTER_SUITE);
    }

    @Test
    public void shouldCreateCompanyPolicyForSingleRoomType() throws Exception{
        // given
        roomTypes.add(RoomTypes.STANDARD);

        // when
        policyService.setCompanyPolicy(companyId, roomTypes);

        // then
        verify(policyRepositoryStub).persistCompanyPolicy(companyId, RoomTypes.STANDARD);
    }

    @Test
    public void shouldCreateCompanyPoliciesForMultipleRoomTypes() throws Exception{
        // given
        roomTypes.add(RoomTypes.STANDARD);
        roomTypes.add(RoomTypes.MASTER_SUITE);

        // when
        policyService.setCompanyPolicy(companyId, roomTypes);

        // then
        verify(policyRepositoryStub).persistCompanyPolicy(companyId, RoomTypes.STANDARD);
        verify(policyRepositoryStub).persistCompanyPolicy(companyId, RoomTypes.MASTER_SUITE);
    }

    @Test
    public void shouldUpdateEmployeePolicyGivenItAlreadyExists() throws Exception{
        // when
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(new EmployeePolicy());
        roomTypes.add(RoomTypes.STANDARD);

        // when
        policyService.setEmployeePolicy(employeeId, roomTypes);

        // then
        verify(policyRepositoryStub).updateEmployeePolicy(employeeId, RoomTypes.STANDARD);
    }

    @Test
    public void shouldUpdateCompanyPolicy_givenItAlreadyExists() throws Exception{
        // when
        given(policyRepositoryStub.findForCompany(companyId)).willReturn(new CompanyPolicy());
        roomTypes.add(RoomTypes.STANDARD);

        // when
        policyService.setCompanyPolicy(companyId, roomTypes);

        // then
        verify(policyRepositoryStub).updateCompanyPolicy(companyId, RoomTypes.STANDARD);
    }

    @Test
    public void shouldAllowBooking_givenNoPoliciesExist() throws Exception{
        // when
        RoomTypes roomType = RoomTypes.STANDARD;
        given(policyRepositoryStub.findForCompany(companyId)).willReturn(null);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(null);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isTrue();

    }

    @Test
    public void shouldAllowBooking_givenEmployeePolicyExists() throws Exception{
        // given
        RoomTypes roomType = RoomTypes.STANDARD;

        EmployeePolicy employeePolicy = new EmployeePolicy();
        employeePolicy.setEmployeeId(employeeId);
        employeePolicy.addRoomType(RoomTypes.STANDARD);

        given(policyRepositoryStub.findForCompany(companyId)).willReturn(null);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(employeePolicy);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isTrue();
    }

    @Test
    public void shouldAllowBooking_givenCompanyPolicyExists() throws Exception{
        // given
        RoomTypes roomType = RoomTypes.STANDARD;

        CompanyPolicy companyPolicy = new CompanyPolicy();
        companyPolicy.setCompanyId(companyId);
        companyPolicy.addRoomType(RoomTypes.STANDARD);

        given(policyRepositoryStub.findForCompany(companyId)).willReturn(companyPolicy);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(null);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isTrue();
    }

    @Test
    public void shouldNotAllowBooking_givenInsufficientEmployeePolicyExists() throws Exception{
        // given
        RoomTypes roomType = RoomTypes.MASTER_SUITE;

        EmployeePolicy employeePolicy = new EmployeePolicy();
        employeePolicy.setEmployeeId(employeeId);
        employeePolicy.addRoomType(RoomTypes.STANDARD);

        given(policyRepositoryStub.findForCompany(companyId)).willReturn(null);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(employeePolicy);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isFalse();

    }

    @Test
    public void shouldNotAllowBooking_givenInsufficientCompanyPolicyExists() throws Exception{
        // when
        RoomTypes roomType = RoomTypes.MASTER_SUITE;

        CompanyPolicy companyPolicy = new CompanyPolicy();
        companyPolicy.setCompanyId(companyId);
        companyPolicy.addRoomType(RoomTypes.STANDARD);

        Company company = new Company(companyId);
        Employee employee = new Employee(employeeId);
        employee.setCompanyId(companyId);

        given(policyRepositoryStub.findForCompany(companyId)).willReturn(companyPolicy);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(null);
        given(employeeRepositoryStub.findById(employeeId)).willReturn(employee);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isFalse();
    }

    @Test
    public void shouldEmployeePolicyAllowButCompanyDoesNotAllow() throws Exception{
        // given
        RoomTypes roomType = RoomTypes.MASTER_SUITE;

        CompanyPolicy companyPolicy = new CompanyPolicy();
        companyPolicy.setCompanyId(companyId);
        companyPolicy.addRoomType(RoomTypes.STANDARD);

        EmployeePolicy employeePolicy = new EmployeePolicy();
        employeePolicy.setEmployeeId(employeeId);
        employeePolicy.addRoomType(RoomTypes.MASTER_SUITE);

        Company company = new Company(companyId);
        Employee employee = new Employee(employeeId);
        employee.setCompanyId(companyId);

        given(policyRepositoryStub.findForCompany(companyId)).willReturn(companyPolicy);
        given(policyRepositoryStub.findForEmployee(employeeId)).willReturn(employeePolicy);
        given(employeeRepositoryStub.findById(employeeId)).willReturn(employee);

        // when
        boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

        // then
        assertThat(isBookingAllowed).isTrue();
    }

}